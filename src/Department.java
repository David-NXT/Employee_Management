import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Department{
    //========[1.thuoc tinh]======
    private String id,name,about,createdate,manager; //thuoc tinh co ban cua class
    private List<Employee> nv; //thuoc tinh muon cua class khac
    
    private Scanner sc = new Scanner(System.in);


    //========[2.phuong thuc]=======
    //(Constructor)
    public Department(String id,String name,String about,String createdate,String manager){ //constructor
        //ogri
        this.id=id;
        this.name=name;
        this.about=about;
        this.createdate=createdate;
        this.manager=manager;
        //muon tu class khac
        this.nv=new ArrayList<>();
    }
    public Department(Department a){ //copy constructor
        id=a.id;
        name=a.name;
        about=a.about;
        createdate=a.createdate;
        manager=a.manager;
        this.nv = new ArrayList<>(a.nv);
    }
    public Department(){} //default constructor

    //(Ogi phuong thuc)

     public void getDepartment(Scanner sc){ //nhap
        System.out.printf("Id phong ban: ");id=sc.nextLine();
        System.out.printf("Ten phong ban: ");name=sc.nextLine();
        System.out.printf("Mieu ta phong ban: ");about=sc.nextLine();
        System.out.printf("Ngay thanh lap phong ban (yy-mm-dd): ");createdate=sc.nextLine();
        System.out.printf("Truong phong ban: ");manager=sc.nextLine();
    }

    public void putDepartment(){ //hien thi/xuat
        System.out.printf("%-5s %-20s %-40s %-12s %-20s",id,name,about,createdate,manager);
    }

    //(Ogri phuong thuc de tuong tac thong tin lay tu class khac)
    class ConcreteEmployee extends Employee {
        public ConcreteEmployee(){ super();} 
    }
    public void addDepartment_Employee(Employee e){ 
        nv.add(e);
    }



    public void printDepartmentInfo(){
        System.out.printf("======Department: %s (ID: %s)========%n", name, id);
        if (nv.isEmpty()){
            System.out.println("Khong co nv nao trong phong ban");
            System.out.println("Co muon them nhan vien?");
            System.out.println("1. Them nhan vien (add new data template)");
            System.out.println("2. Them nhan vien (add Employee from the data file)");
            System.out.println("0. Khong them nhan vien");
            System.out.print(">> Chon: ");
            int chon= sc.nextInt();
            switch (chon) {
                case 1 ->{
                     System.out.print("Nhap so luong nhan vien muon them: ");
                    int n = sc.nextInt();
                    for(int i = 0;i < n;i++){
                        Employee e = new ConcreteEmployee();
                        e.get();
                        nv.add(e);
                    }
                    break;
                }
                case 2 ->{
                    System.out.print("Nhap so luong nhan vien muon them: ");
                    int n = sc.nextInt();
                     for(int i = 0;i < n;i++){

                    }
                    break;
                }
                case 0 -> System.out.println("Khong them nhan vien.");
                default -> System.out.println("ERROR!");
            }
            return;
        }
        if (!nv.isEmpty()) {
        System.out.printf("%-10s %-20s %-20s %-8s %-15s %-20s %-12s%n","ID", "Surname", "Name", "Gender", "Phone", "Email", "Start Day");
        for (Employee e : nv) {
        System.out.printf("%-10s %-20s %-20s %-8s %-15s %-20s %-12s%n",e.getId(), e.getSurname(), e.getName(), e.getGender(),e.getPhoneNumber(), e.getEmail(), e.getStartWorkingDay());
        }
        }
    }


    //(Ogi get/set)
    public String getId(){ return id;}
    public void setId(String id){this.id = id;}
    public String getName(){ return name;}
    public void setName(String name){this.name = name;}
    public String getAbout(){return about;}
    public void setAbout(String about){this.about = about;}
    public String getCreateDate(){return createdate;}
    public void setCreateDate(String createdate){this.createdate = createdate;}
    public String getManager(){return manager;}
    public void setManager(String manager){this.manager = manager;}

    public List<Employee> getEmployees() { return nv;}




    
}
