import java.util.Scanner;

public class MainManager {
    private final static Scanner sc = new Scanner(System.in);

    public void menu() {
        int mode;
        do {
            mainMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runMenu(mode);
        } while (true);
    }

    public void mainMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Employee Manager");
        System.out.println("2. Project Manager");
        System.out.println("3. Allowance Rule Manager");
        System.out.println("4. Department Manager");
        System.out.println("5. Salary Manager");
        System.out.println("6. Calculate Allowance Manager");
        System.out.println("7. Manage Time Sheet");
        System.out.println("8. Manage Contract");
        System.out.println("9. Statistic Year Quarter");
    }

    public void runMenu(int mode) {
        switch (mode) {
            case 1: {
                ManageEmployee a = new ManageEmployee();
                a.menu();
                break;
            }
            case 2: {
                ManagerProject a = new ManagerProject();
                a.menu();
                break;
            }
            case 3: {
                ManageAllowanceRate a = new ManageAllowanceRate();
                a.menu();
                break;
            }
            case 4: {
                DepartmentManage a = new DepartmentManage();
                a.menu();
                break;
            }
            case 5: {
                ManageSalary m = getSalary();
                m.showMenu();
            }
            case 6: {
                ManageAllowance m = getAllowance();
                m.menuAllowance();
            }
            case 7: {
                ManageTimeSheet a = new ManageTimeSheet();
                a.menu();
                break;
            }
            case 8: {
                ManageContract a = new ManageContract();
                a.menu();
                break;
            }
            case 9: {
                Statistic a = new Statistic();
                a.menu();
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    private static ManageSalary getSalary() {
        ListEmployee le = new ListEmployee();
        ListAllowance la= new ListAllowance();
        ListTimeSheetMonth lts = new ListTimeSheetMonth();
        le.readFromFile();
        la.readFromFile();
        lts.readfromfile();
        ManageSalary m = new ManageSalary(le,la,lts);
        return m;
    }

    private static ManageAllowance getAllowance() {
        ListEmployee le = new ListEmployee();
        ListProject lp = new ListProject();
        ListPosition lpos= new ListPosition();
        ListSeniorityAllowanceRule  lr = new ListSeniorityAllowanceRule();
        ListAllowanceRule lar= new ListAllowanceRule();
        le.readFromFile();
        lp.readFromFile();
        lpos.readfromfile();
        lr.readFromFile();
        lar.readFromFile();
        ManageAllowance m = new ManageAllowance(le,lp,lpos,lar,lr);
        return m;
    }

}
