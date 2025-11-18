import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ListContract implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private transient final Scanner sc = new Scanner(System.in);
    private Contract[] data;
    private int n;

    public ListContract() {
        data = new Contract[0];
        n = 0;
    }

    public ListContract(ListContract a) {
        data = a.data;
        n = a.n;
    }

    public void get() {
        int oldn = n;
        System.out.println("Number of contract:");
        n = sc.nextInt();sc.nextLine();
        data = Arrays.copyOf(data,oldn + n);
        for (int i = 0;i < n;i++) {
            System.out.println("ID:");
            String id = sc.nextLine();
            data[i].get(id);
        }
    }

    public void put() {
        for (int i = 0;i < n;i++) {
            data[i].put();
        }
    }

    public void add(Contract a) {
        data = Arrays.copyOf(data,n + 1);
        data[n] = a;
        n++;
    }

    public void delete(String id) {
        int locate = isInList(id);
        if (locate == -1) {
            System.out.println("This ID Does Not Exist!");
            return;
        }

        for (int i = locate;i < n - 1;i++) {
            data[i] = data[i + 1];
        }
        data = Arrays.copyOf(data,n - 1);
        n--;
    }

    public int isInList(String id) {
        for (int i = 0;i < n;i++) {
            if (id.equalsIgnoreCase(data[i].getEmpID())) {
                return i;
            }
        }
        return -1;
    }

    public Contract find(String id) {
        for (int i = 0;i < n;i++) {
            if (id.equalsIgnoreCase(data[i].getEmpID())) return data[i];
        }
        return null;
    }

    public void writeToFile() {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir,"data_Employee_Contract");
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
        File file = new File(basepath + File.separator + "data" + File.separator + "data_Employee_Contract");

        if (!file.exists()) {
            System.out.println("file is not exist");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListContract a = (ListContract) ois.readObject();
            this.data = a.data;
            this.n = a.n;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Contract[] getData() {
        return data;
    }

    public int getN() {
        return n;
    }

    public void setData(Contract[] data) {
        this.data = data;
    }

    public void setN(int n) {
        this.n = n;
    }
}
