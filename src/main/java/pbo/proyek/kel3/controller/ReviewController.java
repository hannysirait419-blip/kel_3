package pbo.proyek.kel3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pbo.proyek.kel3.model.Review;
import pbo.proyek.kel3.repository.ReviewRepository;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepo;

    // HALAMAN ULASAN (PELANGGAN)
    @GetMapping("/ulasan")
    public String ulasan(Model model){
        model.addAttribute("daftarUlasan", reviewRepo.findAll());
        return "ulasan";
    }

    // TAMBAH ULASAN
    @PostMapping("/kirim-ulasan")
    public String kirimUlasan(@ModelAttribute Review review){
        reviewRepo.save(review);
        return "redirect:/ulasan";
    }

    // KELOLA ULASAN (ADMIN)
    @GetMapping("/admin/ulasan")
    public String adminUlasan(Model model){
        model.addAttribute("daftarUlasan", reviewRepo.findAll());
        return "admin/ulasan";
    }

    // HAPUS ULASAN
    @GetMapping("/admin/ulasan/hapus/{id}")
    public String hapusUlasan(@PathVariable int id){
        reviewRepo.deleteById(id);
        return "redirect:/admin/ulasan";
    }

}
