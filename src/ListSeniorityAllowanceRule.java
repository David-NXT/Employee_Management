import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ListSeniorityAllowanceRule implements Serializable {
    private final Scanner sc = new Scanner(System.in);
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
