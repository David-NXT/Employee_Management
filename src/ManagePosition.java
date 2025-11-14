import java.util.*;
public class ManagePosition
{
    Scanner sc=new Scanner(System.in);
    ListPosition ltp=new ListPosition();

    public void menuPosition()
    {
        int x;
        do
        {
            System.out.println("1.Create new list position");
            System.out.println("2.Print list position");
            System.out.println("3.Add position");
            System.out.println("4.Remove position");
            System.out.println("5.Find position");
            System.out.println("6.Updata position");
            System.out.println("7.Static employee positions");
            System.out.println("8.Static number of positions");
            System.out.println("0.Exit");
            System.out.print("choose your option: ");
            x=sc.nextInt();
            sc.nextLine();
            if (x==1)
            {
                ltp.enter();
                ltp.writeToFile();
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            else if  (x==2)
            {
                ltp.ReadFromFile();
                ltp.out();
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            else if (x==3)
            {
                add_position();
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            else if (x==4)
            {
                remove_position();
                System.out.println("Enter to continue");
                sc.nextLine();
            }
            else if (x==5)
            {
                search_id();
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            else if (x==6)
            {
                update();
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            else if (x==7)
            {
                ltp.ReadFromFile();
                ltp.static_position_employee();
                System.out.println("Enter to continue");
                sc.nextLine();
            }
            else if(x==8)
            {
                ltp.ReadFromFile();
                ltp.employee_statistics_by_position();
                System.out.println("Enter to continue");
                sc.nextLine();
            }
            else
            {
                System.out.println("Error please chose again");
                System.out.print("Enter to continue");
                sc.nextLine();
            }
        }
        while (x!=0);
    }
    public void add_position()
    {
        ltp.ReadFromFile();
        Position pos=new Position();
        pos.enter();
        ltp.add(pos);
        ltp.writeToFile();
    }
    public void remove_position()
    {
        ltp.ReadFromFile();
        ltp.out();
        System.out.print("Enter position id you want remove: ");
        String idx=sc.nextLine();
        ltp.remove(idx);
        ltp.writeToFile();
    }
    public void search_id()
    {
        ltp.ReadFromFile();
        System.out.print("Enter position id you want search: ");
        String idx=sc.nextLine();
        ltp.find(idx);
        ltp.out();
    }
    public void update()
    {
        ltp.ReadFromFile();
        ltp.out();
        System.out.print("Enter id you want update: ");
        String idx=sc.nextLine();
        ltp.update(idx);
        ltp.writeToFile();
    }
}
