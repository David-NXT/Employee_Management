import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ListAllowance implements Serializable {
    private static final long serialVersionUID = 1L;
    private Allowance[] data;
    private int n;

    public ListAllowance() {
        data = new Allowance[0];
        n = 0;
    }
    public void writeToFile() {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");

        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir,"data_allowance");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
            oos.close();
            System.out.println("Saved!");
        }
        catch (IOException e) {
            System.out.println("File chua duoc ghi " + e);
        }
    }
    public void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data_allowance"))) {
            ListAllowance tmp = (ListAllowance) ois.readObject();
            this.data = tmp.data;
            this.n = tmp.n;
        } catch (Exception e) {
            data = new Allowance[0];
            n = 0;
        }
    }
    public String getIdAuto() {
        return "AL" + String.format("%03d", n + 1);
    }
    public void createAllowance(String empId,
                                ListEmployee listEmp,
                                ListPosition listPos,
                                ListProject listProj,
                                ListSeniorityAllowanceRule listRule,
                                ListAllowanceRule listAllowRule) {

        Employee emp = listEmp.findById(empId);
        if (emp == null) {
            System.out.println("❌ Không tìm thấy nhân viên!");
            return;
        }

        Employee_Salary salary = emp.getSalary();
        if (salary == null) {
            System.out.println("❌ Nhân viên " + emp.getName() + " chưa có thông tin lương.");
            return;
        }

        String posId = emp.getPositionId();
        Position pos = (posId != null) ? listPos.find(posId) : null;
        double jobAllowance = calcJobAllowance(pos);

        int years = calcYearsOfWork(emp.getStartWorkingDay());
        SeniorityAllowanceRule rule = listRule.findByYear(years);
        double seniorityAllowance = calcSeniorityAllowance(salary, rule);

        double projectAllowance = 0;
        for (Project p : listProj.getData()) {
            if (p.isEmployeeInProject(emp.getId())) {
                double rate = p.getAllowanceRateFor(emp.getId());
                projectAllowance += salary.getBaseSalary() * rate;
            }
        }

        double holidayAllowance = 0;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM");
        String todayStr = LocalDate.now().format(fmt);
        for (AllowanceRule ar : listAllowRule.getData()) {
            if (ar != null && ar.getDay().trim().equals(todayStr)) {
                holidayAllowance = ar.getAllowancerate();
                break;
            }
        }

        double totalAllowance = jobAllowance + seniorityAllowance + projectAllowance + holidayAllowance;

        Allowance a = new Allowance();
        a.setAllowanceId(getIdAuto());
        a.setEmployeeId(emp.getId());
        a.setEmployeeName(emp.getSurname() + " " + emp.getName());
        a.setPositionId(posId);
        a.setJobAllowance(jobAllowance);
        a.setSeniorityAllowance(seniorityAllowance);
        a.setProjectAllowance(projectAllowance);
        a.setAnnualAllowance(holidayAllowance);
        a.setTotalAllowance(totalAllowance);
        a.setCreatedDate(LocalDate.now());

        data = Arrays.copyOf(data, n + 1);
        data[n] = a;
        n++;
        writeToFile();

        System.out.println("✅ Đã tạo phụ cấp cho nhân viên " + emp.getSurname() + " " + emp.getName());
    }
    private int calcYearsOfWork(String startWorkingDay) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate start = LocalDate.parse(startWorkingDay, fmt);
            return Period.between(start, LocalDate.now()).getYears();
        } catch (Exception e) {
            return 0;
        }
    }
    private double calcSeniorityAllowance(Employee_Salary salary, SeniorityAllowanceRule rule) {
        if (rule != null)
            return salary.getBaseSalary() * rule.getAllowancerate();
        else
            return 0;
    }
    private double calcJobAllowance(Position pos) {
        if (pos == null) return 0;
        String job = pos.getJob_title().toLowerCase();
        if (job.contains("lap trinh")) return 2000000;
        if (job.contains("maketing")) return 1500000;
        if (job.contains("sale")) return 800000;
        return 500000;
    }
    public void showAll() {
        if (n == 0) {
            System.out.println("Chưa có phụ cấp nào được lưu.");
            return;
        }
        System.out.printf("%-10s %-10s %-20s %-10s %-12s %-12s %-12s %-12s %-12s\n",
                "ID", "EmpID", "Name", "PosID",
                "Job", "Project", "Seniority", "Total", "Date");
        for (int i = 0; i < n; i++) {
            data[i].display();
        }
    }
    public void generateAllowances(ListEmployee listEmp, ListProject listProj, ListAllowanceRule listAllowRule) {
        data = new Allowance[0];
        n = 0;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM");
        String todayStr = LocalDate.now().format(fmt);

        for (Employee e : listEmp.getData()) {
            if (e instanceof OfficialEmployee) {
                OfficialEmployee emp = (OfficialEmployee) e;

                double seniority = calculateSeniorityAllowance(emp);

                double projectAllowance = 0;
                for (Project p : listProj.getData()) {
                    if (p.isEmployeeInProject(emp.getId())) {
                        double rate = p.getAllowanceRateFor(emp.getId());
                        projectAllowance += emp.getBasicSalary() * rate;
                    }
                }
                double holidayAllowance = 0;
                for (AllowanceRule rule : listAllowRule.getData()) {
                    if (rule != null && rule.getDay().trim().equals(todayStr)) {
                        holidayAllowance = rule.getAllowancerate();
                        break;
                    }
                }
                double total = seniority + projectAllowance + holidayAllowance;
                Allowance a = new Allowance();
                a.setAllowanceId(getIdAuto());
                a.setEmployeeId(emp.getId());
                a.setEmployeeName(emp.getSurname() + " " + emp.getName());
                a.setCreatedDate(LocalDate.now());
                a.setSeniorityAllowance(seniority);
                a.setProjectAllowance(projectAllowance);
                a.setAnnualAllowance(holidayAllowance);
                a.setTotalAllowance(total);
                data = Arrays.copyOf(data, n + 1);
                data[n] = a;
                n++;
            }
        }
        System.out.println("Đã tạo danh sách phụ cấp tự động cho " + n + " nhân viên chính thức.");
    }
    private double calculateSeniorityAllowance(OfficialEmployee emp) {
        if (emp == null) return 0;
        try {
            String startDayStr = emp.getStartWorkingDay();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(startDayStr, formatter);
            LocalDate currentDate = LocalDate.now();
            int yearsOfWork = Period.between(startDate, currentDate).getYears();
            double basicSalary = emp.getSalary() != null ? emp.getSalary().getBaseSalary() : emp.getBasicSalary();
            double ratePerYear = 0.02;
            return basicSalary * ratePerYear * yearsOfWork;
        } catch (Exception e) {
            System.err.println("Lỗi khi tính phụ cấp thâm niên cho nhân viên " + emp.getId() + ": " + e.getMessage());
            return 0;
        }
    }
    public void findByEmployeeId(String id) {
        boolean found = false;
        for (Allowance a : data) {
            if (a.getEmployeeId().equalsIgnoreCase(id)) {
                System.out.println(a);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy phụ cấp cho nhân viên có ID " + id);
        }
    }
    public void calculateMonthlyAllowance(ListAllowanceRule listRule, String month) {
        ListAllowanceRule rulesInMonth = listRule.findAllowanceInMonth(month);
        if (rulesInMonth.getN() == 0) {
            System.out.println("Không có quy tắc phụ cấp nào trong tháng " + month);
            return;
        }
        double total = 0;
        boolean found = false;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM");
        System.out.println("Danh sách phụ cấp trong tháng " + month + ":");
        System.out.printf("%-10s %-10s %-20s %-10s %-12s %-12s %-12s %-12s %-12s\n",
                "ID", "EmpID", "Name", "PosID",
                "Job", "Project", "Seniority", "Total", "Date");
        for (int i = 0; i < n; i++) {
            Allowance a = data[i];
            String createdDay = a.getCreatedDate().format(fmt); // ví dụ "19/11"
            for (int j = 0; j < rulesInMonth.getN(); j++) {
                AllowanceRule rule = rulesInMonth.getData()[j];
                if (createdDay.equals(rule.getDay().trim())) {
                    a.display();
                    total += a.getTotalAllowance();
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Không có phụ cấp nào được áp dụng trong tháng này.");
        } else {
            System.out.println("-------------------------------------------------------------");
            System.out.printf("Tổng phụ cấp tháng %s là: %.2f%n", month, total);
        }
    }
    public Allowance[] getData() {
        return data;
    }
}
