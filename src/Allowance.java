import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Allowance implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String allowanceId;
    private String employeeId;
    private String employeeName;
    private String positionId;

    private double annualAllowance;

    private double jobAllowance;
    private double projectAllowance;
    private double seniorityAllowance;
    private double totalAllowance;
    private LocalDate createdDate;

    // --- Constructors ---
    public Allowance() {}

    public Allowance(String allowanceId, String employeeId, String employeeName, String positionId, double annualAllowance, double jobAllowance, double projectAllowance, double seniorityAllowance) {
        this.allowanceId = allowanceId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.positionId = positionId;
        this.annualAllowance=annualAllowance;
        this.jobAllowance = jobAllowance;
        this.projectAllowance = projectAllowance;
        this.seniorityAllowance = seniorityAllowance;
        this.totalAllowance = jobAllowance + projectAllowance + seniorityAllowance;
        this.createdDate = LocalDate.now();
    }

    // --- Display ---
    public void display() {
        System.out.printf("%-10s %-10s %-20s %-10s %-12.2f %-12.2f %-12.2f %-12.2f %-12.2f %-12s%n",
                allowanceId, employeeId, employeeName, positionId,
                jobAllowance, projectAllowance, seniorityAllowance, annualAllowance, totalAllowance,
                createdDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    // --- Getters & Setters ---
    public String getAllowanceId() { return allowanceId; }
    public void setAllowanceId(String allowanceId) { this.allowanceId = allowanceId; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getPositionId() { return positionId; }
    public void setPositionId(String positionId) { this.positionId = positionId; }



    public double getJobAllowance() { return jobAllowance; }
    public void setJobAllowance(double jobAllowance) { this.jobAllowance = jobAllowance; }

    public double getProjectAllowance() { return projectAllowance; }
    public double getAnnualAllowance() {
        return annualAllowance;
    }
    public void setProjectAllowance(double projectAllowance) { this.projectAllowance = projectAllowance; }

    public double getSeniorityAllowance() { return seniorityAllowance; }
    public void setSeniorityAllowance(double seniorityAllowance) { this.seniorityAllowance = seniorityAllowance; }

    public double getTotalAllowance() { return totalAllowance; }
    public void setTotalAllowance(double totalAllowance) { this.totalAllowance = totalAllowance; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    public void setAnnualAllowance(double annualAllowance) {
        this.annualAllowance = annualAllowance;
    }
    @Override
    public String toString() {
        return String.format(
                "Allowance ID: %s | ID : %s | Name : %s | Position: %s\n" +
                        "  - Allowance Position: %.2f\n" +
                        "  - Seniority Allowance: %.2f\n" +
                        "  - Allowance Project: %.2f\n" +
                        "  => Total Allowance: %.2f\n" +
                        "  Date: %s\n",
                allowanceId,
                employeeId,
                employeeName,
                positionId,
                jobAllowance,
                seniorityAllowance,
                projectAllowance,
                totalAllowance,
                createdDate
        );
    }
}
