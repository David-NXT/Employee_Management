import java.util.Scanner;

public class ListProject {
    private final Scanner sc = new Scanner(System.in);
    private Project[] data;

    public ListProject() {
        data = new Project[0];
    }

    public ListProject(ListProject list) {
        data = list.data;
    }



    public Project[] getData() {
        return data;
    }

    public void setData(Project[] data) {
        this.data = data;
    }
}
