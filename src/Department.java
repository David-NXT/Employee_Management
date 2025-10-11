import java.util.Scanner;

public class Department
{
    private Scanner sc=new Scanner(System.in);

    private String id;
    private String name;
    private String about;
    private String createdate; //yy mm dd
    private String manager;

    public Department(Department a){
        id=a.id;
        name=a.name;
        about=a.about;
        createdate=a.createdate;
        manager=a.manager;
    }

    public Department(String id,String name,String about,String createdate,String manager){
        this.id=id;
        this.name=name;
        this.about=about;
        this.createdate=createdate;
        this.manager=manager;

    }

    public void getDepartment(){
        System.out.printf("Id phong ban: ");id=sc.nextLine();
        System.out.printf("Ten phong ban: ");name=sc.nextLine();
        System.out.printf("Mieu ta phong ban: ");about=sc.nextLine();
        System.out.printf("Ngay thanh lap phong ban: ");createdate=sc.nextLine();
        System.out.printf("Truong phong ban: ");manager=sc.nextLine();
    }

    public void putDepartment(){
        System.out.printf("Id phong ban: %s%n",id);
        System.out.printf("Ten phong ban: %s%n",name);
        System.out.printf("Mieu ta phong ban: %s%n",about);
        System.out.printf("Ngay thanh lap phong ban: %s%n",createdate);
        System.out.printf("Truong phong ban: %s%n",manager);
    }
    //Get/set area
    //
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    //
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    //
    public String getAbout(){
        return about;
    }
    public void setAbout(String about){
        this.about = about;
    }

    //
    public String getCreateDate(){
        return createdate;
    }
    public void setCreateDate(String createdate){
        this.createdate = createdate;
    }

    //
     public String getManager(){
        return manager;
    }
    public void setManager(String manager){
        this.manager = manager;
    }
    
    








}
