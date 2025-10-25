import java.util.Scanner;

public class DepartmentManage {
    //========[1.thuoc tinh]======
    private DepartmentList data;
    private Scanner sc;

    //========[2.phuong thuc]=======
    //(Constructor)
     public DepartmentManage(){
        data = new DepartmentList();
        sc = new Scanner(System.in);
    }
    //(orgi phuong thuc)
     public void menu() {
         int chon;
        do {
            System.out.println("\n===== MENU Quan Ly Phong ban =====");
            System.out.println("1. Xem Danh Sach Phong Ban");
            System.out.println("2. Them Danh Sach Phong Ban");
            System.out.println("3. Xoa 1 Phong Ban (Theo id phong ban)");
            System.out.println("4. Tim kiem Phong Ban");
            System.out.println("5. Nhap Phong Ban (tu file Department)");
            System.out.println("0. Thoat");
            System.out.print(">> Chon: ");
            chon = sc.nextInt();
            sc.nextLine(); // bỏ dòng trống
            
            switch (chon) {
                case 1 -> data.put();
                case 2 -> {
                    System.out.print("Nhap so luong Phong Ban muon them: ");
                    int n = sc.nextInt(); sc.nextLine();
                    for (int i = 0;i < n;i++) {
                    Department a = new Department();
                    a.getDepartment(sc);
                    data.them(a);
                    }
                    break;
                }
                case 3 -> {
                    System.out.print("Nhap ma Phong Ban can xoa: ");
                    String ma = sc.nextLine();
                    data.xoa(ma);
                }
                case 4 -> {
                    System.out.print("Nhap id hoac ten Phong Ban can tim: ");
                    String key = sc.nextLine();
                    data.search(key);
                }
                case 5 -> {
                    data.readFromFile();
                    System.out.print("Da nhap data phong ban tu file Department..");
                }
                case 0 -> System.out.println(">> Ban da thoat tinh nang!");
                default -> System.out.println("ERROR!");
            }
        } while (chon != 0);

    }

}
