import java.io.*;
import java.util.*;
public class ListPosition implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private Position[] p;

    public ListPosition(){p=new Position[0];}
    public ListPosition(Position[] p)
    {
        this.p=p;
    }
    public ListPosition(ListPosition lt)
    {
        this.p=lt.p;
    }

    public void enter()
    {
        System.out.print("Enter number of position: ");
        int n=sc.nextInt();
        sc.nextLine();
        p=new Position[n];
        ListEmployee l = new ListEmployee();
        l.readFromFile();
        l.put();
        for (int i=0;i<n;i++)
        {
            String id;
            boolean check=false;
            do
            {
                System.out.print("Enter your ID: ");
                id=sc.nextLine();
                if (l.isEmployee(id)==-1)
                {
                    System.out.println("Invaible Id");
                    check=false;
                }
                else if (check_id_yet(id)!=-1)
                {
                    System.out.println("This id have already exit");
                    check=false;
                }
                else
                    check=true;
            }
            while (!check);
            p[i]=new Position();
            p[i].setId_employee(id);
            p[i].enter();
        }
    }
    public int check_id_yet(String id)
    {
        for (int i=0;i<p.length;i++)
        {
            if (p[i]!=null &&p[i].getId_employee().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
    public void out()
    {
        quicksort(p,0,p.length-1);
        System.out.printf("%-12s %-20s %-45s %-30s %-20s\n", "ID Employee", "Job Title", "Job Descrip", "Required Skills","Totally Allowance");
        for (int i=0;i<p.length;i++)
        {
            p[i].setAllowance_position(allowance_positionx(p[i]));
            p[i].out();
        }
    }
    public void add(Position np)
    {
        p=Arrays.copyOf(p,p.length+1);
        p[p.length-1]=np;
        quicksort(p,0,p.length-1);
    }
    public void remove(String idx)
    {
        int pos=-1;
        for (int i=0;i<p.length;i++)
        {
            if (idx.equals(p[i].getId_employee()))
            {
                pos=i;
                break;
            }
        }
        if (pos==-1)
        {
            System.out.println("ID employee not found");
            return;
        }
        for (int i=pos;i<p.length-1;i++)
            p[i]=p[i+1];
        p=Arrays.copyOf(p,p.length-1);
    }
    public Position find(String idx)
    {
        for (int i=0;i<p.length;i++)
        {
            if(idx.equals(p[i].getId_employee()))
            {
                return p[i];
            }
        }
        return null;
    }
    public void update(String idx)
    {
        int pos=-1;
        for (int i=0;i<p.length;i++)
        {
            if (idx.equals(p[i].getId_employee()))
            {
                pos=i;
                break;
            }
        }
        if (pos==-1)
        {
            System.out.println("Position not found");
            return;
        }
        int x;
        do
        {
            System.out.println("1.Fix job title");
            System.out.println("2.Fix job description");
            System.out.println("3.Fix required_skills");
            System.out.println("0.Exit");
            System.out.println("choose option; ");
            x=sc.nextInt();
            sc.nextLine();
            if (x==1)
            {
                System.out.print("Enter job title to update: ");
                p[pos].setJob_title(sc.nextLine());
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            else if (x==2)
            {
                System.out.print("Enter job description to update: ");
                p[pos].setJob_desciption(sc.nextLine());
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            else if (x==3)
            {
                System.out.print("Enter required skills to update: ");
                p[pos].setRequired_skills(sc.nextLine());
                System.out.print("Enter to continue");
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
    public void static_position_employee()
    {
        System.out.print("Enter the position: ");
        String positionx=sc.nextLine();
        boolean check=false;
        System.out.printf("%-12s %-20s %-45s %-30s\n", "ID Employee", "Job Title", "Job Descrip", "Required Skills");
        for (int i=0;i<p.length;i++)
        {
            if(positionx.equalsIgnoreCase(p[i].getJob_title()))
            {
                p[i].out();
                check=true;
            }
        }
        if (!check)
        {
            System.out.println("Position not found");
            return;
        }
    }
    public void employee_statistics_by_position()
    {
        int truongphong=0,phophong=0,nhanvien=0,thuctap=0;
        for (int i=0;i<p.length;i++)
        {
            if (p[i].getJob_title().equalsIgnoreCase("truong phong"))
                truongphong++;
            else if(p[i].getJob_title().equalsIgnoreCase("pho phong"))
                phophong++;
            else if (p[i].getJob_title().equalsIgnoreCase("nhan vien"))
                nhanvien++;
            else
                thuctap++;
        }
        System.out.println("Employee statistics by position");
        System.out.println("Truong phong: "+truongphong);
        System.out.println("Pho phong: "+phophong);
        System.out.println("Nhan vien: "+nhanvien);
        System.out.println("Thuc tap: "+thuctap);
    }
    public double allowance_positionx(Position pos)
    {
        ListSalary ls=new ListSalary();
        ls.loadFromFile();
        double totalsalary=0;
        for (Employee_Salary el: ls.getAll())
        {
            if(el.getEmpId().equalsIgnoreCase(pos.getId_employee()))
            {
                totalsalary=el.getTotalSalary();
                break;
            }
        }
        double allowance=0.0;
        if (pos.getJob_title().equalsIgnoreCase("giam doc"))
            allowance=totalsalary*0.20;
        else if (pos.getJob_title().equalsIgnoreCase("truong phong"))
            allowance=totalsalary*0.15;
        else if (pos.getJob_title().equalsIgnoreCase("pho phong"))
            allowance=totalsalary*0.10;
        else if (pos.getJob_title().equalsIgnoreCase("nhan vien"))
            allowance=totalsalary*0.05;
        else
            allowance=totalsalary*0.02;
        allowance=Math.round(allowance*100.0)/100.0;
        return allowance;
    }

    public void writetofile()
    {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");
        if (!dir.exists())
        {
            dir.mkdir();
        }
        File file = new File(dir,"data_Position");
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
        File file = new File(basepath + File.separator + "data" + File.separator + "data_Position");

        if (!file.exists()) {
            System.out.println("File is not exist");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ListPosition lp = (ListPosition)ois.readObject();
            this.p=lp.p;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public int partision(Position ep[], int l, int r)
    {
        Position privot=ep[r];
        int i=l-1;
        for (int j=l;j<r;j++)
        {
            if (Integer.parseInt(ep[j].getId_employee())<=Integer.parseInt(privot.getId_employee()))
            {
                i++;
                Position temp = ep[i];
                ep[i] = ep[j];
                ep[j] = temp;
            }
        }
        i++;
        Position temp = ep[i];
        ep[i] = ep[r];
        ep[r] = temp;
        return i;
    }
    public void quicksort(Position po[], int l, int r)
    {
        if (l<r)
        {
            int p=partision(po, l, r);
            quicksort(po,l,p-1);
            quicksort(po,p+1,r);
        }
    }

//    get
    public Position[] getPositon(){return p;}
//    set
    public void setPosition(Position[] p){this.p=p;}


}
