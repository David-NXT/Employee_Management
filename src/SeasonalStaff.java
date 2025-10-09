import java.util.Scanner;
import java.io.*;

public class SeasonalStaff extends Employee implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    private double hourlysalary;
    private float worktime;

    public SeasonalStaff() {}

    public SeasonalStaff(SeasonalStaff a){
        super(a);
        hourlysalary = a.hourlysalary;
        worktime = a.worktime;
    }

    public SeasonalStaff(String id, String surname, String name, String gender, String address, String phonenumber, String email, String startworkingday, double hourlysalary, float worktime) {
        super(id,surname,name,gender,address,phonenumber,email,startworkingday);
        this.hourlysalary = hourlysalary;
        this.worktime = worktime;
    }

    public void getSeasonalStaff() {
        super.getEmployee();
        System.out.println("Hourly Salary: ");hourlysalary = sc.nextDouble();
        System.out.println("Work Time: ");worktime = sc.nextFloat();
    }

    public void putSeasonalStaff(){
        super.putEmployee();
        System.out.println("Hourly Salary: " + hourlysalary);
        System.out.println("Work Time: " + worktime);
    }

    public double getHourlySalary() {
        return hourlysalary;
    }

    public float getWorkTime() {
        return worktime;
    }

    public void setHourlySalary(double hourlysalary){
        this.hourlysalary = hourlysalary;
    }

    public void setWorkTime(float worktime){
        this.worktime = worktime;
    }
}
