import java.util.*;
public class rollcall
{
    private timesheet[] ts;
    Scanner sc=new Scanner(System.in);

    public void check_employee() 
    {
        System.out.print("enter day: ");
        int daycheck=sc.nextInt();
        System.out.print("enter month: ");
        int monthcheck=sc.nextInt();
        System.out.print("enter year: ");
        int yearcheck=sc.nextInt();
        boolean found=false;
        for (int i=0;i<ts.length;i++)
        {
            timesheet t=ts[i];
            if (t.getMonth()==monthcheck && t.getYear()==yearcheck)
            {
                int[] days=t.getDay();
                for (int j=0;j<days.length;j++)
                {
                    if(daycheck==days[j])
                    {
                        System.out.println("Employee ID: "+t.getId_employee());
                        found=true;
                    }
                }
            }
        }
        if (found==false)
            System.out.println("no employee have working that day");
    }
    //set
    public void setTs(timesheet[] ts) {this.ts = ts;}
    //get
    public timesheet[] getTs() {return ts;}
}