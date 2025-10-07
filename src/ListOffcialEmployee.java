import java.util.Arrays;
import java.util.Scanner;

public class ListOffcialEmployee {
    private final Scanner sc = new Scanner(System.in);
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

    public OffcialEmployee[] getData(){
        return data;
    }

    public void setData(OffcialEmployee[] dataa){
        this.data = data;
    }
}
