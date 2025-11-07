import java.util.*;
public class test {
    public static void main(String[] args) {
        ListProject listProject = new ListProject();
        ListEmployee listEmployee = new ListEmployee();

        listProject.readFromFile();
        listEmployee.readFromFile(); // nếu bạn có file nhân viên

        listProject.put(listEmployee);
    }
}


