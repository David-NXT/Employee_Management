import java.util.*;
public class test {
    public static void main(String[] args) {
ListEmployee le = new ListEmployee();
ListAllowance la =new ListAllowance();
ListTimeSheetMonth lt= new ListTimeSheetMonth();

le.readFromFile();
la.readFromFile();
lt.readfromfile();

ManageSalary m =new ManageSalary(le,la,lt);
m.showMenu();
    }
}
