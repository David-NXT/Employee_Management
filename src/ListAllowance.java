import java.io.*;
import java.util.Arrays;

public class ListAllowance implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Allowance[] data;
    private int n;

    public ListAllowance() {
        data = new Allowance[0];
        n = 0;
    }

    // Thêm phụ cấp mới dùng Arrays.copyOf
    public void add(Allowance a) {
        data = Arrays.copyOf(data, n + 1); // mở rộng mảng thêm 1 phần tử
        data[n] = a;
        n++;
    }

    // Tạo mã phụ cấp tự động
    public String getIdAuto() {
        return "AL" + String.format("%03d", n + 1);
    }

    // Hiển thị toàn bộ phụ cấp
    public void showAll() {
        if (n == 0) {
            System.out.println("No allowances yet.");
            return;
        }

        System.out.printf("%-10s %-10s %-20s %-10s %-12s %-12s %-12s %-12s %-12s %-12s%n",
                "ID", "EmpID", "Name", "PosID",
                "Job", "Project", "Seniority", "Holiday", "Total", "Date");

        for (int i = 0; i < n; i++) {
            data[i].display();
        }
    }

    // Tìm phụ cấp theo mã nhân viên
    public Allowance findByEmployeeId(String empId) {
        for (int i = 0; i < n; i++) {
            if (data[i].getEmployeeId().equalsIgnoreCase(empId)) {
                return data[i];
            }
        }
        return null;
    }

    // Ghi dữ liệu ra file
    public void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("data/data_allowance.dat"))) {
            oos.writeObject(this);
            System.out.println(">> Created data allowance.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Đọc dữ liệu từ file
    public void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("data/data_allowance.dat"))) {
            ListAllowance tmp = (ListAllowance) ois.readObject();
            this.data = tmp.data;
            this.n = tmp.n;
        } catch (Exception e) {
            data = new Allowance[0];
            n = 0;
            System.err.println("Cannot read from file: " + e.getMessage());
        }
    }

    // Getter cho mảng dữ liệu
    public Allowance[] getData() {
        return data;
    }

}