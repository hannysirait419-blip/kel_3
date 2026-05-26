package pbo.proyek.kel3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbo.proyek.kel3.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}