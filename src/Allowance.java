import java.io.Serializable;
public class Allowance implements Serializable{
    private String allowanceId;
    private double jobAllowance;
    private double projectAllowance;
    private double annualAllowance;

    public Allowance(){}
    public Allowance(String allowanceId, double jobAllowance, double projectAllowance, double annualAllowance ){
        this.allowanceId=allowanceId;
        this.jobAllowance=jobAllowance;
        this.projectAllowance=projectAllowance;
        this.annualAllowance=annualAllowance;
    }
    public Allowance(Allowance a){
        allowanceId=a.allowanceId;
        jobAllowance=a.jobAllowance;
        projectAllowance=a.projectAllowance;
        annualAllowance=a.annualAllowance;
    }
    public String getAllowanceId() {
        return allowanceId;
    }
    public double getJobAllowance() {
        return jobAllowance;
    }
    public double getProjectAllowance() {
        return projectAllowance;
    }
    public double getAnnualAllowance() {
        return annualAllowance;
    }
    public void setAllowanceId(String allowanceId) {
        this.allowanceId = allowanceId;
    }
    public void setJobAllowance(double jobAllowance) {
        this.jobAllowance = jobAllowance;
    }
    public void setProjectAllowance(double projectAllowance) {
        this.projectAllowance = projectAllowance;
    }
    public void setAnnualAllowance(double annualAllowance) {
        this.annualAllowance = annualAllowance;
    }
    public double getTotalAllowance() {
        return jobAllowance + projectAllowance + annualAllowance;
    }
    public void displayAllowance() {
        System.out.println("Allowance Id: " + allowanceId);
        System.out.println("Job Allowance: " + jobAllowance);
        System.out.println("Project allowance: " + projectAllowance);
        System.out.println("Annual allowance: " + annualAllowance);
        System.out.println("Total allowance: " + getTotalAllowance());
    }
}
