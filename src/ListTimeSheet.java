import java.util.*;
import java.io.*;
public class ListTimeSheet implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private TimeSheet[] ts;
    
   
    public ListTimeSheet()
    {
        ts=new TimeSheet[0];
    }
    
    public void enter()
    {
        System.out.print("enter number of employee: ");
        int n=sc.nextInt();
        ts=new TimeSheet[n];
        for (int i=0;i<n;i++)
        {
            ts[i]=new TimeSheet();
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
    public void add(TimeSheet nts)
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
    public int partision(TimeSheet ts[], int l, int r)
    {
        TimeSheet privot=ts[r];
        int i=l-1;
        for (int j=l;j<r;j++)
        {
            if (ts[j].getId_employee().compareToIgnoreCase(privot.getId_employee()) <= 0)
            {
                i++;
                TimeSheet temp = ts[i];
                ts[i] = ts[j];
                ts[j] = temp;
            }
        }
        ++i;
        TimeSheet temp = ts[i];
        ts[i] = ts[r];
        ts[r] = temp;
        return i;
    }
    public void quicksort(TimeSheet ts[], int l, int r)
    {
        if (l<r)
        {
            int p=partision(ts, l, r);
            quicksort(ts,l,p-1);
            quicksort(ts,p+1,r);
        }
    }
    public void sortById() 
    {
        if (ts.length == 0) 
        {
            System.out.println("Danh sach rong");
            return;
        }
        quicksort(ts, 0, ts.length - 1);
        System.out.println("da sap xep theo id nhan vien");
    }
    public void rollcall()
    {
        System.out.print("Enter month: ");
        int monthcheck=sc.nextInt();
        for (int i=0;i<ts.length;i++)
        {
            if (monthcheck==ts[i].getMonth())
            {
                ts[i].out();
            }
        }
    }

    //get
    public TimeSheet[] getTs()
    {
        return ts;
    }
    //set
    public void setTs(TimeSheet[] ts)
    {
        this.ts = ts;
    }
}
