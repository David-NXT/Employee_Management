import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.io.Serializable;

public class ListProject implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private Project[] data;
    private int n;

    public ListProject() {
        data = new Project[0];
        n = 0;
    }

    public ListProject(ListProject list) {
        data = list.data;
        n = list.n;
    }

    public void get() {
        System.out.println("Nhap so luong du an: ");n = sc.nextInt();
        sc.nextLine();//xoa \n thua trong buffer
        data = Arrays.copyOf(data,n);
        for (int i = 0;i < data.length;i++) {
            data[i] = new Project();
            data[i].getProject();
        }
    }

    public void add() {
        Project a = new Project();
        a.getProject();

        data = Arrays.copyOf(data,n + 1);
        data[n] = a;
        n++;
    }

    public void add(Project newproject) {
        data = Arrays.copyOf(data,n + 1);
        data[n] = newproject;
        n++;
    }

    public void delete(String projectid) {
        int locate = -1;
        for (int i = 0;i < n;i++) {
            if (data[i].getProjectId().equals(projectid)){
                locate = i;
                break;
            }
        }

        if (locate == -1) {
            System.out.println("This Project Not in List.");
            return;
        }

        n--;
        for (int i = locate;i < n;i++) {
            data[i] = data[i + 1];
        }
        data = Arrays.copyOf(data,n);
    }

    public void writeToFile() {
        String basepath = System.getProperty("user.dir");//lay duong dan goc cua may tinh
        File dir = new File(basepath + File.separator + "data");

        if (!dir.exists()) {
            dir.mkdir();//neu folder chua ton tai -> tao folder
        }

        File file = new File(dir,"data_project.bin");//tao duong dan dung cho file can tao
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
            oos.close();
            System.out.println("Saved!");
        }
        catch (IOException e) {
            System.out.println("loi ghi file " + e);
        }
    }

    public void readFromFile() {
        String basepath = System.getProperty("user.dir");//lay duong dan goc tu may tinh
        File file = new File(basepath + File.separator + "data" + File.separator + "data_project.bin");

        if (!file.exists()) {
            System.out.println("file khong ton tai");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListProject a = (ListProject) ois.readObject();
            this.data = a.data;
            this.n = a.n;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Project find(String projectId) {
        for (Project p : data ) {
            if (p.getProjectId().equalsIgnoreCase(projectId)) {
                return p;
            }
        }
        return null;
    }

    public ListProject findByName(String nameproj) {
        ListProject result = new ListProject();

        for (Project p: data) {
            if (p.getProjectName().contains(nameproj)) result.add(p);
        }

        return result;
    }

    public int[] statistic() {
        int[] result = new int[n];

        for (int i = 0;i < n;i++) {
            result[i] += data[i].getEmployeeId().length;
            if (data[i].getMainengineerId() != null && !data[i].getMainengineerId().equalsIgnoreCase("")) result[i]++;
            if (data[i].getManagerId() != null && !data[i].getManagerId().equalsIgnoreCase("")) result[i]++;
        }
        return result;
    }


    public Project[] getData() {
        return data;
    }

    public int getN() {
        return n;
    }

    public void setData(Project[] data) {
        this.data = data;
    }

    public void setN(int n) {
        this.n = n;
    }
}
