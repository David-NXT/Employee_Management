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
    public ListTimeSheetMonth nextmonth(int month,int year) {
        ListTimeSheetMonth newlist = new ListTimeSheetMonth();
        List<TimeSheetMonth> temp = new ArrayList<>();

        for (TimeSheetMonth oldts : ts) {
            if (!check(oldts.getId_employee(), month, year)) {
                TimeSheetMonth newts = new TimeSheetMonth();
                newts.setId_employee(oldts.getId_employee());
                TimeSheetDay[] olddays = oldts.getData();
                TimeSheetDay[] newdays = new TimeSheetDay[olddays.length];

                for (int j = 0; j < olddays.length; j++) {
                    TimeSheetDay oldday = olddays[j];
                    TimeSheetDay newday = new TimeSheetDay();
                    newday.setId_employee(oldday.getId_employee());
                    newday.setDay(oldday.getDay());
                    newday.setMonth(month);
                    newday.setYear(year);
                    newday.enter();
                    newdays[j] = newday;
                }

                newts.setTd(newdays);
                temp.add(newts);
            }
        }

        newlist.ts = temp.toArray(new TimeSheetMonth[0]);
        quicksort(newlist.ts, 0, newlist.ts.length - 1);
        return newlist;
    }

    public void enter()
    {
        ListEmployee l=new ListEmployee();
        l.readFromFile();
        Employee[] list=l.getData();
        int month=1+(int)(Math.random()*12);
        int year=2023+(int)(Math.random()*3);
        System.out.print("Enter number of employee: ");
        int n=sc.nextInt();
        sc.nextLine();
        ts=new TimeSheetMonth[n];
        l.readFromFile();
        l.put();
        for (int i=0;i<n;i++)
        {
            String id;
            boolean check;
            do
            {
                System.out.print("Enter id: ");
                id=sc.nextLine();
                if (check_id_have_enter(id)!=-1)
                {
                    System.out.println("This id have already yet");
                    check=false;
                }
                else if (l.isEmployee(id)==-1)
                {
                    System.out.println("Invaible id");
                    check=false;
                }
                else
                    check=true;
            }
            while(!check);
            ts[i]=new TimeSheetMonth();
            ts[i].setMonth(month);
            ts[i].setYear(year);
            ts[i].setId_employee(id);
            ts[i].enter();
        }
    }
    public void out()
    {
        System.out.println("data timesheet employee");
        quicksort(ts,0,ts.length-1);
        for (int i=0;i<ts.length;i++)
        {
            System.out.println("timesheet "+(i+1)+":");
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
    public boolean check(String idx,int month,int year)
    {
        for (int i=0;i<ts.length;i++)
        {
            if (idx.equalsIgnoreCase(ts[i].getId_employee()) && month==ts[i].getMonth()  && year==ts[i].getYear())
            {
                return true;
            }
        }
        return false;
    }
    public void add(TimeSheetMonth nts)
    {
        ts=Arrays.copyOf(ts,ts.length+1);
        ts[ts.length-1]=nts;
        quicksort(ts,0,ts.length-1);
    }
    public void deleted(String idx)
    {
        int pos=-1;
        for (int i=0;i<ts.length;i++)
        {
            if(idx.equals(ts[i].getId_employee()))
            {
                pos=i;
                break;
            }
        }
        if (pos==-1)
        {
            System.out.println("employee not on the list");
            return;
        }
        for (int i=pos;i<ts.length-1;i++)
            ts[i]=ts[i+1];
        ts=Arrays.copyOf(ts,ts.length-1);
    }
    public void writetofile()
    {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");
        if (!dir.exists())
        {
            dir.mkdir();
        }
        File file = new File(dir,"data_timesheet_Employee");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
        {
            oos.writeObject(this);
            System.out.println("Saved!");
        }
        catch (IOException e)
        {
            System.out.println("File chua duoc ghi " + e);
        }
    }
    public void readfromfile()
    {
        String basepath = System.getProperty("user.dir");
        File file = new File(basepath + File.separator + "data" + File.separator + "data_timesheet_Employee");

        if (!file.exists()) {
            System.out.println("file is not exist");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListTimeSheetMonth lts = (ListTimeSheetMonth) ois.readObject();
            this.ts=lts.ts;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public TimeSheetMonth search(String idx)
    {
        for (int i=0;i<ts.length;i++)
        {
            if (idx.equalsIgnoreCase(ts[i].getId_employee()))
                return ts[i];
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
    public void Monthly_employee_statistics()
    {
        System.out.print("Enter month: ");
        int monthcheck=sc.nextInt();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n","ID","Date","Time in ","Time out","Status");
        for (int i=0;i<ts.length;i++)
        {
            ts[i].monthcheck(monthcheck);
        }
    }
    public void Dayly_employee_statistics()
    {
        System.out.print("Enter day: ");
        int daycheck=sc.nextInt();
        System.out.printf("%-15s %-15s %-15s %-15s\n","ID","Time in","Time out","Status");
        for (int i=0;i<ts.length;i++)
        {
            ts[i].daycheck(daycheck);
        }
    }
    public double totaltime(String idx)
    {
        double total=0;
        for (int i=0;i<ts.length;i++)
        {
            total=total+ts[i].totaltime(idx);
        }
        return total;
    }
    public void update_timesheet(String idx)
    {
        int pos=-1;
        for (int i=0;i<ts.length;i++)
        {
            if (idx.equalsIgnoreCase(ts[i].getId_employee()))
            {
                pos=i;
                break;
            }
        }
        if (pos==-1)
        {
            System.out.println("Invaible ID employee");
            return;
        }
        else
        {
            System.out.print("Enter day: ");
            int dayx=sc.nextInt();
            System.out.print("Enter month: ");
            int monthx=sc.nextInt();
            System.out.print("Enter year: ");
            int yearx=sc.nextInt();
            TimeSheetDay[] td=ts[pos].getData();
            TimeSheetDay record =null;
            for (int i=0;i<td.length;i++)
            {
                if (dayx==td[i].getDay() && monthx==td[i].getMonth() && yearx==td[i].getYear())
                {
                    record=td[i];
                    break;
                }
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
                else
                {
                    System.out.println("Error please chose again");
                    System.out.println("Enter to continue.....");
                    sc.nextLine();
                }
            }
            while (x!=0);
        }
    }



    //get
    public TimeSheetMonth[] getTs()
    {
        return ts;
    }
    //set
    public void setTs(TimeSheetMonth[] ts)
    {
        this.ts = ts;
    }
}