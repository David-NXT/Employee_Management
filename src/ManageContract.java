import java.util.Scanner;

public class ManageContract {
    private final Scanner sc = new Scanner(System.in);
    private ListContract lcon;

    public ManageContract() {
        lcon = new ListContract();
        lcon.readFromFile();
    }

    public void menu() {
        int mode;
        do {
            mainMenu();
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            runMenu(mode);
        }   while (true);
    }

    public void mainMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Print All Contract");
        System.out.println("2. Add ConTract");
        System.out.println("3. Delete");
        System.out.println("4. Change");
        System.out.println("5. Find");
    }

    public void runMenu(int mode) {
        switch (mode) {
            case 1: {
                lcon.put();
                break;
            }
            case 2: {
                Contract a = new Contract();
                System.out.println("ID Employee:");
                String id = sc.nextLine();
                a.get(id);
                lcon.add(a);
                break;
            }
            case 3: {
                System.out.println("ID:");
                String id = sc.nextLine();
                lcon.delete(id);
                lcon.writeToFile();
                break;
            }
            case 4: {
                change();
                break;
            }
            case 5: {
                System.out.println("ID:");
                String id = sc.nextLine();
                Contract a = lcon.find(id);
                a.put();
                break;
            }
        }
    }


    public void change() {
        System.out.println("ID:");
        String id = sc.nextLine();

        int locate = lcon.isInList(id);
        if (locate == -1) return;
        int mode;
        do {
            System.out.println("0. Exit");
            System.out.println("1. Change Start Working Day");
            System.out.println("2. Change Content");
            mode = sc.nextInt();sc.nextLine();
            if (mode == 0) break;
            switch (mode) {
                case 1: {
                    System.out.println("Alternative Day:");
                    String day = sc.nextLine();
                    lcon.getData()[locate].setDay(day);
                    lcon.writeToFile();
                    break;
                }
                case 2: {
                    System.out.println("Alternative Content:");
                    String content = sc.nextLine();
                    lcon.getData()[locate].setContent(content);
                    lcon.writeToFile();
                    break;
                }
                default: {
                    System.out.println("Invalid Action!");
                    break;
                }
            }
        } while (true);
    }
}
