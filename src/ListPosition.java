import java.io.*;
import java.util.*;
public class ListPosition implements Serializable
{
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
    private Position[] p;

    public ListPosition(){p=new Position[0];}

    public void enter()
    {
        System.out.print("enter number of position: ");
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
        for (int i=0;i<p.length;i++)
            p[i].out();
    }
    public void add(Position np)
    {
        p=Arrays.copyOf(p,p.length+1);
        p[p.length-1]=np;
    }
    public void remove(String idx)
    {
        int pos=-1;
        for (int i=0;i<p.length;i++)
        {
            if (idx.equals(p[i].getPosition_id()))
            {
                pos=i;
                break;
            }
        }
        if (pos==-1)
        {
            System.out.println("position not found");
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
            if(idx.equals(p[i].getPosition_id()))
            {
                return p[i];
            }
        }
        return null;
    }
    public void update()
    {
        System.out.print("enter the id yoy wanna update: ");
        String idx=sc.nextLine();
        int pos=-1;
        for (int i=0;i<p.length;i++)
        {
            if (idx.equals(p[i].getPosition_id()))
            {
                pos=i;
                break;
            }
        }
        if (pos==-1)
        {
            System.out.println("position not found");
            return;
        }
        int x;
        do
        {

            System.out.println("0.save and exit");
            System.out.println("1.fix id");
            System.out.println("2.fix job title");
            System.out.println("3.fix job description");
            System.out.println("4.fix required_skills");
            System.out.println("choose option; ");
            x=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter to continue");
            sc.nextLine();
            if (x==0)
                writeToFile();
            if (x==1)
            {
                System.out.print("Enter position id to update: ");
                p[pos].setPosition_id(sc.nextLine());
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            if (x==2)
            {
                System.out.print("Enter job title to update: ");
                p[pos].setJob_title(sc.nextLine());
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            if (x==3)
            {
                System.out.print("Enter job description to update: ");
                p[pos].setJob_desciption(sc.nextLine());
                System.out.print("Enter to continue");
                sc.nextLine();
            }
            if (x==4)
            {
                System.out.print("Enter required skills to update: ");
                p[pos].setRequired_skills(sc.nextLine());
                System.out.print("Enter to continue");
                sc.nextLine();
            }
        }
        while (x!=0);
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
            System.out.println("file is not exist");
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
}
