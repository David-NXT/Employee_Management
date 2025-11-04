import java.util.*;
public class test {
    public static void main(String[] args) {
        ListEmployee listEmp = new ListEmployee();
        ListPosition listPos = new ListPosition();
        ListProject listPro = new ListProject();
        ListSeniorityAllowanceRule listRule = new ListSeniorityAllowanceRule();
        ListAllowance listAllow = new ListAllowance();
        listEmp.readFromFile();
        listPro.readFromFile();
        listAllow.readFromFile();
        listAllow.generateAllowances(listEmp,listPro);

        Allowance_Menu menu = new Allowance_Menu( listEmp,listPro,listAllow, listPos, listRule);
        menu.run();
    }
}
