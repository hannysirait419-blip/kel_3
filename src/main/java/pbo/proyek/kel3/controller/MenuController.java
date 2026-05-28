package pbo.proyek.kel3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pbo.proyek.kel3.model.Menu;
import pbo.proyek.kel3.repository.MenuRepository;

import java.io.IOException;
import java.nio.file.*;

@Controller
public class MenuController {

    @Autowired
    private MenuRepository menuRepo;

    private final String FOLDER_UPLOAD = "src/main/resources/static/assets/uploads/";

    // ==========================================
    // HALAMAN UNTUK USER (PELANGGAN)
    // ==========================================
    
    // Alamat utama (localhost:9000/) atau (localhost:9000/menu)
    @GetMapping({"/", "/menu"})
    public String daftarMenu(Model model) {
        model.addAttribute("daftarMenu", menuRepo.findAll());
        return "index"; // Memanggil templates/index.html (Halaman Menu Kami)
    }

    // ==========================================
    // HALAMAN UNTUK ADMIN
    // ==========================================

    @GetMapping("/admin/menu")
    public String adminMenu(Model model) {
        model.addAttribute("daftarMenu", menuRepo.findAll());
        return "admin/menu"; // Memanggil templates/admin/menu.html
    }

    @PostMapping("/admin/menu/simpan")
    public String simpanMenu(@ModelAttribute Menu menu, @RequestParam("fileGambar") MultipartFile file) {
        handleFileUpload(menu, file);
        menuRepo.save(menu);
        return "redirect:/admin/menu";
    }

    @GetMapping("/admin/menu/edit/{id}")
    public String editMenu(@PathVariable int id, Model model) {
        model.addAttribute("menu", menuRepo.findById(id).orElse(null));
        return "admin/edit-menu";
    }

    @PostMapping("/admin/menu/update")
    public String updateMenu(@ModelAttribute Menu menu, @RequestParam("fileGambar") MultipartFile file) {
        Menu menuLama = menuRepo.findById(menu.getId()).orElse(null);
        if (!file.isEmpty()) {
            handleFileUpload(menu, file);
        } else if (menuLama != null) {
            menu.setGambarUrl(menuLama.getGambarUrl());
        }
        menuRepo.save(menu);
        return "redirect:/admin/menu";
    }

    @GetMapping("/admin/menu/hapus/{id}")
    public String hapusMenu(@PathVariable int id) {
        menuRepo.deleteById(id);
        return "redirect:/admin/menu";
    }

    // Fungsi Pembantu Upload
    private void handleFileUpload(Menu menu, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Path pathFolder = Paths.get(FOLDER_UPLOAD);
                if (!Files.exists(pathFolder)) Files.createDirectories(pathFolder);
                String namaFile = "menu_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Files.copy(file.getInputStream(), pathFolder.resolve(namaFile), StandardCopyOption.REPLACE_EXISTING);
                menu.setGambarUrl("/assets/uploads/" + namaFile);
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}