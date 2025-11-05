import java.io.*;
import java.util.Arrays;

public class ListSalary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Employee_Salary[] data;
    private final String FILE_PATH="salary.dat";

    public ListSalary(){
        data= new Employee_Salary[0];
        loadFromFile();
    }

    public void addSalary(Employee_Salary s){
        data = Arrays.copyOf(data, data.length+1);
        data[data.length -1]=s;
        saveToFile();
    }

    public void displayAll(){
        for(Employee_Salary s : data){
            s.displaySalaryInfo();
        }
    }

    public void displaySalaryByEmployeeId(String id){
        boolean found=false;
        for(Employee_Salary s : data){
            if(s.getId().equalsIgnoreCase(id)){
                s.displaySalaryInfo();
                found=true;
            }
        }if (!found)
            System.out.println("Not!");
    }

    public void displayByMonthYear(int month, int year){
        boolean found=false;
        for(Employee_Salary s : data){
            if(s.getMonth() == month && s.getYear()==year)
                s.displaySalaryInfo();
            found=true;
        } if(!found)
            System.out.println("Not!");
    }

    public void displayViolations(String id){
        boolean found=false;
        for(Employee_Salary s : data){
            if(s.getId().equalsIgnoreCase(id)){
                System.out.println("Employee: "+s.getSurname()+" "+s.getName());
                System.out.println("Violations: "+s.getDeductions());
                found=true;
            }
        }if(!found)
            System.out.println("Not!");
    }

    public void saveToFile(){
        String basePath=System.getProperty("user.dir");
        File dir = new File(basePath + File.separator+"data");
        if(!dir.exists())
            dir.mkdir();
        File file = new File(dir,"salary.dat");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
            oos.writeObject(data);
        }catch (IOException e){
            System.out.println("Error "+e.getMessage());
        }
    }

    public void loadFromFile(){
        String basepath=System.getProperty("user.dir");
        File file=new File(basepath + File.separator+"data"+File.separator+"salary.dat");
        if(!file.exists()){
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
            data=(Employee_Salary[]) ois.readObject();
        } catch (Exception e){
            data=new Employee_Salary[0];
        }
    }
    public String getAutoSalaryId() {
        int baseId = 1000;
        for (Employee_Salary s : data) {
            if (s.getSalaryId().equalsIgnoreCase("S" + baseId)) {
                baseId++;
            }
        }
        return "S" + baseId;
    }


    public Employee_Salary[] getAll() {
        return data;
    }
    public int getCount() {
        return data.length;
    }
}
