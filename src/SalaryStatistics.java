import java.io.Serializable;

public class SalaryStatistics implements Serializable {
    private static final long serialVersionUID = 1L;
    private Employee_Salary[] data;
    private int n;

    public SalaryStatistics(Employee_Salary[] data, int n) {
        this.data = data;
        this.n = n;
    }

    public double totalNetSalary(int month, int year) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            if (data[i].getMonth() == month && data[i].getYear() == year) {
                sum += data[i].getTotalSalary();
            }
        }
        return sum;
    }

    public double totalBaseSalary(int month, int year) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            if (data[i].getMonth() == month && data[i].getYear() == year) {
                sum += data[i].getBaseSalary();
            }
        }
        return sum;
    }

    public double averageNetSalary(int month, int year) {
        double sum = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (data[i].getMonth() == month && data[i].getYear() == year) {
                sum += data[i].getTotalSalary();
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }

    public int countByMonth(int month, int year) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (data[i].getMonth() == month && data[i].getYear() == year) count++;
        }
        return count;
    }

    public void printStatistics(int month, int year) {
        double totalNet = totalNetSalary(month, year);
        double totalBase = totalBaseSalary(month, year);
        double avgNet = averageNetSalary(month, year);
        int count = countByMonth(month, year);

        System.out.println("\n===== THỐNG KÊ LƯƠNG THÁNG " + month + "/" + year + " =====");
        System.out.println("Số bản ghi: " + count);
        System.out.printf("Tổng lương cơ bản: %.2f\n", totalBase);
        System.out.printf("Tổng lương thực nhận: %.2f\n", totalNet);
        System.out.printf("Lương trung bình: %.2f\n", avgNet);
        System.out.println("========================================");
    }
}
