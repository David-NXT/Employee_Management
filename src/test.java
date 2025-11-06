import java.util.*;
public class test {
    public static void main(String[] args) {
       ListSeniorityAllowanceRule a = new ListSeniorityAllowanceRule();
       a.readFromFile();
       a.put();
       ListEmployee b = new ListEmployee();
       b.readFromFile();
       for (Employee tmp: b.getData()) {
           System.out.println(tmp.getStartWorkingDay());
           System.out.println(a.employeeHaveSeniorityAllowance(tmp.getId()));
           System.out.println();
       }
    }
}
