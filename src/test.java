import java.util.*;
public class test {
    public static void main(String[] args) {
        ListEmployee a1 = new ListEmployee();
        a1.readFromFile();
        ListAllowance a2 = new ListAllowance();
        a2.readFromFile();
        ListTimeSheetMonth a3 = new ListTimeSheetMonth();
        a3.readfromfile();

        a3.out();

        ManageSalary b = new ManageSalary(a1,a2,a3);
        b.showMenu();


        Statistic a = new Statistic();
        a.fullStatisticEmployeeByYear(2023,2025);
    }
}
