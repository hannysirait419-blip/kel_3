package pbo.proyek.kel3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pbo.proyek.kel3.model.Menu; // Sesuaikan jika nama modelnya Menu

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}