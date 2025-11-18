import java.io.Serial;
import java.io.Serializable;
import java.util.*;
public class TimeSheetDay implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private String id_employee;
    private int day;
    private int month;
    private int year;
    private String time_in;
    private String time_out;
    private String status;


    public TimeSheetDay(){}
    public TimeSheetDay(String id_employee,int day,int month,int year,String time_in,String time_out)
    {
        this.id_employee=id_employee;
        this.day=day;
        this.month=month;
        this.year=year;
        this.time_in=time_in;
        this.time_out=time_out;
        this.status=check();
    }
    public TimeSheetDay(TimeSheetDay t)
    {
        this.id_employee=t.id_employee;
        this.day=t.day;
        this.month=t.month;
        this.year=t.year;
        this.time_in=t.time_in;
        this.time_out=t.time_out;
        this.status=t.status;
        t.status=check();
    }


    public void enter()
    {
        month=1+(int)(Math.random()*12);
        year=2023+(int)(Math.random()*2);
        time_in=randomTimeIn();
        time_out=randomTimeOut();
    }
    public void outmonth()
    {
        String date = String.format("%02d/%02d/%04d", day, month, year);
        this.status=check();
        System.out.printf("%-15s %-15s %-15s %-15s\n",date,time_in,time_out,status);
    }
    public void outday()
    {
        String date = String.format("%02d/%02d/%04d", day, month, year);
        this.status=check();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n",id_employee,date,time_in,time_out,status);
    }

    public String randomTimeIn()
    {
        int hour=6+(int)(Math.random()*4);
        int minute=(int)(Math.random()*60);
        return String.format("%02d:%02d",hour,minute);
    }
    public String randomTimeOut()
    {
        int hour=16+(int)(Math.random()*6);
        int minute=(int)(Math.random()*60);
        return String.format("%02d:%02d",hour,minute);
    }
    public String check()
    {
        boolean late = time_in.compareTo("08:00") > 0;
        boolean missingout = time_out.compareTo("00:00") == 0;
        boolean missingin = time_in.compareTo("00:00") == 0;
        boolean overtime = time_out.compareTo("17:00") > 0;
        if (missingin && missingout)
            return "Missing both";
        else if (late && missingout)
            return "Late and missingout";
        else if (late && overtime)
            return "Late and overtime";
        else if (late)
            return "Late";
        else if (missingin)
            return "Missing in";
        else if (missingout)
            return "Missing out";
        else if (overtime)
            return "Over time";
        else
            return "Ok";
    }





//    get
    public String getId_employee(){return id_employee;}
    public int getDay(){return day;}
    public int getMonth(){return month;}
    public int getYear() {return year;}
    public String getTime_in(){return time_in;}
    public String getTime_out(){return time_out;}
    public String getStatus(){return status;}
//    set
    public void setId_employee(String id_employee){this.id_employee=id_employee;}
    public void setDay(int day){this.day = day;}
    public void setMonth(int month){this.month = month;}
    public void setYear(int year){this.year = year;}
    public void setTime_in(String time_in){this.time_in = time_in;}
    public void setTime_out(String time_out){this.time_out = time_out;}
}