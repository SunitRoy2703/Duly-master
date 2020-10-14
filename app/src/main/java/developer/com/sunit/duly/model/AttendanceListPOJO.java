package developer.com.sunit.duly.model;

import java.util.HashMap;

public class AttendanceListPOJO {
   private String timeStamp;
   private HashMap <String , Boolean> list;
   public AttendanceListPOJO(){}
    public AttendanceListPOJO(HashMap <String , Boolean> list,String timeStamp)
    {
        this.list=list;
        this.timeStamp=timeStamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setList(HashMap<String, Boolean> list) {
        this.list = list;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public HashMap<String, Boolean> getList() {
        return list;
    }
}
