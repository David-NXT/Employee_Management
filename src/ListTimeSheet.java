import java.util.*;
import java.io.*;
public class ListTimeSheet implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private TimeSheetMonth[] ts;


    public ListTimeSheet()
    {
        ts=new TimeSheetMonth[0];
    }
    public ListTimeSheet(TimeSheetMonth[] ts)
    {
        this.ts=ts;
    }
    public ListTimeSheet(ListTimeSheet lt)
    {
        this.ts=lt.ts;
    }

    public void enter()
    {
        System.out.print("enter number of employee: ");
        int n=sc.nextInt();
        ts=new TimeSheetMonth[n];
        for (int i=0;i<n;i++)
        {
            ts[i]=new TimeSheetMonth();
            ts[i].enter();
        }
    }
    public void out()
    {
        System.out.println("data timesheet employee");
        for (int i=0;i<ts.length;i++)
        {
            System.out.println("timesheet "+(i+1)+":");
            ts[i].out();
            quicksort(ts,i+1,ts.length-1);
            System.out.println("---------------------------");
        }
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
    public void writeToFile()
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
    public void ReadFromFile()
    {
        String basepath = System.getProperty("user.dir");
        File file = new File(basepath + File.separator + "data" + File.separator + "data_timesheet_Employee");

        if (!file.exists()) {
            System.out.println("file is not exist");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListTimeSheet lts = (ListTimeSheet) ois.readObject();
            this.ts=lts.ts;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void search(String idx)
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
        System.out.println("data timesheet employee");
        System.out.println("timesheet "+(pos+1)+":");
        ts[pos].out();
        System.out.println("---------------------------");
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
    public void rollcallmonth()
    {
        System.out.print("Enter month: ");
        int monthcheck=sc.nextInt();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n","ID","Date","Time in ","Time out","Status");
        for (int i=0;i<ts.length;i++)
        {
            ts[i].monthcheck(monthcheck);
        }
    }
    public void rollcallday()
    {
        System.out.print("Enter day: ");
        int daycheck=sc.nextInt();
        System.out.printf("%-15s %-15s %-15s %-15s\n","ID","Time in","Time out","Status");
        for (int i=0;i<ts.length;i++)
        {
            ts[i].daycheck(daycheck);
        }
    }
    public void static_()
    {

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