package pbo.proyek.kel3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // 1. Mematikan CSRF agar form login simpel bisa jalan
            .csrf(csrf -> csrf.disable())

            // 2. Pengaturan izin akses halaman
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/css/**", "/js/**", "/assets/**").permitAll()
                .requestMatchers("/admin/**").hasAuthority("admin")
                .anyRequest().authenticated()
            )

            // 3. Pengaturan Form Login
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login") // Menjelaskan proses login lewat URL ini
                .successHandler((request, response, authentication) -> {
                    
                    // Cek apakah yang login punya otoritas admin
                    boolean isAdmin = authentication.getAuthorities()
                            .stream()
                            .anyMatch(ga -> ga.getAuthority().equals("admin"));

                    if(isAdmin){
                        response.sendRedirect("/admin/dashboard");
                    } else {
                        response.sendRedirect("/index.html");
                    }
                })
                .failureUrl("/login?error")
                .permitAll()
            )

            // 4. Pengaturan Logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}