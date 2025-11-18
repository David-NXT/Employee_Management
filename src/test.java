import java.util.*;
public class test {
    public static void main(String[] args) {
ListEmployee le = new ListEmployee();
ListAllowance la = new ListAllowance();
ListTimeSheetMonth lts = new ListTimeSheetMonth();

le.readFromFile();
la.readFromFile();
lts.readfromfile();


ManageSalary m = new ManageSalary(le,la,lts);
m.showMenu();


    }
}
