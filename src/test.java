public class test {
    public static void main(String[] args) {
                // Tạo danh sách phụ cấp
                ListAllowance list = new ListAllowance();

                System.out.println("=== NHẬP DANH SÁCH PHỤ CẤP NHÂN VIÊN ===");

                for (int i = 0; i < 5; i++) {  // bạn có thể đổi số 5 thành bao nhiêu tùy thích
                    System.out.println("\n→ Nhập phụ cấp thứ " + (i + 1) + ":");
                    String newId= list.getIdAutoForAE();
                    Allowance a = new Allowance();
                    a.setAllowanceId(newId);
                    a.get();        // nhập thông tin phụ cấp từ bàn phím
                    list.add(a);    // thêm vào danh sách
                }

                // Ghi dữ liệu vào file
                list.writeToFile();

                // Đọc lại dữ liệu từ file để kiểm tra
                System.out.println("\n=== KIỂM TRA DỮ LIỆU ĐÃ LƯU ===");
                list.readFromFile();
                list.put(); // hiển thị toàn bộ danh sách đã đọc được
    }
}
