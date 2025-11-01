import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public class AllowanceRule implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String day;// dd  mm
    private double allowancerate;

    public AllowanceRule(String id, String name, String day, double allowancerate) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.allowancerate = allowancerate;
    }

    public AllowanceRule(AllowanceRule a) {
        id = a.id;
        name = a.name;
        day = a.day;
        allowancerate = a.allowancerate;
    }

    public void get() {
        System.out.println("ID: ");id = sc.nextLine();
        System.out.println("Name Allowance: ");name = sc.nextLine();
        System.out.println("Day: ");day = sc.nextLine();
        System.out.println("Rate: ");allowancerate = sc.nextDouble();
        sc.nextLine();
    }

    public void put() {
        System.out.printf("%s-10%s-15%s-15%f-15%n",id,name,day,allowancerate);
    }

    public String getDay() {
        return day;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAllowancerate() {
        return allowancerate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setAllowancerate(double allowancerate) {
        this.allowancerate = allowancerate;
    }
}
