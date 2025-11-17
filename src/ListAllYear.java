import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class ListAllYear implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private ListAllMonth[] lam;

    public ListAllYear (){}
    public ListAllYear(ListAllMonth[] lam)
    {
        this.lam=lam;
    }
    public ListAllYear(ListAllYear lay)
    {
        this.lam=lay.lam;
    }

    public void enter()
    {
        System.out.print("Enter number of year: ");
        int n=sc.nextInt();
        sc.nextLine();
        int year=2023+(int)(Math.random()*2);
        ListEmployee l=new ListEmployee();
        l.readFromFile();
        l.put();
        lam=new ListAllMonth[n];
        for (int i=0;i<n;i++)
        {
           lam[i]=new ListAllMonth();
            lam[i].enter();
        }
    }
    public void out()
    {
        quicksort(lam,0, lam.length-1);
        for (int i=0;i<lam.length;i++)
        {
            lam[i].out();
        }
    }
    public void add(TimeSheetMonth tsm) {
        if (lam == null) {
            lam = new ListAllMonth[1];
            ListAllMonth newMonth = new ListAllMonth();
            newMonth.add(tsm);   // gọi add của ListAllMonth
            lam[0] = newMonth;
        } else {
            lam = Arrays.copyOf(lam, lam.length + 1);
            ListAllMonth newMonth = new ListAllMonth();
            newMonth.add(tsm);
            lam[lam.length - 1] = newMonth;
        }

        quicksort(lam, 0, lam.length - 1);
    }

    public void delete(String idx,int month,int year)
    {
        if (lam==null || lam.length==0)
            return;
        for (int i=0;i<lam.length;i++)
        {
            lam[i].delete(idx,month,year);
        }
    }
    public void update(String idx,int dayx,int monthx,int yearx)
    {
        TimeSheetDay record=null;
        for (int i=0;i<lam.length;i++)
        {
          ListTimeSheetMonth[] ltsm=lam[i].getData();
          for (int j=0;j<ltsm.length;j++)
          {
              TimeSheetMonth[] tsm=ltsm[j].getTs();
              for (int k=0;k<tsm.length;k++)
              {
                  if (idx.equalsIgnoreCase(tsm[k].getId_employee()) && monthx==tsm[k].getMonth() && yearx==tsm[k].getYear())
                  {
                      TimeSheetDay[] tsd=tsm[k].getData();
                      for (int l=0;l<tsd.length;j++)
                      {
                          if (dayx== tsd[l].getDay())
                          {
                              record=tsd[l];
                              break;
                          }
                      }
                      break;
                  }
              }
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
            else if (x>2)
            {
                System.out.println("Error please chose again");
                System.out.println("Enter to continue.....");
                sc.nextLine();
            }
        }
        while (x!=0);
    }
    public TimeSheetMonth searching(String idx,int month,int  year)
    {
        for  (int i=0;i<lam.length;i++)
        {
            if(lam[i]!=null)
            {
                TimeSheetMonth result=lam[i].searching(idx,month,year);
                if (result!=null)
                    return result;
            }
        }
        return null;
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
            ListAllYear lay = (ListAllYear) ois.readObject();
            this.lam=lay.lam;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void statistic_by_employee()
    {
        int workingday=0,late=0,offday=0,overtime=0;
        System.out.println("Enter id: ");
        String idx=sc.nextLine();
        for (int i=0;i<lam.length;i++)
        {
            lam[i].statistics_by_employee(idx);
        }
    }
    public void statistics_by_month()
    {
        System.out.print("Enter month: ");
        int monthx=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter year: ");
        int yearx=sc.nextInt();
        sc.nextLine();
        for (int i=0;i<lam.length;i++)
        {
           lam[i].employee_statistics_by_month(monthx,yearx);
        }
    }
    public int partition(ListAllMonth ts[], int l, int r) {
        int pivotYear = ts[r].getMinyear();
        int pivotMonth = ts[r].getMinmonth();
        int i = l - 1;
        for (int j = l; j < r; j++) {
            int yearJ = ts[j].getMinyear();
            int monthJ = ts[j].getMinmonth();
            if (yearJ < pivotYear || (yearJ == pivotYear && monthJ < pivotMonth)) {
                i++;
                ListAllMonth temp = ts[i];
                ts[i] = ts[j];
                ts[j] = temp;
            }
        }
        i++;
        ListAllMonth temp = ts[i];
        ts[i] = ts[r];
        ts[r] = temp;
        return i;
    }
    public void quicksort(ListAllMonth ts[], int l, int r)
    {
        if (l<r)
        {
            int p=partition(ts, l, r);
            quicksort(ts,l,p-1);
            quicksort(ts,p+1,r);
        }
    }




}
