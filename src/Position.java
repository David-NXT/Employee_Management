import java.io.Serial;
import java.io.Serializable;
import java.util.*;
public class Position implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private String id_employee;
    private String job_title;
    private String job_descripsion;
    private String required_skills;
    private double allowance_position;

    public Position(String id_employee,String job_title,String job_descripsion,String required_skills)
    {
        this.id_employee=id_employee;
        this.job_title=job_title;
        this.job_descripsion=job_descripsion;
        this.required_skills=required_skills;
        this.allowance_position=0;
    }
    public Position(Position p)
    {
        this.id_employee=p.id_employee;
        this.job_title=p.job_title;
        this.job_descripsion=p.job_descripsion;
        this.required_skills=p.required_skills;
        this.allowance_position=p.allowance_position;
    }
    public Position(){}


    public void enter()
    {
        job_title=randomPosition();
        job_descripsion=ranjob_description();
        required_skills=ranrequired_skills();
    }

    public void out()
    {
        System.out.printf("%-12s %-20s %-45s %-30s %-20s\n",id_employee,job_title,job_descripsion,required_skills,allowance_position);
    }
    public String randomPosition()
    {
        String[] nameposition={"Truong phong","Pho phong","nhan vien","thuc tap sinh"};
        Random rand=new Random();
        String randomposition=nameposition[rand.nextInt(nameposition.length)];
        return randomposition;
    }
    public String ranjob_description()
    {
        String p=job_title;
        Employee_Salary el=new Employee_Salary();
        if (p.equals("Truong phong"))
        {
            return "quan ly va dan dat mot nhom lam viec";
        }
        else  if (p.equals("Pho phong"))
        {
            return "phu tro truong phong";
        }
        else if (p.equals("nhan vien"))
        {
            return "lap trinh";
        }
        else if (p.equals("thuc tap sinh"))
        {
            return "dao tao";
        }
        else
            return null;
    }
    public String ranrequired_skills()
    {
        String p=job_title;
        if (p.equals("Truong phong"))
        {
            return "lanh dao nhom";
        }
        else  if (p.equals("Pho phong"))
        {
            return "thay mat truong phong";
        }
        else if (p.equals("nhan vien"))
        {
            return "cac ngon ngu lap trinh";
        }
        else if (p.equals("thuc tap sinh"))
        {
            return "dao duc va trach nhiem";
        }
        else
            return null;
    }

    //set
    public void setId_employee(String id_employee) {this.id_employee=id_employee;}
    public void setJob_title(String job_title){this.job_title=job_title;}
    public void setJob_desciption(String job_descipsion){this.job_descripsion=job_descipsion;}
    public void setRequired_skills(String required_skills){this.required_skills=required_skills;}
    public void setAllowance_position(double allowance_position) {this.allowance_position = allowance_position;}
    //get
    public String getId_employee() {return id_employee;}
    public String getJob_title(){return job_title;}
    public String getJob_desciption(){return job_descripsion;}
    public String getRequired_skills(){return required_skills;}
    public double getAllowance_position(){return allowance_position;}
    public double getPositionAllowanceRate() {
        if (job_title == null) return 0;
        return switch (job_title.trim().toLowerCase()) {
            case "Giam doc" ->  1;
            case "Truong phong" ->  0.7;
            case "Pho phong" ->  0.5;
            case "nhan vien" ->  0.3;
            default ->  0.2;
        };
    }
}