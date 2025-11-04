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

    // --- Ghi file ---
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

    // --- Sinh ID tự động ---
    public String getIdAuto() {
        return "AL" + String.format("%03d", n + 1);
    }

    // --- Tạo phụ cấp tự động cho 1 nhân viên ---
    public void createAllowance(String empId,
                                ListEmployee listEmp,
                                ListPosition listPos,
                                ListProject listProj,
                                ListSeniorityAllowanceRule listRule) {

        Employee emp = listEmp.findById(empId);
        if (emp == null) {
            System.out.println("Không tìm thấy nhân viên!");
            return;
        }

        Employee_Salary salary = emp.getSalary();
        Position pos = listPos.find(emp.getPositionId());
        if (salary == null) {
            System.out.println("Nhân viên " + emp.getName() + " chưa có thông tin lương.");
            return;
        }

        // ---  Phụ cấp chức vụ ---
        double jobAllowance = calcJobAllowance(pos);

        // ---  Phụ cấp thâm niên ---
        SeniorityAllowanceRule rule = listRule.findByYear(calcYearsOfWork(emp.getStartWorkingDay()));
        double seniorityAllowance = calcSeniorityAllowance(salary, rule);

        // ---  Phụ cấp dự án (nhiều dự án) ---
        double projectAllowance = 0;
        Project[] allProj = listProj.getData();
        for (int i = 0; i < allProj.length; i++) {
            Project p = allProj[i];
            if (p.hasEmployee(emp.getId())) { // Kiểm tra xem nhân viên có trong dự án này không
                double rate = p.getAllowanceRateFor(emp.getId());
                projectAllowance += p.getProjectBudget() * rate;
            }
        }

        // --- Tính tổng ---
        double totalAllowance = jobAllowance + seniorityAllowance + projectAllowance;

        // --- Tạo đối tượng Allowance ---
        Allowance a = new Allowance();
        a.setAllowanceId(getIdAuto());
        a.setEmployeeId(emp.getId());
        a.setEmployeeName(emp.getName());
        a.setPositionId(emp.getPositionId());
        a.setJobAllowance(jobAllowance);
        a.setSeniorityAllowance(seniorityAllowance);
        a.setProjectAllowance(projectAllowance);
        a.setTotalAllowance(totalAllowance);
        a.setCreatedDate(LocalDate.now());

        // ---  Lưu vào danh sách ---
        data = Arrays.copyOf(data, n + 1);
        data[n] = a;
        n++;

        writeToFile();
        System.out.println(" Đã tạo phụ cấp cho nhân viên " + emp.getName());
    }

    // --- Hàm tính số năm công tác ---
    private int calcYearsOfWork(String startWorkingDay) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate start = LocalDate.parse(startWorkingDay, fmt);
            return Period.between(start, LocalDate.now()).getYears();
        } catch (Exception e) {
            return 0;
        }
    }

    // --- Hàm tính phụ cấp thâm niên ---
    private double calcSeniorityAllowance(Employee_Salary salary, SeniorityAllowanceRule rule) {
        if (rule != null)
            return salary.getBaseSalary() * rule.getAllowancerate();
        else
            return 0;
    }

    // --- Hàm tính phụ cấp chức vụ ---
    private double calcJobAllowance(Position pos) {
        if (pos == null) return 0;
        String job = pos.getJob_title().toLowerCase();
        if (job.contains("lap trinh")) return 2000000;
        if (job.contains("Maketing")) return 1500000;
        if (job.contains("Sale")) return 800000;
        return 500000;
    }

    // --- Hiển thị toàn bộ ---
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
    public void generateAllowances(ListEmployee listEmp, ListProject listPro) {
        // Khởi tạo danh sách phụ cấp rỗng
        data = new Allowance[0];
        n = 0;

        // Duyệt qua toàn bộ nhân viên trong danh sách
        for (Employee e : listEmp.getData()) {
            // Chỉ tính cho nhân viên chính thức (OfficialEmployee)
            if (e instanceof OfficialEmployee) {
                OfficialEmployee emp = (OfficialEmployee) e;

                Allowance a = new Allowance();
                a.setEmployeeId(emp.getId());
                a.setEmployeeName(emp.getSurname() + " " + emp.getName());
                a.setCreatedDate(LocalDate.now());

                // 🧮 Tính phụ cấp thâm niên
                double seniority = calculateSeniorityAllowance(emp);

                // 🧮 Tính phụ cấp dự án
                double projectAllowance = 0;
                for (Project p : listPro.getData()) {
                    if (p.hasEmployee(emp.getId())) {
                        projectAllowance += emp.getBasicSalary() * p.getAllowanceRateFor(emp.getId());
                    }
                }

                // Gán giá trị
                a.setSeniorityAllowance(seniority);
                a.setProjectAllowance(projectAllowance);
                a.setTotalAllowance(seniority + projectAllowance);

                // Thêm vào danh sách
                data = Arrays.copyOf(data, n + 1);
                data[n] = a;
                n++;
            }
        }

        System.out.println(" Đã tạo danh sách phụ cấp tự động cho " + n + " nhân viên chính thức.");
    }

    // 📘 Tính phụ cấp thâm niên (seniority allowance)
    private double calculateSeniorityAllowance(OfficialEmployee emp) {
        if (emp == null) return 0;

        try {
            // 1️⃣ Lấy ngày bắt đầu làm việc từ Employee (kiểu String, vd: "12/08/2015")
            String startDayStr = emp.getStartWorkingDay();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.parse(startDayStr, formatter);
            LocalDate currentDate = LocalDate.now();

            // 2️⃣ Tính số năm làm việc
            int yearsOfWork = Period.between(startDate, currentDate).getYears();

            // 3️⃣ Lấy lương cơ bản từ đối tượng Employee_Salary
            double basicSalary = 0;
            Employee_Salary salaryInfo = emp.getSalary();
            if (salaryInfo != null) {
                basicSalary = salaryInfo.getBaseSalary();
            } else {
                // Nếu chưa có salary thì tạm lấy giá trị basicSalary trong Employee (nếu có)
                basicSalary = emp.getBasicSalary();
            }

            // 4️⃣ Tính phụ cấp thâm niên: 2% mỗi năm
            double ratePerYear = 0.02;
            return basicSalary * ratePerYear * yearsOfWork;

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi tính phụ cấp thâm niên cho nhân viên " + emp.getId() + ": " + e.getMessage());
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



}
