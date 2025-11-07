import javax.xml.transform.Source;
import java.io.Serial;
import java.util.Arrays;
import java.util.Scanner;
import java.io.Serializable;

public class Project implements Serializable{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = -1821691918350626398L;
    private float managerallowancerate;
    private float mainengineerallowancerate;
    private float participantallowancerate;
    private String projectid;
    private String projectname;
    private String startday;
    private String accomplishday;
    private String managerid;
    private String mainengineerid;
    private String[] employeeid;//save employee id
    private double projectBudget;

    public Project() {
        employeeid = new String[0];
    }

    public Project(Project a) {
        managerallowancerate = a.managerallowancerate;
        mainengineerallowancerate = a.mainengineerallowancerate;
        participantallowancerate = a.participantallowancerate;
        projectid = a.projectid;
        projectname = a.projectname;
        startday = a.startday;
        accomplishday = a.accomplishday;
        managerid = a.managerid;
        mainengineerid = a.mainengineerid;
        employeeid = a.employeeid;
        projectBudget=a.projectBudget;
    }

    public Project(float managerallowancerate, float mainengineerallowancerate, float participantallowancerate, String projectid, String projectname, String startday, String accomplishday, String managerid, String mainengineerid, double projectBudget, String[] employeeid) {
        this.managerallowancerate = managerallowancerate;
        this.mainengineerallowancerate = mainengineerallowancerate;
        this.participantallowancerate = participantallowancerate;
        this.projectid = projectid;
        this.projectname = projectname;
        this.startday = startday;
        this.accomplishday = accomplishday;
        this.managerid = managerid;
        this.mainengineerid = mainengineerid;
        this.employeeid = employeeid;
        this.projectBudget = projectBudget;
    }

    public void getProject() {
        System.out.println("Manager Allowance Rate: ");managerallowancerate = sc.nextFloat();
        System.out.println("Main Engineer Allowance Rate: ");mainengineerallowancerate = sc.nextFloat();
        System.out.println("Participant Allowance rate: ");participantallowancerate = sc.nextFloat();
        sc.nextLine();//bo \n thua trong buffer
        System.out.println("Project Id: ");projectid = sc.nextLine();
        ListProject l = new ListProject();
        l.readFromFile();
        while(true) {
            boolean flag = false;
            for (int i = 0;i < l.getData().length;i++) {
                if (projectid.equals(l.getData()[i].projectid)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) break;

            System.out.println("This ID Already Exist");
            projectid = sc.nextLine();
        }
        System.out.println("Project Name: ");projectname = sc.nextLine();
        System.out.println("Start Day: ");startday = sc.nextLine();
        System.out.println("Accomplish Day: ");accomplishday = sc.nextLine();
        System.out.println("Manager Id: ");managerid = sc.nextLine();
        System.out.println("Main Engineer Id: ");mainengineerid = sc.nextLine();
        System.out.println("Enter Project Budget:"); projectBudget=sc.nextDouble();
        System.out.println("Number of Employee: ");int n = sc.nextInt();
        sc.nextLine();//bo \n thua trong buffer
        employeeid = Arrays.copyOf(employeeid,n);
        for (int i = 0;i < employeeid.length;i++) {
            while (true) {
                String temp = sc.nextLine();
                if (!isEmployeeInProject(temp)) {
                    employeeid[i] = temp;
                    break;
                }
                System.out.println("This Employee ID Already in List");
            }
        }
    }

    public void putProject(ListEmployee list) {
        System.out.println("________________________________________________________________________________________");
        System.out.printf("%-22s %-28s %-26s %-10s %-12s %-9s %-14s ",managerallowancerate,mainengineerallowancerate,participantallowancerate,projectid,projectname,startday,accomplishday);

        OfficialEmployee name = list.getOfficialEmployee(managerid);
            if (name != null)
                System.out.printf("%-13s %-7s ",name.getName(),name.getId());
            else System.out.printf("%-21s ","Du an chua co quan ly");
            name = list.getOfficialEmployee(mainengineerid);
            if (name != null)
                System.out.printf("%-18s %-7s%n",name.getName(),name.getId());
            else System.out.printf("%-26s%n","Du an chua co ki su truong");

            System.out.println("Nhan vien tham gia du an: " + employeeid.length);
            System.out.printf("%-25s %-7s%n","Name","ID");
            for (String b: employeeid) {
                name = list.getOfficialEmployee(b);
                if (name != null)
                    System.out.printf("%-25s %-7s%n",name.getSurname() + " " + name.getName(),name.getId());
            }
            System.out.println("________________________________________________________________________________________");
    }

    public double projectAllowanceSalary(OfficialEmployee employee) {
        if(employee.getId().equals(managerid))
            return employee.getBasicSalary() * managerallowancerate;//tra ve muc phu cap cua quan ly du an
        else if(employee.getId().equals(mainengineerid))
            return employee.getBasicSalary() * mainengineerallowancerate;//tra ve muc phu cap cua ki su truong
        else {
            for(String a : employeeid) {
                if(employee.getId().equals(a))
                    return employee.getBasicSalary() * participantallowancerate;//tra ve muc phu cap cua nhan vien tham gia
            }
            return 0;//neu khong co trong danh sach tra ve 0 dong
        }
    }

    public boolean isEmployeeInProject(OfficialEmployee employee) {
        if(employee.getId().equals(managerid))
            return true;
        else if(employee.getId().equals(mainengineerid))
            return true;
        else {
            for(String a : employeeid) {
                if(employee.getId().equals(a))
                    return true;
            }
            return false;
        }
    }

    public boolean isEmployeeInProject(String id) {
        if(id.equals(managerid))
            return true;
        else if(id.equals(mainengineerid))
            return true;
        else {
            for(String a : employeeid) {
                if(id.equals(a))
                    return true;
            }
            return false;
        }
    }

    public void addEmployeeId(String id) {
        employeeid = Arrays.copyOf(employeeid,employeeid.length + 1);
        employeeid[employeeid.length - 1] = id;
    }

    public void deleteEmployeeId(String id) {
        int locate = -1;
        for (int i = 0;i < employeeid.length;i++) {
            if (employeeid[i].equals(id)) {
                locate = i;
                break;
            }
        }

        if (locate == -1) {
            System.out.println("There Is No Employee Have That ID");
            return;
        }

        for (int i = locate;i < employeeid.length;i++) {
            employeeid[i] = employeeid[i + 1];
        }
        employeeid = Arrays.copyOf(employeeid,employeeid.length - 1);
    }

    public float getManagerAllowanceRate() {
        return managerallowancerate;
    }

    public float getMainEngineerAllowanceRate() {
        return mainengineerallowancerate;
    }

    public float getParticipantAllowanceRate() {
        return participantallowancerate;
    }

    public String getProjectId() {
        return projectid;
    }

    public String getProjectName() {
        return projectname;
    }

    public String getStartDay() {
        return startday;
    }

    public String getAccomplishDay() {
        return accomplishday;
    }

    public String getManagerId() {
        return managerid;
    }

    public String getMainengineerId() {
        return mainengineerid;
    }
    public double getProjectBudget() {
        return projectBudget;
    }

    public String[] getEmployeeId(){
        return employeeid;
    }

    public void setManagerAllowanceRate(float managerallowancerate) {
        this.managerallowancerate = managerallowancerate;
    }

    public void setMainEngineerAllowanceRate(float mainengineerallowancerate) {
        this.mainengineerallowancerate = mainengineerallowancerate;
    }

    public void setParticipantAllowanceRate(float participantAllowanceRate) {
        this.participantallowancerate = participantAllowanceRate;
    }

    public void setProjectId(String projectid) {
        this.projectid = projectid;
    }

    public void setProjectName(String projectname) {
        this.projectname = projectname;
    }

    public void setStartDay(String startday){
        this.startday = startday;
    }

    public void setAccomplishDay(String accomplishday) {
        this.accomplishday = accomplishday;
    }

    public void setManagerId(String managerid) {
        this.managerid = managerid;
    }

    public void setMainengineerId(String mainengineerid) {
        this.mainengineerid = mainengineerid;
    }

    public void setEmployee(String[] employeeid) {
        this.employeeid = employeeid;
    }

    public boolean hasEmployee(String empId) {
        if (employeeid == null || employeeid.length == 0)
            return false;
        for (String id : employeeid) {
            if (id.equalsIgnoreCase(empId))
                return true;
        }
        return false;
    }
    public double getAllowanceRateFor(String empId) {
        if (empId == null) return 0;

        // Nếu nhân viên là quản lý dự án
        if (empId.equalsIgnoreCase(managerid)) {
            return managerallowancerate;
        }

        // Nếu nhân viên là kỹ sư trưởng
        if (empId.equalsIgnoreCase(mainengineerid)) {
            return mainengineerallowancerate;
        }

        // Nếu nhân viên là thành viên tham gia dự án
        if (employeeid != null) {
            for (String id : employeeid) {
                if (empId.equalsIgnoreCase(id)) {
                    return participantallowancerate;
                }
            }
        }

        // Nếu không có trong dự án → phụ cấp = 0
        return 0;
    }


}
