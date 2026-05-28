package pbo.proyek.kel3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String dashboard() {
        // Ini akan memanggil file templates/admin/dashboard.html
        return "admin/dashboard";
    }
}