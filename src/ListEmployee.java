import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class ListEmployee extends FileIO<ListEmployee> {
    @Serial
    private static final long serialVersionUID = 1L;
    private transient final Scanner sc = new Scanner(System.in);
    private Employee[] data;
    private int n;

    public ListEmployee(ListEmployee a){
        data = a.data;
        n = a.n;
    }

    public ListEmployee(){
        data = new Employee[0];
        n = 0;
    }

    public ListEmployee(Employee[] data) {
        this.data = data;
        this.n = data.length;
    }

    public void get(){
        int num,le = 0;
        if (n != 0) le = n;//luu gia tri cu cua do dai mang
        System.out.print("Number of employee: ");num = sc.nextInt();
        sc.nextLine();//loai bo \n thua trong buffer
        data = Arrays.copyOf(data,n + num);
        n += num;

        for(int i = le;i < le + num;i++){
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
                String newid = getIdAutoForST();
                data[i].get(newid);
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
        n++;
    }

    public void add(SeasonalStaff a) {
        data = Arrays.copyOf(data,data.length + 1);
        data[data.length - 1] = a;
        n++;
    }

    public String getIdAutoForOE() {//OE = Official Employee
        int baseid = 1000;

        for (Employee datum : data) {
            if (datum instanceof OfficialEmployee) {
                int currentId = Integer.parseInt(datum.getId());
                if (currentId > baseid) {
                    baseid = currentId;
                }
            }
        }

        return Integer.toString(baseid);
    }

    public String getIdAutoForST() {//ST = Seasonal Staff
        int baseid = 2000;

        for (Employee datum : data) {
            if (datum instanceof SeasonalStaff) {
                int currentId = Integer.parseInt(datum.getId());
                if (currentId > baseid) {
                    baseid = currentId;
                }
            }
        }

        return Integer.toString(baseid + 1);
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
        n--;
    }

    public void writeToFile() {
        write("data_Employee");
    }

    public void readFromFile() {
        ListEmployee a = FileIO.read("data_Employee");
        if (a != null) {
            // Sao chép dữ liệu từ đối tượng được đọc sang đối tượng hiện tại
            this.data = a.data;
            this.n = a.n;
            System.out.println("Data loaded successfully!");
        } else {
            System.out.println("Could not load data. Starting with empty list.");
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

    public ListEmployee findByName(String name) {
        if (data == null) return null;
        ListEmployee result = new ListEmployee();

        for (Employee e: data) {
            if (e.getName().contains(name)) {
                if (e instanceof OfficialEmployee) {
                    result.add((OfficialEmployee) e);
                } else result.add((SeasonalStaff) e);
            }
        }

        return result;
    }

    public int[] statistic() {
        int[] result = new int[2];
        for (Employee e: data) {
            if (e instanceof OfficialEmployee) result[0]++;
            else result[1]++;
        }

        return result;
    }

    public Employee[] getData(){
        return data;
    }

    public int getN() {
        return n;
    }

    public void setData(Employee[] a){
        data = a;
    }

    public void setN(int n) {
        this.n = n;
    }
}
