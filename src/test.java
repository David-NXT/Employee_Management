import java.util.*;
public class test {
    public static void main(String[] args) {
        ListEmployee l= new ListEmployee();
        ListAllowance la= new ListAllowance();
        ListTimeSheet lt = new ListTimeSheet();

        l.readFromFile();
        la.readFromFile();
        lt.ReadFromFile();

        ManageSalary m = new ManageSalary(l,la,lt);
        m.showMenu();
    }
}
