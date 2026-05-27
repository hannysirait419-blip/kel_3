package pbo.proyek.kel3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nama_pelanggan")
    private String namaPelanggan;

    private String komentar;

    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }


    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}