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
            System.out.println("\n===== MENU QUẢN LÝ LƯƠNG =====");
            System.out.println(" [#] Tổng số bản ghi lương: " + salaryList.getCount());
            System.out.println("1. Tạo bảng lương từ bảng chấm công và lưu vào file");
            System.out.println("2. Hiển thị toàn bộ bảng lương");
            System.out.println("3. Tìm bảng lương theo mã nhân viên");
            System.out.println("4. Hiển thị bảng lương theo tháng/năm");
            System.out.println("5. Xem chi tiết các khoản trừ (vi phạm) theo mã nhân viên");
            System.out.println("6. Bảng thống kê lương theo tháng");
            System.out.println("0. Thoát khỏi chương trình");
            System.out.print("→ Nhập lựa chọn của bạn: ");

            while (!sc.hasNextInt()) {
                System.out.print(" Vui lòng nhập một số hợp lệ: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> generateSalaries();
                case 2 -> salaryList.displayAll();
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
                        System.out.println("Chưa có dữ liệu lương. Hãy tạo bảng lương trước.");
                        break;
                    }
                    System.out.print("Nhập tháng cần thống kê: ");
                    int month = sc.nextInt();
                    System.out.print("Nhập năm: ");
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
        int createdCount = 0;

        for (Employee emp : employeeList.getData()) {
            TimeSheetMonth sheet = null;
            for (TimeSheetMonth t : sheets) {
                if (t.getId_employee().equalsIgnoreCase(emp.getId())) {
                    sheet = t;
                    break;
                }
            }
            if (sheet == null) {
                System.out.println(" Không có bảng chấm công cho nhân viên: " + emp.getId());
                continue;
            }

            Allowance allow = allowanceList.findByEmployeeId(emp.getId());
            double baseSalary = calculateBaseSalary(emp,sheet);
            double allowance = (allow != null) ? allow.getTotalAllowance() : 0;
            double bonus = calculateBonus(sheet, baseSalary);
            double deductions = calculateDeductions(sheet);

            Employee_Salary salary = new Employee_Salary(
                    salaryList.getAutoSalaryId(),
                    emp.getId(),
                    emp.getSurname() + " " + emp.getName(),
                    baseSalary,
                    allowance,
                    bonus,
                    deductions,
                    sheet
            );

            salaryList.addSalary(salary);
            createdCount++;
        }

        System.out.println(" Đã tạo bảng lương cho " + createdCount + " nhân viên.");
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