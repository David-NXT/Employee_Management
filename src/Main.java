public class Main {
    public static void main(String[] args) {
        ListOffcialEmployee a = new ListOffcialEmployee();
        a.get();
        a.put();
        ListProject b = new ListProject();
        b.get();
        b.put(a);
        Project c = b.getData()[0];
        System.out.println(c.projectAllowanceSalary(a.getData()[0]));
    }
}