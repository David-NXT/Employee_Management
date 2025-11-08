import java.io.Serial;
import java.io.Serializable;
import java.util.*;
public class TimeSheetMonth implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private String id_clock;
    private String id_employee;
    private TimeSheetDay[] td;


    public TimeSheetMonth(){}
    public TimeSheetMonth(String id_clock,String id_employee)
    {
        this.id_clock=id_clock;
        this.id_employee=id_employee;
    }
    public TimeSheetMonth(TimeSheetMonth t)
    {
        this.id_clock=t.id_clock;
        this.id_employee=t.id_employee;
    }

    public void enter()
    {
//        System.out.print("Enter ID clock: ");
        id_clock=randomid_clock();
        ListEmployee l=new ListEmployee();
        l.readFromFile();
        l.put();
        System.out.print("Enter ID employee: ");
        id_employee=sc.nextLine();
        int check_id=l.isEmployee(id_employee);
        if (check_id==-1)
        {
            System.out.println("your ID is Invaible");
            enter();
        }
//        System.out.print("Number of Working day: ");
        int n=1+(int)(Math.random()*30);
        td=new TimeSheetDay[n];
        int month=randomMonth();
        int year=randomYear();
        int[] day=new int[n];
        for (int i=0;i<n;i++)
        {
            td[i]=new TimeSheetDay();
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
            day[i]=d;
            td[i].enter(id_employee,d,month,year);
        }
    }
    public void out()
    {
        System.out.printf("%-15s %-15s\n","ID","ID Clock");
        System.out.printf("%-15s %-15s\n",id_employee,id_clock);
        System.out.printf(" %-15s %-15s %-15s %-15s\n","Date","Time in","Time out","Status");
        quickSort(td,0,td.length-1);
        for (int i=0;i<td.length;i++)
        {
            td[i].out();
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
        if (l < r) {
            int p = partition(arr, l, r);
            quickSort(arr, l, p - 1);
            quickSort(arr, p + 1, r);
        }
    }

    public void monthcheck(int monthx)
    {
        for (int i=0;i<td.length;i++)
        {
            if(monthx==td[i].getMonth())
            {
                String date = String.format("%02d/%02d/%04d", td[i].getDay(),td[i].getMonth(),td[i].getYear());
                String status=td[i].check();
                System.out.printf("%-15s %-15s %-15s %-15s %-15s\n",id_employee,date,td[i].getTime_in(),td[i].getTime_out(),status);
            }
        }

    }

    public void daycheck(int dayx)
    {
        for (int i=0;i<td.length;i++)
        {
            if(dayx==td[i].getDay())
            {
                String status=td[i].check();
                System.out.printf("%-15s %-15s %-15s %-15s\n",td[i].getId_employee(),td[i].getTime_in(),td[i].getTime_out(),status);
            }
        }
    }

    public int randomMonth()
    {
        return 1+(int)(Math.random()*12);
    }
    public int randomYear()
    {
        return 2025+(int)(Math.random()*41);
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
    public String randomid_clock()
    {
        int id=1+(int)(Math.random()*100);
        return String.format("%02d",id);
    }


//    get
    public String getId_clock() {return id_clock;}
    public String getId_employee() {return id_employee;}
//    set
    public void setId_clock(String id_clock) {this.id_clock=id_clock;}
    public void setId_employee(String id_employee) {this.id_employee=id_employee;}

    public TimeSheetDay[] getData() {
        return td;
    }
}