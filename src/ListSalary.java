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

    public void addSalary(Employee_Salary s) {
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length - 1] = s;
        saveToFile();
    }

    public void displayAll() {
        if (getCount() == 0) {
            System.out.println(" Chưa có dữ liệu lương.");
            return;
        }
            System.out.println("\nTổng số bản ghi lương: " + getCount());
            System.out.printf("%-8s %-10s %-25s %-10s %-10s %-12s %-12s %-12s %-8s%n",
                    "ID", "Mã NV", "Tên nhân viên", "Lương CB", "Phụ cấp", "Thưởng", "Khấu trừ", "Thực lĩnh", "Tháng/Năm");
            System.out.println("-------------------------------------------------------------------------------------------------------------");

            for (Employee_Salary s : data) {
                System.out.printf("%-8s %-10s %-25s %-10.2f %-10.2f %-12.2f %-12.2f %-12.2f %02d/%d%n",
                        s.getSalaryId(),
                        s.getEmpId(),
                        s.getEmpName(),
                        s.getBaseSalary(),
                        s.getAllowance(),
                        s.getBonus(),
                        s.getDeductions(),
                        s.getTotalSalary(),
                        s.getMonth(),
                        s.getYear());
            }
    }

    public void displaySalaryByEmployeeId(String id) {
        boolean found = false;
        for (Employee_Salary s : data) {
            if (s.getEmpId().equalsIgnoreCase(id)) {
                s.display();
                found = true;
            }
        }
        if (!found)
            System.out.println(" Không tìm thấy nhân viên.");
    }

    public void displayByMonthYear(int month, int year) {
        boolean found = false;
        for (Employee_Salary s : data) {
            if (s.getMonth() == month && s.getYear() == year) {
                s.display();
                found = true;
            }
        }
        if (!found)
            System.out.println(" Không có dữ liệu lương cho tháng/năm này.");
    }

    public void displayViolations(String id) {
        boolean found = false;
        for (Employee_Salary s : data) {
            if (s.getEmpId().equalsIgnoreCase(id)) {
                System.out.println(" Nhân viên: " +  s.getEmpName());
                System.out.println(" Số tiền bị trừ do vi phạm: " + s.getDeductions());
                found = true;
            }
        }
        if (!found)
            System.out.println(" Không tìm thấy nhân viên.");
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println(" Lỗi ghi file: " + e.getMessage());
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