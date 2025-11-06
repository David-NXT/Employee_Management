import java.util.*;
public class test {
    public static void main(String[] args) {
     /*
      ListSeniorityAllowanceRule a = new ListSeniorityAllowanceRule();
       a.readFromFile();
       a.put();
       ListEmployee b = new ListEmployee();
       b.readFromFile();
       for (Employee tmp: b.getData()) {
           System.out.println(tmp.getStartWorkingDay());
           System.out.println(a.employeeHaveSeniorityAllowance(tmp.getId()));
           System.out.println();
       }

*/
                // Khởi tạo các danh sách
                ListEmployee listEmp = new ListEmployee();
                ListProject listProj = new ListProject();
                ListAllowance listAllow = new ListAllowance();
                ListPosition listPos = new ListPosition();
                ListSeniorityAllowanceRule listRule = new ListSeniorityAllowanceRule();
                ListAllowanceRule listAllowRule = new ListAllowanceRule();
                listEmp.readFromFile();
                listAllowRule.readFromFile();
                listProj.readFromFile();
                listPos.ReadFromFile();
                listAllow.readFromFile();
                listRule.readFromFile();

                // Tạo menu phụ cấp
                Allowance_Menu menu = new Allowance_Menu(
                        listEmp,
                        listProj,
                        listAllow,
                        listPos,
                        listRule,
                        listAllowRule
                );

                // Chạy menu
                menu.run();

    }
}
