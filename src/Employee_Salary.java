import java.util.Scanner;
import java.io.Serializable;
import java.time.LocalDate;
public class Employee_Salary extends Employee implements Serializable {
    private final Scanner sc= new Scanner(System.in);
    private double baseSalary;
    private String salaryId;
    private Allowance allowc;
    private double bonus;
    private double deductions;
    private double totalSalary;
    private int month;
    private int year;

    public Employee_Salary(){}
    public Employee_Salary(Employee a) {
        super(a);
        LocalDate today = LocalDate.now();
        this.month= today.getMonthValue();
        this.year=today.getYear();
        this.salaryId = "S" + getId() + "-" + month + "-" + year;
        calculateAutoSalary(a);
        this.totalSalary = calculateTotal();
    }

    // tinh tong luong thuc nhan
    public double calculateTotal() {
        double totalAllowance = (allowc != null) ? allowc.getTotalAllowance() : 0;
        totalSalary = baseSalary + totalAllowance + bonus - deductions;
        return totalSalary;
    }

    private void calculateAutoSalary(Employee a) {
        if (a instanceof OfficialEmployee offcialEm) {
            this.baseSalary = offcialEm.getBasicSalary();
            double jobAllowance = offcialEm.getSalaryAllowance();
            double projectAllowance = baseSalary * 0.05;
            double annualAllowance = baseSalary * 0.03;

            this.allowc = new Allowance(
                    "A" + offcialEm.getId(),
                    jobAllowance,
                    projectAllowance,
                    annualAllowance
            );
            this.bonus = baseSalary * 0.1;
            this.deductions = baseSalary * 0.005;

        } else if (a instanceof SeasonalStaff seaEmp) {
            this.baseSalary = seaEmp.getHourlySalary() * seaEmp.getWorkTime();
            this.allowc = new Allowance("A" + seaEmp.getId(), 0, 0, 0);
            this.bonus = this.baseSalary > 15000000 ? baseSalary * 0.02 : 0;
            this.deductions = 0;

        } else {
            // Truong hop khong xac dinh (Employee thường)
            this.baseSalary = 0;
            this.allowc = new Allowance("A0", 0, 0, 0);
            this.bonus = 0;
            this.deductions = 0;
        }
        this.totalSalary = calculateTotal();
    }

    public void displaySalaryInfo() {
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getSurname() + " " + getName());
        System.out.println("Base Salary: " + baseSalary);
        if (allowc != null) {
            System.out.println("  Job Allowance: " + allowc.getJobAllowance());
            System.out.println("  Project Allowance: " + allowc.getProjectAllowance());
            System.out.println("  Annual Allowance: " + allowc.getAnnualAllowance());
            System.out.println("  Total Allowance: " + allowc.getTotalAllowance());
        }
        System.out.println("Bonus: " + bonus);
        System.out.println("Deductions: " + deductions);
        System.out.println("Total Salary: " + calculateTotal());
        System.out.println("------------------------");
    }
    public int getMonth() { return month; }
    public int getYear() { return year; }
    public double getDeductions() { return deductions; }
    public double getTotalSalary() { return totalSalary; }
    public Allowance getAllowance() { return allowc; }
}
