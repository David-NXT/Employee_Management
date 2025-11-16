import java.util.*;
public class ManageTimeSheet
{
        Scanner sc=new Scanner(System.in);
        private ListTimeSheetMonth ltm = new ListTimeSheetMonth();
        private ListTimeSheetDay ltd=new ListTimeSheetDay();

        public void menumain()
        {
            int x;
            do
            {
                System.out.println("1. Time Sheet day");
                System.out.println("2. Time sheet month");
                System.out.println("0. Exit");
                System.out.print("Chose your option: ");
                x=sc.nextInt();
                sc.nextLine();
                if (x==1)
                {
                    menu_list_time_sheet_day();
                    System.out.println("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==2)
                {
                    menu_list_time_sheet_month();
                    System.out.println("Enter to continue...");
                    sc.nextLine();
                }
                else if (x>2)
                {
                    System.out.println("Error please chose again");
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
            }
            while (x!=0);
        }
        public void menu_list_time_sheet_month()
        {
            int x;
            do
            {
                System.out.println("1. Create new list Timesheet");
                System.out.println("2. Print list Timesheet");
                System.out.println("3. Add Timesheet");
                System.out.println("4. Delete Timesheet");
                System.out.println("5. Check employee day");
                System.out.println("6. Update TimeSheet");
                System.out.println("7. Search employee");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                x=sc.nextInt();
                sc.nextLine();
                if (x==1)
                {
                    ltm.enter();
                    ltm.writetofile();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==2)
                {
                    ltm.readfromfile();
                    ltm.out();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==3)
                {
                    add_timesheet_month();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==4)
                {
                    delete_employee_month();
                    System.out.println("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==5)
                {
                    ltm.Dayly_employee_statistics();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==6)
                {
                    update_employee_month();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==7)
                {
                    search_id_month();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x>7)
                {
                    System.out.println("Error please chose again");
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
            }
            while (x!=0);
        }
        public void menu_list_time_sheet_day()
        {
            int x;
            do
            {
                System.out.println("1. Create new list Timesheet");
                System.out.println("2. Print list Timesheet");
                System.out.println("3. Add Timesheet");
                System.out.println("4. Delete Timesheet");
                System.out.println("5. Check employee status");
                System.out.println("6. Update TimeSheet");
                System.out.println("7. Search employee");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                x=sc.nextInt();
                sc.nextLine();
                if (x==1)
                {
                    ltd.enter();
                    ltd.writetofile();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==2)
                {
                    ltd.readfromfile();
                    ltd.out();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==3)
                {
                    add_timesheet_day();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==4)
                {
                    delete_employee_day();
                    System.out.println("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==5)
                {
                    ltd.employee_statistics_status();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==6)
                {
                    update_employee_day();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==7)
                {
                    search_id_day();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x>7)
                {
                    System.out.println("Error please chose again");
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
            }
            while (x!=0);
        }
        public void search_id_month()
        {
            ltm.readfromfile();
            System.out.print("Enter Id you want searching: ");
            String idx=sc.nextLine();
            TimeSheetMonth result=ltm.search(idx);
            if (result!=null)
            {
                result.out();
            }
            else
                System.out.println("Employee not found");
        }
        public void search_id_day()
        {
            ltd.readfromfile();
            System.out.print("Enter Id you want searching: ");
            String idx=sc.nextLine();
            TimeSheetDay result=ltd.search(idx);
            if (result!=null)
            {
                System.out.printf("%-15s %-15s %-15s %-15s %-15s\n","ID","Date","Time in","Time out","Status");
                result.outday();  // In riêng bản ghi
            }
            else
                System.out.println("Employee not found");

        }

        public void update_employee_month()
        {
            ltm.readfromfile();
            ltm.out();
            System.out.print("Enter Id you want update: ");
            String idx=sc.nextLine();
            ltm.update_timesheet(idx);
            ltm.writetofile();
        }
        public void update_employee_day()
        {
            ltd.readfromfile();
            ltd.out();
            System.out.print("Enter Id you want update: ");
            String idx=sc.nextLine();
            ltd.update_timesheet(idx);
            ltd.writetofile();
        }
        public void delete_employee_month()
        {
            ltm.readfromfile();
            ltm.out();
            System.out.print("Enter id employee to delete: ");
            String idx=sc.nextLine();
            ltm.deleted(idx);
            ltm.writetofile();
        }
        public void delete_employee_day()
        {
            ltd.readfromfile();
            ltd.out();
            System.out.print("Enter id employee to delete: ");
            String idx=sc.nextLine();
            ltd.deleted(idx);
            ltd.writetofile();
        }
        public void add_timesheet_month()
        {
            ListEmployee l=new ListEmployee();
            l.readFromFile();
            l.put();
            ltm.readfromfile();
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
                tm.setMonth(ltm.getTs()[0].getMonth());
                tm.setYear(ltm.getTs()[0].getYear());
                tm.setId_employee(id);
                tm.enter();
                ltm.add(tm);
            }
            ltm.writetofile();
        }
        public void add_timesheet_day()
        {
            ListEmployee l=new ListEmployee();
            l.readFromFile();
            l.put();
            ltd.readfromfile();
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
                    if (ltd.check_id_have_enter(id) != -1)
                    {
                        System.out.println("This ID have already exit");
                        check = false;
                    }
                    else if (id.equalsIgnoreCase("out"))
                    {
                        return;
                    }
                    else
                        check=true;
                }
                while (!check);
                TimeSheetDay td=new TimeSheetDay();
                td.setId_employee(id);
                td.enter();
                ltd.add(td);
            }
            ltd.writetofile();
        }

}
