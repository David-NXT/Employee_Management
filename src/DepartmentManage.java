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
            System.out.println("\n===== MENU Of Department Managing =====");
            System.out.println("1. Take a look at Department list");
            System.out.println("2. Add a department to the list");
            System.out.println("3. Remove a department (using Department ID)");
            System.out.println("4. Searching for department");
            System.out.println("5. Input Department list (from Department data file)");
            System.out.println("6. Send ramdom employees to departments");
            System.out.println("7. Write to file");
            System.out.println("8. Update info about a department");
            System.out.println("0. Exit");
            System.out.print(">> Select: ");
            chon = sc.nextInt();
            sc.nextLine(); // bỏ dòng trống
            
            switch (chon) {
                case 1 -> data.put();
                case 2 -> {
                    System.out.print("Number of Departments you wanna add: ");
                    int n = sc.nextInt(); sc.nextLine();
                    for (int i = 0;i < n;i++) {
                    Department a = new Department();
                    a.getDepartment();
                    data.them(a);
                    }
                    break;
                }
                case 3 -> {
                    System.out.print("ID of department you want to remove: ");
                    String ma = sc.nextLine();
                    data.xoa(ma);
                }
                case 4 -> {
                    System.out.print("ID or name of department you are searching for: ");
                    String key = sc.nextLine();
                    data.search(key);
                }
                case 5 -> {

                    System.out.print("Added departments data from file Department..");
                    data.readFromFiles();
                }
                case 6 -> {
                    //System.out.print("Nhap n so nhan vien ngau nhien: ");
                    //int n = sc.nextInt();
                    //sc.nextLine();
                    data.addRandom();
                }
                case 7 -> {
                    System.out.println("Printed info to file");
                    data.writeToFile();

                }
                case 8 ->{
                    System.out.print("ID or name of department you want to update: ");
                    String key = sc.nextLine();
                    data.searchToUpdate(key);
                }

                case 0 -> System.out.println(">> You exited the department menu!");
                default -> System.out.println("ERROR!");
            }
        } while (chon != 0);

    }

}
