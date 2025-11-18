
import java.util.*;
public class ManageTimeSheet
{
    Scanner sc=new Scanner(System.in);
    ListTimeSheetMonth lt = new ListTimeSheetMonth();

    public void menu()
    {
        int x;
        do
        {
            System.out.println("1. Create new list Timesheet");
            System.out.println("2. Print list Timesheet");
            System.out.println("3. Add Timesheet");
            System.out.println("4. Delete Timesheet");
            System.out.println("5. Check employee month");
            System.out.println("6. Check employee day");
            System.out.println("7. Update TimeSheet");
            System.out.println("8. Search employee");
            System.out.println("9. Total working of employee");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            x=sc.nextInt();
            sc.nextLine();
            if (x==1)
            {
                lt.enter();
                lt.writetofile();
                System.out.print("Enter to continue...");
                sc.nextLine();
            }
            else if (x==2)
            {
                lt.readfromfile();
                lt.out();
                System.out.print("Enter to continue...");
                sc.nextLine();
            }
            else if (x==3)
            {
                add_timesheet();
                System.out.print("Enter to continue...");
                sc.nextLine();
            }
            else if (x==4)
            {
                delete_employee();
                System.out.println("Enter to continue...");
                sc.nextLine();
            }
            else if (x==5)
            {
                lt.Monthly_employee_statistics();
                System.out.print("Enter to continue...");
                sc.nextLine();
            }
            else if (x==6)
            {
                lt.Dayly_employee_statistics();
                System.out.print("Enter to continue...");
                sc.nextLine();
            }
            else if (x==7)
            {
                update_employee();
                System.out.print("Enter to continue...");
                sc.nextLine();
            }
            else if (x==8)
            {
                search_id();
                System.out.print("Enter to continue...");
                sc.nextLine();
            }
            else if (x==9)
            {
                totalworking();
                System.out.print("Enter to continue...");
                sc.nextLine();
            }
            else if (x>9)
            {
                System.out.println("Error please chose again");
                System.out.print("Enter to continue...");
                sc.nextLine();
            }

        }
        while (x!=0);
    }
    public void search_id()
    {
        System.out.print("Enter Id you want searching: ");
        String idx=sc.nextLine();
        lt.search(idx);
        lt.out();
    }
    public void update_employee()
    {
        lt.readfromfile();
        lt.out();
        System.out.print("Enter Id you want update: ");
        String idx=sc.nextLine();
        lt.update_timesheet(idx);
        lt.writetofile();
    }
    public void delete_employee()
    {
        lt.readfromfile();
        lt.out();
        System.out.print("Enter id employee to delete: ");
        String idx=sc.nextLine();
        lt.deleted(idx);
        lt.writetofile();
    }
    public void add_timesheet()
    {
        ListEmployee l=new ListEmployee();
        l.readFromFile();
        Employee[] list=l.getData();
        l.put();
        lt.readfromfile();
        System.out.print("Number of timesheet you want add: ");
        int n=sc.nextInt();
        System.out.print("Enter month: ");
        int month=sc.nextInt();
        System.out.print("Enter year: ");
        int year=sc.nextInt();
        sc.nextLine();
        for (int i=0;i<n;i++)
        {
            String id=list[i].getId();
//            boolean check=false;
//            do
//            {
//                System.out.print("Enter your ID: ");
//                id = sc.nextLine();
//                if (lt.check_id_have_enter(id) != -1)
//                {
//                    System.out.print("This ID have already exit");
//                    check = false;
//                }
//                else
//                    check = true;
//            }
//            while (!check);
            TimeSheetMonth tm=new TimeSheetMonth();
            tm.setMonth(month);
            tm.setYear(year);
            tm.setId_employee(id);
            tm.enter();
            lt.add(tm);
        }
        lt.writetofile();
    }
    public void totalworking()
    {
        ListEmployee l=new ListEmployee();
        l.readFromFile();
        l.put();
        lt.readfromfile();
        System.out.print("Enter id: ");
        String id=sc.nextLine();
        lt.totaltime(id);
        if (lt.totaltime(id)==0)
        {
            System.out.print("Invalible id");
        }
        else
            System.out.println("Total working: "+lt.totaltime(id));

    }

}
