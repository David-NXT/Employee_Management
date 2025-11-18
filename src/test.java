import java.util.*;
public class test {
    public static void main(String[] args) {
ListEmployee le = new ListEmployee();
ListAllowance la = new ListAllowance();
ListTimeSheetMonth lts = new ListTimeSheetMonth();
ListSeasonalStaff ls = new ListSeasonalStaff();

le.readFromFile();
la.readFromFile();
lts.readfromfile();
lts.out();


ManageSalary m = new ManageSalary(le,la,lts);
m.showMenu();


    }
}
