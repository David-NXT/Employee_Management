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
        ListTimeSheetMonth lts = new ListTimeSheetMonth();
        ListAllYear allYearList = new ListAllYear();
        allYearList.readfromfile();

        // Đọc dữ liệu từ file (nếu có)
        listEmp.readFromFile();
        listPro.readFromFile();
        listAllow.readFromFile();
        listPos.readfromfile();
        listRule.readFromFile();
        listAllowRule.readFromFile();
allYearList.out();

    }
}
