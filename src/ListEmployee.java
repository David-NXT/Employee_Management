import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.Scanner;

public class ListEmployee {
    private final Scanner sc = new Scanner(System.in);
    private Employee[] data;
    private int n;//number of employee in data

    public ListEmployee(ListEmployee a){
        data = a.data;
        n = a.data.length;
    }

    public ListEmployee(){
        data = new Employee[0];
        n = 0;
    }

    public void get(){
        System.out.print("Number of employee: ");n = sc.nextInt();
        sc.nextLine();//loai bo \n thua trong buffer
        data = Arrays.copyOf(data,n);

        for(int i = 0;i < n;i++){
            data[i] = new Employee();
            data[i].getEmployee();
            System.out.println();
        }
    }

    public void put(){
        System.out.println("Number of employee: " + n);

        for(int i = 0;i < n;i++){
            data[i].putEmployee();
            System.out.println();
        }
    }

    public Employee[] getData(){
        return data;
    }

    public void setData(Employee[] a){
        data = a;
    }
}
