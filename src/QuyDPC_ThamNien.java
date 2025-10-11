import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class QDPC_ThamNien extends QuyDinhPhuCap{

    private Employee nv;
    private int requirements;

    public QDPC_ThamNien(Employee nv,String id, String name, String about, int amount, String date_ap_dung, boolean status){
        super(id,name,about,amount,date_ap_dung,status);
        this.nv = nv;
        this.requirements = requirements;

    }
    public void getQDPC_ThamNien(){
        System.out.printf("Yeu cau tham nien:");requirements=sc.nextInt();
    }
    public void putQDPC_ThamNien(){
        System.out.printf("Yeu cau tham nien: %d%n",requirements);
    }

    public long tinhThamNien(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yy MM dd");
        LocalDate start = LocalDate.parse(nv.getStartWorkingDay(), fmt);
        LocalDate now = LocalDate.now();
        return ChronoUnit.YEARS.between(start,now); //years
    }

    public int phuCap(){
        long thamnien = tinhThamNien();
        return (thamnien >= requirements)?amount:0;
    }

    public void putPhuCapThamNien() {
        System.out.printf("So nam lam viec: %d%n",tinhThamNien());
        System.out.printf("Phu cap thuc nhan: %d%n",phuCap());
    }

     //Get/set area
     //
    public int getRequirements() { return requirements; }
    public void setrequirements(int requirements) { this.requirements = requirements; }





}
