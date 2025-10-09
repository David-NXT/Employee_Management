
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
        System.out.print("Nhập mã hợp đồng: ");
        contractId = sc.nextLine();
        System.out.print("Nhập loại hợp đồng: ");
        contractType = sc.nextLine();
        System.out.print("Nhập ngày ký: ");
        daySign = sc.nextLine();
        System.out.print("Nhập ngày hết hạn: ");
        dayEnd = sc.nextLine();
        System.out.print("Nhập lương: ");
        reasonableSalary = sc.nextLine();
        System.out.print("Nhập điều khoản: ");
        provision = sc.nextLine();

        System.out.println("\n--- Nhập thông tin nhân viên ký hợp đồng ---");
        employee = new Employee();
        employee.getEmployee();
    }

    public void outputContract() {
        System.out.println("\n===== THÔNG TIN HỢP ĐỒNG LAO ĐỘNG =====");
        System.out.println("Mã hợp đồng: " + contractId);
        System.out.println("Loại hợp đồng: " + contractType);
        System.out.println("Ngày ký: " + daySign);
        System.out.println("Ngày kết thúc: " + dayEnd);
        System.out.println("Lương thỏa đáng: " + reasonableSalary);
        System.out.println("Điều khoản: " + provision);
        System.out.println("\n--- Thông tin nhân viên ---");
        employee.putEmployee();
    }
}
