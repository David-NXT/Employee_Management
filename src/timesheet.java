import java.util.*;
import java.io.*;
public class TimeSheet implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;

    private String id_clock;
    private String id_employee;
    private int[] day;
    private int month;
    private int year;
    private String[] time_in;
    private String[] time_out;


    public TimeSheet()
    {
        id_clock = "";
        id_employee = "";
        day = null;
        month = 0;
        year = 0;
        time_in = null;
        time_out = null;
    }
    public TimeSheet(String id_clock, String id_employee)
    {
        this.id_clock = id_clock;
        this.id_employee = id_employee;
    }
    public TimeSheet(TimeSheet ts)
    {
        this.id_clock = ts.id_clock;
        this.id_employee = ts.id_employee;
    }



    private String randomInTime() {
        int hour = 6 + (int) (Math.random() * 4);//tu 6 - 9 gio
        int minute =(int) (Math.random() * 60);//tu 0 - 59 phut
        return String.format("%02d:%02d",hour,minute);
    }
    private String randomTimeOut() {
        int hour = 16 + (int) (Math.random() * 3);//tu 16 - 18
        int minute = (int) (Math.random() * 60);// tu 0 - 59 phut
        return String.format("%02d:%02d",hour,minute);
    }
    private int RandomDay(int month,int year)
    {
        Random rand = new Random();
        int maxday=dayinmonth(month,year);
        if (maxday>0)
            return rand.nextInt(maxday)+1;
        return -1;
    }
    private int RandomMonth()
    {
        Random rand = new Random();
        return rand.nextInt(12)+1;
    }
    private int Randomyear()
    {
        Random rand = new Random();
        int start =2020;
        int end =2025;
        return rand.nextInt(end-start+1)+start;
    }
    private int dayinmonth(int month,int year)
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
        System.out.print("id clock: ");
        id_clock = sc.nextLine();
        ListEmployee l=new ListEmployee();
        l.readFromFile();
        for (int i=0;i<l.getData().length;i++)
            l.put();
        System.out.print("enter your id: ");
        id_employee=sc.nextLine();
        int check_id=l.isEmployee(id_employee);
        if (check_id==-1)
            System.out.println("Invalid id!");
        year=Randomyear();
        month=RandomMonth();
        System.out.print("number of working day: ");
        int n=sc.nextInt();
        day = new int[n];
        time_in = new String[n];
        time_out = new String[n];
        for (int i = 0; i < n; i++)
        {
            boolean check;
            int d;
            do
            {
                d=RandomDay(month,year);
                check=false;
                for (int j = 0; j < i; j++)
                {
                    if (day[j]==d)
                    {
                        check=true;
                        break;
                    }
                }
            }
            while (check);
            day[i]=d;
            time_in[i] = randomInTime();
            time_out[i] = randomTimeOut();
        }
    }
    public void out() 
    {
        System.out.printf("%-15s %-15s\n", "Timesheet ID", "Employee ID");
        System.out.printf("%-15s %-15s\n", id_clock, id_employee);
        System.out.println("Day   Month  Year   Status");
        for (int i = 0; i <day.length ; i++) 
        {
            String status=checkclock(i);
            QuickSort(day,0,day.length-1);
            System.out.printf("%1d %6d %7d     %s\n", day[i], month, year, status);
        }
    }
    public String checkclock(int i) //ra ve luc may h
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
    private int Partision(int[] day,int l,int r)
    {
        int x=day[r];
        int i=l-1;
        for (int j=l;j<r;j++)
        {
            if(day[j]<=x)
            {
                i++;
                int temp=day[i];
                day[i]=day[j];
                day[j]=temp;
            }
        }
        i++;
        int temp=day[i];
        day[i]=day[r];
        day[r]=temp;
        return i;
    }
    private void QuickSort(int[] day,int l,int r)
    {
        if (l<r)
        {
            int p=Partision(day,l,r);
            QuickSort(day,l,p-1);
            QuickSort(day,p+1,r);
        }
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
