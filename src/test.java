
public class test {
    public static void main(String[] args) {
       ListProject a = new ListProject();
       a.readFromFile();
       ListEmployee b = new ListEmployee();
       b.readFromFile();
       a.put(b);
       }
    }
