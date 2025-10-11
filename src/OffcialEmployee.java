import java.io.Serial;
import java.util.Scanner;
import java.io.Serializable;

public class OffcialEmployee extends Employee implements Serializable{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
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

    @Override
    public void get(){
        super.get();
        System.out.println("Basic Salary: ");basicsalary = sc.nextDouble();
        System.out.println("Salary Allowance: ");salaryallowance = sc.nextDouble();
    }

    @Override
    public void put(){
        super.put();
        System.out.printf("%-15.2f %-15.2f %n",basicsalary,salaryallowance);
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
