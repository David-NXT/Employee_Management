public class Main {
    public static void main(String[] args) {
 Travis-feature
        ListOffcialEmployee a = new ListOffcialEmployee();
        a.get();
        a.writeToFile();
        a.readFromFile();
        a.put();

        Salary_menu sm = new Salary_menu();
        sm.showMenu();

        EmployeeManagerMenu a = new EmployeeManagerMenu();
        a.menu();
 master
    }

}