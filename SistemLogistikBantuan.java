/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Responsi1;

/**
 *
 * @author Inka Putri
 */


import java.util.Scanner;

public class SistemLogistikBantuan {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ManajemenLogistik logistik = new ManajemenLogistik();
        int pilihan = 0;

        do {
            System.out.println("\n=== SISTEM LOGISTIK BANTUAN ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Tampilkan Semua Barang");
            System.out.println("3. Ubah Data Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("5. Selesai dan Cetak Manifes");
            System.out.print("Pilih menu: ");

            try {
                pilihan = Integer.parseInt(input.nextLine().trim());

                switch (pilihan) {
                    case 1:
                        tambahBarang(logistik);
                        break;

                    case 2:
                        logistik.tampilkanSemuaBarang();
                        break;

                    case 3:
                        ubahBarang(logistik);
                        break;

                    case 4:
                        System.out.print("Masukkan kode barang: ");
                        String kodeHapus = input.nextLine().trim();
                        logistik.hapusBarang(kodeHapus);
                        break;

                    case 5:
                        logistik.cetakManifes();
                        break;

                    default:
                        System.out.println("Pilihan tidak valid. Masukkan 1-5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka yang valid.");

            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());

            } finally {
                System.out.println("Proses menu selesai.");
            }

        } while (pilihan != 5);

        input.close();
        System.out.println("Program logistik ditutup.");
    }

    
private static void tambahBarang(ManajemenLogistik logistik) {
    boolean berhasil = false;

    while (!berhasil) {
        try {
            System.out.print("Kode barang (EDX-): ");
            String kode = input.nextLine().trim();

            if (!kode.startsWith("EDX-")) {
                throw new IllegalArgumentException(
                        "Kode barang harus diawali EDX-"
                );
            }

            System.out.print("Nama barang: ");
            String nama = input.nextLine().trim();

            System.out.print("Kategori barang: ");
            String kategori = input.nextLine().trim();

            System.out.print("Berat barang (kg): ");
            double berat = Double.parseDouble(
                    input.nextLine().trim()
            );

            logistik.tambahBarang(kode, nama, kategori, berat);
            berhasil = true;

        } catch (BeratInvalidException e) {
            System.out.println("Kesalahan: " + e.getMessage());
            System.out.println("Silakan ulangi input barang.");

        } catch (IllegalArgumentException e) {
            System.out.println("Kesalahan: " + e.getMessage());
            System.out.println("Silakan ulangi input barang.");

        } catch (Exception e) {
            System.out.println("Input tidak valid. Pastikan berat berupa angka.");
            System.out.println("Silakan ulangi input barang.");

        } finally {
            System.out.println("Proses validasi selesai.");
        }
    }
}

    private static void ubahBarang(ManajemenLogistik logistik) {
        try {
            System.out.print("Kode barang yang diubah: ");
            String kode = input.nextLine().trim();

            System.out.print("Nama barang baru: ");
            String nama = input.nextLine().trim();

            System.out.print("Kategori baru: ");
            String kategori = input.nextLine().trim();

            System.out.print("Berat baru (kg): ");
            double berat = Double.parseDouble(
                    input.nextLine().trim()
            );
            logistik.ubahBarang(kode, nama, kategori, berat);

        } catch (BeratInvalidException e) {
            System.out.println("Kesalahan berat: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Kesalahan input: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Gagal mengubah data. Periksa kembali input.");

        } finally {
            System.out.println("Proses perubahan data selesai.");
        }
    }
}

