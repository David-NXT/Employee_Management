import java.io.Serializable;
import java.util.*;
import java.io.*;
public class ListAllMonth implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private ListTimeSheetMonth[] list;

    public ListAllMonth(){}
    public ListAllMonth(ListTimeSheetMonth[] list){this.list=list;}
    public ListAllMonth(ListAllMonth lt)
    {
        this.list=lt.list;
    }

    public void enter()
    {
        System.out.print("Enter number of month: ");
        int n=sc.nextInt();
        sc.nextLine();
        list=new ListTimeSheetMonth[n];
        for (int i=0;i<n;i++)
        {
            list[i]=new ListTimeSheetMonth();
            list[i].enter();
        }
    }
    public void out()
    {
        quicksort(list,0,list.length-1);
        for (int i=0;i<list.length;i++)
        {
            list[i].out();
        }
    }
    public void add(TimeSheetMonth ts)
    {
        if (list == null)
        {
            list = new ListTimeSheetMonth[1];
            list[0] = new ListTimeSheetMonth();
            list[0].add(ts);
        }
        else
        {
            list = Arrays.copyOf(list, list.length + 1);
            ListTimeSheetMonth newmonth = new ListTimeSheetMonth();
            newmonth.add(ts);
            list[list.length - 1] = newmonth;
        }

        quicksort(list, 0, list.length - 1);
    }
    public void delete(String idx,int month,int year)
    {
       if (list==null || list.length==0)
           return;
        for (int i=0;i< list.length;i++)
       {
           TimeSheetMonth[] newts=list[i].deleted(idx,month,year);
           list[i].setTs(newts);
       }
    }
    public void update(String idx,int dayx,int monthx,int yearx)
    {
        TimeSheetDay record=null;
        for (int i=0;i<list.length;i++)
        {
            TimeSheetMonth[] tsm=list[i].getTs();
            for (int j=0;j<tsm.length;j++)
            {
                if (idx.equalsIgnoreCase(tsm[i].getId_employee()) && monthx==tsm[j].getMonth() && yearx==tsm[j].getYear())
                {
                    TimeSheetDay[] tsd=tsm[j].getData();
                    for (int k=0;k<tsd.length;k++)
                    {
                        if (dayx==tsd[k].getDay())
                        {
                            record=tsd[k];
                            break;
                        }
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
    public TimeSheetMonth searching(String idx,int month,int year)
    {
        for (int i=0;i<list.length;i++)
        {
            TimeSheetMonth result=list[i].search(idx,month,year);
            if (result!=null)
                return result;
        }
        return null;
    }

    public void employee_statistics_by_day()
    {
        System.out.print("Enter day: ");
        int dayx=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter month: ");
        int monthx=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter year: ");
        int yearx=sc.nextInt();
        sc.nextLine();
        for (int i=0;i<list.length;i++)
        {
            list[i].dayly_employee_statistics(dayx,monthx,yearx);
        }
    }
    public void employee_statistics_by_month(int monthx,int yearx)
    {
        for (int i=0;i<list.length;i++)
        {
            list[i].monthly_employee_statistics(monthx,yearx);
        }
    }
    public void statistics_by_employee(String idx)
    {
        int workingday=0,late=0,offday=0,overtime=0;
        for (int i=0;i<list.length;i++)
        {
            TimeSheetMonth[] tsm=list[i].getTs();
            for (int j=0;j<tsm.length;j++)
            {
                if (idx.equalsIgnoreCase(tsm[j].getId_employee()))
                {

                    TimeSheetDay[] tsd=tsm[j].getData();
                    workingday += tsd.length;
                    offday += dayinmonth(tsm[j].getMonth(), tsm[j].getYear()) - tsd.length;
                    for (int k=0;k<tsd.length;k++)
                    {
                        String status = tsd[k].getStatus().toLowerCase();
                        if (status.contains("late")) late++;
                        if (status.contains("over time")) overtime++;
                    }
                }
            }
        }
        System.out.println("Employee ID: "+idx);
        System.out.println("Day off: "+offday);
        System.out.println("Working day: "+workingday);
        System.out.println("late: "+late);
        System.out.println("Over time: "+overtime);
    }
    public int dayinmonth(int month,int year)
    {
        switch (month)
        {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            return 31;
            case 2:
                if (year%4==0)
                    return 29;
                else
                    return 28;
            default:
                return 30;
        }
    }
    public void statistics_by_status()
    {
        System.out.print("Enter status: ");
        String statusx=sc.nextLine();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n","ID","Date","Time in","Time out","Status");
        for (int i=0;i<list.length;i++)
        {
            TimeSheetMonth[] tsm=list[i].getTs();
            for (int j=0;j<tsm.length;j++)
            {
                TimeSheetDay[] tsd=tsm[j].getData();
                for (int k=0;k<tsd.length;k++)
                {
                    if (statusx.equalsIgnoreCase(tsd[k].getStatus()))
                    {
                        tsd[k].outday();
                    }
                }
            }
        }
    }
    public int partition(ListTimeSheetMonth ts[], int l, int r) {
        int pivotYear = ts[r].getMinYear();
        int pivotMonth = ts[r].getMinMonth();
        int i = l - 1;
        for (int j = l; j < r; j++) {
            int yearJ = ts[j].getMinYear();
            int monthJ = ts[j].getMinMonth();
            if (yearJ < pivotYear || (yearJ == pivotYear && monthJ < pivotMonth)) {
                i++;
                ListTimeSheetMonth temp = ts[i];
                ts[i] = ts[j];
                ts[j] = temp;
            }
        }
        i++;
        ListTimeSheetMonth temp = ts[i];
        ts[i] = ts[r];
        ts[r] = temp;
        return i;
    }
    public void quicksort(ListTimeSheetMonth ts[], int l, int r)
    {
        if (l<r)
        {
            int p=partition(ts, l, r);
            quicksort(ts,l,p-1);
            quicksort(ts,p+1,r);
        }
    }
    public int getMinyear()
    {
        int minyear =Integer.MAX_VALUE;
        for (int i=0;i<list.length;i++)
        {
            TimeSheetMonth[] tsm=list[i].getTs();
            for (int j=0;j<tsm.length;j++)
            {
                if (tsm[j].getYear()<minyear)
                    minyear=tsm[j].getYear();
            }
        }
        return minyear;
    }
    public int getMinmonth()
    {
        int minmonth=13;
        int year=getMinyear();
        for (int i=0;i<list.length;i++)
        {
            TimeSheetMonth[] tsm=list[i].getTs();
            for (int j=0;j<tsm.length;j++)
            {
                if (tsm[j].getYear()==year && tsm[j].getMonth()<minmonth)
                    minmonth=tsm[j].getMonth();
            }
        }
        return minmonth;
    }
//    get
    public ListTimeSheetMonth[] getData(){return list;}
//    set
    public void setData(ListTimeSheetMonth[] list){this.list=list;}
}
