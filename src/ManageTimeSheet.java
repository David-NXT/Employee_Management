import java.util.*;
public class ManageTimeSheet
{
        Scanner sc=new Scanner(System.in);
        ListTimeSheet lt = new ListTimeSheet();

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
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                x=sc.nextInt();
                sc.nextLine();
                if (x==1)
                {
                    lt.enter();
                    lt.writeToFile();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                else if (x==2)
                {
                    lt.ReadFromFile();
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
                else
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
            lt.ReadFromFile();
            lt.out();
            System.out.print("Enter Id you want update: ");
            String idx=sc.nextLine();
            lt.update_timesheet(idx);
            lt.writeToFile();
        }
        public void delete_employee()
        {
            lt.ReadFromFile();
            lt.out();
            System.out.print("Enter id employee to delete: ");
            String idx=sc.nextLine();
            lt.deleted(idx);
            lt.writeToFile();
        }
        public void add_timesheet()
        {
            TimeSheetMonth nts=new TimeSheetMonth();
            nts.enter();
            lt.add(nts);
            lt.writeToFile();
        }

}
