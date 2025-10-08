import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.Scanner;
import java.io.Serializable;

public class Project implements Serializable{
    private final Scanner sc = new Scanner(System.in);
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
    }

    public Project(float managerallowancerate, float mainengineerallowancerate, float participantallowancerate, String projectid, String projectname, String startday, String accomplishday, String managerid, String mainengineerid, String[] employeeid) {
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
    }

    public void getProject() {
        System.out.println("Manager Allowance Rate: ");managerallowancerate = sc.nextFloat();
        System.out.println("Main Engineer Allowance Rate: ");mainengineerallowancerate = sc.nextFloat();
        System.out.println("Participant Allowance rate: ");participantallowancerate = sc.nextFloat();
        sc.nextLine();//bo \n thua trong buffer
        System.out.println("Project Id: ");projectid = sc.nextLine();
        System.out.println("Project Name: ");projectname = sc.nextLine();
        System.out.println("Start Day: ");startday = sc.nextLine();
        System.out.println("Accomplish Day: ");accomplishday = sc.nextLine();
        System.out.println("Manager Id: ");managerid = sc.nextLine();
        System.out.println("Main Engineer Id: ");mainengineerid = sc.nextLine();
        System.out.println("Number of Employee: ");int n = sc.nextInt();
        sc.nextLine();//bo \n thua trong buffer
        employeeid = Arrays.copyOf(employeeid,n);
        for (int i = 0;i < employeeid.length;i++) {
            employeeid[i] = sc.nextLine();
        }
    }

    public void putProject(ListOffcialEmployee list) {
        System.out.println("Manager Allowance Rate: " + managerallowancerate);
        System.out.println("Main Engineer Allowance Rate: " + mainengineerallowancerate);
        System.out.println("Participant Allowance rate: " + participantallowancerate);
        System.out.println("Project Id: " + projectid);
        System.out.println("Project Name: " + projectname);
        System.out.println("Start Day: " + startday);
        System.out.println("Accomplish Day: " + accomplishday);
        System.out.println("Number of Employee: " + employeeid.length);
        OffcialEmployee name = list.isInList(managerid);
            if (name != null)
                System.out.println("Quan ly du an: " + name.getName() + " " + name.getId());
            else System.out.println("Du an chua co quan ly");
            name = list.isInList(mainengineerid);
            if (name != null)
                System.out.println("Ky su truong du an: " + name.getName() + " " + name.getId());
            else System.out.println("Du an chua co ki su truong");

            System.out.println("Nhan vien tham gia du an: ");
            for (String b: employeeid) {
                name = list.isInList(b);
                if (name != null)
                    System.out.println("\t" + name.getName() + " " + name.getId());
            }
    }

    public double projectAllowanceSalary(OffcialEmployee employee) {
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
}
