import java.util.Scanner;

public class Statistic {
    private final transient Scanner sc = new Scanner(System.in);
    private ListSalary a;
    private ListEmployee b;

        public Statistic() {
            a = new ListSalary();
            a.loadFromFile();
            b = new ListEmployee();
            b.readFromFile();
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


    public void mainMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Statistic Employee Salary By Quarter");
        System.out.println("2. Statistic Salary By year");
    }

    public void runMenu(int mode) {
        switch (mode) {
            case 1: {
                fullStatisticEmployeeByYear(a.findMinYear(),a.findMaxYear());
                break;
            }
            case 2: {
                fullStatisticByYear(a.findMinYear(),a.findMaxYear());
                break;
            }
            default: {
                System.out.println("Invalid Action!");
                break;
            }
        }
    }

    public void fullStatisticByYear(int startyear, int endyear) {
        ListSalary a = new ListSalary();
        a.loadFromFile();
        SalaryStatistics data = new SalaryStatistics(a.getAll(),a.getCount());

        System.out.printf("%-10s","");
        for (int i = startyear;i <= endyear;i++) {
            System.out.printf("%20d ",i);
        }
        System.out.println();

        for (int i = 1;i <= 12;i++) {
            System.out.printf("%-10d",i);
            for (int j = startyear;j <= endyear;j++) {
                System.out.printf("%20f ",data.totalNetSalary(i,j));
            }
            System.out.println();
        }
    }

    public void fullStatisticEmployeeByYear(int startyear, int endyear) {

        System.out.printf("%-42s","");
        for (int i = startyear;i <= endyear;i++) {
            System.out.printf("|%129d |",i);
        }
        System.out.println();

        System.out.printf("%-42s","");
        for (int i = startyear;i <= endyear;i++) {
            System.out.printf("|%30s ||%30s ||%30s ||%30s |","1","2","3","4");
        }
        System.out.println();

        for (int i = 0;i < b.getData().length;i++) {
            System.out.printf("%-10s %-30s ",b.getData()[i].getId(),b.getData()[i].getSurname() + " " + b.getData()[i].getName());
            for (int j = startyear;j <= endyear;j++) {
                employeeQuarterSalary(1,j,a,b.getData()[i].getId());
                employeeQuarterSalary(2,j,a,b.getData()[i].getId());
                employeeQuarterSalary(3,j,a,b.getData()[i].getId());
                employeeQuarterSalary(4,j,a,b.getData()[i].getId());
            }
            System.out.println();
        }
    }

    public void employeeQuarterSalary(int mode, int year, ListSalary data, String empID) {
        double sum = 0;

        if (mode != 1) {
            if (mode == 2) mode += 2;
            else if (mode == 3) mode += 4;
            else mode += 7;
        }

        //tim tong tien luong trong 3 thang
        for (int i = 0;i < 3;i++) {
            sum += data.getMonthSalary(year,mode + i,empID);
        }

        System.out.printf("|%30f |",sum);
    }


}
