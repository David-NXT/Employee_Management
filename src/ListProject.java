import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.io.Serializable;

public class ListProject implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private Project[] data;

    public ListProject() {
        data = new Project[0];
    }

    public ListProject(ListProject list) {
        data = list.data;
    }

    public void get() {
        int n;
        System.out.println("Nhap so luong du an: ");n = sc.nextInt();
        sc.nextLine();//xoa \n thua trong buffer
        data = Arrays.copyOf(data,n);
        for (int i = 0;i < data.length;i++) {
            data[i] = new Project();
            data[i].getProject();
        }
    }

    public void put(ListEmployee list) {
        System.out.printf("%-22s %-28s %-26s %-10s %-12s %-9s %-14s %n","Manager Allowancerate","Main Engineer Allowancerate","Participant Allowancerate","Project ID","Project Name","Start Day","Accomplish Day");
        for(Project a: data) {
            a.putProject(list);
        }
    }

    public void add() {
        Project a = new Project();
        a.getProject();

        data = Arrays.copyOf(data,data.length + 1);

        data[data.length - 1] = a;
    }

    public void add(Project newproject) {
        data = Arrays.copyOf(data,data.length + 1);
        data[data.length - 1] = newproject;
    }

    public void delete(String projectid) {
        int locate = -1;
        for (int i = 0;i < data.length;i++) {
            if (data[i].getProjectId().equals(projectid)){
                locate = i;
                break;
            }
        }

        if (locate == -1) {
            System.out.println("This Project Not in List.");
            return;
        }

        for (int i = locate;i < data.length - 1;i++) {
            data[i] = data[i + 1];
        }
        data = Arrays.copyOf(data,data.length - 1);
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
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Project[] getData() {
        return data;
    }

    public void setData(Project[] data) {
        this.data = data;
    }
}
