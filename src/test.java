import java.util.*;
public class test {
    public static void main(String[] args) {
        // Khởi tạo danh sách nhân viên
        ListEmployee employeeList = new ListEmployee();
        employeeList.readFromFile(); // Đọc từ file nếu có

        // Khởi tạo danh sách phụ cấp
        ListAllowance allowanceList = new ListAllowance();
        allowanceList.readFromFile(); // Đọc từ file nếu có

        // Khởi tạo danh sách chấm công
        ListTimeSheet timeSheetList = new ListTimeSheet();
        timeSheetList.ReadFromFile(); // Đọc từ file nếu có

        // Gọi menu tính lương
        Salary_menu salaryMenu = new Salary_menu(employeeList, allowanceList, timeSheetList);
        salaryMenu.showMenu();
    }
}


