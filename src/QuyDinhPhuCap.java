import java.util.Scanner;

public class QuyDinhPhuCap{  
    
    private Scanner sc=new Scanner(System.in);

    protected String id;
    protected String name;
    protected String about;
    protected long amount;
    protected String date_ap_dung;
    protected Boolean status;

    public QuyDinhPhuCap(){}

    public QuyDinhPhuCap(String id, String name, String about, long amount, String date_ap_dung, boolean status){
        this.id = id;
        this.name = name;
        this.about = about;
        this.amount = amount;
        this.date_ap_dung = date_ap_dung; //ko phai ngay ap dung cho 1 nhan vien, ma la date bat dau dung loai phu cap nay cho toan cong ty
        this.status = status;
    }


    public void getQuyDinhPhuCap(){
        System.out.printf("ID Phu Cap:"); id=sc.nextLine();
        System.out.printf("Ten Phu Cap: "); name=sc.nextLine();
        System.out.printf("Mo ta Phu Cap: ");about=sc.nextLine();
        System.out.printf("Muc Phu Cap(Vnd): ");amount=sc.nextInt();
        System.out.printf("Thoi gian phu cap bat dau:");date_ap_dung=sc.nextLine();
        System.out.printf("Trang thai phu cap (true/false): "); status=sc.nextBoolean();

    }

    public void putPhuCap(){
        System.out.printf("ID Phu Cap: %s%n",id);
        System.out.printf("Ten Phu Cap: %s%n",name);
        System.out.printf("Mo ta Phu Cap: %s%n",about);
        System.out.printf("Muc Phu Cap(Vnd): %s%n",amount);
        System.out.printf("Ngay bat dau ap dung phu cap: %s%n",date_ap_dung);
        System.out.printf("Trang thai phu cap: %sn",status);
    }

    
    //
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    //
    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
    //
    public long getAmount() { return amount; }
    public void setAmount(long amount) { this.amount = amount; }
    //
    public String getDateApDung() { return date_ap_dung; }
    public void setDateApDung(String date_ap_dung) { this.date_ap_dung = date_ap_dung; }
    //
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }





    

}

