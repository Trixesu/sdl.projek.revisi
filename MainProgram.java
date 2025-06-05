package MainProgram;

import java.util.Scanner;

public class MainProgram {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        PendudukManager.preloadData();

        boolean running = true;
        while (running) {
            tampilkanMenu();
            System.out.print("Pilih menu (1-8): ");
            String pilihan = input.nextLine();

            switch (pilihan) {
                case "1":
                    PendudukManager.tambahPenduduk();
                    break;
                case "2":
                    PendudukManager.hapusPenduduk();
                    break;
                case "3":
                    PendudukManager.cariPenduduk();
                    break;
                case "4":
                    PendudukManager.dataBantuan.sortByNama();
                    System.out.println("Data sudah diurutkan berdasarkan nama.");
                    PendudukManager.dataBantuan.tampilkanSemua();
                    break;
                case "5":
                    PendudukManager.dataBantuan.tampilkanSemua();
                    break;
                case "6":
                    PendudukManager.dataBantuan.tampilkanSudahMenerima();
                    PendudukManager.dataBantuan.tampilkanBelumMenerima();
                    break;
                case "7":
                    PendudukManager.editDataPenduduk();
                    break;
                case "8":
                    running = false;
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
            System.out.println();
        }
    }

    private static void tampilkanMenu() {
        System.out.println("===== Program Data Bantuan Sosial =====");
        System.out.println("1. Tambah Penduduk");
        System.out.println("2. Hapus Penduduk berdasarkan No KK");
        System.out.println("3. Cari Penduduk berdasarkan Nama");
        System.out.println("4. Urutkan Data Penduduk berdasarkan Nama");
        System.out.println("5. Tampilkan Semua Data Penduduk");
        System.out.println("6. Tampilkan Penduduk Berdasarkan Status Bantuan");
        System.out.println("7. Edit Data Alamat / Status Bantuan Penduduk");
        System.out.println("8. Keluar");
    }
}
