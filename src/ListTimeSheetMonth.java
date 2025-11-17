import java.util.*;
import java.io.*;
public class ListTimeSheetMonth implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private TimeSheetMonth[] ts;


    public ListTimeSheetMonth()
    {
        ts=new TimeSheetMonth[0];
    }
    public ListTimeSheetMonth(TimeSheetMonth[] ts)
    {
        this.ts=ts;
    }
    public ListTimeSheetMonth(ListTimeSheetMonth lt)
    {
        this.ts=lt.ts;
    }

    public void enter()
    {
        System.out.print("Enter number of employee: ");
        int n=sc.nextInt();
        sc.nextLine();
        ts=new TimeSheetMonth[n];
        ListEmployee l=new ListEmployee();
        l.readFromFile();
        int month=randomMonth();
        int year=randomYear();
        for (int i=0;i<n;i++)
        {
            String id;
            boolean check2=false;
           do
           {
               System.out.print("Enter your ID: ");
               id=sc.nextLine();
               if (l.isEmployee(id)==-1)
               {
                   System.out.println("Invaible ID");
                   check2=false;
               }
               else if (check_id_have_enter(id)!=-1)
               {
                   System.out.println("This ID have already exit");
                   check2=false;
               }
               else
                   check2=true;
           }
           while (!check2);
           ts[i]=new TimeSheetMonth();
           ts[i].setId_employee(id);
           ts[i].setMonth(month);
           ts[i].setYear(year);
           ts[i].enter();
        }
    }
    public void out()
    {
        System.out.println("Data time sheet employee");
        quicksort(ts,0,ts.length-1);
        number_of_employee_statistics();
        for (int i=0;i<ts.length;i++)
        {
            System.out.println("Time sheet month "+(i+1)+":");
            ts[i].out();
            System.out.println("---------------------------");
        }
    }
    public int check_id_have_enter(String id)
    {
        for (int i=0;i<ts.length;i++)
        {
            if (ts[i]!=null && ts[i].getId_employee().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
    public void add(TimeSheetMonth nts)
    {
        ts=Arrays.copyOf(ts,ts.length+1);
        ts[ts.length-1]=nts;
        quicksort(ts,0,ts.length-1);
    }
    public TimeSheetMonth[] deleted(String idx,int month,int year)
    {
        if (ts==null || ts.length==0)
            return ts;
        int pos=-1;
        for (int i=0;i<ts.length;i++)
        {
            if(idx.equals(ts[i].getId_employee()) && month==ts[i].getMonth() && year==ts[i].getYear())
            {
                pos=i;
                break;
            }
        }
        if (pos==-1)
        {
            System.out.println("Employee not on the list");
            return ts;
        }
        for (int i=pos;i<ts.length-1;i++)
            ts[i]=ts[i+1];
        ts=Arrays.copyOf(ts,ts.length-1);
        return ts;
    }

    public TimeSheetMonth search(String idx,int month,int year)
    {
       for (int i=0;i<ts.length;i++)
       {
           if (idx.equalsIgnoreCase(ts[i].getId_employee()) && month == ts[i].getMonth() && ts[i].getYear()==year)
           {
               return ts[i];
           }
       }
       return null;
    }
    public int partision(TimeSheetMonth ts[], int l, int r)
    {
        TimeSheetMonth privot=ts[r];
        int i=l-1;
        for (int j=l;j<r;j++)
        {
            if (Integer.parseInt(ts[j].getId_employee())<=Integer.parseInt(privot.getId_employee()))
            {
                i++;
                TimeSheetMonth temp = ts[i];
                ts[i] = ts[j];
                ts[j] = temp;
            }
        }
        ++i;
        TimeSheetMonth temp = ts[i];
        ts[i] = ts[r];
        ts[r] = temp;
        return i;
    }
    public void quicksort(TimeSheetMonth ts[], int l, int r)
    {
        if (l<r)
        {
            int p=partision(ts, l, r);
            quicksort(ts,l,p-1);
            quicksort(ts,p+1,r);
        }
    }
    public void dayly_employee_statistics(int daycheck,int monthcheck,int yearcheck)
    {
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n","ID","Date","Time in","Time out","Status");
        for (int i=0;i<ts.length;i++)
        {
            if (monthcheck==ts[i].getMonth() && yearcheck==ts[i].getYear())
            {
                TimeSheetDay[] tsd=ts[i].getData();
                for (int j=0;j<tsd.length;j++)
                {
                    if (daycheck==tsd[j].getDay())
                    {
                        tsd[i].outday();
                    }
                }
            }
        }
    }
    public void monthly_employee_statistics(int monthcheck,int yearcheck)
    {
        for (int i=0;i<ts.length;i++)
        {
            if (monthcheck==ts[i].getMonth() && yearcheck==ts[i].getYear())
            {
               ts[i].out();
            }
        }
    }

    public void number_of_employee_statistics()
    {
        int employee=ts.length;
        System.out.println("Number of employee: "+employee);
    }
    public void update_timesheet(String idx,int dayx,int monthx,int yearx,Scanner sc)
    {
        TimeSheetDay record =null;
        for (int i=0;i<ts.length;i++)
        {
            if (monthx==ts[i].getMonth() && yearx==ts[i].getYear() && idx.equalsIgnoreCase(ts[i].getId_employee()))
            {
               TimeSheetDay[] tsd=ts[i].getData();
               for (int j=0;j<tsd.length;j++)
               {
                   if (dayx==tsd[j].getDay())
                   {
                       record=tsd[j];
                       break;
                   }
               }
               break;
            }
        }
        if (record==null)
        {
            System.out.println("No record found for this date.");
            return;
        }
        int x;
        do
        {
            System.out.println("1. Time in");
            System.out.println("2. Time out");
            System.out.println("0. Exit");
            System.out.print("Chose your option: ");
            x=sc.nextInt();
            sc.nextLine();
            if (x==1)
            {
                System.out.print("Enter new Time in (HH:MM): ");
                String timeinx=sc.nextLine();
                record.setTime_in(timeinx);
                System.out.println("Enter to continue.....");
                sc.nextLine();
            }
            else if (x==2)
            {
                System.out.print("Enter new Time out (HH:MM): ");
                String timeoutx=sc.nextLine();
                record.setTime_out(timeoutx);
                System.out.println("Enter to continue.....");
                sc.nextLine();
            }
            else if (x>2)
            {
                System.out.println("Error please chose again");
                System.out.println("Enter to continue.....");
                sc.nextLine();
            }
        }
        while (x!=0);
    }
    public int randomMonth()
    {
        return 1+(int)(Math.random()*12);
    }
    public int randomYear()
    {
        return 2023+(int)(Math.random()*2);
    }





    //get
    public TimeSheetMonth[] getTs()
    {
        return ts;
    }
    public int getMinYear() {
        int minYear = Integer.MAX_VALUE;
        for (TimeSheetMonth tm : ts) {
            if (tm.getYear() < minYear) minYear = tm.getYear();
        }
        return minYear;
    }

    public int getMinMonth() {
        int year = getMinYear();
        int minMonth = 13;
        for (TimeSheetMonth tm : ts) {
            if (tm.getYear() == year && tm.getMonth() < minMonth) minMonth = tm.getMonth();
        }
        return minMonth;
    }
    //set
    public void setTs(TimeSheetMonth[] ts)
    {
        this.ts = ts;
    }
}