import java.util.*;
public class test {
    public static void main(String[] args) {
// Khởi tạo các danh sách
        ListEmployee listEmp = new ListEmployee();
        ListProject listPro = new ListProject();
        ListAllowance listAllow = new ListAllowance();
        ListPosition listPos = new ListPosition();
        ListSeniorityAllowanceRule listRule = new ListSeniorityAllowanceRule();
        ListAllowanceRule listAllowRule = new ListAllowanceRule();

        // Đọc dữ liệu từ file (nếu có)
        listEmp.readFromFile();
        listPro.readFromFile();
        listAllow.readFromFile();
        listPos.ReadFromFile();
        listRule.readFromFile();
        listAllowRule.readFromFile();

        // Tạo menu và chạy
        Allowance_Menu menu = new Allowance_Menu(
                listEmp,
                listPro,
                listAllow,
                listPos,
                listRule,
                listAllowRule
        );

        menu.run();
    }
}
