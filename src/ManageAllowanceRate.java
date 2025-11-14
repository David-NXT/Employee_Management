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

    public void findHolidayManager() {
        int mode;
        do {
            findHolidayMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runFindHolidayManager(mode);
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
            }
            case 3: {
                findHolidayManager();
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
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void runFindHolidayManager(int mode) {
        ListAllowanceRule data = new ListAllowanceRule();
        data.readFromFile();

        switch (mode) {
            case 1: {
                findHollidayByID(data);
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
    }

    public void seniorMenu(){
        System.out.println("___---Seniority Allowance Manager---___");
        System.out.println("0. Exit");
        System.out.println("1. Print All Allowance");
        System.out.println("2. Add New Allowance");
        System.out.println("3. Find");
    }

    public void findHolidayMenu(){
        System.out.println("Choose What To Find:");
        System.out.println("0. Exit");
        System.out.println("1. Find By ID");
        System.out.println("2. Find By Month");
    }

    public void findHollidayByID(ListAllowanceRule data){
        System.out.println("ID:");
        String id = sc.nextLine();
        AllowanceRule result = data.find(id);
        if (result != null) result.put();
        else {
            System.out.println("Invalid ID");
        }
    }
}
