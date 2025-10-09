import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class ListOffcialEmployee implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    private OffcialEmployee[] data;
    private int n;//number of data

    public ListOffcialEmployee(){
        data = new OffcialEmployee[0];
        n = 0;
    }

    public ListOffcialEmployee(ListOffcialEmployee a){
        data = a.data;
        n = a.data.length;
    }

    public void get(){
        System.out.print("Number of Offcial Employee: ");n = sc.nextInt();
        sc.nextLine();//delete \n in buffer

        data = Arrays.copyOf(data,n);//cap them n phan tu cho mang data

        for(int i = 0;i < n;i++){
            data[i] = new OffcialEmployee();//create new object each i of data
            data[i].getOffcialEmployee();
        }
    }

    public void put(){
        System.out.println("Number of Offcial Employee: " + n);
        for(int i = 0;i < n;i++){
            data[i].putOffcialEmployee();
        }
    }

    public OffcialEmployee isInList(String employeeid) {
        for (OffcialEmployee a: data)
            if (a.isEmployee(employeeid)) return a;//neu nhan vien a la nhan vien trung id thi tra ve
        return null;//khong tim thay
    }

    public void add() {
        OffcialEmployee a = new OffcialEmployee();
        a.getOffcialEmployee();//lay nhan vien muon them

        data = Arrays.copyOf(data,data.length + 1);
        data[data.length - 1] = a;
    }

    public void add(OffcialEmployee newoffcialemployee) {
        data = Arrays.copyOf(data,data.length + 1);
        data[data.length - 1] = newoffcialemployee;
    }

    public void delete(String employeeid) {
        int locate = -1;
        for (int i = 0;i < data.length;i++) {
            if (data[i].getId().equals(employeeid)) {//tim vi tri cua nhan vien can tim
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
            dir.mkdir();//neu chua ton tai thi tao
        }

        File file = new File(dir,"data_OffcialEmployee.bin");//tao duong dan file can tao
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        }
        catch (IOException e){
            System.out.println("loi ghi file " + e);
        }
    }

    public void readFromFile() {
        String basepath = System.getProperty("user.dir");
        File file = new File(basepath + File.separator + "data" + File.separator + "data_OffcialEmployee.bin");

        if (!file.exists()) {
            System.out.println("file khong ton tai");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListOffcialEmployee a = (ListOffcialEmployee) ois.readObject();
            this.data = a.data;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public OffcialEmployee[] getData(){
        return data;
    }

    public void setData(OffcialEmployee[] data){
        this.data = data;
    }
}
