import java.util.Arrays;
import java.util.Scanner;

public class ListDepartment {
    private final Scanner sc = new Scanner(System.in);
    private Department[] data;
    private int n; //so phong ban

    public ListDepartment(ListDepartment a){
        data = a.data;
        n = a.data.length;
    }

    public ListDepartment(){
        data = new Department[0];
        n = 0;
    }

    public void get(){
        System.out.printf("So luong phong ban: ");n = sc.nextInt(); //sc.nextLine();
        data = Arrays.copyOf(data,n);
        for(int i = 0;i < n;i++){
            data[i] = new Department("","","","","");
            data[i].getDepartment();
            System.out.println();
        }
    }

    public void put(){
        System.out.printf("====Danh sach phong ban====%n");
        System.out.printf("So phong ban: %d%n",n);
        for(int i = 0;i < n;i++){
            data[i].putDepartment();
            System.out.println();
        }
    }
    //public data
    public Department[] getData(){
        return data;
    }

    public void setData(Department[] a){
        data = a;
    }

    


    
}
