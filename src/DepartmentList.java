import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DepartmentList {
    //========[1.thuoc tinh]======
    private Department[] data;
    private int n;
    private final String file_path="data\\data_Department.bin";

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
    public void readFromFiles(){ //nhap-doc tu file
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
        System.out.printf("%-5s %-20s %-39s %-12s %-20s%n","[ID]","[Name]","[About]","[Createdate]","[Manager]");
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
        n--;
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
    public void addRandom() {
    
        ListEmployee list = new ListEmployee();
        list.readFromFile();
        Employee[] all = list.getData();
        
        System.out.println("So nhan vien doc duoc: " + all.length);

        Random rand = new Random();
        if (n == 0) {
        System.out.println("Du lieu phong ban trong, hay nhap [5] hay [2] truoc!");
        return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap n so nhan vien ngau nhien: ");
        int a = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < a && i < all.length ; i++) {
            int index_e = rand.nextInt(all.length);
            Employee employee = all[index_e];
            boolean alreadyAssigned = false;
            for (Department department : data) {
            if (department != null && department.findEmployeeById(employee.getId()) != null) {
                alreadyAssigned = true;
                break;
            }
            }
            if (alreadyAssigned) { continue;}
            int index_dp = rand.nextInt(n); 
            data[index_dp].addEmployee(all[index_e]); 
            System.out.println("Added employee " + all[index_e].getName() + " to department "+ data[index_dp].getName());
        }

    }
     // Ghi dữ liệu ra file
    public void writeToFile() {
    try (PrintWriter pw = new PrintWriter(file_path)) {
        pw.println(n);
        for (Department d : data) {
            pw.println(d.getId());
            pw.println(d.getName());
            pw.println(d.getAbout());
            pw.println(d.getCreateDate());
            pw.println(d.getManager());
        }
        System.out.println("Saved Department.");
    } catch (IOException e) {
        System.err.println("Loi ghi file: " + e.getMessage());
    }
}



    //// Getter cho mảng dữ liệu
    //public Department[] getData() {
    //    return data;
    //}
    

    //(Ogi get/set)
    public Department[] getData(){ return data;}
    public void setData(Department[] a){ data=a;}


    
}
