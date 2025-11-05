import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;
public class Position implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private String id_employee;
    private String job_title;
    private String job_descripsion;
    private String required_skills;

    public Position(String id_employee,String job_title,String job_descripsion,String required_skills)
    {
        this.id_employee=id_employee;
        this.job_title=job_title;
        this.job_descripsion=job_descripsion;
        this.required_skills=required_skills;
    }
    public Position(Position p)
    {
        this.id_employee=p.id_employee;
        this.job_title=p.job_title;
        this.job_descripsion=p.job_descripsion;
        this.required_skills=p.required_skills;
    }
    public Position(){}


    public void enter()
    {
        ListEmployee l = new ListEmployee();
        l.readFromFile();
        l.put();
        System.out.print("Enter your id: ");
        id_employee=sc.nextLine();
        int check_id= l.isEmployee(id_employee);
        if(check_id==-1)
        {
            System.out.println("Invalid employee ID");
            enter();
        }
        System.out.print("nhap ten cong viec: ");
        job_title=sc.nextLine();
        System.out.print("nhap mo ta cong viec: ");
        job_descripsion=sc.nextLine();
        System.out.print("nhap ky nang can thiet: ");
        required_skills=sc.nextLine();
    }

    public void out()
    {
        System.out.printf("%-15s %-25s %-35s %-25s\n",id_employee,job_title,job_descripsion,required_skills);
    }
    //set
    public void setId_employee(String id_employee) {this.id_employee=id_employee;}
    public void setJob_title(String job_title){this.job_title=job_title;}
    public void setJob_desciption(String job_descipsion){this.job_descripsion=job_descipsion;}
    public void setRequired_skills(String required_skills){this.required_skills=required_skills;}
    //get
    public String getId_employee() {return this.id_employee;}
    public String getJob_title(){return job_title;}
    public String getJob_desciption(){return job_descripsion;}
    public String getRequired_skills(){return required_skills;}
}