import java.util.Scanner;

public class AllowanceRule {
    private transient final Scanner sc = new Scanner(System.in);
    private String id;
    private String name;
    private double allowancerate;

    public AllowanceRule(String id, String name, double allowancerate) {
        this.id = id;
        this.name = name;
        this.allowancerate = allowancerate;
    }

    public AllowanceRule(AllowanceRule a) {
        id = a.id;
        name = a.name;
        allowancerate = a.allowancerate;
    }

    public void get() {
        System.out.println("ID: ");id = sc.nextLine();
        System.out.println("Name Allowance: ");name = sc.nextLine();
        System.out.println("Rate: ");allowancerate = sc.nextDouble();
    }

    public void put() {
        System.out.printf("%s-10%s-15%f-15%n",id,name,allowancerate);
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
}
