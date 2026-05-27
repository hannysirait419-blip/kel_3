package pbo.proyek.kel3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pbo.proyek.kel3.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}