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
                        //them chuc nang cho quan ly phong ban nha bao
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

    public void changeOfficialEmployeeMenu() {
        System.out.println("Choose Information That You Want To Change:");
        System.out.println("0. Exit");
        System.out.println("1. Name");
        System.out.println("2. Gender");
        System.out.println("3. Phone Number");
        System.out.println("4. Email");
        System.out.println("5. Start Working Day");
        System.out.println("6. Basic Salary");
    }

    public void seasonalStaffMenu() {
        System.out.println("---Seasonal Staff Manager---");
        System.out.println("0. Save and Exit");
        System.out.println("1. reCreate List");
        System.out.println("2. Add Employee");
        System.out.println("3. Delete Employee");
        System.out.println("4. Print Data");
    }

    public void projectMenu() {
        System.out.println("---Project Manager---");
        System.out.println("0. Save and Exit");
        System.out.println("1. reCreate List");
        System.out.println("2. Add Project");
        System.out.println("3. Delete Project");
        System.out.println("4. Print Data");
    }

    public void runOfficialEmployeeManager(int mode, ListEmployee list) {
        switch (mode) {
            case 0: {
                list.writeToFile();
                break;
            }
            case 1: {
                list.getOffcialEmployee();
                break;
            }
            case 2: {
                OfficialEmployee a = new OfficialEmployee();
                a.get();
                list.add(a);
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
                    System.out.println("There No Official Employ Have That ID.");
                    break;
                }

                //chay menu chinh sua
                else {
                    do {
                        changeOfficialEmployeeMenu();
                        mode = sc.nextInt();
                        sc.nextLine();
                        runChangeOfficialEmployeeInformation(mode,locate,list);
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

    public void runChangeOfficialEmployeeInformation (int mode, int locate, ListEmployee list) {
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
                System.out.println("New Basic Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();
                list.getData()[locate] = (OfficialEmployee) list.getData()[locate];
                ((OfficialEmployee) list.getData()[locate]).setBasicSalary(salary);
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
                SeasonalStaff a = new SeasonalStaff();
                a.get();
                list.add(a);
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
            default: {
                System.out.println("khong co chuc nang nay, xin vui long nhap lai.");
                break;
            }
        }
    }
}
