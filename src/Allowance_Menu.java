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
            System.out.println("1. Nhập phụ cấp thủ công cho nhân viên");
            System.out.println("2. Tính phụ cấp tự động cho toàn bộ nhân viên");
            System.out.println("3. Hiển thị toàn bộ danh sách phụ cấp");
            System.out.println("4. Tìm phụ cấp theo mã nhân viên");
            System.out.println("5. Lưu dữ liệu phụ cấp ra file");
            System.out.println("6. Tính tổng phụ cấp theo tháng");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> addManualAllowance(sc);
                case 2 -> generateAllowances();
                case 3 -> listAllow.showAll();
                case 4 -> findAllowanceById(sc);
                case 5 -> saveAllowances();
                case 6 -> calcInMonth(sc);
                case 0 -> System.out.println("⬅ Quay lại menu chính...");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    // ───────────────────────────────────────────────
    private void addManualAllowance(Scanner sc) {
        System.out.print("Nhập ID nhân viên cần thêm phụ cấp: ");
        String id = sc.nextLine();

        Employee emp = listEmp.findById(id);
        if (emp != null) {
            listAllow.createAllowance(id, listEmp, listPos, listPro, listRule);
        } else {
            System.out.println("❌ Không tìm thấy nhân viên có ID " + id);
        }
    }

    private void generateAllowances() {
        listAllow.generateAllowances(listEmp, listPro);
        System.out.println("✅ Đã tính phụ cấp tự động cho toàn bộ nhân viên.");
    }

    private void findAllowanceById(Scanner sc) {
        System.out.print("Nhập ID nhân viên cần tìm: ");
        String id = sc.nextLine();
        listAllow.findByEmployeeId(id);
    }

    private void saveAllowances() {
        listAllow.writeToFile();
        System.out.println("💾 Đã lưu danh sách phụ cấp vào file 'data_allowance'");
    }

    // ───────────────────────────────────────────────
    private void calcInMonth(Scanner sc) {
        System.out.print("Nhập tháng cần tính (định dạng MM/yyyy): ");
        int m=sc.nextInt();
        sc.nextLine();
        String month = String.format("%02d", m);

        System.out.println("📆 Đang tính phụ cấp cho tháng " + month + "...");
        listAllow.calculateMonthlyAllowance(listAllowRule, month);
    }
}
