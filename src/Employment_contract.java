import java.util.Scanner;
public class Employment_contract {
    private final Scanner sc= new Scanner(System.in);
    private String contractId;
    private String contractType;
    private String daySign;
    private String dayEnd;
    private String reasonableSalary;
    private String provision;
    private String id;

    public Employment_contract(){}
    public Employment_contract(String ci, String ct, String ds, String de, String rs, String p, String ma){
        this.contractId = ci;
        this.contractType=ct;
        this.daySign=ds;
        this.dayEnd=de;
        this.reasonableSalary=rs;
        this.provision=p;
        this.id=ma;
    }
    public Employment_contract(Employment_contract a){
       contractId=a.contractId;
       contractType=a.contractType;
       daySign=a.daySign;
       dayEnd=a.dayEnd;
       reasonableSalary=a.reasonableSalary;
       provision=a.provision;
       id=a.id;
    }
    public String getContractId() {
        return contractId;
    }
    public String getContractType(){
        return contractType;
    }
    public String getDaySign() {
        return daySign;
    }
    public String getDayEnd() {
        return dayEnd;
    }
    public String getReasonableSalary() {
        return reasonableSalary;
    }
    public String getProvision() {
        return provision;
    }
    public String getId() {
        return id;
    }
    public void setContractId(String contractId){
        this.contractId=contractId;
    }
    public void setContractType(String contractType){
        this.contractType=contractType;
    }
    public void setDaySign(String daySign) {
        this.daySign = daySign;
    }
    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }
    public void setReasonable_salary(String reasonableSalary) {
        this.reasonableSalary = reasonableSalary;
    }
    public void setProvision(String provision) {
        this.provision = provision;
    }
    public void setId(String id) {
        this.id = id;
    }
}
