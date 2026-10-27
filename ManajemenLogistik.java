/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi1;

/**
 *
 * @author Inka Putri
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManajemenLogistik {
    private ArrayList<BarangBantuan> daftarBarang = new ArrayList<>();

    private final String fileData = "data_barang.txt";

    public ManajemenLogistik() {
        muatData();
    }

    private void validasiBarang(String kode, String nama,String kategori, double berat)throws BeratInvalidException {
        if (kode == null || !kode.startsWith("EDX-")) {
            throw new IllegalArgumentException(
                    "Kode barang harus diawali EDX-");
        }

        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama barang tidak boleh kosong.");
        }

        if (kategori == null || kategori.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori barang tidak boleh kosong.");
        }

        if (berat <= 0 || Double.isInfinite(berat)
                || Double.isNaN(berat)) {
            throw new BeratInvalidException("Berat barang harus lebih dari 0 kg.");
        }
    }

    private boolean kodeSudahAda(String kode) {
        for (BarangBantuan barang : daftarBarang) {
            if (barang.getKodeBarang().equalsIgnoreCase(kode)) {
                return true;
            }
        }
        return false;
    }

    public void tambahBarang(String kode, String nama,String kategori, double berat)throws BeratInvalidException {
        validasiBarang(kode, nama, kategori, berat);

        if (kodeSudahAda(kode)) {
            throw new IllegalArgumentException("Kode barang sudah terdaftar.");
        }

        BarangBantuan barang = new BarangBantuan(
                kode, nama, kategori, berat);

        daftarBarang.add(barang);
        simpanData();

        System.out.println("Barang berhasil ditambahkan.");
    }

    public void tampilkanSemuaBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada barang yang dicatat.");
            return;
        }

        System.out.println("\n=== DAFTAR BARANG BANTUAN ===");

        int nomor = 1;

        for (BarangBantuan barang : daftarBarang) {
            System.out.println("\nBarang ke-" + nomor++);
            barang.tampilkanInfo();
        }
    }

    public void ubahBarang(String kode, String nama,String kategori, double berat)throws BeratInvalidException {
        validasiBarang(kode, nama, kategori, berat);

        ListIterator<BarangBantuan> iterator =
                daftarBarang.listIterator();

        while (iterator.hasNext()) {
            BarangBantuan barang = iterator.next();

            if (barang.getKodeBarang().equalsIgnoreCase(kode)) {
                barang.setNamaBarang(nama);
                barang.setKategori(kategori);
                barang.setBeratKg(berat);

                simpanData();
                System.out.println("Data barang berhasil diubah.");
                return;
            }
        }

        System.out.println("Kode barang tidak ditemukan.");
    }

    public void hapusBarang(String kode) {
        Iterator<BarangBantuan> iterator =
                daftarBarang.iterator();

        while (iterator.hasNext()) {
            BarangBantuan barang = iterator.next();

            if (barang.getKodeBarang().equalsIgnoreCase(kode)) {
                iterator.remove();
                simpanData();

                System.out.println("Barang berhasil dihapus.");
                return;
            }
        }

        System.out.println("Peringatan: kode barang tidak ditemukan.");
    }

    public int hitungTotalBarang() {
        return daftarBarang.size();
    }

    public double hitungTotalBerat() {
        double total = 0;

        for (BarangBantuan barang : daftarBarang) {
            total += barang.getBeratKg();
        }

        return total;
    }

    public void simpanData() {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(fileData))) {

            for (BarangBantuan barang : daftarBarang) {
                writer.write(
                        barang.getKodeBarang() + ";"
                        + barang.getNamaBarang() + ";"
                        + barang.getKategori() + ";"
                        + barang.getBeratKg()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    public void muatData() {
        daftarBarang.clear();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileData))) {

            String baris;

            while ((baris = reader.readLine()) != null) {
                String[] data = baris.split(";", -1);

                if (data.length != 4) {
                    continue;
                }

                try {
                    String kode = data[0];
                    String nama = data[1];
                    String kategori = data[2];
                    double berat = Double.parseDouble(data[3]);

                    validasiBarang(kode, nama, kategori, berat);

                    if (!kodeSudahAda(kode)) {
                        daftarBarang.add(new BarangBantuan(kode, nama, kategori, berat));
                    }

                } catch (IllegalArgumentException
                         | BeratInvalidException e) {
                    System.out.println("Data file dilewati: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Belum ada file data sebelumnya. " + "Data baru akan disimpan saat ada perubahan.");
        } finally {
            System.out.println("Proses pemuatan data selesai.");
        }
    }

    public void cetakManifes() {
        double totalBerat = hitungTotalBerat();
        String armada;

        if (totalBerat <= 50) {
            armada = "Mobil Pickup";
        } else {
            armada = "Truk Ekspedisi";
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("manifest_pengiriman.txt"))) {

            writer.write("MANIFES PENGIRIMAN BANTUAN");
            writer.newLine();
            writer.write("===========================");
            writer.newLine();
            writer.newLine();

            writer.write("DAFTAR BARANG:");
            writer.newLine();

            int nomor = 1;

            for (BarangBantuan barang : daftarBarang) {
                writer.write(
                        nomor++ + ". Kode: "
                        + barang.getKodeBarang()
                        + " | Nama: " + barang.getNamaBarang()
                        + " | Kategori: " + barang.getKategori()
                        + " | Berat: " + barang.getBeratKg()
                        + " kg");
                writer.newLine();
            }

            writer.newLine();
            writer.write(
                    "Total barang: " + hitungTotalBarang()
            );
            writer.newLine();
            writer.write("Total berat: " + totalBerat + " kg");
            writer.newLine();
            writer.write("Label armada: " + armada);
            writer.newLine();

            System.out.println("Data manifes berhasil diekspor ke " + "manifest_pengiriman.txt");
            System.out.println("Total barang: " + hitungTotalBarang());
            System.out.println("Total berat: " + totalBerat + " kg");
            System.out.println("Label armada: " + armada);

        } catch (IOException e) {
            System.out.println("Gagal menulis manifes: " + e.getMessage());
        }
    }
}
