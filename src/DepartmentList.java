import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class DepartmentList {
    //========[1.thuoc tinh]======
    private Department[] data;
    private int n;
    private final String file_path="Employee_Management/data/data_Department.txt";

    //========[2.phuong thuc]=======
    //(Constructor)
    public DepartmentList(DepartmentList a){ //copy constructor
        this.data = Arrays.copyOf(a.data, a.data.length);
        this.n = a.n;
    }

    public DepartmentList(){ //default constructor
        data= new Department[0];
        n=0;
    }

    //(ogri phuong thuc)
    public void readFromFile(){ //nhap-doc tu file
        try {
            File file= new File(file_path);
            Scanner sc= new Scanner(file);
            if(sc.hasNextInt()){
                n=sc.nextInt();
                sc.nextLine();
            }
            data=new Department[n];
            for(int i=0;i<n;i++){
                String id= sc.nextLine();
                String name= sc.nextLine();
                String about=sc.nextLine();
                String createdate=sc.nextLine();
                String manager=sc.nextLine();
                data[i]=new Department(id,name,about,createdate,manager);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.printf("File 404");
        }
    }

    public void put(){ //xuat
        System.out.printf("=====Danh sach phong ban=====%n");
        System.out.printf("So phong ban %d%n",n);
        System.out.printf("%-5s %-20s %-40s %-12s %-20s%n","ID","Name","about","createdate","manager");
        for(int i=0;i<n;i++){
            data[i].putDepartment();
            System.out.println();
        }
    }

    public void them(Department a){
        data = Arrays.copyOf(data,data.length + 1);
        data[data.length - 1] = a;
        n++;
    }

    public int isDepartment(String id) {//ktra department co ton tai ko
        int locate = -1;
        for (int i = 0;i < data.length;i++) {
            if (data[i].getId().equals(id)) {
                locate = i;
                break;
            }
        }
        if (locate == -1) {
            return -1;
        }
        return locate;
    }

    public void xoa(String id){ //xoa
        int locate = isDepartment(id);
        if (locate == -1) {
            System.out.println("Khong ton tai phong ban nay..");
            return;
        }
        for (int i = locate;i < data.length - 1;i++) {
            data[i] = data[i + 1];
        }
        data = Arrays.copyOf(data,data.length - 1);
    }

    public void search(String keyword){
        boolean found = false;
        System.out.println("\nKet qua tim kiem cho: " + keyword);
        for (Department d : data) {
            if (d.getId().equalsIgnoreCase(keyword) || d.getName().equalsIgnoreCase(keyword)) {
                d.printDepartmentInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay phong ban phu hop..");
        }

    }


    //(Ogi get/set)
    public Department[] getData(){ return data;}
    public void setData(Department[] a){ data=a;}


    
}
