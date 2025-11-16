import java.time.LocalDate;
import java.util.Scanner;

public class ManageSalary {
    private final Scanner sc = new Scanner(System.in);
    private final ListSalary salaryList;
    private final ListEmployee employeeList;
    private final ListAllowance allowanceList;
    private final ListTimeSheet timeSheetList;

    public ManageSalary(ListEmployee empList, ListAllowance allowList, ListTimeSheet timeList) {
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
            System.out.println("1. Tạo bảng lương từ bảng chấm công");
            System.out.println("2. Hiển thị toàn bộ bảng lương");
            System.out.println("3. Tìm bảng lương theo mã nhân viên");
            System.out.println("4. Hiển thị bảng lương theo tháng/năm");
            System.out.println("5. Xem chi tiết các khoản trừ (vi phạm) theo mã nhân viên");
            System.out.println("6. Bảng thống kê lương theo tháng");
            System.out.println("7. Lưu file dữ liệu bảng lương");
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

                    // Tạo đối tượng thống kê từ dữ liệu trong ListSalary
                    SalaryStatistics stats = new SalaryStatistics(
                            salaryList.getAll(),
                            salaryList.getCount()
                    );
                    stats.printStatistics(month, year);
                }
                case 7 ->{
                    System.out.print("Đã lưu dữ liệu vào file.");
                    salaryList.saveToFile();
                }
                case 0 -> System.out.println("Exiting salary menu...");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);
    }

    private void generateSalaries() {
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        TimeSheetMonth[] sheets = timeSheetList.getTs(); // lấy danh sách chấm công

        for (Employee emp : employeeList.getData()) {
            TimeSheetMonth sheet = null;
            // Tìm TimeSheetMonth tương ứng với nhân viên
            for (TimeSheetMonth t : sheets) {
                if (t.getId_employee().equals(emp.getId())) {
                    sheet = t;
                    break;
                }
            }

            Allowance allow = allowanceList.findByEmployeeId(emp.getId());
            double baseSalary = calculateBaseSalary(emp);
            double allowance = (allow != null) ? allow.getTotalAllowance() : 0;
            double bonus = calculateBonus( sheet, baseSalary);
            double deductions = calculateDeductions(sheet);

            Employee_Salary salary = new Employee_Salary(
                    salaryList.getAutoSalaryId(),
                    emp.getId(),
                    emp.getSurname() + " " + emp.getName(),
                    baseSalary,
                    allowance,
                    bonus,
                    deductions,
                    month,
                    year
            );

            salaryList.addSalary(salary);
        }

        System.out.println("Đã tạo bảng lương cho " + employeeList.getData().length + " nhân viên.");
    }

    private double calculateBaseSalary(Employee emp) {

        if (emp instanceof OfficialEmployee offEmp) {
            return offEmp.getBasicSalary(); // hoặc: * (workDays / 26.0)
        } else if (emp instanceof SeasonalStaff seaEmp) {
            return seaEmp.getHourlySalary() * seaEmp.getWorkTime();
        }
        return 0;
    }

    private double calculateBonus( TimeSheetMonth timesheet, double baseSalary) {
        int overtimeDays = countOvertimeDays(timesheet);
        double overtimeBonus = overtimeDays * 50000;
        return baseSalary * 0.05 + overtimeBonus;
    }

    private double calculateDeductions(TimeSheetMonth timesheet) {
        int lateDays = countLateDays(timesheet);
        int missingDays = countMissingDays(timesheet);
        return (lateDays * 20000) + (missingDays * 50000);
    }

    private int countOvertimeDays(TimeSheetMonth sheet) {
        int count = 0;
        for (TimeSheetDay day : sheet.getData()) {
            if (day.check().equals("Over time")) count++;
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