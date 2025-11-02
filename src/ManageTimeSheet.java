import java.util.*;
public class ManageTimeSheet
{
        Scanner sc=new Scanner(System.in);
        public void menu()
        {
            ListTimeSheet lt = new ListTimeSheet();
            int x;
            do
            {
                System.out.println("1. Enter list Timesheet");
                System.out.println("2. Print list Timesheet");
                System.out.println("3. Add Timesheet");
                System.out.println("4. Delete Timesheet");
                System.out.println("5. Check employee month");
                System.out.println("6. Check employee day");
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
                if (x==2)
                {
                    lt.ReadFromFile();
                    lt.out();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                if (x==3)
                {
                    TimeSheetMonth nts=new TimeSheetMonth();
                    nts.enter();
                    lt.add(nts);
                    lt.writeToFile();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                if (x==4)
                {
                    lt.ReadFromFile();
                    lt.out();
                    System.out.print("Enter id employee to delete: ");
                    String idx=sc.nextLine();
                    lt.deleted(idx);
                    lt.writeToFile();
                    System.out.println("Enter to continue...");
                    sc.nextLine();
                }
                if (x==5)
                {
                    lt.rollcallmonth();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }
                if (x==6)
                {
                    lt.rollcallday();
                    System.out.print("Enter to continue...");
                    sc.nextLine();
                }

            }
            while (x!=0);
        }

}
