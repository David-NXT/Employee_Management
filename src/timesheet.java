import java.util.Scanner;
public class timesheet 
{
    private String id_clock;
    private nhanvien nv;
    private int year;
    private int month;
    private int work_day;
    private int Paid_leave;
    private int unpaid_leave;
    private int overtime;
    Scanner sc=new Scanner(System.in);
    //ham nhap
    public void enter()
    {
        System.out.print("nhap ma bang cham cong: ");
        id_clock=sc.nextLine();
        System.out.print("nhap nam: "); 
        year=sc.nextInt();
        System.out.print("nhap thang: ");
        month=sc.nextInt();
        System.out.print("nhap so ngay lam viec: ");
        work_day=sc.nextInt();
        System.out.print("nhap so ngay nghi co phep: ");
        Paid_leave=sc.nextInt();
        System.out.print("nhap so ngay nghi khong phep: ");
        unpaid_leave=sc.nextInt();
        System.out.print("nhap so gio lam them: ");
        overtime=sc.nextInt();   
    }
    //ham xuat
    public void out()
    {
        if (nv!=null)
        {
            System.out.println("-----bang cham cong-----");
            System.out.println("ma bang cham cong: "+id_clock);
            System.out.println("ma nhan vien: "+nv.getID());
            System.out.println("nam: "+year);
            System.out.println("thang: "+month);
            System.out.println("so ngay lam viec: "+work_day);
            System.out.println("so ngay nghi co phep: "+Paid_leave);
            System.out.println("so ngay nghi khong phep: "+unpaid_leave);
            System.out.println("so gio lam them: "+overtime);
        }
        else 
        {
            System.out.println("moi ban nhap lai thong tin cua minh");
            nv=new nhanvien();
            nv.enter();
            out();
        }
    }
    //set
    public void setId_clock(String id_clock){this.id_clock=id_clock;}
    public void setYear(int year){this.year=year;}
    public void setMonth(int month){this.month=month;}
    public void setWork_day(int work_day){this.work_day=work_day;}
    public void setPaid_leave(int Paid_leave){this.Paid_leave=Paid_leave;}
    public void setUnpaid_leave(int unpaid_leave){this.unpaid_leave=unpaid_leave;}
    public void setOvertime(int overtime){this.overtime=overtime;}
    //get
    public String getId_clock(){return id_clock;}
    public int getYear(){return year;}
    public int getMonth(){return month;}
    public int getWork_day(){return work_day;}
    public int getPaid_leave(){return Paid_leave;}
    public int getUnpaid_leave(){return unpaid_leave;}
    public int getOvertime(){return overtime;}
}

