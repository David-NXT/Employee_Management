import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public class SeniorityAllowanceRule implements Serializable {
    private final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private int age;
    private double allowancerate;

    public SeniorityAllowanceRule () {
        id = "";
        age = 0;
        allowancerate = 0.0;
    }

    public SeniorityAllowanceRule (String id, int age, double allowancerate) {
        this.id = id;
        this.age = age;
        this.allowancerate = allowancerate;
    }

    public SeniorityAllowanceRule(SeniorityAllowanceRule a) {
        id = a.id;
        age = a.age;
        allowancerate = a.allowancerate;
    }

    public void get() {
        System.out.println("ID:");id = sc.nextLine();
        System.out.println("Age:");age = sc.nextInt();
        System.out.println("Allowance Rate:");allowancerate = sc.nextDouble();
        sc.nextLine();
    }

    public void put() {
        System.out.printf("%s-10%d-5%f-15%n",id,age,allowancerate);
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public double getAllowancerate() {
        return allowancerate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAllowancerate(double allowancerate) {
        this.allowancerate = allowancerate;
    }
}
