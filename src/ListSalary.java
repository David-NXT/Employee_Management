import java.io.*;
public class ListSalary implements Serializable {
    private Employee_Salary[] slr;
    private int n;
    private final String FILE_PATH="salary.dat";

    public ListSalary(){
        slr= new Employee_Salary[0];
        loadFromFile();
    }

    public void generateFromEmployees(Employee []employees){
        if(employees==null || employees.length == 0){
            System.out.println("Not!");
            return;
        }
        for (Employee emp : employees){
            Employee_Salary salary=new Employee_Salary(emp);
            addSalary(salary);
        }
        saveFile();
    }

    public void addSalary(Employee_Salary s){
        Employee_Salary[]temp = new Employee_Salary[n+1];
        for(int i=0;i<n;i++) temp[i] = slr[i];
        temp[n]=s;
        slr=temp;
        n++;
    }

    public void displayAll(){
        if(n==0){
            System.out.println("Not!");
            return;
        } System.out.println("==== LIST SALARY ====");
        for(int i=0;i<n;i++){
            System.out.println("No #"+(i+1));
            slr[i].displaySalaryInfo();
        }
    }

    public void displaySalaryByEmployeeId(String id){
        boolean found=false;
        for(int i=0;i<n;i++){
            if(slr[i].getId().equalsIgnoreCase(id)){
                slr[i].displaySalaryInfo();
                found=true;
            } if (!found)
                System.out.println("Not!");
        }
    }

    public void displayByMonthYear(int month, int year){
        boolean found=false;
        for(int i=0;i<n;i++){
            if(slr[i].getMonth() == month && slr[i].getYear()==year)
                slr[i].displaySalaryInfo();
            found=true;
        } if(!found)
            System.out.println("Not!");
    }

    public void displayViolations(String id){
        boolean found=false;
        for(int i=0;i<n;i++){
            if(slr[i].getId().equalsIgnoreCase(id)){
                System.out.println("Employee: "+slr[i].getSurname()+" "+slr[i].getName());
                System.out.println("Violations: "+slr[i].getDeductions());
                found=true;
            } if(!found)
                System.out.println("Not!");
        }
    }

    public void saveFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
            oos.writeObject(slr);
        }catch (IOException e){
            System.out.println("Error "+e.getMessage());
        }
    }

    public void loadFromFile(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
            slr=(Employee_Salary[]) ois.readObject();
            n=slr.length;
        } catch (Exception e){
            slr=new Employee_Salary[0];
            n=0;
        }
    }

    public Employee_Salary[] getSlr() {
        return slr;
    }
    public int getCount() {
        return n;
    }
}
