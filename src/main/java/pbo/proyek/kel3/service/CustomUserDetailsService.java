package pbo.proyek.kel3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import pbo.proyek.kel3.model.User;
import pbo.proyek.kel3.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User kaga ada cok!"));
        
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }
}