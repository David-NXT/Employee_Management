import java.util.*;
public class ManageTimeSheet
{
        Scanner sc=new Scanner(System.in);
        private ListAllYear lay=new ListAllYear();
        private ListTimeSheetMonth ltm=new ListTimeSheetMonth();

        public void menumain()
        {
            int x;
            do
            {
                System.out.println("1. Create new list Timesheet");
                System.out.println("2. Print list Timesheet");
                System.out.println("3. Add Timesheet");
                System.out.println("4. Delete Timesheet");
                System.out.println("5. Check employee working day");
                System.out.println("6. Static employee by month");
                System.out.println("7. Update employee");
                System.out.println("8. Search employee");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                x=sc.nextInt();
                sc.nextLine();
                if (x==1)
                {
                    lay.enter();
                    lay.writetofile();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==2)
                {
                    lay.readfromfile();
                    lay.out();
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
                    lay.statistic_by_employee();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==6)
                {
                    lay.statistics_by_month();
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
            }
            while (x!=0);
        }
        public void search_id()
        {

            System.out.print("Enter Id you want searching: ");
            String idx=sc.nextLine();
            System.out.print("Enter month: ");
            int month=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter year: ");
            int year=sc.nextInt();
            sc.nextLine();
            lay.readfromfile();
            TimeSheetMonth result=lay.searching(idx,month,year);
            if (result!=null)
            {
                result.out();
            }
            else
                System.out.println("Employee not found");
        }

        public void update_employee()
        {
            lay.readfromfile();
            lay.out();
            System.out.print("Enter Id you want update: ");
            String idx=sc.nextLine();
            System.out.print("Enter day: ");
            int day=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter month: ");
            int month=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter year: ");
            int year=sc.nextInt();
            sc.nextLine();
            lay.update(idx,day,month,year);
            lay.writetofile();
        }
        public void delete_employee()
        {
            lay.readfromfile();
            lay.out();
            System.out.print("Enter id employee to delete: ");
            String idx=sc.nextLine();
            System.out.print("Enter month: ");
            int month=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter year: ");
            int year=sc.nextInt();
            sc.nextLine();
            lay.delete(idx,month,year);
            lay.writetofile();
        }
        public void add_timesheet()
        {
            ListEmployee l=new ListEmployee();
            l.readFromFile();
            l.put();
            lay.readfromfile();
            System.out.print("Number of timesheet you want add: ");
            int n=sc.nextInt();
            sc.nextLine();
            for (int i=0;i<n;i++)
            {
                String id;
                boolean check=false;
                do
                {
                    System.out.print("Enter your ID: ");
                    id = sc.nextLine();
                    if (ltm.check_id_have_enter(id) != -1)
                    {
                        System.out.println("This ID have already exit");
                        check = false;
                    }
                    else if (id.equalsIgnoreCase("out"))
                    {
                        return;
                    }
                    else
                        check = true;
                }
                while (!check);
                TimeSheetMonth tm=new TimeSheetMonth();
                System.out.print("Enter month: ");
                int month = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter year: ");
                int year = sc.nextInt();
                sc.nextLine();
                tm.setMonth(month);
                tm.setYear(year);
                tm.setId_employee(id);
                tm.enter();
                lay.add(tm);
            }
            lay.writetofile();
        }

}
