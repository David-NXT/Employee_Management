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
        System.out.println("3. Allowance Rate Manager");
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
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }
}
