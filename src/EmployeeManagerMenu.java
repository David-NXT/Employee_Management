import java.util.Scanner;

public class EmployeeManagerMenu {
    private transient final Scanner sc = new Scanner(System.in);

    public void menu() {
        int mainmode;
        int mode;
        do {
            mainMenu();
            mainmode = sc.nextInt();
            sc.nextLine();//loai bo \n thua trong buffer
            switch (mainmode) {
                case 0: {
                    break;
                }
                case 1: {
                    ListEmployee list = new ListEmployee();
                    list.readFromFile();
                    do {
                        offcialEmployeeMenu();
                        mode = sc.nextInt();
                        sc.nextLine();
                        runOfficialEmployeeManager(mode, list);
                    } while (mode != 0);
                    break;
                }
                case 2: {
                    ListEmployee list = new ListEmployee();
                    list.readFromFile();
                    do {
                        seasonalStaffMenu();
                        mode = sc.nextInt();
                        sc.nextLine();
                        runSeasonalStaffManager(mode, list);
                    } while (mode != 0);
                    break;
                }
                case 3: {
                    ListProject list = new ListProject();
                    list.readFromFile();
                    do {
                        projectMenu();
                        mode = sc.nextInt();
                        sc.nextLine();
                        runProjectManager(mode, list);
                    } while (mode != 0);
                    break;
                }
                case 4: {
                    do {
                        //them chuc nang cho quan ly luong nha Travis
                        mode =sc.nextInt();
                        sc.nextLine();// xoa \n thua trong buffer
                    } while(mode != 0);
                    break;
                }
                case 5: {
                    do {
                        //them chuc nang cho quan ly phong ban nha Nghi
                        mode =sc.nextInt();
                        sc.nextLine();// xoa \n thua trong buffer
                    } while(mode != 0);
                    break;
                }
                default: {
                    System.out.println("khong co chuc nang nay, voi long nhap lai");
                    break;
                }
            }
        } while (mainmode != 0);
    }

    public void mainMenu() {
        System.out.println("0. End Program");
        System.out.println("1. Official Employee Manager");
        System.out.println("2. Seasonal Staff Manager");
        System.out.println("3. Project Manager");
        System.out.println("4. Salary Manager");
        System.out.println("5. Department Manager");
    }

    public void offcialEmployeeMenu() {
        System.out.println("---Official Employee Manager---");
        System.out.println("0. Save and Exit");
        System.out.println("1. reCreate List");
        System.out.println("2. Add Employee");
        System.out.println("3. Delete Employee");
        System.out.println("4. Print Data");
        System.out.println("5. Change Employee Information");
    }

    public void changeEmployeeMenu(int type) {// 1 for official employee | 2 for seasonal staff
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

    public void seasonalStaffMenu() {
        System.out.println("---Seasonal Staff Manager---");
        System.out.println("0. Save and Exit");
        System.out.println("1. reCreate List");
        System.out.println("2. Add Employee");
        System.out.println("3. Delete Employee");
        System.out.println("4. Print Data");
        System.out.println("5. Change Employee Information");
    }

    public void projectMenu() {
        System.out.println("---Project Manager---");
        System.out.println("0. Save and Exit");
        System.out.println("1. reCreate List");
        System.out.println("2. Add Project");
        System.out.println("3. Delete Project");
        System.out.println("4. Print Data");
        System.out.println("5. Change Project Information");
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

    public void runOfficialEmployeeManager(int mode, ListEmployee list) {
        switch (mode) {
            case 0: {
                list.writeToFile();
                break;
            }
            case 1: {
                list.getOfficialEmployee();
                break;
            }
            case 2: {
                System.out.println("Number Employee You Want To Add: ");
                int n = sc.nextInt();
                sc.nextLine();
                for (int i = 0;i < n;i++) {
                    OfficialEmployee a = new OfficialEmployee();
                    String id = list.getIdAutoForOE();
                    a.get(id);
                    list.add(a);
                }
                break;
            }
            case 3: {
                System.out.println("Put The Employee ID That You Want to Delete: ");String id = sc.nextLine();
                list.delete(id);
                break;
            }
            case 4: {
                list.putOffcialEmployee();
                break;
            }
            case 5: {//nhap id -> ktr co trong list vs la nv chinh thuc khong -> chay menu sua
                System.out.print("Employee ID That You Want To Change: ");String id = sc.nextLine();
                //ktr co trong list ko
                int locate = -1;
                for (int i = 0;i < list.getData().length;i++) {
                    if (id.equals(list.getData()[i].getId()) && list.getData()[i] instanceof OfficialEmployee) {
                        locate = i;
                        break;
                    }
                }
                if (locate == -1) {
                    System.out.println("There Is No Official Employ Have That ID.");
                    break;
                }

                //chay menu chinh sua
                else {
                    do {
                        changeEmployeeMenu(1);
                        mode = sc.nextInt();
                        sc.nextLine();
                        runChangeEmployeeInformation(mode,locate,list,1);
                    } while (mode != 0);
                }

                break;
            }
            default: {
                System.out.println("khong co chuc nang nay, xin vui long nhap lai.");
                break;
            }
        }
    }

    public void runChangeEmployeeInformation (int mode, int locate, ListEmployee list, int type) {
        switch (mode) {
            case 0: {
                break;
            }
            case 1: {
                System.out.println("New Surname: ");String surname = sc.nextLine();
                System.out.println("New LastName: ");String lastname = sc.nextLine();
                surname = surname.trim();
                lastname = lastname.trim();
                list.getData()[locate].setSurname(surname);
                list.getData()[locate].setName(lastname);
                break;
            }
            case 2: {
                if (list.getData()[locate].getGender().equals("nam")) {
                    list.getData()[locate].setGender("nu");
                } else {
                    list.getData()[locate].setGender("nam");
                }

                System.out.println("Done!");
                break;
            }
            case 3: {
                System.out.println("New Phone Number: ");String newphonenumber = sc.nextLine();
                list.getData()[locate].setPhoneNumber(newphonenumber);
                break;
            }
            case 4: {
                System.out.println("New Email: ");String newemail = sc.nextLine();
                list.getData()[locate].setEmail(newemail);
                break;
            }
            case 5: {
                System.out.println("Alter Stark Working Day: ");String newday = sc.nextLine();
                list.getData()[locate].setStartWorkingDay(newday);
                break;
            }
            case 6: {
                if (type == 1) {
                    System.out.println("New Basic Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    ((OfficialEmployee) list.getData()[locate]).setBasicSalary(salary);
                } else if (type == 2) {
                    System.out.println("New Hourly Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    ((SeasonalStaff) list.getData()[locate]).setHourlySalary(salary);
                }

                break;
            }
            default: {
                System.out.println("khong co chuc nang nay, xin vui long nhap lai.");
                break;
            }
        }
    }

    public void runSeasonalStaffManager(int mode, ListEmployee list) {
        switch (mode) {
            case 0: {
                list.writeToFile();
                break;
            }
            case 1: {
                list.getSeasonalStaff();
                break;
            }
            case 2: {
                System.out.println("Number Employee You Want To Add: ");
                int n = sc.nextInt();
                sc.nextLine();
                for (int i = 0;i < n;i++) {
                    SeasonalStaff a = new SeasonalStaff();
                    String id = list.getIdAutoForST();
                    a.get(id);
                    list.add(a);
                }
                break;
            }
            case 3: {
                System.out.println("Put The Employee ID That You Want to Delete: ");String id = sc.nextLine();
                list.delete(id);
                break;
            }
            case 4: {
                list.putSeasonalStaff();
                break;
            }
            case 5: {
                System.out.print("Employee ID That You Want To Change: ");String id = sc.nextLine();
                //ktr co trong list ko
                int locate = -1;
                for (int i = 0;i < list.getData().length;i++) {
                    if (id.equals(list.getData()[i].getId()) && list.getData()[i] instanceof SeasonalStaff) {
                        locate = i;
                        break;
                    }
                }
                if (locate == -1) {
                    System.out.println("There Is No Seasonal Staff Have That ID.");
                    break;
                }

                //chay menu chinh sua
                else {
                    do {
                        changeEmployeeMenu(2);
                        mode = sc.nextInt();
                        sc.nextLine();
                        runChangeEmployeeInformation(mode,locate,list,2);
                    } while (mode != 0);
                }

                break;
            }
            default: {
                System.out.println("khong co chuc nang nay, xin vui long nhap lai.");
                break;
            }
        }
    }

    public void runProjectManager(int mode, ListProject list) {
        ListEmployee listemployee = new ListEmployee();
        listemployee.readFromFile();
        switch (mode) {
            case 0: {
                list.writeToFile();
                break;
            }
            case 1: {
                list.get();
                break;
            }
            case 2: {
                list.add();
                break;
            }
            case 3: {
                System.out.println("Put The Project ID That You Want to Delete: ");String id = sc.nextLine();
                list.delete(id);
                break;
            }
            case 4: {
                list.put(listemployee);
                break;
            }
            case 5: {
                System.out.println("Project ID That You Want To Change: ");
                String id = sc.nextLine();

                int locate = -1;
                for (int i = 0;i < list.getData().length;i++) {
                    if (id.equals(list.getData()[i].getProjectId())) {
                        locate = i;
                        break;
                    }
                }

                if (locate == -1) {
                    System.out.println("There Is No Project Have That ID");
                    break;
                }

                do {
                    changeProjectMenu();
                    mode = sc.nextInt();
                    sc.nextLine();
                    runChangeProject(mode,locate,list);
                } while (mode != 0);
                break;
            }
            default: {
                System.out.println("khong co chuc nang nay, xin vui long nhap lai.");
                break;
            }
        }
    }

    public void runChangeProject(int mode, int locate, ListProject list) {
        switch (mode) {
            case 0: {
                break;
            }
            case 1: {
                System.out.println("New Manager Allowance Rate: ");
                float newrate = sc.nextFloat();
                sc.nextLine();
                list.getData()[locate].setManagerAllowanceRate(newrate);
                break;
            }
            case 2: {
                System.out.println("New Main Engineer Allowance Rate: ");
                float newrate = sc.nextFloat();
                sc.nextLine();
                list.getData()[locate].setMainEngineerAllowanceRate(newrate);
                break;
            }
            case 3: {
                System.out.println("New Participant Allowance Rate: ");
                float newrate = sc.nextFloat();
                sc.nextLine();
                list.getData()[locate].setParticipantAllowanceRate(newrate);
                break;
            }
            case 4: {
                System.out.println("New Name: ");
                String newname = sc.nextLine();
                list.getData()[locate].setProjectName(newname);
                break;
            }
            case 5: {
                System.out.println("Alter Start Day: ");
                String newday = sc.nextLine();
                list.getData()[locate].setStartDay(newday);
                break;
            }
            case 6: {
                System.out.println("Accomplish Day");
                String newday = sc.nextLine();
                list.getData()[locate].setAccomplishDay(newday);
                break;
            }
            case 7: {
                System.out.println("New Manager ID: ");
                String newid = sc.nextLine();
                list.getData()[locate].setManagerId(newid);
                break;
            }
            case 8: {
                System.out.println("New Main Engineer ID: ");
                String newid = sc.nextLine();
                list.getData()[locate].setMainengineerId(newid);
                break;
            }
            case 9: {
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
                            System.out.println("Numbers Of ID You Want To Add: ");
                            int numbers = sc.nextInt();
                            sc.nextLine();
                            for (int i = 0;i < numbers;i++) {
                                System.out.println("ID You Want To Add: ");
                                String id = sc.nextLine();
                                list.getData()[locate].addEmployeeId(id);
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("Numbers Of ID You Want To Delete: ");
                            int numbers = sc.nextInt();
                            sc.nextLine();
                            for (int i = 0;i < numbers;i++) {
                                System.out.println("ID You Want To Delete: ");
                                String id = sc.nextLine();
                                list.getData()[locate].deleteEmployeeId(id);
                            }
                            break;
                        }
                        default: {
                            System.out.println("khong co che do nay");
                            break;
                        }
                    }
                } while (mode != 0);
                break;
            }
            default: {
                System.out.println("khong co chuc nang nay");
                break;
            }
        }
    }
}
