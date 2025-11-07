import java.util.Scanner;

public class Salary_menu {
    private final Scanner sc = new Scanner(System.in);
    private final ListSalary salaryList;

    private final ListEmployee employeeList;
    private final ListAllowance allowanceList;
    private final ListTimeSheet timeSheetList;

    public Salary_menu(ListEmployee empList, ListAllowance allowList, ListTimeSheet timeList) {
        this.employeeList = empList;
        this.allowanceList = allowList;
        this.timeSheetList = timeList;
        this.salaryList = new ListSalary(); // tự động load từ file khi khởi tạo
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n===== SALARY MENU =====");
            System.out.println("📊 Total salary records: " + salaryList.getCount());
            System.out.println("1. Generate payroll from timesheet");
            System.out.println("2. Display all payroll records");
            System.out.println("3. Search payroll by employee ID");
            System.out.println("4. Display payroll by month/year");
            System.out.println("5. View salary deductions (violations) by ID");
            System.out.println("6. Save payroll to file");
            System.out.println("7. Reload payroll from file");
            System.out.println("0. Exit");
            System.out.print("→ Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> {
                    System.out.println("🔄 Generating payroll...");
                    salaryList.generateSalaries(employeeList, allowanceList, timeSheetList, salaryList);
                }
                case 2 -> salaryList.displayAll();
                case 3 -> {
                    System.out.print("🔍 Enter employee ID: ");
                    String id = sc.nextLine().trim();
                    salaryList.displaySalaryByEmployeeId(id);
                }
                case 4 -> {
                    System.out.print("📅 Enter month (1–12): ");
                    int month = sc.nextInt();
                    System.out.print("📅 Enter year: ");
                    int year = sc.nextInt();
                    sc.nextLine(); // clear buffer
                    salaryList.displayByMonthYear(month, year);
                }
                case 5 -> {
                    System.out.print("🔍 Enter employee ID: ");
                    String id = sc.nextLine().trim();
                    salaryList.displayViolations(id);
                }
                case 6 -> {
                    salaryList.saveToFile();
                    System.out.println("✅ Payroll data saved to file.");
                }
                case 7 -> {
                    salaryList.loadFromFile();
                    System.out.println("✅ Payroll data reloaded from file.");
                }
                case 0 -> System.out.println("👋 Exiting salary menu...");
                default -> System.out.println("⚠ Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }
}