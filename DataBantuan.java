package MainProgram;
public class DataBantuan {

    private Node head;
    private int jumlahPenduduk;

    public DataBantuan() {
        head = null;
        jumlahPenduduk = 0;
    }

    public int getJumlahPenduduk() {
        return jumlahPenduduk;
    }

    public Penduduk getPenduduk(int index) {
        if (index < 0 || index >= jumlahPenduduk) {
            return null;
        }
        Node current = head;
        int count = 0;
        while (current != null && count < index) {
            current = current.next;
            count++;
        }
        return (current != null) ? current.penduduk : null;
    }

    public boolean tambahPenduduk(Penduduk p) {
        Node newNode = new Node(p);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        jumlahPenduduk++;
        return true;
    }

    public boolean hapusPendudukByNoKK(String noKK) {
        if (head == null) {
            return false;
        }
        if (head.penduduk.getNoKK().equals(noKK)) {
            head = head.next;
            jumlahPenduduk--;
            return true;
        }

        Node current = head;
        while (current.next != null && !current.next.penduduk.getNoKK().equals(noKK)) {
            current = current.next;
        }
        if (current.next == null) {
            return false;
        }

        current.next = current.next.next;
        jumlahPenduduk--;
        return true;
    }

    public boolean isNoKKExist(String noKK) {
        Node current = head;
        while (current != null) {
            if (current.penduduk.getNoKK().equals(noKK)) {
                return true;  // Jika ditemukan, kembalikan true
            }
            current = current.next;  // Pindah ke node berikutnya
        }
        return false;  // Jika tidak ditemukan, kembalikan false
    }

    public void sortByNama() {
        if (head == null) return;
        head = mergeSort(head);
    }

    private Node mergeSort(Node node) {
        if (node == null || node.next == null) {
            return node;
        }

        Node middle = getMiddle(node);
        Node nextOfMiddle = middle.next;
        middle.next = null;

        Node left = mergeSort(node);
        Node right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        if (left.penduduk.compareTo(right.penduduk) <= 0) {
            left.next = merge(left.next, right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }
    }

    private Node getMiddle(Node node) {
        if (node == null) return node;

        Node slow = node;
        Node fast = node.next;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    public int binarySearchByNama(String nama) {
        int left = 0, right = jumlahPenduduk - 1;

        // Pastikan data sudah terurut
        sortByNama();

        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = getPenduduk(mid).getNama().compareToIgnoreCase(nama);

            if (cmp == 0) {
                return mid;  // Data ditemukan, kembalikan index
            } else if (cmp < 0) {
                left = mid + 1;  // Cari di sebelah kanan
            } else {
                right = mid - 1;  // Cari di sebelah kiri
            }
        }

        return -1;  // Tidak ditemukan
    }

    public void tampilkanSemua() {
        cetakHeaderTabel();
        Node current = head;
        while (current != null) {
            current.penduduk.tampilkanData();
            current = current.next;
        }
        cetakFooterTabel();
    }

    public void tampilkanSudahMenerima() {
        cetakHeaderTabel();
        boolean ada = false;
        Node current = head;
        while (current != null) {
            if (current.penduduk.isSudahMenerimaBantuan()) {
                current.penduduk.tampilkanData();
                ada = true;
            }
            current = current.next;
        }
        if (!ada) {
            System.out.println("|           Tidak ada penduduk yang sudah menerima bantuan          |");
        }
        cetakFooterTabel();
    }

    public void tampilkanBelumMenerima() {
        cetakHeaderTabel();
        boolean ada = false;
        Node current = head;
        while (current != null) {
            if (!current.penduduk.isSudahMenerimaBantuan()) {
                current.penduduk.tampilkanData();
                ada = true;
            }
            current = current.next;
        }
        if (!ada) {
            System.out.println("|          Semua penduduk sudah menerima bantuan                    |");
        }
        cetakFooterTabel();
    }

    public void cetakHeaderTabel() {
        System.out.println("+------------------+----------------------+----------------------+--------+--------------+");
        System.out.println("| No KK            | Nama                 | Alamat               | Status | Jenis Bansos |");
        System.out.println("+------------------+----------------------+----------------------+--------+--------------+");
    }

    public void cetakFooterTabel() {
        System.out.println("+------------------+----------------------+----------------------+--------+--------------+");
    }
}
