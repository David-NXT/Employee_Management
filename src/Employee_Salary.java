import java.util.Scanner;
import java.io.Serializable;
public class Employee_Salary extends Employee implements Serializable {
    private final Scanner sc= new Scanner(System.in);
    private double baseSalary;
    private String salaryId;
    private double allowance; // phụ cấp
    private double bonus; // thưởng
    private double deductions; // khấu trừ
    private double totalSalary;

    public Employee_Salary(){}
    public Employee_Salary(Employee a) {
        super(a);
        calculateAutoSalary(a);
        this.totalSalary = calculateTotal();
    }
    // tinh tong luong thuc nhan
    public double calculateTotal() {
        return baseSalary + allowance + bonus - deductions;
    }
    private void calculateAutoSalary(Employee a) {
        if(a instanceof OffcialEmployee offcialEm){
            this.baseSalary=offcialEm.getBasicSalary();
            this.allowance=offcialEm.getSalaryAllowance();

            this.bonus=baseSalary *0.1;
            this.deductions=baseSalary*0.005;
        } else if (a instanceof SeasonalStaff seaEmp) {
            this.baseSalary=seaEmp.getHourlySalary() * seaEmp.getWorkTime();
            this.allowance=0;
            this.bonus=0;
            this.deductions=0;
        } else {
            this.baseSalary=0;
            this.allowance=0;
            this.bonus=0;
            this.deductions=0;
        }
    }
    public void displaySalaryInfo(){
        System.out.println("ID: "+getId());
        System.out.println("Name: "+getSurname()+" "+getName());
        System.out.println("BaseSalry: "+baseSalary);
        System.out.println("Allowance: "+allowance);
        System.out.println("Bonus: "+bonus);
        System.out.println("Deductions: "+deductions);
        System.out.println("The totalSalary: "+totalSalary);
        System.out.println("------------------------");
    }
}
