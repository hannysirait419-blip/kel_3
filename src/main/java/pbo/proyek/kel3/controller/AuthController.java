package pbo.proyek.kel3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pbo.proyek.kel3.model.Menu;
import pbo.proyek.kel3.model.Review;
import pbo.proyek.kel3.model.User;
import pbo.proyek.kel3.repository.MenuRepository;
import pbo.proyek.kel3.repository.ReviewRepository;
import pbo.proyek.kel3.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MenuRepository menuRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    // LOGIN
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // REGISTER
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        // AUTO ROLE
        if(user.getUsername().toLowerCase().contains("admin")){
            user.setRole("ROLE_ADMIN");
        } else {
            user.setRole("ROLE_USER");
        }

        repo.save(user);

        return "redirect:/login?success";
    }

    // HALAMAN USER
    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("daftarMenu", menuRepo.findAll());
        model.addAttribute("daftarUlasan", reviewRepo.findAll());

        return "index";
    }

    // TAMBAH ULASAN
    @PostMapping("/kirim-ulasan")
    public String kirimUlasan(@ModelAttribute Review review){

        reviewRepo.save(review);

        return "redirect:/#ulasan";
    }

    // DASHBOARD ADMIN
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model){

        model.addAttribute("daftarMenu", menuRepo.findAll());
        model.addAttribute("daftarUlasan", reviewRepo.findAll());
        model.addAttribute("menuBaru", new Menu());

        return "admin";
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

    // HAPUS ULASAN
    @GetMapping("/admin/ulasan/hapus/{id}")
    public String hapusUlasan(@PathVariable int id){

        reviewRepo.deleteById(id);

        return "redirect:/admin/dashboard";
    }

}