import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ManageAllowance {
    private final Scanner sc;
    private final ListAllowance listAllowance;
    private final ListEmployee listEmp;
    private final ListProject listProj;
    private final ListPosition listPos;
    private final ListAllowanceRule listAllowRule;
    private final ListSeniorityAllowanceRule seniorityRule;

    public ManageAllowance(ListEmployee listEmp, ListProject listProj,
                           ListPosition listPos, ListAllowanceRule listAllowRule,
                           ListSeniorityAllowanceRule seniorityRule) {
        this.sc = new Scanner(System.in);
        this.listAllowance = new ListAllowance();
        this.listEmp = listEmp;
        this.listProj = listProj;
        this.listPos = listPos;
        this.listAllowRule = listAllowRule;
        this.seniorityRule = seniorityRule;
    }

    public void menuAllowance() {
        int select;
        do {
            System.out.println("\n===== EMPLOYEE ALLOWANCE MANAGEMENT MENU =====");
            System.out.println("1. Automatically calculate allowances for employees.");
            System.out.println("2. Display all employee allowances.");
            System.out.println("3. Find allowances by employee code.");
            System.out.println("4. Save employee allowance data file.");
            System.out.println("5. Read allowance data from file.");
            System.out.println("0. Exit menu.");
            System.out.println("-> Select: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            select = sc.nextInt();
            sc.nextLine();

            switch (select) {
                case 1 -> generateAllowances();
                case 2 -> listAllowance.showAll();
                case 3 -> {
                    System.out.print("Enter employee code: ");
                    String empId = sc.nextLine();
                    Allowance a = listAllowance.findByEmployeeId(empId);
                    if (a != null) {
                        System.out.printf("%-10s %-10s %-20s %-10s %-12s %-12s %-12s %-12s %-12s %-12s %-12s%n",
                                "ID", "EmpID", "Name", "PosID",
                                "Job", "Project", "Seniority", "Total", "Date");
                        a.display();
                    } else {
                        System.out.println("No allowances found for this employee.");
                    }
                }

                case 4 -> listAllowance.writeToFile();
                case 5 -> {
                    listAllowance.readFromFile();
                    System.out.println(">> Read allowance data from file.");
                }
                case 0 -> System.out.println(">> You have exited the allowance menu.");
                default -> System.out.println("Invalid choice.");
            }
        } while (select != 0);
    }

    private void generateAllowances() {
        for (Employee emp : listEmp.getData()) {
            String empId = emp.getId();
            Position pos = listPos.find(empId);

            double basicSalary = 0;
            if (emp instanceof OfficialEmployee off) {
                basicSalary = off.getBasicSalary();
            } else if (emp instanceof SeasonalStaff s) {
                basicSalary = s.getHourlySalary() * s.getWorkTime();
            }

            double jobAllowance = (pos != null)
                    ? pos.getPositionAllowanceRate() * basicSalary
                    : calcJobAllowance(pos, basicSalary);

            double seniorityAllowance = calcSeniorityAllowance(emp);
            double projectAllowance = calcProjectAllowance(emp);
            String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
            double holidayAllowance = calcHolidayAllowanceByMonth(currentMonth);
            double total = jobAllowance + seniorityAllowance + projectAllowance + holidayAllowance;

            Allowance a = new Allowance();
            a.setAllowanceId(listAllowance.getIdAuto());
            a.setEmployeeId(empId);
            a.setEmployeeName(emp.getSurname() + " " + emp.getName());
            a.setPositionId(empId);
            a.setJobAllowance(jobAllowance);
            a.setSeniorityAllowance(seniorityAllowance);
            a.setProjectAllowance(projectAllowance);
            a.setAnnualAllowance(holidayAllowance);
            a.setTotalAllowance(total);
            a.setCreatedDate(LocalDate.now());

            listAllowance.add(a);
        }

        System.out.println(">> Allowance created for " + listEmp.getData().length + " employee.");
    }

    private double calcJobAllowance(Position pos, double salary) {
        if (pos == null || pos.getJob_title() == null) return 0;
        String title = pos.getJob_title().trim().toLowerCase();
        return switch (title) {
            case "Giam doc" -> salary * 1;
            case "Truong phong" -> salary * 0.7;
            case "Pho phong" -> salary * 0.5;
            case "nhan vien" -> salary * 0.3;
            case "thuc tap sinh " -> salary *0.2;
            default -> salary * 0.1;
        };
    }

    private double calcSeniorityAllowance(Employee emp) {
        try {
            String startDate = emp.getStartWorkingDay();
            if (startDate == null || startDate.length() < 10) return 0;

            int startYear = Integer.parseInt(startDate.substring(6, 10));
            int currentYear = LocalDate.now().getYear();
            int yearsWorked = currentYear - startYear;

            SeniorityAllowanceRule rule = seniorityRule.findYear(yearsWorked);
            return (rule != null) ? rule.getAllowancerate() : 0;
        } catch (Exception e) {
            System.out.println("Error calculating seniority allowance for employee " + emp.getId());
            return 0;
        }
    }
    private double calcProjectAllowance(Employee emp) {
        double total = 0;
        if (emp instanceof OfficialEmployee off) {
            for (Project p : listProj.getData()) {
                if (p.isEmployeeInProject(off)) {
                    total += p.projectAllowanceSalary(off);
                }
            }
        }
        return total;
    }

    private double calcHolidayAllowanceByMonth(String month) {
        ListAllowanceRule holidays = listAllowRule.findAllowanceInMonth(month);
        double totalHolidayAllowance = 0;

        for (AllowanceRule rule : holidays.getData()) {
            if (rule != null) {
                totalHolidayAllowance += rule.getAllowancerate();
            }
        }

        return totalHolidayAllowance;
    }
}