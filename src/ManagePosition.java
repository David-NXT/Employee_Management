import java.util.*;
public class ManagePosition
{
    Scanner sc=new Scanner(System.in);

    public void menuPosition()
    {
        ListPosition ltp=new ListPosition();
        int x;
        do
        {
            System.out.println("1.Enter list position");
            System.out.println("2.Print list position");
            System.out.println("3.Add position");
            System.out.println("4.Remove position");
            System.out.println("5.Find position");
            System.out.println("6.Updata position");
            System.out.println("0.Exit");
            System.out.print("choose your option: ");
            x=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter to continue");
            sc.nextLine();
            if (x==1)
            {
                ltp.enter();
                ltp.writeToFile();
                System.out.println("Enter to continue");
                sc.nextLine();
            }
            if  (x==2)
            {
                ltp.ReadFromFile();
                ltp.out();
                System.out.println("Enter to continue");
                sc.nextLine();
            }
            if (x==3)
            {
                Position pos=new Position();
                pos.enter();
                ltp.add(pos);
                ltp.writeToFile();
                System.out.println("Enter to continue");
                sc.nextLine();
            }
            if (x==4)
            {
                ltp.ReadFromFile();
                ltp.out();
                System.out.print("Enter position id you want remove: ");
                String idx=sc.nextLine();
                ltp.remove(idx);
                ltp.writeToFile();
                System.out.println("Enter to continue");
                sc.nextLine();
            }
            if (x==5)
            {
                ltp.ReadFromFile();
                System.out.print("Enter position id you want search: ");
                String idx=sc.nextLine();
                ltp.find(idx);
                ltp.out();
                System.out.println("Enter to continue");
                sc.nextLine();
            }
            if (x==6)
            {
                ltp.update();
                ltp .writeToFile();
                System.out.println("Enter to continue");
                sc.nextLine();
            }
        }
        while (x!=0);
    }

}
