import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public class SeniorityAllowanceRule implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private int yearofwork;
    private double allowancerate;

    public SeniorityAllowanceRule () {
        id = "";
        yearofwork = 0;
        allowancerate = 0.0;
    }

    public SeniorityAllowanceRule (String id, int yearofwork, double allowancerate) {
        this.id = id;
        this.yearofwork = yearofwork;
        this.allowancerate = allowancerate;
    }

    public SeniorityAllowanceRule(SeniorityAllowanceRule a) {
        id = a.id;
        yearofwork = a.yearofwork;
        allowancerate = a.allowancerate;
    }

    public void get() {
        System.out.println("ID:");id = sc.nextLine();
        System.out.println("Year Of Work:");
        yearofwork = sc.nextInt();
        System.out.println("Allowance Rate:");allowancerate = sc.nextDouble();
        sc.nextLine();
    }

    public void put() {
        System.out.printf("%-10s %-5d %-15f%n",id, yearofwork,allowancerate);
    }

    public String getId() {
        return id;
    }

    public int getYearofwork() {
        return yearofwork;
    }

    public double getAllowancerate() {
        return allowancerate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setYearofwork(int yearofwork) {
        this.yearofwork = yearofwork;
    }

    public void setAllowancerate(double allowancerate) {
        this.allowancerate = allowancerate;
    }
}
