import java.util.*;
public class timesheet 
{
    private String id_clock;
    private String id_employee;
    private int[] day;
    private int month;
    private int year;
    private String[] time_in;
    private String[] time_out;
    private int n;
    Scanner sc=new Scanner(System.in);

    public void enter()
    {
        System.out.print("id timesheet: ");
        id_clock=sc.nextLine();
        System.out.print("id employee: ");
        id_employee=sc.nextLine();
        System.out.print("year: ");
        year=sc.nextInt();
        System.out.print("month: ");
        month=sc.nextInt();
        int dayinmonth=0;
        if (month==2)
        {
            if(year%4==0&&year%100!=0)
                dayinmonth=29;
            else 
                dayinmonth=28;
        }
        else if (month==4||month==6||month==9||month==11)
            dayinmonth=30;
        else 
            dayinmonth=31;
        day=new int[dayinmonth];
        System.out.print("number of working day: ");
        n=sc.nextInt();
        time_in = new String[n];
        time_out = new String[n];
        for (int i = 0; i < n; i++) 
        {
            System.out.print("day: ");
            day[i] = sc.nextInt();
            sc.nextLine();
            System.out.print("time in (HH:MM): ");
            time_in[i] = sc.nextLine();
            System.out.print("time out (HH:MM): ");
            time_out[i] = sc.nextLine();
        }
    }
    public void out() 
    {
        System.out.printf("%-15s %-15s\n", "Timesheet ID", "Employee ID");
        System.out.printf("%-15s %-15s\n", id_clock, id_employee);
        System.out.println("Day   Month  Year   Status");
        for (int i = 0; i < n; i++) 
        {
            String status=checkclock(i);
            System.out.printf("%1d %6d %8d     %s\n", day[i], month, year, status);
        }
    }
    public String checkclock(int i) 
    {
        boolean late = time_in[i].compareTo("08:00") > 0;
        boolean missingout = time_out[i].isEmpty();
        if(late&&missingout)
            return "late and missingout";
        else if(late)
            return "late";
        else if(missingout)
            return "missingout";
        else 
            return "ok";
    }
    //get
    public String getId_clock() {return id_clock;}
    public String getId_employee() {return id_employee;}
    public int[] getDay() {return day;}
    public int getMonth() {return month;}
    public String[] getTime_in() {return time_in;}
    public String[] getTime_out() {return time_out;}
    //set
    public void setId_clock(String id_clock) {this.id_clock = id_clock;}
    public void setId_employee(String id_employee) {this.id_employee = id_employee;}
    public void setDay(int[] day) {this.day = day;}
    public void setMonth(int month) {this.month = month;}
    public void setTime_in(String[] time_in) {this.time_in = time_in;}
    public void setTime_out(String[] time_out) {this.time_out = time_out;}
}
