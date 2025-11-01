import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ListAllowanceRule implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private AllowanceRule[] data;
    private int n;

    public ListAllowanceRule() {
        data = new AllowanceRule[0];
        n = 0;
    }

    public ListAllowanceRule(ListAllowanceRule a) {
        data = a.data;
        n = a.n;
    }

    public void add(AllowanceRule a) {
        data = Arrays.copyOf(data,n + 1);
        data[n] = a;
        n++;
    }

    public void delete(String id) {
        int locate = -1;
        for (int i = 0;i < n;i++) {
            if (data[i].getId().equals(id)) locate = i;
        }
        if (locate == -1) {
            System.out.println("This ID Does Not Exit");
            return;
        }

        for (int i = locate;i < n - 1;i++) {
            data[i] = data[i + 1];
        }
        data = Arrays.copyOf(data,n - 1);
        n--;
    }

    public AllowanceRule find(String id) {
        for (int i = 0;i < n;i++) {
            if (data[i].getId().equals(id)) return data[i];
        }
        return null;
    }

    public void writeToFile() {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir,"data_Allowance_Rule.bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
            oos.close();
            System.out.println("Saved!");
        } catch (IOException e) {
            System.out.println("Error! " + e);
        }
    }

    public void readFromFile() {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");

        if (!dir.exists()) {
            System.out.println("File Did Not Exist!");
            return;
        }

        File file = new File(dir,"data_Allowance_Rule.bin");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListAllowanceRule a =(ListAllowanceRule) ois.readObject();
            data = a.data;
            n = a.n;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public AllowanceRule[] getData() {
        return data;
    }

    public int getN() {
        return n;
    }
}
