import java.util.Scanner;
public class test {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        ListAllowance l = new ListAllowance();
        l.get();
        l.put();
        String empId=sc.nextLine();
        l.showAllowanceByEmployeeId(empId);
    }
}
