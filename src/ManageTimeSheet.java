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
                System.out.println("0.save and exit");
                System.out.println("1. Enter list Timesheet");
                System.out.println("2. Out list Timesheet");
                System.out.println("3. Add Timesheet");
                System.out.println("4. Delete Timesheet");
                System.out.print("Choose an option: ");
                x=sc.nextInt();
                System.out.println("enter to continue...");
                System.console().readLine();
                if(x==0)
                    lt.writeToFile();
                if (x==1)
                {
                    lt.enter();
                    System.out.println("enter to continue...");
                    System.console().readLine();
                }
                if (x==2)
                {
                    lt.out();
                    System.out.println("enter to continue...");
                    System.console().readLine();
                }
                if (x==3)
                {
                    TimeSheet nts=new TimeSheet();
                    nts.enter();
                    lt.add(nts);
                    System.out.println("enter to continue...");
                    System.console().readLine();
                }
                if (x==4)
                {
                    System.out.print("enter id employee to delete: ");
                    String idx=System.console().readLine();
                    lt.deleted(idx);
                    System.out.println("enter to continue...");
                    System.console().readLine();
                }

            }
            while (x!=0);
        }

}
