import java.io.*;

public abstract class FileIO<T extends Serializable> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public void write(String fileName) {
        String basepath = System.getProperty("user.dir");
        File dir = new File(basepath + File.separator + "data");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(dir, fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            // Ghi chính đối tượng hiện tại (là lớp con, ví dụ ListEmployee)
            oos.writeObject(this);
            System.out.println("Saved to " + fileName + "!");
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T read(String fileName) {
        String basepath = System.getProperty("user.dir");
        File file = new File(basepath + File.separator + "data" + File.separator + fileName);

        if (!file.exists()) {
            System.out.println("File is not exist: " + fileName);
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // Ép kiểu (casting) an toàn cho đối tượng được đọc
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}