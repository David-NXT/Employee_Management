import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class ListSeasonalStaff implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    private SeasonalStaff[] data;
    private int n;//number of data

    public ListSeasonalStaff() {
        data = new SeasonalStaff[0];
        n = 0;
    }

    public ListSeasonalStaff(ListSeasonalStaff a) {
        data = a.data;
        n = a.data.length;
    }

    public void get() {
        System.out.print("Number of Seasonal Staff: ");n = sc.nextInt();
        sc.nextLine();//delete \n in buffer

        data = Arrays.copyOf(data,n);//cap them n phan tu

        for(int i = 0;i < n;i++){
            data[i] = new SeasonalStaff();
            data[i].getSeasonalStaff();
        }
    }

    public void put() {
        System.out.println("Number of Seasonal Staff: " + n);
        for(int i = 0;i < n;i++){
            data[i].putSeasonalStaff();
        }
    }

    public void add() {
        SeasonalStaff a = new SeasonalStaff();
        a.getSeasonalStaff();

        data = Arrays.copyOf(data,data.length + 1);
        data[data.length - 1] = a;
    }

    public void add(SeasonalStaff newseasonalstaff) {
        data = Arrays.copyOf(data,data.length + 1);
        data[data.length - 1] = newseasonalstaff;
    }

    public void delete(String seasonalstaffid) {
        int locate = -1;
        for (int i = 0;i < data.length;i++) {
            if (data[i].getId().equals(seasonalstaffid)) {//tim vi tri cua nhan vien can tim
                locate = i;
                break;
            }
        }
        if (locate == -1) {
            System.out.println("This Employee Not Exist in List.");
            return;//neu khong tim thay -> ket thuc ctr
        }

        for (int i = locate;i < data.length - 1;i++) {//don cac doi tuong vao vi tri trong
            data[i] = data[i + 1];
        }

        data = Arrays.copyOf(data,data.length - 1);//xoa vi tri cuoi
    }

    public void writeToFile() {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir,"data_SeasonalStaff");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        }
        catch (IOException e) {
            System.out.println("khong the ghi file " + e);
        }
    }

    public void readFromFile() {
        String basepath = System.getProperty("user.dir");
        File file = new File(basepath + File.separator + "data" + File.separator + "data_SeasonalStaff");

        if (!file.exists()) {
            System.out.println("file khong ton tai");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListSeasonalStaff a = (ListSeasonalStaff) ois.readObject();
            this.data = a.data;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SeasonalStaff[] getData() {
        return data;
    }

    public void setData(SeasonalStaff[] data) {
        this.data = data;
    }
}
