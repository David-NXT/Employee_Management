import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;
public class Allowance implements Serializable{
    @Serial
    private static final long SerialVersionUID = 1L;
    private String employeeId;
    private String allowanceId;
    private String allowanceName;
    private String dayMonthYear;
    private double jobAllowance;
    private double projectAllowance;
    private double annualAllowance;

    public Allowance(){}
    public Allowance(String employeeId, double jobAllowance, double projectAllowance, double annualAllowance, String dayMonthYear, String allowanceName ){
        this.allowanceId=allowanceId;
        this.jobAllowance=jobAllowance;
        this.allowanceName=allowanceName;
        this.dayMonthYear=dayMonthYear;
        this.projectAllowance=projectAllowance;
        this.annualAllowance=annualAllowance;
        this.employeeId=employeeId;
    }
    public Allowance(Allowance a){
        allowanceId=a.allowanceId;
        jobAllowance=a.jobAllowance;
        allowanceName=a.allowanceName;
        dayMonthYear=a.dayMonthYear;
        projectAllowance=a.projectAllowance;
        annualAllowance=a.annualAllowance;
        employeeId=a.employeeId;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public String getAllowanceId() {
        return allowanceId;
    }
    public String getAllowanceName(){return allowanceName;}
    public double getJobAllowance() {
        return jobAllowance;
    }
    public double getProjectAllowance() {
        return projectAllowance;
    }
    public double getAnnualAllowance() {
        return annualAllowance;
    }
    public String getDayMonthYear(){ return dayMonthYear;}
    public void setAllowanceId(String allowanceId) {
        this.allowanceId = allowanceId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public void setAllowanceName(String allowanceName){this.allowanceName=allowanceName;}
    public void setJobAllowance(double jobAllowance) {
        this.jobAllowance = jobAllowance;
    }
    public void setDayMonthYear(String dayMonthYear){this.dayMonthYear=dayMonthYear;}
    public void setProjectAllowance(double projectAllowance) {
        this.projectAllowance = projectAllowance;
    }
    public void setAnnualAllowance(double annualAllowance) {
        this.annualAllowance = annualAllowance;
    }
    public double getTotalAllowance() {
        return jobAllowance + projectAllowance + annualAllowance;
    }
    public Allowance(ListAllowance list) {
        this.allowanceId = list.getIdAutoForAE();
    }

    public void get() {
        Scanner sc = new Scanner(System.in);
        ListEmployee l = new ListEmployee();
        l.readFromFile();
        l.put();
        System.out.println("Enter Employee ID for this Allowance: ");
        employeeId=sc.nextLine();
        System.out.println("Enter Allowance Name:");
        allowanceName=sc.nextLine();
        System.out.print("Enter Job Allowance: ");
        jobAllowance = sc.nextDouble();
        System.out.print("Enter Project Allowance: ");
        projectAllowance = sc.nextDouble();
        System.out.print("Enter Annual Allowance: ");
        annualAllowance = sc.nextDouble();
        System.out.println("Enter Allowance Date (dd-mm-yyyy): ");
        sc.nextLine();
        dayMonthYear=sc.nextLine();
    }

    public void put() {
        System.out.printf("%-10s %-20s %-15.2f %-15.2f %-15.2f %-15s%n",
                allowanceId, allowanceName, jobAllowance, projectAllowance, annualAllowance, dayMonthYear);
    }

}
