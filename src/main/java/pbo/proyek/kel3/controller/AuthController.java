package pbo.proyek.kel3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pbo.proyek.kel3.model.User;
import pbo.proyek.kel3.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    // ROOT → langsung ke login (aman)
    @GetMapping("/")
    public String root() {
        return "redirect:/login";
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // REGISTER PAGE
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // INDEX (halaman setelah login)
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // PROCESS REGISTER
    @PostMapping("/register")
    public String processRegister(User user) {

        // encode password
        user.setPassword(encoder.encode(user.getPassword()));

        // set role
        if (user.getUsername() != null &&
            user.getUsername().toLowerCase().contains("admin")) {
            user.setRole("admin");
        } else {
            user.setRole("pelanggan");
        }

        repo.save(user);

        return "redirect:/login?success";
    }
}