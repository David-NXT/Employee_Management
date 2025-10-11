import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class QDPC_ThamNien extends QuyDinhPhuCap{
    private Employee nv;
    private int requirements;

    public QDPC_ThamNien(Employee nv,String id, String name, String about, long amount, String date_ap_dung, boolean status){
        super(id,name,about,amount,date_ap_dung,status);
        this.nv = nv;
        this.requirements = requirements;
    

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

    public int requirements(){
        int requirements = Integer.parseInt(sc.nextLine());
    }

    public void putPhuCapThamNien() {
        System.out.println("So nam lam viec: %s%n",tinhThamNien());
        System.out.println("Phu cap thuc nhan: %s%n",phuCap());
    }



}
