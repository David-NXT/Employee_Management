public class Statistic {
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
        ListSalary a = new ListSalary();
        a.loadFromFile();
        ListEmployee b = new ListEmployee();
        b.readFromFile();

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

        for (int i = 0;i < a.getCount();i++) {
            System.out.printf("%-10s %-30s ",a.getAll()[i].getEmpId(),b.findById(a.getAll()[i].getEmpId()).getSurname() + " " + b.findById(a.getAll()[i].getEmpId()).getName());
            for (int j = startyear;j <= endyear;j++) {
                employeeQuarterSalary(1,j,a.getAll()[i].getSalaryId());
                employeeQuarterSalary(2,j,a.getAll()[i].getSalaryId());
                employeeQuarterSalary(3,j,a.getAll()[i].getSalaryId());
                employeeQuarterSalary(4,j,a.getAll()[i].getSalaryId());
            }
            System.out.println();
        }
    }

    public void employeeQuarterSalary(int mode, int year, String id) {
        ListSalary a = new ListSalary();
        a.loadFromFile();
        double sum = 0;
        int count = 0;

        if (mode != 1) {
            if (mode == 2) mode += 2;
            else if (mode == 3) mode += 4;
            else mode += 7;
        }

        for (int i = 0;i < a.getCount();i++) {
            if ((a.getAll()[i].getMonth() == mode || a.getAll()[i].getMonth() == (mode + 1) || a.getAll()[i].getMonth() == (mode + 2)) && a.getAll()[i].getYear() == year  && a.getAll()[i].getSalaryId().equals(id)) {
                sum += a.getAll()[i].getTotalSalary();
                count++;
                if (count == 3) {
                    break;
                }
            }
        }
        System.out.printf("|%30f |",sum);
    }
}
