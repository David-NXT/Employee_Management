import java.util.Scanner;

public class OffcialEmployee extends Employee {
    private final Scanner sc = new Scanner(System.in);
    private double basicsalary;
    private double salaryallowance;

    public OffcialEmployee(){}

    public OffcialEmployee(OffcialEmployee a){
        super(a);
        basicsalary = a.basicsalary;
        salaryallowance = a.salaryallowance;
    }

    public OffcialEmployee(String id, String surname, String name, String gender, String address, String phonenumber, String email, String startworkingday, double basicsalary, double salaryallowance){
        super(id,surname,name,gender,address,phonenumber,email,startworkingday);
        this.basicsalary = basicsalary;
        this.salaryallowance = salaryallowance;
    }

    public void getOffcialEmployee(){
        super.getEmployee();
        System.out.println("Basic Salary: ");basicsalary = sc.nextDouble();
        System.out.println("Salary Allowance: ");salaryallowance = sc.nextDouble();
    }

    public void putOffcialEmployee(){
        super.putEmployee();
        System.out.println("Basic Salary: " + basicsalary);
        System.out.println("Salary Allowance: " + salaryallowance);
    }

    public boolean isEmployee(String employeeid) {
        return getId().equals(employeeid);
    }

    public double getBasicSalary(){
        return basicsalary;
    }

    public double getSalaryAllowance() {
        return salaryallowance;
    }

    public void setBasicSalary(double basicsalary){
        this.basicsalary = basicsalary;
    }

    public void setSalaryAllowance(double salaryallowance){
        this.salaryallowance = salaryallowance;
    }
}
