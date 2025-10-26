import java.util.Scanner;

public class Salary_menu {
    private final Scanner sc = new Scanner(System.in);
    private ListSalary salaryList = new ListSalary();

    public void showMenu() {
        int choice=-1;
        do {
            System.out.println("\n===== SALARY MENU =====");
            System.out.println("1. Export this month's payroll");
            System.out.println("2. Look up payroll by employee ID");
            System.out.println("3. Export monthly/yearly payroll");
            System.out.println("4. View wage deductions (violations) by ID");
            System.out.println("0.Exit");
            System.out.print("→ Enter your choice: ");
            while(!sc.hasNextInt()){
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> salaryList.displayAll();
                case 2 -> {
                    System.out.print("Enter employee id: ");
                    String id = sc.nextLine();
                    salaryList.displaySalaryByEmployeeId(id);
                }
                case 3 -> {
                    System.out.print("Enter month: ");
                    int month = sc.nextInt();
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    salaryList.displayByMonthYear(month, year);
                }
                case 4 -> {
                    System.out.print("Enter employee id: ");
                    String id = sc.nextLine();
                    salaryList.displayViolations(id);
                }
                case 0 -> System.out.println("Exit employee salary menu!");
                default -> System.out.println("Error!");
            }

        } while (choice != 0);
    }

}
