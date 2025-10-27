import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;
public class Position implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private String position_id;
    private String job_title;
    private String job_descripsion;
    private String required_skills;

    public Position(String position_id,String job_title,String job_descripsion,String required_skills)
    {
        this.position_id=position_id;
        this.job_title=job_title;
        this.job_descripsion=job_descripsion;
        this.required_skills=required_skills;
    }
    public Position(Position p)
    {
        this.position_id=p.position_id;
        this.job_title=p.job_title;
        this.job_descripsion=p.job_descripsion;
        this.required_skills=p.required_skills;
    }
    public Position(){}


    public void enter()
    {
        System.out.print("nhap ma vi tri: ");
        position_id=sc.nextLine();
        System.out.print("nhap ten cong viec: ");
        job_title=sc.nextLine();
        System.out.print("nhap mo ta cong viec: ");
        job_descripsion=sc.nextLine();
        System.out.print("nhap ky nang can thiet: ");
        required_skills=sc.nextLine();
    }

    public void out()
    {
        System.out.printf("%-15s %-20s %-25s %-20s\n",position_id,job_title,job_descripsion,required_skills);
    }
    //set
    public void setPosition_id(String position_id){this.position_id=position_id;}
    public void setJob_title(String job_title){this.job_title=job_title;}
    public void setJob_desciption(String job_descipsion){this.job_descripsion=job_descipsion;}
    public void setRequired_skills(String required_skills){this.required_skills=required_skills;}
    //get
    public String getPosition_id(){return position_id;}
    public String getJob_title(){return job_title;}
    public String getJob_desciption(){return job_descripsion;}
    public String getRequired_skills(){return required_skills;}
}