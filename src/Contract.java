import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public class Contract implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private transient final Scanner sc = new Scanner(System.in);
    private String empID;
    private String day;
    private String content;

    public Contract(Contract a) {
        empID = a.empID;
        day = a.day;
        content = a.content;
    }

    public Contract(String empID, String day, String content) {
        this.empID = empID;
        this.day = day;
        this.content = content;
    }

    public void get(String id) {
        empID = id;
        System.out.println("Sign Day:");sc.nextLine();
        content = "An employment contract is an agreement between the employer and the employee that defines\n" +
                " the required job duties, salary, working hours and rest periods, the rights and obligations of\n" +
                " each party, working conditions, the contract term, benefits, insurance provisions, workplace\n" +
                " safety rules, conditions for contract termination, and responsibilities for compensation in case\n" +
                " of violations; the contract becomes effective when both parties sign and agree to fully comply with\n" +
                " all stated terms.";
    }

    public void put() {
        System.out.println("ID: " + empID);
        System.out.println("Sign Day: " + day);
        System.out.println(content);
    }

    public String getDay() {
        return day;
    }

    public String getEmpID() {
        return empID;
    }

    public String getContent() {
        return content;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
