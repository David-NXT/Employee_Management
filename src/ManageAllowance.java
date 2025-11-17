import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ManageAllowance {
    private final Scanner sc;
    private final ListAllowance listAllowance;
    private final ListEmployee listEmp;
    private final ListProject listProj;
    private final ListPosition listPos;
    private final ListAllowanceRule listAllowRule;
    private final ListSeniorityAllowanceRule seniorityRule;

    public ManageAllowance(ListEmployee listEmp, ListProject listProj,
                           ListPosition listPos, ListAllowanceRule listAllowRule,
                           ListSeniorityAllowanceRule seniorityRule) {
        this.sc = new Scanner(System.in);
        this.listAllowance = new ListAllowance();
        this.listEmp = listEmp;
        this.listProj = listProj;
        this.listPos = listPos;
        this.listAllowRule = listAllowRule;
        this.seniorityRule = seniorityRule;
    }

    public void menuAllowance() {
        int chon;
        do {
            System.out.println("\n===== MENU QUẢN LÝ PHỤ CẤP NHÂN VIÊN =====");
            System.out.println("1. Tính phụ cấp tự động cho nhân viên.");
            System.out.println("2. Hiển thị toàn bộ phụ cấp nhân viên.");
            System.out.println("3. Tìm phụ cấp theo mã nhân viên.");
            System.out.println("4. Lưu file dữ liệu phụ cấp nhân viên.");
            System.out.println("5. Đọc dữ liệu phụ cấp từ file.");
            System.out.println("0. Thoát khỏi menu.");
            System.out.print("-> Chọn: ");

            while (!sc.hasNextInt()) {
                System.out.print("Vui lòng nhập số hợp lệ: ");
                sc.next();
            }
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1 -> generateAllowances();
                case 2 -> listAllowance.showAll();
                case 3 -> {
                    System.out.print("Nhập mã nhân viên: ");
                    String empId = sc.nextLine();
                    Allowance a = listAllowance.findByEmployeeId(empId);
                    if (a != null) {
                        System.out.printf("%-10s %-10s %-20s %-10s %-12s %-12s %-12s %-12s %-12s %-12s%n",
                                "ID", "EmpID", "Name", "PosID",
                                "Job", "Project", "Seniority", "Total", "Date");
                        a.display();
                    } else {
                        System.out.println("Không tìm thấy phụ cấp cho nhân viên này.");
                    }
                }

                case 4 -> listAllowance.writeToFile();
                case 5 -> {
                    listAllowance.readFromFile();
                    System.out.println(">> Đã đọc dữ liệu phụ cấp từ file.");
                }
                case 0 -> System.out.println(">> Bạn đã thoát khỏi menu phụ cấp.");
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (chon != 0);
    }

    private void generateAllowances() {
        for (Employee emp : listEmp.getData()) {
            String empId = emp.getId();
            Position pos = listPos.find(empId);

            double basicSalary = 0;
            if (emp instanceof OfficialEmployee off) {
                basicSalary = off.getBasicSalary();
            } else if (emp instanceof SeasonalStaff s) {
                basicSalary = s.getHourlySalary() * s.getWorkTime();
            }

            double jobAllowance = (pos != null)
                    ? pos.getPositionAllowanceRate() * basicSalary
                    : calcJobAllowance(pos, basicSalary);

            double seniorityAllowance = calcSeniorityAllowance(emp);
            double projectAllowance = calcProjectAllowance(emp);
            String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
            double holidayAllowance = calcHolidayAllowanceByMonth(currentMonth);
            double total = jobAllowance + seniorityAllowance + projectAllowance + holidayAllowance;

            Allowance a = new Allowance();
            a.setAllowanceId(listAllowance.getIdAuto());
            a.setEmployeeId(empId);
            a.setEmployeeName(emp.getSurname() + " " + emp.getName());
            a.setPositionId(empId);
            a.setJobAllowance(jobAllowance);
            a.setSeniorityAllowance(seniorityAllowance);
            a.setProjectAllowance(projectAllowance);
            a.setAnnualAllowance(holidayAllowance);
            a.setTotalAllowance(total);
            a.setCreatedDate(LocalDate.now());

            listAllowance.add(a);
        }

        System.out.println(">> Đã tạo phụ cấp cho " + listEmp.getData().length + " nhân viên.");
    }

    private double calcJobAllowance(Position pos, double salary) {
        if (pos == null || pos.getJob_title() == null) return 0;
        String title = pos.getJob_title().trim().toLowerCase();
        return switch (title) {
            case "Giam doc" -> salary * 1;
            case "Truong phong" -> salary * 0.7;
            case "Pho phong" -> salary * 0.5;
            case "nhân viên" -> salary * 0.3;
            default -> salary * 0.2;
        };
    }

    private double calcSeniorityAllowance(Employee emp) {
        try {
            String startDate = emp.getStartWorkingDay();
            if (startDate == null || startDate.length() < 10) return 0;

            int startYear = Integer.parseInt(startDate.substring(6, 10));
            int currentYear = LocalDate.now().getYear();
            int yearsWorked = currentYear - startYear;

            SeniorityAllowanceRule rule = seniorityRule.findYear(yearsWorked);
            return (rule != null) ? rule.getAllowancerate() : 0;
        } catch (Exception e) {
            System.err.println("Lỗi tính phụ cấp thâm niên cho nhân viên " + emp.getId());
            return 0;
        }
    }
    private double calcProjectAllowance(Employee emp) {
        double total = 0;
        if (emp instanceof OfficialEmployee off) {
            for (Project p : listProj.getData()) {
                if (p.isEmployeeInProject(off)) {
                    total += p.projectAllowanceSalary(off);
                }
            }
        }
        return total;
    }

    private double calcHolidayAllowanceByMonth(String month) {
        ListAllowanceRule holidays = listAllowRule.findAllowanceInMonth(month);
        double totalHolidayAllowance = 0;

        for (AllowanceRule rule : holidays.getData()) {
            if (rule != null) {
                totalHolidayAllowance += rule.getAllowancerate();
            }
        }

        return totalHolidayAllowance;
    }
}