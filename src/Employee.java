import java.io.Serial;
import java.util.Scanner;
import java.io.Serializable;

abstract class Employee implements Serializable {
    private transient final Scanner sc = new Scanner(System.in);
    @Serial
    private static final long serialVersionUID = 1L;
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

    public void get(){
        System.out.println("ID: ");id = sc.nextLine();
        ListEmployee l = new ListEmployee();
        l.readFromFile();
        while(true) {
            boolean flag = false;
            for (int i = 0;i < l.getData().length;i++) {
                if (id.equals(l.getData()[i].id)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) break;

            System.out.println("This ID Already Exist");
            id = sc.nextLine();
        }
        System.out.println("Surname: ");surname = sc.nextLine();
        surname = surname.trim();
        System.out.println("Name: ");name = sc.nextLine();
        name = name.trim();
        System.out.println("Gender: (male|female)");gender = sc.nextLine();
        while (true) {
            if (gender.equals("male") || gender.equals("female")) break;
            System.out.println("Invalid! (male|female)");
            gender = sc.nextLine();
        }
        System.out.println("Address: ");address = sc.nextLine();
        System.out.println("Phone Number: ");phonenumber = sc.nextLine();
        System.out.println("Email: "); email = sc.nextLine();
        while (true) {
            if (email.endsWith("@gmail.com")) {
                break;
            }
            else {
                System.out.println("Invalid Email!");
                email = sc.nextLine();
            }
        }
        System.out.println("Start Working Day: ");startworkingday = sc.nextLine();
    }

    public void get(String id){
        this.id = id;
        System.out.println("Surname: ");surname = sc.nextLine();
        surname = surname.trim();
        System.out.println("Name: ");name = sc.nextLine();
        name = name.trim();
        System.out.println("Gender: (male|female)");gender = sc.nextLine();
        while (true) {
            if (gender.equals("male") || gender.equals("female")) break;
            System.out.println("Invalid! (male|female)");
            gender = sc.nextLine();
        }
        System.out.println("Address: ");address = sc.nextLine();
        System.out.println("Phone Number: ");phonenumber = sc.nextLine();
        System.out.println("Email: "); email = sc.nextLine();
        while (true) {
            if (email.endsWith("@gmail.com")) {
                break;
            }
            else {
                System.out.println("Invalid Email!");
                email = sc.nextLine();
            }
        }
        System.out.println("Start Working Day: ");startworkingday = sc.nextLine();
    }

    public void put(){
        System.out.printf("%-10s %-20s %-10s %-8s %-50s %-12s %-20s %-17s ",id,surname,name,gender,address,phonenumber,email,startworkingday);
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
