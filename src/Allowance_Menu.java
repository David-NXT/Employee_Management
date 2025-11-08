import java.util.Scanner;

public class Allowance_Menu {
    private final ListEmployee listEmp;
    private final ListProject listPro;
    private final ListAllowance listAllow;
    private final ListPosition listPos;
    private final ListSeniorityAllowanceRule listRule;
    private final ListAllowanceRule listAllowRule;

    public Allowance_Menu(ListEmployee listEmp,
                          ListProject listPro,
                          ListAllowance listAllow,
                          ListPosition listPos,
                          ListSeniorityAllowanceRule listRule,
                          ListAllowanceRule listAllowRule) {
        this.listEmp = listEmp;
        this.listPro = listPro;
        this.listAllow = listAllow;
        this.listPos = listPos;
        this.listRule = listRule;
        this.listAllowRule = listAllowRule;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n========= MENU PHỤ CẤP =========");
            System.out.println("1. Tính phụ cấp tự động cho toàn bộ nhân viên");
            System.out.println("2. Hiển thị toàn bộ danh sách phụ cấp");
            System.out.println("3. Tìm phụ cấp theo mã nhân viên");
            System.out.println("4. Lưu dữ liệu phụ cấp ra file");
            System.out.println("5. Tính phụ cấp theo tháng khi có lễ");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> generateAllowances();
                case 2 -> listAllow.showAll();
                case 3 -> findAllowanceById(sc);
                case 4 -> saveAllowances();
                case 5 -> calcInMonth(sc);
                case 0 -> System.out.println("⬅ Quay lại menu chính...");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }



    private void generateAllowances() {
        listAllow.generateAllowances(listEmp, listPro,listPos,listAllowRule);
        listAllow.calculateProjectAllowance(listPro, listEmp);
        System.out.println(" Đã tính phụ cấp tự động cho toàn bộ nhân viên.");
    }

    private void findAllowanceById(Scanner sc) {
        System.out.print("Nhập ID nhân viên cần tìm: ");
        String id = sc.nextLine();
        listAllow.findByEmployeeId(id);
    }

    private void saveAllowances() {
        listAllow.writeToFile();
        System.out.println(" Đã lưu danh sách phụ cấp vào file 'data_allowance'");
    }

    // ───────────────────────────────────────────────
    private void calcInMonth(Scanner sc) {
        System.out.print("Nhập tháng cần tính (định dạng MM/yyyy): ");
        int m=sc.nextInt();
        sc.nextLine();
        String month = String.format("%02d", m);

        System.out.println(" Đang tính phụ cấp cho tháng " + month + "...");
        for (Allowance a : listAllow.getData()) {
            System.out.println("Phụ cấp: " + a.getAllowanceId() + " - Ngày tạo: " + a.getCreatedDate());
        }
        listAllow.calculateMonthlyAllowance(listAllowRule, month);
    }
}
