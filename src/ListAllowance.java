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

    private double calcJobAllowance(Position pos, double basicSalary) {
        if (pos == null || pos.getJob_title() == null) return 0;

        String title = pos.getJob_title().trim().toLowerCase();
        double rate;

        switch (title) {
            case "giám đốc" -> rate = 0.07;
            case "trưởng phòng" -> rate = 0.05;
            case "nhân viên" -> rate = 0.03;
            default -> rate = 0.02; // phụ cấp mặc định nếu không khớp
        }

        return basicSalary * rate;
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
    public void generateAllowances(ListEmployee listEmp, ListProject listProj, ListPosition listPos, ListAllowanceRule listAllowRule) {
        data = new Allowance[0];
        n = 0;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM");
        String todayStr = LocalDate.now().format(fmt);

        for (Employee emp : listEmp.getData()) {
            String posId = emp.getId();
            Position pos = (posId != null) ? listPos.find(posId) : null;

            double basicSalary = 0;
            if (emp instanceof OfficialEmployee) {
                basicSalary = ((OfficialEmployee) emp).getBasicSalary();
            } else if (emp instanceof SeasonalStaff) {
                SeasonalStaff s = (SeasonalStaff) emp;
                basicSalary = s.getHourlySalary() * s.getWorkTime();
            }
            double jobAllowance = (pos != null) ? pos.getPositionAllowanceRate() * basicSalary : 0;

            double seniorityAllowance = calculateSeniorityAllowance(emp, basicSalary);

            double projectAllowance = 0;
               if(emp instanceof OfficialEmployee offemp){
                   for(Project p : listProj.getData()){
                       if(p.isEmployeeInProject(offemp)){
                           projectAllowance += p.projectAllowanceSalary(offemp);
                       }
                   }
               }
            System.out.println(" " + emp.getId() + " - Project Allowance: " + projectAllowance);
            double holidayAllowance = 0;
            for (AllowanceRule rule : listAllowRule.getData()) {
                if (rule != null && rule.getDay().trim().equals(todayStr)) {
                    holidayAllowance = rule.getAllowancerate();
                    break;
                }
            }

            double total = jobAllowance + seniorityAllowance + projectAllowance + holidayAllowance;

            Allowance a = new Allowance();
            a.setAllowanceId(getIdAuto());
            a.setEmployeeId(emp.getId());
            a.setEmployeeName(emp.getSurname() + " " + emp.getName());
            a.setPositionId(posId);
            a.setJobAllowance(jobAllowance);
            a.setSeniorityAllowance(seniorityAllowance);
            a.setProjectAllowance(projectAllowance);
            a.setAnnualAllowance(holidayAllowance);
            a.setTotalAllowance(total);
            a.setCreatedDate(LocalDate.now());

            data = Arrays.copyOf(data, n + 1);
            data[n] = a;
            n++;
        }

        System.out.println(" Đã tạo phụ cấp cho toàn bộ nhân viên (" + n + " người).");
    }
    private double calculateSeniorityAllowance(Employee emp, double basicSalary) {
        try {
            String startDayStr = emp.getStartWorkingDay();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(startDayStr, formatter);
            int years = Period.between(startDate, LocalDate.now()).getYears();
            return basicSalary * 0.02 * years;
        } catch (Exception e) {
            System.err.println("❌ Lỗi thâm niên cho " + emp.getId() + ": " + e.getMessage());
            return 0;
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
    public Allowance findByEmployeeId(String empId) {
        for (Allowance a : data) {
            if (a.getEmployeeId().equalsIgnoreCase(empId)) {
                return a;
            }
        }
        return null;
    }
    public void calculateProjectAllowance(ListProject projectList, ListEmployee employeeList) {
        for (Project p : projectList.getData()) {
            for (Employee emp : employeeList.getData()) {
                if (!(emp instanceof OfficialEmployee offEmp)) continue;

                String empId = offEmp.getId();
                if (!p.isEmployeeInProject(empId)) continue;

                float rate = p.getAllowanceRateFor(empId);
                double projectBonus = offEmp.getBasicSalary() * rate;

                Allowance allow = findByEmployeeId(empId);
                if (allow != null) {
                    allow.setProjectAllowance(projectBonus);
                    allow.updateTotalAllowance();
                }
            }
        }
    }

    public Allowance[] getData() {
        return data;
    }
}
