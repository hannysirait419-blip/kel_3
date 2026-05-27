package pbo.proyek.kel3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pbo.proyek.kel3.model.Menu;
import pbo.proyek.kel3.repository.MenuRepository;

@Controller
public class MenuController {

    @Autowired
    private MenuRepository menuRepo;

    // HALAMAN MENU (PELANGGAN)
    @GetMapping("/menu")
    public String menu(Model model){
        model.addAttribute("daftarMenu", menuRepo.findAll());
        return "menu";
    }

    // DASHBOARD ADMIN (KELOLA MENU)
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model){
        model.addAttribute("daftarMenu", menuRepo.findAll());
        return "admin/dashboard";
    }

    // SIMPAN MENU
    @PostMapping("/admin/menu/simpan")
    public String simpanMenu(@ModelAttribute Menu menu){
        menuRepo.save(menu);
        return "redirect:/admin/dashboard";
    }

    // HAPUS MENU
    @GetMapping("/admin/menu/hapus/{id}")
    public String hapusMenu(@PathVariable int id){
        menuRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }

}
