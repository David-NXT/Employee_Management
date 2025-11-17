import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Employee_Salary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String salaryId;
    private String empId;
    private String empName;
    private double baseSalary;
    private double allowance;
    private double bonus;
    private double deductions;
    private double totalSalary;
    private int month;
    private int year;
    private LocalDate createdDate;

    public Employee_Salary(){}

    public Employee_Salary(String salaryId, String empId, String empName,
                  double baseSalary, double allowance, double bonus, double deductions,
                  TimeSheetMonth ts) {
        this.salaryId = salaryId;
        this.empId = empId;
        this.empName = empName;
        this.baseSalary = baseSalary;
        this.allowance = allowance;
        this.bonus = bonus;
        this.deductions = deductions;
        this.totalSalary = baseSalary + allowance + bonus - deductions;
        this.month = ts.getMonth();
        this.year = ts.getYear();
        this.createdDate = LocalDate.now();
    }
    public void display() {
        System.out.printf("%-8s %-10s %-20s %-10.2f %-10.2f %-10.2f %-10.2f %-10.2f %02d/%d\n",
                salaryId, empId, empName, baseSalary, allowance,
                bonus, deductions, totalSalary, month, year);
    }

    // Getters and setters

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public int getMonth() { return month; }
    public int getYear() { return year; }
    public double getDeductions() { return deductions; }
    public double getTotalSalary() { return totalSalary; }
    public double getAllowance() { return allowance; }
    public String getSalaryId() { return salaryId; }
    public double getBaseSalary() { return baseSalary; }
    public double getBonus() { return bonus; }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
    }
    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
    public void setYear(int year) {
        this.year = year;
    }
}