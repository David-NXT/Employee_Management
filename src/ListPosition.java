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
        for (int i=0;i<n;i++)
        {
            p[i]=new Position();
            p[i].enter();
        }
    }
    public void out()
    {
        quicksort(p,0,p.length-1);
        System.out.printf("%-12s %-20s %-45s %-30s %-20s\n", "ID Employee", "Job Title", "Job Descrip", "Required Skills","Totally Allowance");
        for (int i=0;i<p.length;i++)
        {
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
    public void update()
    {
        System.out.print("Enter the id you wanna update: ");
        String idx=sc.nextLine();
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
            System.out.println("1.fix job title");
            System.out.println("2.fix job description");
            System.out.println("3.fix required_skills");
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
            if (x==2)
            {
                System.out.print("Enter job description to update: ");
                p[pos].setJob_desciption(sc.nextLine());
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            if (x==3)
            {
                System.out.print("Enter required skills to update: ");
                p[pos].setRequired_skills(sc.nextLine());
                System.out.print("Enter to continue");
                sc.nextLine();
            }
        }
        while (x!=0);
    }
    public void numberofemployeeposition()
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

    public void writeToFile()
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
    public void ReadFromFile()
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
    public Position findById(String id){
        for(Position pos: p){
            if(pos.getId_employee().equalsIgnoreCase(id)){
                return pos;
            }
        }
        return null;
    }

//    get
    public Position[] getPositon(){return p;}
//    set
    public void setPosition(Position[] p){this.p=p;}


}
