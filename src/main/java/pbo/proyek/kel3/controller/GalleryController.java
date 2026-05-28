package pbo.proyek.kel3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pbo.proyek.kel3.model.Gallery;
import pbo.proyek.kel3.repository.GalleryRepository;

import java.io.IOException;
import java.nio.file.*;

@Controller
public class GalleryController {

    @Autowired
    private GalleryRepository galleryRepo;

    private final String FOLDER_UPLOAD = "src/main/resources/static/assets/uploads/";

    // ==========================================
    // HALAMAN UNTUK USER (PELANGGAN)
    // ==========================================
    @GetMapping("/galeri")
    public String galeri(Model model) {
        model.addAttribute("daftarGaleri", galleryRepo.findAll());
        return "galeri"; // Memanggil templates/galeri.html
    }

    // ==========================================
    // HALAMAN UNTUK ADMIN
    // ==========================================
    
    // 1. Tampil Daftar Galeri Admin
    @GetMapping("/admin/galeri")
    public String adminGaleri(Model model) {
        model.addAttribute("daftarGaleri", galleryRepo.findAll());
        return "admin/galeri"; // Memanggil templates/admin/galeri.html
    }

    // 2. Simpan Foto Baru
    @PostMapping("/admin/galeri/simpan")
    public String simpanGaleri(@ModelAttribute Gallery gallery, @RequestParam("fileGambar") MultipartFile file) {
        handleFileUpload(gallery, file);
        galleryRepo.save(gallery);
        return "redirect:/admin/galeri";
    }

    // 3. Tampil Halaman Edit
    @GetMapping("/admin/galeri/edit/{id}")
    public String editGaleri(@PathVariable int id, Model model) {
        model.addAttribute("gallery", galleryRepo.findById(id).orElse(null));
        return "admin/edit-galeri";
    }

    // 4. Proses Update Foto
    @PostMapping("/admin/galeri/update")
    public String updateGaleri(@ModelAttribute Gallery gallery, @RequestParam("fileGambar") MultipartFile file) {
        Gallery lama = galleryRepo.findById(gallery.getId()).orElse(null);
        if (!file.isEmpty()) {
            handleFileUpload(gallery, file);
        } else if (lama != null) {
            gallery.setGambarUrl(lama.getGambarUrl());
        }
        galleryRepo.save(gallery);
        return "redirect:/admin/galeri";
    }

    // 5. Hapus Foto
    @GetMapping("/admin/galeri/hapus/{id}")
    public String hapusGaleri(@PathVariable int id) {
        galleryRepo.deleteById(id);
        return "redirect:/admin/galeri";
    }

    // Fungsi Pembantu Upload File
    private void handleFileUpload(Gallery g, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Path pathFolder = Paths.get(FOLDER_UPLOAD);
                if (!Files.exists(pathFolder)) Files.createDirectories(pathFolder);
                String namaFile = "gal_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Files.copy(file.getInputStream(), pathFolder.resolve(namaFile), StandardCopyOption.REPLACE_EXISTING);
                g.setGambarUrl("/assets/uploads/" + namaFile);
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}