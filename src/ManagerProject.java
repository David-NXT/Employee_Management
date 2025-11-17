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
    }






    public void runMainMenu(int mode) {
        switch (mode) {
            case 1: {
                for (int i = 0;i < lproj.getN();i++) {
                    putProject(i);
                }
                break;
            }
            case 2: {
                addProjects();
                break;
            }
        }
    }






    public void putProject(int locate) {
        Employee emp;

        //thong tin co ban cua du an
        System.out.printf("%50s ",lproj.getData()[locate].getProjectName());
        System.out.printf("%10s ",lproj.getData()[locate].getProjectId());
        System.out.printf("%5s ",lproj.getData()[locate].getManagerAllowanceRate());
        System.out.printf("%5s ",lproj.getData()[locate].getMainEngineerAllowanceRate());
        System.out.printf("%5s %n",lproj.getData()[locate].getParticipantAllowanceRate());

        //thong tin quan ly du an
        emp = lemp.findById(lproj.getData()[locate].getManagerId());
        System.out.printf("%10s %30s %50s %n",lproj.getData()[locate].getManagerId(),emp.getSurname() + " " + emp.getName(),"Project's Manager");


        //thong tin ki su truong du an
        emp = lemp.findById(lproj.getData()[locate].getMainengineerId());
        System.out.printf("%10s %30s %50s %n",lproj.getData()[locate].getMainengineerId(),emp.getSurname() + " " + emp.getName(),"Project's Main Engineer");

        for (String b: lproj.getData()[locate].getEmployeeId()) {
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
            newproj.getProject();

            //ktr id neu khong ton tai thi xoa
            for (int j = 0;j < newproj.getEmployeeId().length;j++) {
                if (lemp.findById(newproj.getEmployeeId()[i]) == null) {
                    System.out.println(newproj.getEmployeeId()[i] + "Isn't Exist!");
                    newproj.deleteEmployeeId(newproj.getEmployeeId()[i]);
                }
            }

            lproj.add(newproj);
        }

        lproj.writeToFile();
    }
}
