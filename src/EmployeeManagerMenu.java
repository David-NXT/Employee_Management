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
                    ListOffcialEmployee list = new ListOffcialEmployee();
                    list.readFromFile();
                    do {
                        offcialEmployeeMenu();
                        mode = sc.nextInt();
                        sc.nextLine();
                        runOffcialEmployeeManager(mode, list);
                    } while (mode != 0);
                    break;
                }
                case 2: {
                    ListSeasonalStaff list = new ListSeasonalStaff();
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
                default: {
                    System.out.println("khong co chuc nang nay, voi long nhap lai");
                    break;
                }
            }
        } while (mainmode != 0);
    }

    public void mainMenu() {
        System.out.println("0. End Program");
        System.out.println("1. Offcial Employee Manager");
        System.out.println("2. Seasonal Staff Manager");
        System.out.println("3. Project Manager");
    }

    public void offcialEmployeeMenu() {
        System.out.println("---Offcial Employee Manager---");
        System.out.println("0. Save and Exit");
        System.out.println("1. reCreate List");
        System.out.println("2. Add Employee");
        System.out.println("3. Delete Employee");
        System.out.println("4. Print Data");
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

    public void runOffcialEmployeeManager(int mode, ListOffcialEmployee list) {
        switch (mode) {
            case 0: {
                list.writeToFile();
                System.out.println("da luu file");
                break;
            }
            case 1: {
                list.get();
                list.writeToFile();
                break;
            }
            case 2: {
                list.add();
                list.writeToFile();
                break;
            }
            case 3: {
                System.out.println("Put The Employee ID That You Want to Delete: ");String id = sc.nextLine();
                list.delete(id);
                list.writeToFile();
                break;
            }
            case 4: {
                list.put();
                break;
            }
            default: {
                System.out.println("khong co chuc nang nay, xin vui long nhap lai.");
                break;
            }
        }
    }

    public void runSeasonalStaffManager(int mode, ListSeasonalStaff list) {
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
                System.out.println("Put The Employee ID That You Want to Delete: ");String id = sc.nextLine();
                list.delete(id);
                break;
            }
            case 4: {
                list.put();
                break;
            }
            default: {
                System.out.println("khong co chuc nang nay, xin vui long nhap lai.");
                break;
            }
        }
    }

    public void runProjectManager(int mode, ListProject list) {
        ListOffcialEmployee listemployee = new ListOffcialEmployee();
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
