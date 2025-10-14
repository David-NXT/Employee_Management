public class Main {
    public static void main(String[] args) {
        ListOffcialEmployee a = new ListOffcialEmployee();
        a.get();
        a.writeToFile();
        a.readFromFile();
        a.put();

        Salary_menu sm = new Salary_menu();
        sm.showMenu();
    }
}