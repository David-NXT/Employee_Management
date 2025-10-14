import java.lang.reflect.Array;
import java.util.Scanner;
public class timesheet 
{
    private String id_clock;
    private String id_employee;
    private String[] day=new String[31];
    private String[] time_in=new String[31];
    private String[] time_out=new String[31];
    private int n;
    Scanner sc=new Scanner(System.in);

    public void enter()
    {
        System.out.print("id timesheet: ");
        id_clock=sc.nextLine();
        System.out.print("id employee: ");
        id_employee=sc.nextLine();
        System.out.print("number of day: ");
        n=sc.nextInt();
        sc.nextLine();
        for (int i=0;i<n;i++)
        {
            System.out.print("day: ");
            day[i]=sc.nextLine();
            System.out.print("time in: ");
            time_in[i]=sc.nextLine();
            System.out.print("time out: ");
            time_out[i]=sc.nextLine();
        }
    }
    public void out()
    {
        System.out.printf("%-15s %-15s\n", "Timesheet ID", "Employee ID");
        System.out.printf("%-15s %-15s\n", id_clock, id_employee);
        for (int i=0;i<n;i++)
        {
            System.out.printf("%15s %15s %15s\n",day[i],time_in[i],time_out[i]);
        }
    }
    public boolean checkclock()
    {
        boolean aviable=true;
        for (int i=0;i<n;i++)
        {
            boolean late = time_in[i].compareTo("08:00") > 0;
            boolean missingout=time_out[i].isEmpty();
            if (late&&missingout)
                aviable=false;
        }
        return aviable;
    }
    //get
    public String getId_clock() {return id_clock;}
    //set
    public void setId_clock(String id_clock) {this.id_clock = id_clock;}
}
