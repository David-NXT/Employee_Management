import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class rollcall
{
    private timesheet[] ts;
    private String result=" ";
    Scanner sc=new Scanner(System.in);

    public void check_employee() 
    {
        System.out.print("enter day: ");
        int daycheck=sc.nextInt();
        System.out.print("enter month: ");
        int monthcheck=sc.nextInt();
        System.out.print("enter year: ");
        int yearcheck=sc.nextInt();
        boolean found=false;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<ts.length;i++)
        {
            timesheet t=ts[i];
            if (t.getMonth()==monthcheck && t.getYear()==yearcheck)
            {
                int[] days=t.getDay();
                for (int j=0;j<days.length;j++)
                {
                    if(daycheck==days[j])
                    {
                        String line = "Employee ID: " + t.getId_employee() + " worked on " + daycheck + "/" + monthcheck + "/" + yearcheck + "\n";
                        System.out.print(line);
                        sb.append(line);
                        found = true;
                    }
                }
            }
        }
        if (found==false)
            System.out.println("no employee have working that day");
        result=sb.toString();
    }
    public void writetofile()
    {
        File file=new File("check_employee.txt");
        try
        {
            if (!file.exists())
            {
                file.createNewFile();
                System.out.println("File 'check_employee.txt' created successfully");
            }
            FileWriter fw=new FileWriter("check_employee.txt",true);
            fw.write(result);
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("error file: "+e.getMessage());
        }
    }
    public void readfile()
    {
        try
        {
            File file=new File("check_employee.txt");
            if (!file.exists())
            {
                System.out.println("File 'check_employee.txt' haven't found");
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
    //set
    public void setTs(timesheet[] ts) {this.ts = ts;}
    //get
    public timesheet[] getTs() {return ts;}
}
