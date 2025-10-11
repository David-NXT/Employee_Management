import java.util.Arrays;
import java.util.Scanner;

public class ListQDPC_ThamNien{
    private final Scanner sc = new Scanner(System.in);
    private QDPC_ThamNien[] data;
    private int n;

    public ListQDPC_ThamNien(ListQDPC_ThamNien a){
        data = a.data;
        n = a.data.length;
    }

    public ListQDPC_ThamNien(){
        data = new QDPC_ThamNien[0];
        n = 0;
    }



    public void get() {
        System.out.print("So luong quy dinh: ");
        n = Integer.parseInt(sc.nextLine());
        data = Arrays.copyOf(data, n);

        for (int i = 0; i < n; i++) {
            System.out.println("Nhap quy dinh thu " + (i + 1));
            Employee nv = new Employee(); 
            nv.getEmployee(); // nhap thong tin nhan vien
            data[i] = new QDPC_ThamNien();
            data[i].inputRequirements(sc);
        }
    }

    public void put() {
        System.out.println("====Danh sach quy dinh phu cap====");
        for (int i = 0; i < n; i++) {
            data[i].putPhuCapThamNien();
            System.out.println();
        }
    }

    public QDPC_ThamNien[] getData() {
        return data;
    }

    public void setData(QDPC_ThamNien[] a) {
        data = a;
    }



}
