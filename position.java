import java.util.Scanner;
class position
{
    private String position_id;
    private String job_title;
    private String job_descripsion;
    private String required_skills;
    Scanner sc=new Scanner(System.in);
    public void setPosition_id(String position_id){this.position_id=position_id;}
    public void setJob_title(String job_title){this.job_title=job_title;}
    public void setJob_desciption(String job_descipsion){this.job_descripsion=job_descipsion;}
    public void setRequired_skills(String required_skills){this.required_skills=required_skills;}
    public String getPosition_id(){return position_id;}
    public String getJob_title(){return job_title;}
    public String getJob_desciption(){return job_descripsion;}
    public String getRequired_skills(){return required_skills;}
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
    public static void main(String[] args) 
    {
        position p=new position();
        p.enter();
        p.out();
    }
}