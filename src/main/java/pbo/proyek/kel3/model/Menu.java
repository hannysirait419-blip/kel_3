package pbo.proyek.kel3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nama;

    private String kategori;

    private double harga;

    @Column(length = 1000)
    private String deskripsi;

    @Column(name = "gambar_url")
    private String gambarUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }


    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }


    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }


    public String getGambarUrl() {
        return gambarUrl;
    }

    public void setGambarUrl(String gambarUrl) {
        this.gambarUrl = gambarUrl;
    }
}