package pbo.proyek.kel3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pbo.proyek.kel3.model.Gallery;
import pbo.proyek.kel3.repository.GalleryRepository;

@Controller
public class GalleryController {

    @Autowired
    private GalleryRepository galleryRepo;

    // HALAMAN GALERI (PELANGGAN)
    @GetMapping("/galeri")
    public String galeri(Model model){
        model.addAttribute("daftarGaleri", galleryRepo.findAll());
        return "galeri";
    }

    // KELOLA GALERI (ADMIN)
    @GetMapping("/admin/galeri")
    public String adminGaleri(Model model){
        model.addAttribute("daftarGaleri", galleryRepo.findAll());
        return "admin/galeri";
    }

    // SIMPAN GALERI
    @PostMapping("/admin/galeri/simpan")
    public String simpanGaleri(@ModelAttribute Gallery gallery){
        galleryRepo.save(gallery);
        return "redirect:/admin/galeri";
    }

    // HAPUS GALERI
    @GetMapping("/admin/galeri/hapus/{id}")
    public String hapusGaleri(@PathVariable int id){
        galleryRepo.deleteById(id);
        return "redirect:/admin/galeri";
    }

}
