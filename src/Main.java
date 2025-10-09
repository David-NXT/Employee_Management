public class Main {
    public static void main(String[] args) {
        ListProject a = new ListProject();
        ListOffcialEmployee b = new ListOffcialEmployee();
        a.readFromFile();
        a.put(b);
    }
}