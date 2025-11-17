import java.io.Serial;
import java.io.Serializable;
import java.util.*;
public class TimeSheetMonth implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private String id_employee;
    private int[] day;
    private int month;
    private int year;
    private TimeSheetDay[] td;


    public TimeSheetMonth(){}
    public TimeSheetMonth(String id_employee,int[] day,int month,int year,TimeSheetDay[] td)
    {
        this.id_employee=id_employee;
        this.day=day;
        this.month=month;
        this.year=year;
        this.td=td;
    }
    public TimeSheetMonth(TimeSheetMonth t)
    {
        this.id_employee=t.id_employee;
        this.day=day;
        this.month=month;
        this.year=year;
        this.td=t.td;
    }

    public void enter()
    {
        int n=1+(int)(Math.random()*30);
        td=new TimeSheetDay[n];
        day=new int[n];
        for (int i=0;i<n;i++)
        {
            boolean check;
            int d;
            do
            {
                d=randomDay(month,year);
                check=false;
                for (int j=0;j<i;j++)
                {
                    if (day[j]==d)
                    {
                        check=true;
                        break;
                    }
                }
            }
            while(check);
            td[i]=new TimeSheetDay();
            day[i]=d;
            td[i].setId_employee(id_employee);
            td[i].setMonth(month);
            td[i].setYear(year);
            td[i].setDay(d);
            td[i].enter();
        }
    }
    public void out()
    {
        System.out.printf("%-1s","ID: ");
        System.out.printf("%-1s\n",id_employee);
        System.out.printf("%-15s %-15s %-15s %-15s\n","Date","Time in","Time out","Status");
        quickSort(td,0,td.length-1);
        for (int i=0;i<td.length;i++)
        {
            td[i].outmonth();
        }
    }
    private int partition(TimeSheetDay[] arr, int l, int r)
    {
        int pivot = arr[r].getDay();
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (arr[j].getDay() <= pivot) {
                i++;
                TimeSheetDay temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        TimeSheetDay temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;
        return i;
    }

    private void quickSort(TimeSheetDay[] arr, int l, int r)
    {
        if (l < r)
        {
            int p = partition(arr, l, r);
            quickSort(arr, l, p - 1);
            quickSort(arr, p + 1, r);
        }
    }
    public int randomDay(int month,int year)
    {
        int maxday;
        switch (month)
        {
            case 4: case 6: case 9: case 11:
            maxday=30;
            break;
            case 2:
                if (year%400==0||(year%4==0&& year%100!=0))
                    maxday=29;
                else
                    maxday=28;
                break;
            default:
                maxday=31;
        }
        return 1+(int)(Math.random()*maxday);
    }


//    get
    public String getId_employee() {return id_employee;}
    public int[] getDay(){return day;}
    public int getMonth(){return month;}
    public int getYear(){return year;}
    public TimeSheetDay[] getData() {return td;}
//    set
    public void setId_employee(String id_employee) {this.id_employee=id_employee;}
    public void setDay(int[] day){this.day=day;}
    public void setMonth(int month){this.month=month;}
    public void setYear(int year){this.year=year;}
    public void setTd(TimeSheetDay[] td){this.td=td;}
}