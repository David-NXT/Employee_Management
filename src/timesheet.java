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


    public timesheet() 
    {
        id_clock = "";
        id_employee = "";
        day = null;
        month = 0;
        year = 0;
        time_in = null;
        time_out = null;
        n = 0;
    }
    public timesheet(String id_clock, String id_employee, int[] day, int month, int year, String[] time_in, String[] time_out, int n) 
    {
        this.id_clock = id_clock;
        this.id_employee = id_employee;
        this.day = day;
        this.month = month;
        this.year = year;
        this.time_in = time_in;
        this.time_out = time_out;
        this.n = n;
    }
    public timesheet(timesheet ts) 
    {
        this.id_clock = ts.id_clock;
        this.id_employee = ts.id_employee;
        this.day = ts.day;
        this.month = ts.month;
        this.year = ts.year;
        this.time_in = ts.time_in;
        this.time_out = ts.time_out;
        this.n = ts.n;
    }


    public int dayinmonth(int month,int year)
    {
        switch (month) 
        {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
                    return 29;
                else
                    return 28;
            default:
                return 0;
        }
    }
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
        System.out.print("number of working day: ");
        n=sc.nextInt();
        day = new int[n];
        time_in = new String[n];
        time_out = new String[n];
        for (int i = 0; i < n; i++) 
        {
            System.out.print("day: ");
            day[i] = sc.nextInt();
            sc.nextLine();
            if (day[i] > dayinmonth(month, year)) 
            {
                System.out.println("Error: Invalid day. Try again!");
                i--; // Decrement i to repeat this iteration
                continue;
            }
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
            System.out.printf("%1d %6d %7d     %s\n", day[i], month, year, status);
        }
    }
    public String checkclock(int i) 
    {
        boolean late = time_in[i].compareTo("08:00") > 0;
        boolean missingout = time_out[i].isEmpty();
        boolean missingin=time_in[i].isEmpty();
        if(late&&missingout)
            return "late and missingout";
        else if(late)
            return "late";
        else if(missingin)
            return "missing in";
        else if(missingout)
            return "missing out";
        else 
            return "ok";
    }
    //get
    public String getId_clock() {return id_clock;}
    public String getId_employee() {return id_employee;}
    public int[] getDay() {return day;}
    public int getMonth() {return month;}
    public int getYear() {return year;}
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
