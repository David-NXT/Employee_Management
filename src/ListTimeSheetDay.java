import java.util.*;
import java.io.*;
public class ListTimeSheetDay implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private TimeSheetDay[] td;


    public ListTimeSheetDay()
    {
        td=new TimeSheetDay[0];
    }
    public ListTimeSheetDay(TimeSheetDay[] td)
    {
        this.td=td;
    }
    public ListTimeSheetDay(ListTimeSheetDay lt)
    {
        this.td=lt.td;
    }



    public void enter()
    {
        System.out.print("Enter number of employee: ");
        int n=sc.nextInt();
        sc.nextLine();
        td=new TimeSheetDay[n];
        int year=randomYear();
        int month=randomMonth();
        int day=randomDay(month,year);
        ListEmployee l=new ListEmployee();
        l.readFromFile();
        l.put();
        for (int i=0;i<n;i++)
        {
            String id;
            boolean check=false;
            do
            {
                System.out.print("Enter your ID: ");
                id=sc.nextLine();
                if (l.isEmployee(id)==-1)
                {
                    System.out.println("Invaible ID");
                    check=false;
                }
                else if (check_id_have_enter(id)!=-1)
                {
                    System.out.println("This ID have already exit");
                    check=false;
                }
                else
                    check=true;
            }
            while (!check);
            td[i]=new TimeSheetDay();
            td[i].setId_employee(id);
            td[i].setDay(day);
            td[i].setMonth(month);
            td[i].setYear(year);
            td[i].enter();
        }
    }
    public void out()
    {
        System.out.println("Data timesheet employee");
        number_of_employee_statistics();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n","ID","Date","Time in","Time out","Status");
        quicksort(td,0,td.length-1);
        for (int i=0;i<td.length;i++)
        {
            td[i].outday();
        }
    }
    public int check_id_have_enter(String id)
    {
        for (int i=0;i<td.length;i++)
        {
            if (td[i]!=null && td[i].getId_employee().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
    public void add(TimeSheetDay nts)
    {
        nts.setDay(td[0].getDay());
        nts.setMonth(td[0].getMonth());
        nts.setYear(td[0].getYear());
        td=Arrays.copyOf(td,td.length+1);
        td[td.length-1]=nts;
        quicksort(td,0,td.length-1);
    }
    public void deleted(String idx)
    {
        int pos=-1;
        for (int i=0;i<td.length;i++)
        {
            if(idx.equals(td[i].getId_employee()))
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
        for (int i=pos;i<td.length-1;i++)
            td[i]=td[i+1];
        td=Arrays.copyOf(td,td.length-1);
    }
    public void writetofile()
    {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");
        if (!dir.exists())
        {
            dir.mkdir();
        }
        File file = new File(dir,"data_timesheetday_Employee");
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
        File file = new File(basepath + File.separator + "data" + File.separator + "data_timesheetday_Employee");

        if (!file.exists()) {
            System.out.println("file is not exist");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListTimeSheetDay ltd = (ListTimeSheetDay) ois.readObject();
            this.td=ltd.td;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public TimeSheetDay search(String idx)
    {
        for (int i=0;i<td.length;i++)
        {
            if (td[i] != null && td[i].getId_employee().trim().equalsIgnoreCase(idx.trim()))
                return td[i];
        }
        return null;
    }
    public int partision(TimeSheetDay ts[], int l, int r)
    {
        TimeSheetDay privot=ts[r];
        int i=l-1;
        for (int j=l;j<r;j++)
        {
            if (Integer.parseInt(ts[j].getId_employee())<=Integer.parseInt(privot.getId_employee()))
            {
                i++;
                TimeSheetDay temp = ts[i];
                ts[i] = ts[j];
                ts[j] = temp;
            }
        }
        ++i;
        TimeSheetDay temp = ts[i];
        ts[i] = ts[r];
        ts[r] = temp;
        return i;
    }
    public void quicksort(TimeSheetDay ts[], int l, int r)
    {
        if (l<r)
        {
            int p=partision(ts, l, r);
            quicksort(ts,l,p-1);
            quicksort(ts,p+1,r);
        }
    }
    public void employee_statistics_status()
    {
        System.out.print("Enter status: ");
        String statusx=sc.nextLine();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n","ID","Date","Time in","Time out","Status");
        for (int i=0;i<td.length;i++)
        {
            if (statusx.equalsIgnoreCase(td[i].getStatus()))
            {
                td[i].outday();
            }
        }
    }
    public void number_of_employee_statistics()
    {
       int employee=td.length;
       System.out.println("Number of employee: "+employee);
    }
    public void update_timesheet(String idx)
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
        TimeSheetDay id_found=null;
        for (int i=0;i<td.length;i++)
        {
            if (idx.equalsIgnoreCase(td[i].getId_employee()) && dayx==td[i].getDay() && monthx==td[i].getMonth() && yearx==td[i].getYear())
            {
                id_found=td[i];
                break;
            }
        }
        if (id_found==null)
        {
            System.out.println("Employee with this ID and date not found.");
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
                System.out.print("Enter time in (HH:MM): ");
                String timeinx=sc.nextLine();
                id_found.setTime_in(timeinx);
                System.out.println("Enter to continue...");
                sc.nextLine();
            }
            else if (x==2)
            {
                System.out.print("Enter time out (HH:MM): ");
                String timeoutx=sc.nextLine();
                id_found.setTime_out(timeoutx);
                System.out.println("Enter to continue...");
                sc.nextLine();
            }
            else if (x>2)
            {
                System.out.println("Error please chose again");
                System.out.println("Enter to continue...");
                sc.nextLine();
            }
        }
        while(x!=0);
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
    public int randomMonth()
    {
        return 1+(int)(Math.random()*12);
    }
    public int randomYear()
    {
        return 2023+(int)(Math.random()*2);
    }



    //get
    public TimeSheetDay[] getTd()
    {
        return td;
    }
    //set
    public void setTd(TimeSheetDay[] td)
    {
        this.td = td;
    }
}