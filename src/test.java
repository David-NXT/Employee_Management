import java.util.*;
public class test {
    public static void main(String[] args) {
// Khởi tạo các danh sách
        ListEmployee le = new ListEmployee();
        ListAllowance la= new ListAllowance();
        ListTimeSheet lt = new ListTimeSheet();

        le.readFromFile();
        la.readFromFile();
        lt.ReadFromFile();
        ManageSalary mn = new ManageSalary(le,la,lt);
        mn.showMenu();

    }
}
