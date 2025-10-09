import javax.xml.transform.Source;
import java.util.Scanner;
import java.io.Serializable;

public class Employee implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    private String id;
    private String surname;
    private String name;
    private String gender;
    private String address;
    private String phonenumber;
    private String email;
    private String startworkingday;//yy mm dd

    public Employee(Employee a){
        id = a.id;
        surname = a.surname;
        name = a.name;
        gender = a.gender;
        address = a.address;
        phonenumber = a.phonenumber;
        email = a.email;
        startworkingday = a.startworkingday;
    }

    public Employee(String id, String surname, String name, String gender, String address, String phonenumber, String email, String startworkingday){
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phonenumber = phonenumber;
        this.email = email;
        this.startworkingday = startworkingday;
    }

    public Employee(){}

    public void getEmployee(){
        System.out.println("ID: ");id = sc.nextLine();
        System.out.println("Surname: ");surname = sc.nextLine();
        System.out.println("Name: ");name = sc.nextLine();
        System.out.println("Gender: ");gender = sc.nextLine();
        System.out.println("Address: ");address = sc.nextLine();
        System.out.println("Phone Number: ");phonenumber = sc.nextLine();
        System.out.println("Email: "); email = sc.nextLine();
        System.out.println("Start Working Day: ");startworkingday = sc.nextLine();
    }

    public void putEmployee(){
        System.out.println("ID: " + id);
        System.out.println("Surname: " + surname);
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phonenumber);
        System.out.println("Email: " + email);
        System.out.println("Start Working Day: " + startworkingday);
    }

    public String getId(){
        return id;
    }

    public String getSurname(){
        return surname;
    }

    public String getName(){
        return name;
    }

    public String getGender(){
        return gender;
    }

    public String getAddress(){
        return address;
    }

    public String getPhoneNumber(){
        return phonenumber;
    }

    public String getEmail(){
        return email;
    }

    public String getStartWorkingDay(){
        return startworkingday;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPhoneNumber(String phonenumber){
        this.phonenumber = phonenumber;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setStartWorkingDay(String startworkingday){
        this.startworkingday = startworkingday;
    }
}
