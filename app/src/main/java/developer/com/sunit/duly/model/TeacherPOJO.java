package developer.com.sunit.duly.model;

public class TeacherPOJO {
    public String userName;
    public String mobNum;
    //private long time;



    public TeacherPOJO(String userName,String mobNum)
    {
        this.userName=userName;
        this.mobNum=mobNum;


    }
    public TeacherPOJO()
    {}
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName=userName;
    }

    public String getMobNum() {return mobNum;}

    public void setMobNum(String mobNum) {this.mobNum=mobNum;}
}
