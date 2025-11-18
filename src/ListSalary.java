import java.io.*;
import java.util.Arrays;

public class ListSalary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Employee_Salary[] data;
    private final String FILE_PATH = "data/data_salary.dat";

    public ListSalary() {
        data = new Employee_Salary[0];
        loadFromFile();
    }

    public void addSalary(Employee_Salary s) {
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length - 1] = s;
        saveToFile();
    }

    public void displayGroupedByMonthYear() {
        if (getCount() == 0) {
            System.out.println("No salaries yet.");
            return;
        }

// Sort by month/year first
        sortByMonthYear();

        int currentMonth = -1;
        int currentYear = -1;

        for (Employee_Salary s : data) {
// If a new month/year is encountered, print the group title
            if (s.getMonth() != currentMonth || s.getYear() != currentYear) {
                currentMonth = s.getMonth();
                currentYear = s.getYear();

                System.out.println("\n===== Monthly salary " + currentMonth + "/" + currentYear + " =====");
                System.out.printf("%-8s %-10s %-25s %-10s %-10s %-12s %-12s %-12s %-8s%n",
                        "ID", "Employee Code", "Employee Name", "Employee Salary", "Allowances", "Bonus", "Deductions", "Actual Income", "Month/Year");
                System.out.println("-------------------------------------------------------------------------------------------------------------");
            }

// Print details for each employee
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
            System.out.println(" No employee found.");
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
            System.out.println("There is no salary data for this month/year.");
    }

    public void displayViolations(String id) {
        boolean found = false;
        for (Employee_Salary s : data) {
            if (s.getEmpId().equalsIgnoreCase(id)) {
                System.out.println(" Employee: " + s.getEmpName());
                System.out.println(" Amount deducted due to violation: " + s.getDeductions());
                found = true;
            }
        }
        if (!found)
            System.out.println(" No employee found.");
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println(" Error writing file: " + e.getMessage());
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

    public double getMonthSalary(int year, int month, String id) {
        for (int i = 0;i < data.length;i++) {
            if (data[i].isES(year,month,id)) return data[i].getTotalSalary();
        }
        return 0;
    }
    public void sortByMonthYear() {
        if (getCount() <= 1) return;
        quickSortByMonthYear(data, 0, getCount() - 1);
    }

    private void quickSortByMonthYear(Employee_Salary[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSortByMonthYear(arr, left, pivotIndex - 1);
            quickSortByMonthYear(arr, pivotIndex + 1, right);
        }
    }

    private int partition(Employee_Salary[] arr, int left, int right) {
        Employee_Salary pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j].getYear() < pivot.getYear() ||
                    (arr[j].getYear() == pivot.getYear() && arr[j].getMonth() < pivot.getMonth())) {
                i++;
                // đổi chỗ
                Employee_Salary temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Employee_Salary temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1;
    }

    public int findMinYear() {
        int result = 10000;
        for (int i = 0;i < data.length;i++) {
            if (result > data[i].getYear()) result = data[i].getYear();
        }
        return result;
    }

    public int findMaxYear() {
        int result = 0;
        for (int i = 0;i < data.length;i++) {
            if (result < data[i].getYear()) result = data[i].getYear();
        }
        return result;
    }

    public Employee_Salary[] getAll() {
        return data;
    }

    public int getCount() {
        return data.length;
    }
}