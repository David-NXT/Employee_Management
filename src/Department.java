

import java.util.Arrays;
import java.util.Scanner;

public class Department {
    private String id, name, about, createdate, manager;
    private Employee[] nv;
    private int n;
    private Scanner sc = new Scanner(System.in);

    public Department(String id, String name, String about, String createdate, String manager) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.createdate = createdate;
        this.manager = manager;
        this.nv = new Employee[100];
        this.n = 0;
    }
    public Department(Department a) {
        this.id = a.id;
        this.name = a.name;
        this.about = a.about;
        this.createdate = a.createdate;
        this.manager = a.manager;
        this.n = a.n;
        this.nv = new Employee[100];
        for (int i = 0; i < a.n; i++) {
            this.nv[i] = a.nv[i];
        }
    }
    public Department() {
        this.nv = new Employee[100];
        this.n = 0;
    }
    public void getDepartment() {
        System.out.print("Department's Id: "); id = sc.nextLine();
        System.out.print("Department's name: "); name = sc.nextLine();
        System.out.print("Department's about: "); about = sc.nextLine();
        System.out.print("Create date (yy-mm-dd): "); createdate = sc.nextLine();
        System.out.print("Department manager: "); manager = sc.nextLine();
    }
    public void putDepartment() {
        System.out.printf("%-5s %-20s %-40s %-12s %-20s", id, name, about, createdate, manager);
    }

    //(Ogri Employee-related)

    public Employee findEmployeeById(String id) {
        for (int i = 0; i < n; i++) {
            if (nv[i].getId().equalsIgnoreCase(id)){
                return nv[i];
            }
        }
        return null;
    }

    public void addEmployee(Employee a) {
        if (n >= nv.length) {
            nv = Arrays.copyOf(nv, nv.length + 100);
        }
        nv[n++] = a;
    }


   public void addEmployeeFromList(String inputId, ListEmployee list) {
        Employee[] all = list.getData();
        if (all == null || all.length == 0) {
            System.out.println("The Employee list is empty!");
            return;
        }
        for (Employee e : all) {
            if (e != null && e.getId().equalsIgnoreCase(inputId)) {
                addEmployee(e);
                System.out.println("Employee sent to department successfully: " + e.getName());
                return;
            }
        }
        System.out.println("Cant find employee with ID: " + inputId);
    }


    public void printDepartmentInfo() {
        System.out.printf("====== Department: %s (ID: %s) ======%n", name, id);
        if (n == 0) {
            System.out.println("No employee here.");
            addEmployees();
            return;
        }
        printEmployeeTable();
        System.out.println("Wanna add employees?");
        addEmployees();
    }

    private void addEmployees() {
        System.out.println("1. Input from keyboard");
        System.out.println("2. Input from file data");
        System.out.println("0. Exit");
        System.out.println(">> Select:");
        int choice = sc.nextInt(); 
        sc.nextLine(); 
        switch (choice) {
            case 1->{
                System.out.print("Amount: ");
                int count = sc.nextInt();
                sc.nextLine();
                for (int i = 0; i < count; i++) {
                    System.out.println("-- Input Employee no." + (i + 1) + " --");
                    System.out.println("Select Employee type: 1. Official 2. Seasonal");
                    int type = sc.nextInt();
                    sc.nextLine();
                    Employee e;
                    if (type == 1) e = new OfficialEmployee();
                    else e = new SeasonalStaff();
                    e.get(); // gọi phương thức get() của từng loại
                    addEmployee(e);
                }
            
                break;}

            case 2 ->{
                ListEmployee list = new ListEmployee();
                list.readFromFile();
                System.out.print("Enter ID of wanted Employee: ");
                String inputId = sc.nextLine();
                addEmployeeFromList(inputId, list);
                break;}

            case 0 ->{
                System.out.println("Exited.");
                break;}

            default -> System.out.println("Choice is vaild.");
        }
    }

    private void printEmployeeTable() {
        System.out.printf("%-10s %-15s %-15s %-8s %-15s %-30s %-12s %-12s %-12s %-12s %-12s%n","ID", "Surname", "Name", "Gender", "Phone", "Email", "Start Day", "Basic Sal", "Allowance", "Hourly/Time","Type");
        for (int i = 0; i < n; i++) {
        Employee e = nv[i];
        if (e == null) continue;
        if (e instanceof OfficialEmployee) {
            OfficialEmployee oe = (OfficialEmployee) e;
            System.out.printf("%-10s %-15s %-15s %-8s %-15s %-30s %-12s %-12.2f %-12.2f %-12s %-12s%n",oe.getId(), oe.getSurname(), oe.getName(), oe.getGender(), oe.getPhoneNumber(),oe.getEmail(), oe.getStartWorkingDay(), oe.getBasicSalary(), oe.getSalaryAllowance(), "-","Offical");
        } else if (e instanceof SeasonalStaff) {
            SeasonalStaff ss = (SeasonalStaff) e;
            System.out.printf("%-10s %-15s %-15s %-8s %-15s %-30s %-12s %-12s %-12s %-12s %-12s%n",ss.getId(), ss.getSurname(), ss.getName(), ss.getGender(), ss.getPhoneNumber(),ss.getEmail(), ss.getStartWorkingDay(), "-", "-", ss.getHourlySalary() + "$/ " + ss.getWorkTime(),"Seasonal");
        } else {
            System.out.printf("%-10s %-15s %-15s %-8s %-15s %-30s %-12s %-12s %-12s %-12s %-12s%n",e.getId(), e.getSurname(), e.getName(), e.getGender(), e.getPhoneNumber(),e.getEmail(), e.getStartWorkingDay(), "-", "-", "-", "-","Other");
        }
        }
    }
    
    // Thêm vào class Department.java

    public void updateDepartment() {
        System.out.println("====== Update Department (" + this.id + ") info ======");
        System.out.print("New department name (Current: " + this.name + "): ");
        String newName = sc.nextLine();
        if (!newName.isEmpty()) {
            setName(newName);
        }

        System.out.print("New department about (Current: " + this.about + "): ");
        String newAbout = sc.nextLine();
        if (!newAbout.isEmpty()) {
           setAbout(newAbout);
        }

        System.out.print("New department manager (Current: " + this.manager + "): ");
        String newManager = sc.nextLine();
        if (!newManager.isEmpty()) {
           setManager(newManager);
        }

        System.out.print("Update department create date (Current: " + this.createdate + "): ");
        String newCreateDate = sc.nextLine();
        if (!newCreateDate.isEmpty()) {
           setCreateDate(newCreateDate);
        }


    
        System.out.println("Updated info for this department successfully!");
    }
    
    


    //(Orgi Get/Set)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
    public String getCreateDate() { return createdate; }
    public void setCreateDate(String createdate) { this.createdate = createdate; }
    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    public Employee[] getEmployees() { return nv; }
    public int getN() { return n; }


}
