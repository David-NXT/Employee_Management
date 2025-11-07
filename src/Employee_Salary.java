import java.io.Serializable;
import java.time.LocalDate;

public class Employee_Salary extends Employee implements Serializable {
    private double baseSalary;
    private String salaryId;
    private Allowance allowc;
    private double bonus;
    private double deductions;
    private double totalSalary;
    private int month;
    private int year;

    public Employee_Salary() {}

    public Employee_Salary(Employee emp, Allowance allowc, TimeSheetMonth timesheet, ListSalary listSalary) {
        super(emp);
        LocalDate today = LocalDate.now();
        this.month = today.getMonthValue();
        this.year = today.getYear();
        this.salaryId = listSalary.getAutoSalaryId(); // truyền ListSalary vào constructor

        this.allowc = allowc;
        this.baseSalary = calculateBaseSalary(emp, timesheet);
        this.bonus = calculateBonus(emp, timesheet);
        this.deductions = calculateDeductions(emp, timesheet);
        this.totalSalary = calculateTotal();
    }

    private double calculateBaseSalary(Employee emp, TimeSheetMonth timesheet) {
        int workDays = countValidDays(timesheet);
        if (emp instanceof OfficialEmployee offEmp) {
            return offEmp.getBasicSalary(); // có thể nhân với workDays / 26 nếu muốn tính theo ngày
        } else if (emp instanceof SeasonalStaff seaEmp) {
            return seaEmp.getHourlySalary() * seaEmp.getWorkTime();
        }
        return 0;
    }

    private double calculateBonus(Employee emp, TimeSheetMonth timesheet) {
        int overtimeDays = countOvertimeDays(timesheet);
        double overtimeBonus = overtimeDays * 50000; // ví dụ: 50k/ngày
        return baseSalary * 0.05 + overtimeBonus;
    }

    private double calculateDeductions(Employee emp, TimeSheetMonth timesheet) {
        int lateDays = countLateDays(timesheet);
        int missingDays = countMissingDays(timesheet);
        return (lateDays * 20000) + (missingDays * 50000); // ví dụ: trừ 20k đi muộn, 50k vắng
    }

    private int countValidDays(TimeSheetMonth sheet) {
        int count = 0;
        for (TimeSheetDay day : sheet.getData()) {
            String status = day.check();
            if (!status.contains("Missing")) count++;
        }
        return count;
    }

    private int countOvertimeDays(TimeSheetMonth sheet) {
        int count = 0;
        for (TimeSheetDay day : sheet.getData()) {
            if (day.check().equals("Over time")) count++;
        }
        return count;
    }

    private int countLateDays(TimeSheetMonth sheet) {
        int count = 0;
        for (TimeSheetDay day : sheet.getData()) {
            if (day.check().contains("Late")) count++;
        }
        return count;
    }

    private int countMissingDays(TimeSheetMonth sheet) {
        int count = 0;
        for (TimeSheetDay day : sheet.getData()) {
            String status = day.check();
            if (status.contains("Missing")) count++;
        }
        return count;
    }

    public double calculateTotal() {
        double totalAllowance = (allowc != null) ? allowc.getTotalAllowance() : 0;
        return baseSalary + totalAllowance + bonus - deductions;
    }

    public void displaySalaryInfo() {
        System.out.printf("ID: %s | Name: %s %s | Month: %d/%d\n", getId(), getSurname(), getName(), month, year);
        System.out.printf("Base Salary: %.2f | Bonus: %.2f | Deductions: %.2f\n", baseSalary, bonus, deductions);
        if (allowc != null) {
            System.out.printf("Allowance: %.2f (Job: %.2f, Project: %.2f, Annual: %.2f)\n",
                    allowc.getTotalAllowance(), allowc.getJobAllowance(), allowc.getProjectAllowance(), allowc.getAnnualAllowance());
        }
        System.out.printf("Total Salary: %.2f\n", calculateTotal());
        System.out.println("--------------------------------------------------");
    }

    // Getters
    public int getMonth() { return month; }
    public int getYear() { return year; }
    public double getDeductions() { return deductions; }
    public double getTotalSalary() { return totalSalary; }
    public Allowance getAllowance() { return allowc; }
    public String getSalaryId() { return salaryId; }
    public double getBaseSalary() { return baseSalary; }
    public double getBonus() { return bonus; }
}