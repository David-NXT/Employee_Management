import java.io.*;
import java.util.Arrays;

public class ListSalary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Employee_Salary[] data;
    private final String FILE_PATH = "data/salary.dat";

    public ListSalary() {
        data = new Employee_Salary[0];
        loadFromFile();
    }

    // ✅ Tạo bảng lương từ danh sách nhân viên, phụ cấp và chấm công
    public void generateSalaries(ListEmployee listEmp, ListAllowance listAllow, ListTimeSheet listTimeSheet, ListSalary listSalary) {
        data = new Employee_Salary[0];

        for (Employee emp : listEmp.getData()) {
            // Tìm bảng chấm công của nhân viên
            TimeSheetMonth sheet = null;
            for (TimeSheetMonth t : listTimeSheet.getTs()) {
                if (t.getId_employee().equalsIgnoreCase(emp.getId())) {
                    sheet = t;
                    break;
                }
            }

            if (sheet == null) {
                System.out.println("❌ Không có bảng chấm công cho nhân viên " + emp.getId());
                continue;
            }

            // Tìm phụ cấp
            Allowance allow = null;
            for (Allowance a : listAllow.getData()) {
                if (a.getEmployeeId().equalsIgnoreCase(emp.getId())) {
                    allow = a;
                    break;
                }
            }

            // Tạo lương
            Employee_Salary salary = new Employee_Salary(emp, allow, sheet, listSalary);
            addSalary(salary);
        }

        System.out.println("✅ Đã tạo bảng lương cho " + data.length + " nhân viên có chấm công.");
    }

    public void addSalary(Employee_Salary s) {
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length - 1] = s;
        saveToFile();
    }

    public void displayAll() {
        if (getCount() == 0) {
            System.out.println("❌ Chưa có dữ liệu lương.");
            return;
        }
        System.out.println("📊 Tổng số bản ghi lương: " + getCount());
        for (Employee_Salary s : data) {
            s.displaySalaryInfo();
        }
    }

    public void displaySalaryByEmployeeId(String id) {
        boolean found = false;
        for (Employee_Salary s : data) {
            if (s.getId().equalsIgnoreCase(id)) {
                s.displaySalaryInfo();
                found = true;
            }
        }
        if (!found)
            System.out.println("❌ Không tìm thấy nhân viên.");
    }

    public void displayByMonthYear(int month, int year) {
        boolean found = false;
        for (Employee_Salary s : data) {
            if (s.getMonth() == month && s.getYear() == year) {
                s.displaySalaryInfo();
                found = true;
            }
        }
        if (!found)
            System.out.println("❌ Không có dữ liệu lương cho tháng/năm này.");
    }

    public void displayViolations(String id) {
        boolean found = false;
        for (Employee_Salary s : data) {
            if (s.getId().equalsIgnoreCase(id)) {
                System.out.println("👤 Nhân viên: " + s.getSurname() + " " + s.getName());
                System.out.println("❗ Số tiền bị trừ do vi phạm: " + s.getDeductions());
                found = true;
            }
        }
        if (!found)
            System.out.println("❌ Không tìm thấy nhân viên.");
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println("❌ Lỗi ghi file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            data = new Employee_Salary[0];
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            data = (Employee_Salary[]) ois.readObject();
        } catch (Exception e) {
            data = new Employee_Salary[0];
        }
    }

    public String getAutoSalaryId() {
        int baseId = 1000;
        for (Employee_Salary s : data) {
            if (s.getSalaryId().equalsIgnoreCase("S" + baseId)) {
                baseId++;
            }
        }
        return "S" + baseId;
    }

    public Employee_Salary[] getAll() {
        return data;
    }

    public int getCount() {
        return data.length;
    }
}