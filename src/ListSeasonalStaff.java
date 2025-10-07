import java.util.Arrays;
import java.util.Scanner;

public class ListSeasonalStaff {
    private final Scanner sc = new Scanner(System.in);
    private SeasonalStaff[] data;
    private int n;//number of data

    public ListSeasonalStaff() {
        data = new SeasonalStaff[0];
        n = 0;
    }

    public ListSeasonalStaff(ListSeasonalStaff a) {
        data = a.data;
        n = a.data.length;
    }

    public void get() {
        System.out.print("Number of Seasonal Staff: ");n = sc.nextInt();
        sc.nextLine();//delete \n in buffer

        data = Arrays.copyOf(data,n);//cap them n phan tu

        for(int i = 0;i < n;i++){
            data[i] = new SeasonalStaff();
            data[i].getSeasonalStaff();
        }
    }

    public void put() {
        System.out.println("Number of Seasonal Staff: " + n);
        for(int i = 0;i < n;i++){
            data[i].putSeasonalStaff();
        }
    }

    public SeasonalStaff[] getData() {
        return data;
    }

    public void setData(SeasonalStaff[] data) {
        this.data = data;
    }
}
