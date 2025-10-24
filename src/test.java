public class test {
    public static void main(String[] args) {
        listtimesheet a = new listtimesheet();
        a.enter();
        a.writeToFile();
        timesheet b = new timesheet();
        b.enter();
        a.add(b);
        a.writeToFile();
        a.readFromFile();
        a.out();
    }
}
