import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class listtimesheet 
{
    private timesheet[] ts;
    private int n;//bo
    private final Scanner sc=new Scanner(System.in);
    
   
    public listtimesheet()
    {
        ts=new timesheet[0];
    }
    public listtimesheet(listtimesheet lt)//sua
    {
        this.n=lt.n;
    }
    
    
    public void enter()
    {
        System.out.print("enter number of employee: ");
        n=sc.nextInt();
        ts=new timesheet[n];
        for (int i=0;i<n;i++)
        {
            ts[i]=new timesheet();
            ts[i].enter();
        }
    }
    public void out()
    {
        System.out.println("data timesheet employee");
        for (int i=0;i<n;i++)
        {
            System.out.println("timesheet "+(i+1)+":");
            ts[i].out();
            System.out.println("---------------------------");
        }
    }
    public timesheet add(timesheet nts)
    {
        ts=Arrays.copyOf(ts,ts.length+1);
        ts[n]=nts;
        n++;
        return nts;
    }
    public int deleted(String idx)
    {
        int pos=-1;
        for (int i=0;i<n;i++)
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
            return n;
        }
        for (int i=pos;i<n-1;i++)
            ts[i]=ts[i+1];
        n--;
        return n;
    }
    public void writetofile()
    {
        File file=new File("timesheet.txt");
        try
        {
            if (!file.exists())
            {
                file.createNewFile();
                System.out.println("File 'timesheet.txt' created successfully");
            }
            FileWriter fw=new FileWriter("timesheet.txt",true);
            for (int i=0;i<n;i++)
            {
                timesheet t=ts[i];
                fw.write("ID clock: "+t.getId_clock()+"\n");
                fw.write("ID employee: "+t.getId_employee()+"\n");
                fw.write("Month: " + t.getMonth() + ", Year: " + t.getYear() + "\n");
                fw.write("Days worked: ");
                int[] days = t.getDay();
                for (int j = 0; j < days.length; j++)
                {
                    fw.write(days[j] + " ");
                }
                fw.write("\n---------------------------\n");
            }
            fw.close();
            System.out.println("Data written to 'timesheet.txt' successfully");
        }
        catch (IOException e)
        {
            System.out.println("error file: "+e.getMessage());
        }
    }
    public void readfile()// sua
    {
        try
        {
            File file=new File("timesheet.txt");
            if (!file.exists())
            {
                System.out.println("File 'timesheet.txt' haven't found");
                return;
            }
            Scanner reader=new Scanner(file);
            while(reader.hasNextLine())
            {
                String line=reader.nextLine();
                System.out.println(line);
            }
            reader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("error file: "+e.getMessage());
        }
    }
    //get
    public timesheet[] getTs() 
    {
        return ts;
    }
    //set
    public void setTs(timesheet[] ts) 
    {
        this.ts = ts;
    }
}
