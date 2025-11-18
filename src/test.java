import java.util.*;
public class test {
    public static void main(String[] args) {
ListEmployee le = new ListEmployee();
ListAllYear all = new ListAllYear();
ListAllowance la = new ListAllowance();
le.readFromFile();
la.readFromFile();
all.readfromfile();
le.put();
/*
ManageSalary m = new ManageSalary(le,la,all);
m.showMenu();
*/
    }
}
