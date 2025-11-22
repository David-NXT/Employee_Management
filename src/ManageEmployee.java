import java.util.Scanner;

public class ManageEmployee {
    private transient final Scanner sc = new Scanner(System.in);
    private final ListEmployee lemp;

    public ManageEmployee() {
        lemp = new ListEmployee();
        lemp.readFromFile();
    }

    public void menu() {
        int mode;
        do {
            mainMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runMenu(mode);
        } while (true);
    }

    public void changeManage(int locate, int type) {
        int mode;
        do {
            changeMenu(type);
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runChange(mode,locate,type);
        } while (true);
    }

    public void findManage() {
        int mode;
        do {
            findMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runFind(mode);
        } while(true);
    }

    public void mainMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Print All Employee");
        System.out.println("2. Print Official Employee");
        System.out.println("3. Print Seasonal Staff");
        System.out.println("4. Add");
        System.out.println("5. Delete");
        System.out.println("6. Change");
        System.out.println("7. Find");
        System.out.println("8. Statistic");
        System.out.println("9. Sort");
    }

    public void changeMenu(int type) {
        System.out.println("Choose Information That You Want To Change:");
        System.out.println("0. Exit");
        System.out.println("1. Name");
        System.out.println("2. Gender");
        System.out.println("3. Phone Number");
        System.out.println("4. Email");
        System.out.println("5. Start Working Day");
        if (type == 1) System.out.println("6. Basic Salary");
        else System.out.println("6. Hourly Salary");
    }

    public void findMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Find By ID");
        System.out.println("2. Find By Name");
    }

    public void runMenu(int mode) {
        switch (mode) {
            case 1: {
                lemp.put();
                break;
            }
            case 2: {
                lemp.putOffcialEmployee();
                break;
            }
            case 3: {
                lemp.putSeasonalStaff();
                break;
            }
            case 4: {
                lemp.get();
                break;
            }
            case 5: {
                System.out.println("ID:");
                String id = sc.nextLine();
                lemp.delete(id);
                break;
            }
            case 6: {
                changeInformation();
                break;
            }
            case 7: {
                findManage();
                break;
            }
            case 8: {
                statistic();
                break;
            }
            case 9: {
                lemp.quickSort(0, lemp.getN() - 1);
                System.out.println("Done!");
                lemp.writeToFile();
                break;
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void runChange(int mode, int locate, int type) {
        switch (mode) {
            case 1: {
                changeName(locate);
                break;
            }
            case 2: {
                if (lemp.getData()[locate].getGender().equalsIgnoreCase("male")) lemp.getData()[locate].setGender("female");
                else lemp.getData()[locate].setGender("male");
                lemp.writeToFile();
                break;
            }
            case 3: {
                System.out.println("New Phone Number:");
                String pn = sc.nextLine();
                lemp.getData()[locate].setPhoneNumber(pn);
                lemp.writeToFile();
                break;
            }
            case 4: {
                changeEmail(locate);
                break;
            }
            case 5: {
                System.out.println("Alternative Start Working Day:");
                String newday = sc.nextLine();
                lemp.getData()[locate].setStartWorkingDay(newday);
                lemp.writeToFile();
                break;
            }
            case 6: {
                changeByType(locate,type);
                break;
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void runFind(int mode) {
        switch (mode) {
            case 1: {
                findByID();
                break;
            }
            case 2: {
                findByName();
                break;
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void findByID() {
        System.out.println("ID:");
        String id = sc.nextLine();
        Employee result = lemp.findById(id);
        if (result != null) result.put();
        else System.out.println("There's No Employee With That ID!");
    }

    public void changeInformation() {
        System.out.println("Employee ID You Want To Change:");
        String id = sc.nextLine();

        int locate = lemp.isEmployee(id);
        if (locate == -1) {
            System.out.println("This Employee Does Not Exist!");
            return;
        }

        int type;
        if (lemp.isOfficialEmployee(id)) type = 1;
        else type = 2;

        changeManage(locate,type);
    }

    public void findByName() {
        System.out.println("Name:");
        String name = sc.nextLine();
        ListEmployee result = lemp.findByName(name);
        result.put();
    }

    public void changeName(int locate) {
        System.out.println("New Sur Name:");
        String newsur = sc.nextLine();
        System.out.println("New Name:");
        String newname = sc.nextLine();
        lemp.getData()[locate].setSurname(newsur);
        lemp.getData()[locate].setName(newname);
        lemp.writeToFile();
    }

    public void changeEmail(int locate) {
        System.out.println("Email: ");
        String email = sc.nextLine();
        while (true) {
            if (email.endsWith("@gmail.com")) {
                break;
            }
            else {
                System.out.println("Invalid Email!");
                email = sc.nextLine();
            }
        }
        email = email.toLowerCase();

        lemp.getData()[locate].setEmail(email);
        lemp.writeToFile();
    }

    public void changeByType(int locate, int type) {
        if (type == 1) {
            System.out.println("Alternative Basic Salary:");
            double newsalary = sc.nextDouble();sc.nextLine();
            ((OfficialEmployee) lemp.getData()[locate]).setBasicSalary(newsalary);
        } else {
            System.out.println("Alternative Hour Salary:");
            double newsalary = sc.nextDouble();sc.nextLine();
            ((SeasonalStaff) lemp.getData()[locate]).setHourlySalary(newsalary);
        }
        lemp.writeToFile();
    }

    public void statistic() {
        int[] result = lemp.statistic();
        System.out.println("Number Of Official Employee: " + result[0]);
        System.out.println("Number Of Seasonal Staff: " + result[1]);
    }
}
