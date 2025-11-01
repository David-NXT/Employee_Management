import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ListAllowance implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private transient final Scanner sc = new Scanner(System.in);
    private Allowance[] data;
    private final String FILE_PATH = System.getProperty("user.dir") + File.separator + "data" + File.separator + "data_allowance.dat";

    public ListAllowance() {
        data = new Allowance[0];
        readFromFile();
    }

    public void get() {
        System.out.print("Number of new allowances: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            Allowance a = new Allowance(this);
            a.get();
            add(a);
        }
        writeToFile();
    }

    public void put() {
        if (data.length == 0) {
            System.out.println("No allowances to display.");
            return;
        }
        System.out.printf("%-10s %-15s %-15s %-15s %-15s%n",
                "ID", "Job", "Project", "Annual", "Total");
        for (Allowance allowance : data) {
            allowance.put();
        }
    }

    public void add(Allowance a) {
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length - 1] = a;
    }

    public void delete(String id) {
        int index = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i].getAllowanceId().equalsIgnoreCase(id)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Allowance ID not found!");
            return;
        }
        for (int i = index; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        data = Arrays.copyOf(data, data.length - 1);
        System.out.println("Deleted successfully!");
        writeToFile();
    }

    public String getIdAutoForAE() {
        int baseid = 1000;
        boolean exists;
        do {
            exists = false;
            for (Allowance a : data) {
                if (a.getAllowanceId().equals(String.valueOf(baseid))) {
                    exists = true;
                    baseid++;
                    break;
                }
            }
        } while (exists);
        return String.valueOf(baseid);
    }

    public void writeToFile() {
        try {
            File dir = new File(System.getProperty("user.dir") + File.separator + "data");
            if (!dir.exists()) dir.mkdir();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                oos.writeObject(data);
                System.out.println("Allowance data saved!");
            }
        } catch (IOException e) {
            System.out.println("Error writing allowance data: " + e.getMessage());
        }
    }

    public void readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            data = (Allowance[]) ois.readObject();
            System.out.println("Allowance data loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading allowance data: " + e.getMessage());
            data = new Allowance[0];
        }
    }

    public void showAllowanceByEmployeeId(String empId) {
        boolean found = false;
        for (Allowance a : data) {
            if (a.getEmployeeId() != null && a.getEmployeeId().equalsIgnoreCase(empId)) {
                System.out.println("Allowance for Employee ID: " + empId);
                System.out.printf("%-10s %-20s %-15.2f %-15.2f %-15.2f %-15s%n",
                        a.getAllowanceId(), a.getAllowanceName(),
                        a.getJobAllowance(), a.getProjectAllowance(),
                        a.getAnnualAllowance(), a.getDayMonthYear());
                System.out.println("---------------------------------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No allowance found for employee ID: " + empId);
        }
    }

    public Allowance[] getData() {
        return data;
    }

    public void setData(Allowance[] data) {
        this.data = data;
    }
}
