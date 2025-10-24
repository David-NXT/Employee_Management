import java.util.Scanner;
import java.io.*;

public class SeasonalStaff extends Employee implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
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

    @Override
    public void get() {
        super.get();
        System.out.println("Hourly Salary: ");hourlysalary = sc.nextDouble();
    }

    @Override
    public void get(String id) {
        super.get(id);
        int random = (int) (Math.random() * 3);
        switch (random) {
            case 0: {
                hourlysalary = 25;
                break;
            }
            case 1: {
                hourlysalary = 35;
                break;
            }
            case 2: {
                hourlysalary = 50;
                break;
            }
        }
    }

    @Override
    public void put(){
        super.put();
        System.out.printf("%-15.2f %-15.2f%n",hourlysalary,worktime);
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
