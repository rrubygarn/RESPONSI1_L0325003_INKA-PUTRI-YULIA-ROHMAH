/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi1;

/**
 *
 * @author Inka Putri
 */

public class BarangBantuan {
    private String kodeBarang;
    private String namaBarang;
    private String kategori;
    private double beratKg;

    public BarangBantuan(String kodeBarang, String namaBarang,String kategori, double beratKg) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.beratKg = beratKg;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public double getBeratKg() {
        return beratKg;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setBeratKg(double beratKg) {
        this.beratKg = beratKg;
    }

    public void tampilkanInfo() {
        System.out.println("Kode     : " + kodeBarang);
        System.out.println("Nama     : " + namaBarang);
        System.out.println("Kategori : " + kategori);
        System.out.println("Berat    : " + beratKg + " kg");
    }
}
