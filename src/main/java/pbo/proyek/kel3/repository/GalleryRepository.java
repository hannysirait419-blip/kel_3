package pbo.proyek.kel3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pbo.proyek.kel3.model.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
}
