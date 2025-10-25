public class test {
    public static void main(String[] args) {
        ListTimeSheet a = new ListTimeSheet();
        a.enter();
        a.writeToFile();
        TimeSheet b = new TimeSheet();
        b.enter();
        a.add(b);
        a.writeToFile();
        a.ReadFromFile();
        a.out();
    }
}
