import java.util.Scanner;

public class ManageSalary {
    private final Scanner sc = new Scanner(System.in);
    private final ListSalary salaryList;
    private final ListEmployee employeeList;
    private final ListAllowance allowanceList;
    private final ListTimeSheetMonth timeSheetList;

    public ManageSalary(ListEmployee empList, ListAllowance allowList, ListTimeSheetMonth timeList) {
        this.employeeList = empList;
        this.allowanceList = allowList;
        this.timeSheetList = timeList;
        this.salaryList = new ListSalary(); // tự động load từ file khi khởi tạo
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n===== SALARY MANAGEMENT MENU =====");
            System.out.println(" [#] Total number of salary records: " + salaryList.getCount());
            System.out.println("1. Create a payroll from the timesheet and save it to file");
            System.out.println("2. Display the entire payroll");
            System.out.println("3. Find payroll by employee code");
            System.out.println("4. Display payroll by month/year");
            System.out.println("5. View details of deducted (violated) accounts by employee code");
            System.out.println("6. Monthly salary statistics table");
            System.out.println("0.Exit the program");
            System.out.print("→ Enter your selection: ");

            while (!sc.hasNextInt()) {
                System.out.print(" Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> generateSalaries();
                case 2 -> salaryList.displayGroupedByMonthYear();
                case 3 -> {
                    System.out.print("Enter employee ID: ");
                    String id = sc.nextLine().trim();
                    salaryList.displaySalaryByEmployeeId(id);
                }
                case 4 -> {
                    System.out.print("Enter month (1–12): ");
                    int month = sc.nextInt();
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    sc.nextLine(); // clear buffer
                    salaryList.displayByMonthYear(month, year);
                }
                case 5 -> {
                    System.out.print(" Enter employee ID: ");
                    String id = sc.nextLine().trim();
                    salaryList.displayViolations(id);
                }
                case 6 -> {
                    if (salaryList.getCount() == 0) {
                        System.out.println("No salary data yet. Please create a salary table first.");
                        break;
                    }
                    System.out.print("Enter month: ");
                    int month = sc.nextInt();
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    sc.nextLine(); // xóa bộ đệm

                    salaryList.displayByMonthYear(month,year);

                    SalaryStatistics stats = new SalaryStatistics(
                            salaryList.getAll(),
                            salaryList.getCount()
                    );
                    stats.printStatistics(month, year);
                }
                case 0 -> System.out.println("Exiting salary menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    private void generateSalaries() {
        TimeSheetMonth[] sheets = timeSheetList.getTs();
        int createdCount = 0;   // tổng số bảng lương được tạo
        int employeeCount = 0;  // số nhân viên có bảng lương

        for (Employee emp : employeeList.getData()) {

            // Duyệt tất cả bảng công, tạo lương cho từng bảng công
            boolean hasTimesheet = false;
            for (TimeSheetMonth t : sheets) {
                if (!t.getId_employee().equalsIgnoreCase(emp.getId())) continue;

                hasTimesheet = true;
                Allowance allow = allowanceList.findByEmployeeId(emp.getId());
                double baseSalary = calculateBaseSalary(emp, t);
                double allowance = (allow != null) ? allow.getTotalAllowance() : 0;
                double bonus = calculateBonus(t, baseSalary);
                double deductions = calculateDeductions(t);

                Employee_Salary salary = new Employee_Salary(
                        salaryList.getAutoSalaryId(),
                        emp.getId(),
                        emp.getSurname() + " " + emp.getName(),
                        baseSalary,
                        allowance,
                        bonus,
                        deductions,
                        t
                );

                salaryList.addSalary(salary);
                createdCount++;
            }

            if (hasTimesheet) {
                employeeCount++;
            } else {
                System.out.println(" No timesheets for employees: " + emp.getId());
            }
        }

        System.out.println(" Created " + createdCount + " salary table for " + employeeCount + " employee.");
        System.out.println(" Months/Years created:");
    }


    private double calculateBaseSalary(Employee emp, TimeSheetMonth sheet) {
        if (emp instanceof OfficialEmployee offEmp) {
            return offEmp.getBasicSalary();
        } else if (emp instanceof SeasonalStaff seaEmp) {
            return calculateSeasonalSalary(seaEmp,sheet);
        }
        return 0;
    }

    private double calculateBonus(TimeSheetMonth timesheet, double baseSalary) {
        int overtimeDays = countOvertimeDays(timesheet);
        double overtimeBonus = overtimeDays * 50000;
        return baseSalary * 0.05 + overtimeBonus;
    }

    private double calculateDeductions(TimeSheetMonth timesheet) {
        int lateDays = countLateDays(timesheet);
        int missingDays = countMissingDays(timesheet);
        return (lateDays * 20000) + (missingDays * 50000);
    }
    private double calculateHoursWorked(TimeSheetDay day) {
        if (day.getTime_in() == null || day.getTime_out() == null) return 0;

        String[] inParts = day.getTime_in().split(":");
        String[] outParts = day.getTime_out().split(":");

        int inHour = Integer.parseInt(inParts[0]);
        int inMinute = Integer.parseInt(inParts[1]);
        int outHour = Integer.parseInt(outParts[0]);
        int outMinute = Integer.parseInt(outParts[1]);

        double start = inHour + inMinute / 60.0;
        double end = outHour + outMinute / 60.0;

        return Math.max(0, end - start); // số giờ làm trong ngày
    }
    private double calculateMonthlyHours(TimeSheetMonth sheet) {
        double totalHours = 0;
        for (TimeSheetDay day : sheet.getData()) {
            totalHours += calculateHoursWorked(day);
        }
        return totalHours;
    }
    private double calculateSeasonalSalary(SeasonalStaff staff, TimeSheetMonth sheet) {
        double totalHours = calculateMonthlyHours(sheet);
        return staff.getHourlySalary() * totalHours;
    }

    private int countOvertimeDays(TimeSheetMonth sheet) {
        int count = 0;
        for (TimeSheetDay day : sheet.getData()) {
            if (day.check().contains("Over time")) count++;
        }
        return count;
    }

    private int countLateDays(TimeSheetMonth sheet) {
        int count = 0;
        for (TimeSheetDay day : sheet.getData()) {
            if (day.check().contains("Late")) count++;
        }
        return count;
    }

    private int countMissingDays(TimeSheetMonth sheet) {
        int count = 0;
        for (TimeSheetDay day : sheet.getData()) {
            if (day.check().contains("Missing")) count++;
        }
        return count;
    }
}