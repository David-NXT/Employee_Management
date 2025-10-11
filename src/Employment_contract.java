
import java.io.Serializable;
import java.util.Scanner;

public class Employment_contract extends Employee implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    private String contractId;
    private String contractType;
    private String daySign;
    private String dayEnd;
    private String reasonableSalary;
    private String provision;
    private Employee employee;

    public Employment_contract() {}

    public Employment_contract(String contractId, String contractType, String daySign,
                               String dayEnd, String reasonableSalary, String provision,
                               Employee employee) {
        this.contractId = contractId;
        this.contractType = contractType;
        this.daySign = daySign;
        this.dayEnd = dayEnd;
        this.reasonableSalary = reasonableSalary;
        this.provision = provision;
        this.employee = employee;
    }

    public Employment_contract(Employment_contract a) {
        this.contractId = a.contractId;
        this.contractType = a.contractType;
        this.daySign = a.daySign;
        this.dayEnd = a.dayEnd;
        this.reasonableSalary = a.reasonableSalary;
        this.provision = a.provision;
        this.employee = new Employee(a.employee);
    }

    public String getContractId() { return contractId; }
    public String getContractType() { return contractType; }
    public String getDaySign() { return daySign; }
    public String getDayEnd() { return dayEnd; }
    public String getReasonableSalary() { return reasonableSalary; }
    public String getProvision() { return provision; }

    public void setContractId(String contractId) { this.contractId = contractId; }
    public void setContractType(String contractType) { this.contractType = contractType; }
    public void setDaySign(String daySign) { this.daySign = daySign; }
    public void setDayEnd(String dayEnd) { this.dayEnd = dayEnd; }
    public void setReasonableSalary(String reasonableSalary) { this.reasonableSalary = reasonableSalary; }
    public void setProvision(String provision) { this.provision = provision; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public void inputContract() {
        System.out.print("Contract Id: ");
        contractId = sc.nextLine();
        System.out.print("Contract Type: ");
        contractType = sc.nextLine();
        System.out.print("Day sign: ");
        daySign = sc.nextLine();
        System.out.print("Day end: ");
        dayEnd = sc.nextLine();
        System.out.print("Salary: ");
        reasonableSalary = sc.nextLine();
        System.out.print("Provision: ");
        provision = sc.nextLine();

        System.out.println("\n--- Enter employee sign the contract ---");
        employee = new Employee();
        employee.getEmployee();
    }

    public void outputContract() {
        System.out.println("\n===== Imformation of contract =====");
        System.out.println("Contract Id: " + contractId);
        System.out.println("Contract Type: " + contractType);
        System.out.println("Day sign: " + daySign);
        System.out.println("Day end: " + dayEnd);
        System.out.println("Reasonable salary: " + reasonableSalary);
        System.out.println("Provision: " + provision);
        System.out.println("\n---Employee Imformation ---");
        employee.putEmployee();
    }
}
