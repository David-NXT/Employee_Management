import javax.xml.transform.Source;
import java.util.List;
import java.util.Scanner;

public class ManageAllowanceRate {
    private final transient Scanner sc = new Scanner(System.in);

    public void menu(){
        int mode;

        do{
            mainMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runMainMenu(mode);
        } while (true);
    }

    public void holidayManager() {
        int mode;
        do {
            holidayMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runHolidayManager(mode);
        } while (true);
    }

    public void findHolidayManager(ListAllowanceRule data) {
        int mode;
        do {
            findHolidayMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runFindHolidayManager(mode,data);
        } while (true);
    }

    public void changeHolidayManager(ListAllowanceRule data, int locate) {
        int mode;
        do {
            changeHolidayMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runChangeHoliday(mode,data,locate);
        } while (true);
    }

    public void seniorManager() {
        int mode;
        do {
            seniorMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runSeniorManager(mode);
        } while (true);
    }

    public void changeSeniorManager(ListSeniorityAllowanceRule data, int locate) {
        int mode;
        do {
            changeSeniorMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runChangeSenior(mode,data,locate);
        } while (true);
    }

    public void runMainMenu(int mode) {
        switch (mode) {
            case 1: {
                holidayManager();
                break;
            }
            case 2: {
                seniorManager();
                break;
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void runHolidayManager(int mode) {
        ListAllowanceRule data = new ListAllowanceRule();
        data.readFromFile();

        switch (mode) {
            case 1: {
                data.put();
                break;
            }
            case 2: {
                AllowanceRule a = new AllowanceRule();
                a.get();
                data.add(a);
                data.writeToFile();
                break;
            }
            case 3: {
                findHolidayManager(data);
                break;
            }
            case 4: {
                System.out.println("ID:");
                String id = sc.nextLine();
                data.delete(id);
                data.writeToFile();
                break;
            }
            case 5: {
                System.out.println("ID:");
                String id = sc.nextLine();
                int locate = data.find(id);
                if (locate != -1) changeHolidayManager(data,locate);
                else System.out.println("Invalid ID!");
                break;
            }
            default :{
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void runSeniorManager(int mode){
        ListSeniorityAllowanceRule data = new ListSeniorityAllowanceRule();
        data.readFromFile();

        switch (mode) {
            case 1: {
                data.put();
                break;
            }
            case 2:{
                SeniorityAllowanceRule a = new SeniorityAllowanceRule();
                a.get();
                data.add(a);
                data.writeToFile();
                break;
            }
            case 3: {
                findSeniorAllowance(data);
                break;
            }
            case 4: {
                System.out.println("ID:");
                String id = sc.nextLine();
                data.delete(id);
                data.writeToFile();
                break;
            }
            case 5: {
                int locate = findSeniorByID(data);
                if (locate == -1) break;
                changeSeniorManager(data,locate);
                break;
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void runFindHolidayManager(int mode, ListAllowanceRule data) {

        switch (mode) {
            case 1: {
                findHolidayByID(data);
                break;
            }
            case 2: {
                System.out.println("Month:");
                String month = sc.nextLine();
                ListAllowanceRule result = data.findAllowanceInMonth(month);
                result.put();
                break;
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void runChangeHoliday(int mode, ListAllowanceRule data, int locate){
        switch (mode) {
            case 1: {
                System.out.println("Alternative Name:");
                String name = sc.nextLine();
                data.getData()[locate].setName(name);
                data.writeToFile();
                break;
            }
            case 2: {
                System.out.println("Alternative Day:");
                String day = sc.nextLine();
                data.getData()[locate].setDay(day);
                data.writeToFile();
                break;
            }
            case 3: {
                System.out.println("Alternative Rate:");
                double rate = sc.nextDouble();sc.nextLine();
                data.getData()[locate].setAllowancerate(rate);
                data.writeToFile();
                break;
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void runChangeSenior(int mode, ListSeniorityAllowanceRule data, int locate){
        switch (mode) {
            case 1: {
                System.out.println("Alternative Year:");
                int year = sc.nextInt();sc.nextLine();
                data.getData()[locate].setYearofwork(year);
                data.writeToFile();
                break;
            }
            case 2: {
                System.out.println("Alternative Rate:");
                double rate = sc.nextDouble();sc.nextLine();
                data.getData()[locate].setAllowancerate(rate);
                data.writeToFile();
                break;
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void mainMenu(){
        System.out.println("0. Exit");
        System.out.println("1. Holiday Allowance Manager");
        System.out.println("2. Seniority Allowance Manager");
    }

    public void holidayMenu(){
        System.out.println("___---Holiday Allowance Manager---___");
        System.out.println("0. Exit");
        System.out.println("1. Print All Allowance");
        System.out.println("2. Add New Allowance");
        System.out.println("3. Find");
        System.out.println("4. Delete");
        System.out.println("5. Change Information");
    }

    public void seniorMenu(){
        System.out.println("___---Seniority Allowance Manager---___");
        System.out.println("0. Exit");
        System.out.println("1. Print All Allowance");
        System.out.println("2. Add New Allowance");
        System.out.println("3. Find");
        System.out.println("4. Delete");
        System.out.println("5. Change Information");
    }

    public void findHolidayMenu(){
        System.out.println("Choose What To Find:");
        System.out.println("0. Exit");
        System.out.println("1. Find By ID");
        System.out.println("2. Find By Month");
    }

    public void changeHolidayMenu() {
        System.out.println("Choose What To Change");
        System.out.println("0. Exit");
        System.out.println("1. Name");
        System.out.println("2. Day");
        System.out.println("3. Allowance Rate");
    }

    public void changeSeniorMenu() {
        System.out.println("Choose What To Change");
        System.out.println("0. Exit");
        System.out.println("1. Year");
        System.out.println("2. Allowance Rate");
    }

    public void findHolidayByID(ListAllowanceRule data){
        System.out.println("ID:");
        String id = sc.nextLine();
        int locate = data.find(id);
        if (locate != -1) data.getData()[locate].put();
        else {
            System.out.println("Invalid ID");
        }
    }

    public int findSeniorByID(ListSeniorityAllowanceRule data) {
        System.out.println("ID:");
        String id = sc.nextLine();
        int locate = data.find(id);
        if (locate != -1) return locate;
        else {
            System.out.println("Invalid ID");
            return -1;
        }
    }

    public void findSeniorAllowance(ListSeniorityAllowanceRule data) {
        System.out.println("Year:");
        int year = sc.nextInt();
        SeniorityAllowanceRule result = data.findYear(year);
        result.put();
    }
}
