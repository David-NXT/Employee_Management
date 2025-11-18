import java.util.Scanner;

public class ManageContract {
    private final Scanner sc = new Scanner(System.in);
    private ListContract lcon;

    public ManageContract() {
        lcon = new ListContract();
        lcon.readFromFile();
    }


    public void mainMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Print All Contract");
        System.out.println("2. Add");
        System.out.println("3. Delete");
        System.out.println("4. Change");
        System.out.println("5. Find");
    }


}
