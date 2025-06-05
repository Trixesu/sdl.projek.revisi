package MainProgram;

import java.util.Scanner;

public class PendudukManager {

    private static Scanner input = new Scanner(System.in);
    static DataBantuan dataBantuan = new DataBantuan();

    public static void tambahPenduduk() {
        System.out.print("Masukkan No KK: ");
        String noKK = input.nextLine();

        if (dataBantuan.isNoKKExist(noKK)) {
            System.out.println("No KK sudah terdaftar, tidak bisa tambah data.");
            return;
        }

        System.out.print("Masukkan Nama: ");
        String nama = input.nextLine();

        System.out.print("Masukkan Alamat: ");
        String alamat = input.nextLine();

        System.out.print("Sudah menerima bantuan? (Sudah/Belum): ");
        String status = input.nextLine().trim().toLowerCase();
        boolean sudahMenerima = status.equals("sudah") || status.equals("y");

        System.out.print("Masukkan Jenis Bansos (BLT/PKH/BNPT/etc): ");
        String jenisBansos = input.nextLine();

        Penduduk p = new Penduduk(noKK, nama, alamat, sudahMenerima, jenisBansos);
        if (dataBantuan.tambahPenduduk(p)) {
            System.out.println("Penduduk berhasil ditambahkan.");
        } else {
            System.out.println("Data penuh, gagal menambahkan penduduk.");
        }
    }

    public static void hapusPenduduk() {
        System.out.print("Masukkan No KK penduduk yang ingin dihapus: ");
        String noKK = input.nextLine();

        if (dataBantuan.hapusPendudukByNoKK(noKK)) {
            System.out.println("Penduduk dengan No KK " + noKK + " berhasil dihapus.");
        } else {
            System.out.println("Penduduk dengan No KK " + noKK + " tidak ditemukan.");
        }
    }

    public static void cariPenduduk() {
        System.out.print("Masukkan nama penduduk yang dicari: ");
        String namaCari = input.nextLine();

        dataBantuan.sortByNama(); // Pastikan data terurut
        int index = dataBantuan.binarySearchByNama(namaCari);

        if (index != -1) {
            System.out.println("Data penduduk ditemukan:");
            dataBantuan.cetakHeaderTabel();
            dataBantuan.getPenduduk(index).tampilkanData();
            dataBantuan.cetakFooterTabel();
        } else {
            System.out.println("Penduduk dengan nama \"" + namaCari + "\" tidak ditemukan.");
        }
    }

    public static void editDataPenduduk() {
        System.out.print("Masukkan No KK penduduk yang ingin diedit: ");
        String noKK = input.nextLine();

        Penduduk target = null;
        for (int i = 0; i < dataBantuan.getJumlahPenduduk(); i++) {
            if (dataBantuan.getPenduduk(i).getNoKK().equals(noKK)) {
                target = dataBantuan.getPenduduk(i);
                break;
            }
        }

        if (target == null) {
            System.out.println("Penduduk dengan No KK tersebut tidak ditemukan.");
            return;
        }

        System.out.println("Data saat ini:");
        dataBantuan.cetakHeaderTabel();
        target.tampilkanData();
        dataBantuan.cetakFooterTabel();

        String pilihan;

        while (true) {
            System.out.println("Pilih data yang ingin diedit:");
            System.out.println("1. Alamat");
            System.out.println("2. Status Bantuan");
            System.out.println("3. Jenis Bansos");
            System.out.println("4. Semua (Alamat, Status Bantuan, Jenis Bansos)");
            System.out.print("Pilihan: ");
            pilihan = input.nextLine();

            if (pilihan.equals("1") || pilihan.equals("2") || pilihan.equals("3") || pilihan.equals("4")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid. Silakan pilih 1, 2, 3, atau 4.\n");
            }
        }

        if (pilihan.equals("1") || pilihan.equals("4")) {
            System.out.print("Masukkan alamat baru: ");
            String alamatBaru = input.nextLine();
            target.setAlamat(alamatBaru);
        }

        if (pilihan.equals("2") || pilihan.equals("4")) {
            boolean sudahMenerima;
            while (true) {
                System.out.print("Status bantuan baru (Sudah/Belum): ");
                String status = input.nextLine().trim().toLowerCase();
                if (status.equals("sudah") || status.equals("y")) {
                    sudahMenerima = true;
                    break;
                } else if (status.equals("belum") || status.equals("n")) {
                    sudahMenerima = false;
                    break;
                } else {
                    System.out.println("Input tidak valid. Silakan ketik 'Sudah' atau 'Belum'.");
                }
            }
            target.setSudahMenerimaBantuan(sudahMenerima);
        }

        if (pilihan.equals("3") || pilihan.equals("4")) {
            System.out.print("Masukkan jenis bansos baru: ");
            String jenisBansosBaru = input.nextLine();
            target.setJenisBansos(jenisBansosBaru);
        }

        System.out.println("Data penduduk berhasil diperbarui.");
    }
    
    public static void preloadData() {
        // Menambahkan beberapa data awal penduduk
        dataBantuan.tambahPenduduk(new Penduduk("1234567890123456", "Budi Santoso", "Jl. Merdeka No.1", true, "BLT"));
        dataBantuan.tambahPenduduk(new Penduduk("2345678901234567", "Siti Aminah", "Jl. Melati No.2", false, "PKH"));
        dataBantuan.tambahPenduduk(new Penduduk("3456789012345678", "Andi Wijaya", "Jl. Kenanga No.3", true, "BNPT"));
        dataBantuan.tambahPenduduk(new Penduduk("4567890123456789", "Dewi Lestari", "Jl. Mawar No.4", false, "PKH"));
    }
}

