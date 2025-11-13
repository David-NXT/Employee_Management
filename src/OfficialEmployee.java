import java.io.Serial;
import java.util.Scanner;
import java.io.Serializable;

public class OfficialEmployee extends Employee implements Serializable{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private double basicsalary;
    private double salaryallowance = 0.0;

    public OfficialEmployee(){}

    public OfficialEmployee(OfficialEmployee a){
        super(a);
        basicsalary = a.basicsalary;
        salaryallowance = a.salaryallowance;
    }

    public OfficialEmployee(String id, String surname, String name, String gender, String address, String phonenumber, String email, String startworkingday, double basicsalary, double salaryallowance, String positionId){
        super(id,surname,name,gender,address,phonenumber,email,startworkingday);
        this.basicsalary = basicsalary;
        this.salaryallowance = salaryallowance;
    }

    @Override
    public void get(){
        super.get();
        System.out.println("Basic Salary: ");basicsalary = sc.nextDouble();
    }

    @Override
    public void get(String id){
        super.get(id);
        int random = (int) (Math.random() * 3);
        switch (random) {
            case 0: {
                basicsalary = 300;
                break;
            }
            case 1: {
                basicsalary = 350;
                break;
            }
            case 2: {
                basicsalary = 500;
                break;
            }
        }
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
