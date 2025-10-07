import java.util.Scanner;

public class Project {
    private final Scanner sc = new Scanner(System.in);
    private float managerallowancerate;
    private float mainengineerallowancerate;
    private float participantallowancerate;
    private String projectid;
    private String projectname;
    private String startday;
    private String accomplishday;

    public Project() {}

    public Project(Project a) {
        managerallowancerate = a.managerallowancerate;
        mainengineerallowancerate = a.mainengineerallowancerate;
        participantallowancerate = a.participantallowancerate;
        projectid = a.projectid;
        projectname = a.projectname;
        startday = a.startday;
        accomplishday = a.accomplishday;
    }

    public Project(float managerallowancerate, float mainengineerallowancerate, float participantallowancerate, String projectid, String projectname, String startday, String accomplishday) {
        this.managerallowancerate = managerallowancerate;
        this.mainengineerallowancerate = mainengineerallowancerate;
        this.participantallowancerate = participantallowancerate;
        this.projectid = projectid;
        this.projectname = projectname;
        this.startday = startday;
        this.accomplishday = accomplishday;
    }



    public float getManagerAllowanceRate() {
        return managerallowancerate;
    }

    public float getMainEngineerAllowanceRate() {
        return mainengineerallowancerate;
    }

    public float getParticipantAllowanceRate() {
        return participantallowancerate;
    }

    public String getProjectId() {
        return projectid;
    }

    public String getProjectName() {
        return projectname;
    }

    public String getStartDay() {
        return startday;
    }

    public String getAccomplishDay() {
        return accomplishday;
    }

    public void setManagerAllowanceRate(float managerallowancerate) {
        this.managerallowancerate = managerallowancerate;
    }

    public void setMainEngineerAllowanceRate(float mainengineerallowancerate) {
        this.mainengineerallowancerate = mainengineerallowancerate;
    }

    public void setParticipantAllowanceRate(float participantAllowanceRate) {
        this.participantallowancerate = participantAllowanceRate;
    }

    public void setProjectId(String projectid) {
        this.projectid = projectid;
    }

    public void setProjectName(String projectname) {
        this.projectname = projectname;
    }

    public void setStartDay(String startday){
        this.startday = startday;
    }

    public void setAccomplishDay(String accomplishday) {
        this.accomplishday = accomplishday;
    }
}
