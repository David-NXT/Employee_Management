import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class ListEmployee implements Serializable{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private Employee[] data;

    public ListEmployee(ListEmployee a){
        data = a.data;
    }

    public ListEmployee(){
        data = new Employee[0];
    }
    public void get(){
        int n;
        System.out.print("Number of employee: ");n = sc.nextInt();
        sc.nextLine();//loai bo \n thua trong buffer
        data = Arrays.copyOf(data,n);

        for(int i = 0;i < n;i++){
            System.out.println("1. Official Employee");
            System.out.println("2. Seasonal Staff");
            int mode = sc.nextInt();
            if (mode == 1) {
                data[i] = new OfficialEmployee();
                String newid = getIdAutoForOE();
                data[i].get(newid);
                System.out.println();
            } else if (mode == 2) {
                data[i] = new SeasonalStaff();
                data[i].get();
                System.out.println();
            } else {
                System.out.println("Invalid! Please choose again");
                i--;
            }
        }
    }

    public void getOfficialEmployee() {
        int n;
        System.out.println("Number of new Offcial Employee: ");n = sc.nextInt();
        sc.nextLine();

        data = Arrays.copyOf(data,n);

        for (int i = 0;i < n;i++) {
            data[i] = new OfficialEmployee();
            data[i].get();
            System.out.println();
        }
    }

    public void getSeasonalStaff() {
        int n;
        System.out.println("Number of new Seasonal Staff: ");n = sc.nextInt();
        sc.nextLine();

        data = Arrays.copyOf(data,n);

        for (int i = 0;i < n;i++) {
            data[i] = new SeasonalStaff();
            data[i].get();
            System.out.println();
        }
    }

    public void put(){
        System.out.println("Number of employee: " + data.length);

        for(int i = 0;i < data.length;i++){
            data[i].put();
            System.out.println();
        }
    }

    public void putOffcialEmployee() {
        System.out.println("Number Of Offcial Employee: " + numOffcialEmployee());

        System.out.printf("%-10s %-20s %-10s %-8s %-50s %-12s %-20s %-17s %-15s %-15s%n","ID","Surname","Name","Gender","Address","Phone Number","Email","Start Working Day","Basic Salary","Salary Allowance");

        for(int i = 0;i < data.length;i++) {
            if (data[i] instanceof OfficialEmployee) {
                data[i].put();
            }
        }
    }

    public void putSeasonalStaff() {
        System.out.println("Number Of Seasonal Staff: " + numSeasonalStaff());

        System.out.printf("%-10s %-20s %-10s %-8s %-50s %-12s %-20s %-17s %-15s %-15s%n","ID","Surname","Name","Gender","Address","Phone Number","Email","Start Working Day","Hourly Salary","Work Time");


        for (int i = 0;i < data.length;i++) {
            if (data[i] instanceof SeasonalStaff) {
                data[i].put();
            }
        }
    }

    public int numOffcialEmployee() {
        int d = 0;
        for (int i = 0;i < data.length;i++) {
            if (data[i] instanceof OfficialEmployee) d++;
        }
        return d;
    }

    public int numSeasonalStaff() {
        int d = 0;
        for (int i = 0;i < data.length;i++) {
            if (data[i] instanceof SeasonalStaff) d++;
        }
        return d;
    }

    public void add(OfficialEmployee a) {
        data = Arrays.copyOf(data,data.length + 1);
        data[data.length - 1] = a;
        quickSort(0, data.length - 1);
    }

    public void add(SeasonalStaff a) {
        data = Arrays.copyOf(data,data.length + 1);
        data[data.length - 1] = a;
        quickSort(0, data.length - 1);
    }

    public String getIdAutoForOE() {//OE = Official Employee
        int baseid = 1000;

        for (int i = 0;i < data.length;i++) {//kiem tra tu dau mang de phong co id da bi xoa va chua co ai dung
            if (data[i] instanceof OfficialEmployee) {
                if (Integer.toString(baseid).equals(data[i].getId())) {
                    baseid++;//neu id goc bang id cua nhan vien thi tang them 1
                    i--;
                }
            }
        }
        return Integer.toString(baseid);
    }

    public String getIdAutoForST() {//ST = Seasonal Staff
        int baseid = 2000;

        for (int i = 0;i < data.length;i++) {
            if (Integer.toString(baseid).equals(data[i].getId())) {
                baseid++;//neu id goc bang id cua nhan vien thi tang them 1
                i--;
            }
        }
        return Integer.toString(baseid);
    }

    public OfficialEmployee getOfficialEmployee(String id) {
        for (int i = 0;i < data.length;i++) {
            if (data[i].getId().equals(id) && data[i] instanceof OfficialEmployee) {
                return (OfficialEmployee) data[i];
            }
        }
        return null;
    }

    public boolean isOfficialEmployee(String id) {
        for (int i = 0;i < data.length;i++) {
            if (data[i].getId().equals(id) && data[i] instanceof OfficialEmployee) {
                return true;
            }
        }
        return false;
    }

    public int isEmployee(String id) {//check if there is an employee with that ID
        int locate = -1;
        for (int i = 0;i < data.length;i++) {
            if (data[i].getId().equals(id)) {
                locate = i;
                break;
            }
        }
        if (locate == -1) {
            return -1;
        }

        return locate;
    }

    public void delete(String id) {
        int locate = isEmployee(id);
        if (locate == -1) {
            System.out.println("This Employee Does Not Exist");
            return;
        }

        for (int i = locate;i < data.length - 1;i++) {
            data[i] = data[i + 1];
        }

        data = Arrays.copyOf(data,data.length - 1);
    }

    public void writeToFile() {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir,"data_Employee");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
            oos.close();
            System.out.println("Saved!");
        }
        catch (IOException e) {
            System.out.println("File chua duoc ghi " + e);
        }
    }

    public void readFromFile() {
        String basepath = System.getProperty("user.dir");
        File file = new File(basepath + File.separator + "data" + File.separator + "data_Employee");

        if (!file.exists()) {
            System.out.println("file is not exist");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListEmployee a = (ListEmployee) ois.readObject();
            this.data = a.data;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //QuickSort
    public void quickSort (int l, int r) {
        if (l < r) {
            int idx = partition(l,r);
            quickSort(l,idx - 1);
            quickSort(idx + 1,r);
        }
    }

    private int partition (int l, int r) {
        int pivot = Integer.parseInt(data[r].getId());
        int i = l - 1;

        for (int j = l;j < r;j++) {
            if (Integer.parseInt(data[j].getId()) < pivot) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1,r);
        return i + 1;
    }

    private void swap(int i, int j) {
        Employee tmp = data[j];
        data[j] = data[i];
        data[i] = tmp;

    }
    // Trả về Employee nếu tìm thấy, ngược lại trả về null
    public Employee findById(String id) {
        if (data == null) return null;
        for (Employee e : data) {
            if (e != null && e.getId() != null && e.getId().equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;
    }


    public Employee[] getData(){
        return data;
    }

    public void setData(Employee[] a){
        data = a;
    }
}
