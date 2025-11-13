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
//        System.out.print("Enter your job title: ");
        job_title=randomPosition();
//        System.out.print("Enter your job description: ");
        job_descripsion=ranjob_description();
//        System.out.print("Enter your required skills: ");
        required_skills=ranrequired_skills();
    }

    public void out()
    {
        allowance_position=allowance_positionx(job_title);
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
        String p=randomPosition();
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
        String p=randomPosition();
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
    public double allowance_positionx(String jobx)
    {
        ListSalary ls=new ListSalary();
        ls.loadFromFile();
        double totalsalary=0;
        for (Employee_Salary el: ls.getAll())
        {
            if(el.getId().equalsIgnoreCase(id_employee))
            {
                totalsalary=el.getTotalSalary();
            }
        }
        jobx=job_title;
        if (jobx.equalsIgnoreCase("giam doc"))
            return totalsalary*0.20;
        else if (jobx.equalsIgnoreCase("truong phong"))
            return totalsalary*0.15;
        else if (jobx.equalsIgnoreCase("pho phong"))
            return totalsalary*0.10;
        else if (jobx.equalsIgnoreCase("nhan vien"))
            return totalsalary*0.5;
        else
            return totalsalary*0.2;

    }

    //set
    public void setId_employee(String id_employee) {this.id_employee=id_employee;}
    public void setJob_title(String job_title){this.job_title=job_title;}
    public void setJob_desciption(String job_descipsion){this.job_descripsion=job_descipsion;}
    public void setRequired_skills(String required_skills){this.required_skills=required_skills;}
    public void setAllowance_position(double allowance_position) {this.allowance_position = allowance_position;}
    //get
    public String getId_employee() {return this.id_employee;}
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