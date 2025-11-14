import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ListSeniorityAllowanceRule implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 6093134262717247092L;
    private SeniorityAllowanceRule[] data;
    private int n;

    public ListSeniorityAllowanceRule() {
        data = new SeniorityAllowanceRule[0];
        n = 0;
    }

    public ListSeniorityAllowanceRule(ListSeniorityAllowanceRule a) {
        data = a.data;
        n = a.n;
    }

    public void put() {
        System.out.printf("%-10s %-5s %-15s%n","id", "yearofwork","allowancerate");
        for (int i = 0;i < n;i++) {
            data[i].put();
        }
    }

    public void add(SeniorityAllowanceRule a) {
        data = Arrays.copyOf(data,n + 1);
        data[n] = a;
        n++;
    }

    public void delete(String id) {
        int locate = -1;
        for (int i = 0;i < n;i++) {
            if (data[i].getId().equals(id)) {
                locate = i;
                break;
            }
        }

        if (locate == -1) {
            System.out.println("This ID Does Not Exist!");
            return;
        }

        for (int i = locate;i < n - 1;i++)
            data[i] = data[i + 1];
        data = Arrays.copyOf(data,n - 1);
        n--;
    }

    public double employeeHaveSeniorityAllowance(String id) {
        ListEmployee employee = new ListEmployee();
        employee.readFromFile();
        int locate = employee.isEmployee(id);
        if (locate == -1) {
            return 0;//neu khong tim thay nhan vien thi muc phu cap cua thanh nien do bang 0
        }

        int yearofwork = LocalDate.now().getYear() - Integer.parseInt(employee.getData()[locate].getStartWorkingDay().substring(6,10));
        for (int i = n - 1;i >= 0;i--) {// mang duoc sap xep tu be den lon nen duyet tu cuoi mang de tim ra muc phu cap lon nhat
            if (yearofwork >= data[i].getYearofwork()) return data[i].getAllowancerate();
        }
        return 0;// neu chay het mang ma khong co thi tra ve 0
    }

    public void writeToFile() {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir,"data_Seniority_Allowance_Rule.bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
            oos.close();
            System.out.println("Save!");
        } catch (IOException e) {
            System.out.println("Error! " + e);
        }
    }

    public void readFromFile() {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");

        if (!dir.exists()) {
            System.out.println("File Does Not Exist!");
            return;
        }

        File file = new File(dir,"data_Seniority_Allowance_Rule.bin");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListSeniorityAllowanceRule a = (ListSeniorityAllowanceRule) ois.readObject();
            data = a.data;
            n = a.n;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public SeniorityAllowanceRule findByYear(int years) {
        for (SeniorityAllowanceRule r : data) {
            if (years >= r.getYearofwork())
                return r;
        }
        return null;
    }
    public SeniorityAllowanceRule findYear(int years) {
        for (int i = n - 1; i >= 0; i--) {
            if (years >= data[i].getYearofwork()) {
                return data[i]; // Trả về mức cao nhất phù hợp
            }
        }
        return null;
    }

    public SeniorityAllowanceRule[] getData() {
        return data;
    }

    public int getN() {
        return n;
    }

    public void setData(SeniorityAllowanceRule[] data) {
        this.data = data;
    }

    public void setN(int n) {
        this.n = n;
    }
}
