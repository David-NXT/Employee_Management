import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.io.Serializable;

public class ListProject implements Serializable {
    private final Scanner sc = new Scanner(System.in);
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

    public void put(ListOffcialEmployee list) {
        for(Project a: data) {
            a.putProject(list);
        }
    }

    public void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/data_project.bin"))) {
            oos.writeObject(this);
        }
        catch (IOException e) {
            System.out.println("loi ghi file " + e);
        }
    }

    public void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/data_project.bin"))) {
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
