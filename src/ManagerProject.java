import java.util.Scanner;

public class ManagerProject {
    private final transient Scanner sc = new Scanner(System.in);
    private ListEmployee lemp;
    private ListProject lproj;

    public ManagerProject() {
        lemp = new ListEmployee();
        lproj = new ListProject();
        lemp.readFromFile();
        lproj.readFromFile();
    }

    public void menu() {
        System.out.println(lproj.getN());
        int mode;
        do {
            mainMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runMainMenu(mode);
        } while(true);
    }

    public void mainMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Print All Project");
        System.out.println("2. Add Project");
        System.out.println("3. Delete Project");
        System.out.println("4. Find");
        System.out.println("5. Change");
        System.out.println("6. Statistic");
    }

    public void findManager() {
        int mode;
        do {
            findMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runFind(mode);
        } while (true);
    }

    public void changeManager(int locate) {
        int mode;
        do {
            changeProjectMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runChangeProject(mode,locate);
        } while (true);
    }

    public void findMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Find By ID");
        System.out.println("2. Find By Name");
    }

    public void changeProjectMenu() {
        System.out.println("Choose Information That You Want To Change");
        System.out.println("0. Exit");
        System.out.println("1. Manager Allowance Rate");
        System.out.println("2. Main Engineer Allowance Rate");
        System.out.println("3. Participant Allowance Rate");
        System.out.println("4. Project Name");
        System.out.println("5. Start Day");
        System.out.println("6. Accomplish Day");
        System.out.println("7. Manager ID");
        System.out.println("8. Main Engineer ID");
        System.out.println("9. Participant ID");
    }


    public void runMainMenu(int mode) {
        switch (mode) {
            case 1: {
                sort();
                lproj.writeToFile();
                for (int i = 0;i < lproj.getN();i++) {
                    putProject(lproj.getData()[i]);
                }
                break;
            }
            case 2: {
                sort();
                lproj.writeToFile();
                addProjects();
                break;
            }
            case 3: {
                deleteProject();
                break;
            }
            case 4: {
                findManager();
                break;
            }
            case 5: {
                int locate = checkIfProjectInList();
                if (locate == -1) break;
                changeManager(locate);
                break;
            }
            case 6: {
                statistic();
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
                System.out.println("ID:");
                String id = sc.nextLine();
                Project result = lproj.find(id);
                putProject(result);
                break;
            }
            case 2: {
                findByName();
                break;
            }
            default: {
                System.out.println("Invalid Action!");
            }
        }

    }

    public void runChangeProject(int mode, int locate) {
        switch (mode) {
            case 0: {
                lproj.writeToFile();
                break;
            }
            case 1: {
                System.out.println("New Manager Allowance Rate: ");
                float newrate = sc.nextFloat();
                sc.nextLine();
                lproj.getData()[locate].setManagerAllowanceRate(newrate);
                break;
            }
            case 2: {
                System.out.println("New Main Engineer Allowance Rate: ");
                float newrate = sc.nextFloat();
                sc.nextLine();
                lproj.getData()[locate].setMainEngineerAllowanceRate(newrate);
                break;
            }
            case 3: {
                System.out.println("New Participant Allowance Rate: ");
                float newrate = sc.nextFloat();
                sc.nextLine();
                lproj.getData()[locate].setParticipantAllowanceRate(newrate);
                break;
            }
            case 4: {
                System.out.println("New Name: ");
                String newname = sc.nextLine();
                lproj.getData()[locate].setProjectName(newname);
                break;
            }
            case 5: {
                System.out.println("Alter Start Day: ");
                String newday = sc.nextLine();
                lproj.getData()[locate].setStartDay(newday);
                break;
            }
            case 6: {
                System.out.println("Accomplish Day");
                String newday = sc.nextLine();
                lproj.getData()[locate].setAccomplishDay(newday);
                break;
            }
            case 7: {
                System.out.println("New Manager ID: ");
                String newid = sc.nextLine();
                lproj.getData()[locate].setManagerId(newid);
                break;
            }
            case 8: {
                System.out.println("New Main Engineer ID: ");
                String newid = sc.nextLine();
                lproj.getData()[locate].setMainengineerId(newid);
                break;
            }
            case 9: {
                doChangeParticipant(locate);
                break;
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }


    public void putProject(Project proj) {
        Employee emp;

        //thong tin co ban cua du an
        System.out.printf("%50s ",proj.getProjectName());
        System.out.printf("%10s ",proj.getProjectId());
        System.out.printf("%5s ",proj.getManagerAllowanceRate());
        System.out.printf("%5s ",proj.getMainEngineerAllowanceRate());
        System.out.printf("%5s %n",proj.getParticipantAllowanceRate());

        //thong tin quan ly du an
        emp = lemp.findById(proj.getManagerId());
        System.out.printf("%10s %30s %50s %n",proj.getManagerId(),emp.getSurname() + " " + emp.getName(),"Project's Manager");


        //thong tin ki su truong du an
        emp = lemp.findById(proj.getMainengineerId());
        System.out.printf("%10s %30s %50s %n",proj.getMainengineerId(),emp.getSurname() + " " + emp.getName(),"Project's Main Engineer");

        for (String b: proj.getEmployeeId()) {
            emp = lemp.findById(b);
            if (emp != null) System.out.printf("%10s %30s %n",b,emp.getSurname() + " " + emp.getName());
        }
        System.out.println();
    }

    public void addProjects() {
        System.out.println("Number Project You Want To Add:");
        int n = sc.nextInt();sc.nextLine();
        for (int i = 0;i < n;i++) {
            Project newproj = new Project();
            newproj.getProject(autoID());

            //ktr id neu khong ton tai thi xoa
            for (int j = 0;j < newproj.getEmployeeId().length;j++) {
                if (lemp.findById(newproj.getEmployeeId()[j]) == null) {
                    System.out.println(newproj.getEmployeeId()[j] + "Isn't Exist!");
                    newproj.deleteEmployeeId(newproj.getEmployeeId()[j]);
                }
            }

            lproj.add(newproj);
        }
        sort();
        lproj.writeToFile();
    }

    public void deleteProject() {
        System.out.println("ID Project You Want To Delete: ");
        String id = sc.nextLine();
        lproj.delete(id);
        lproj.writeToFile();
    }

    public void findByName() {
        System.out.println("Project Name: ");
        String name = sc.nextLine();
        ListProject result = lproj.findByName(name);
        for (Project p: result.getData()) {
            putProject(p);
        }
    }

    public void sort() {
        for (int i = 0;i < lproj.getN() - 1;i++)
            for (int j = i + 1;j < lproj.getN();j++) {
                if (Integer.parseInt(lproj.getData()[i].getProjectId()) > Integer.parseInt(lproj.getData()[j].getProjectId())) {
                    Project tmp = lproj.getData()[i];
                    lproj.getData()[i] = lproj.getData()[j];
                    lproj.getData()[j] = tmp;
                }
            }
    }

    public String  autoID() {
        int newID = 3000;
        int i = 0;
        while (i < lproj.getN()) {
            //so xem id moi co trung id cu khong neu co thi tang 1 don vi neu khong trung thi out ra
            if (newID == Integer.parseInt(lproj.getData()[i++].getProjectId())) {
                newID++;
            }
            else break;
        }
        return Integer.toString(newID);
    }

    public void statistic() {
        int[] result = lproj.statistic();

        System.out.printf("%30s |%15s| %n","Project Name","Number Employee");
        System.out.println("-----------------------------------------------------------------------------");
        for (int i = 0;i < lproj.getN();i++) {
            System.out.printf("%30s |%15d| %n",lproj.getData()[i].getProjectName(),result[i]);
        }
    }

    public int checkIfProjectInList() {
        System.out.println("Project ID That You Want To Change: ");String id = sc.nextLine();
        //ktr co trong list ko
        int locate;
        for (int i = 0;i < lproj.getData().length;i++) {
            if (id.equals(lproj.getData()[i].getProjectId())) {
                locate = i;
                return locate;
            }
        }
        System.out.println("There Is No Project Have That ID");
        return -1;
    }

    public void doChangeParticipant(int locate) {
        int mode;
        do {
            System.out.println("0. Exit");
            System.out.println("1. Add");
            System.out.println("2. Remove");
            mode = sc.nextInt();
            sc.nextLine();
            switch (mode) {
                case 0: {
                    break;
                }
                case 1: {
                    doAddParticipant(locate);
                    break;
                }
                case 2: {
                    doDeleteParticipant(locate);
                    break;
                }
                default: {
                    System.out.println("khong co che do nay");
                    break;
                }
            }
        } while (mode != 0);
    }

    public void doAddParticipant(int locate) {
        System.out.println("Numbers Of ID You Want To Add: ");
        int numbers = sc.nextInt();
        sc.nextLine();
        for (int i = 0;i < numbers;i++) {
            System.out.println("ID You Want To Add: ");
            String id = sc.nextLine();
            lproj.getData()[locate].addEmployeeId(id);
        }
    }

    public void doDeleteParticipant(int locate) {
        System.out.println("Numbers Of ID You Want To Delete: ");
        int numbers = sc.nextInt();
        sc.nextLine();
        for (int i = 0;i < numbers;i++) {
            System.out.println("ID You Want To Delete: ");
            String id = sc.nextLine();
            lproj.getData()[locate].deleteEmployeeId(id);
        }
    }
}
