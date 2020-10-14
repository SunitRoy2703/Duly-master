package developer.com.sunit.duly.model;



public class AnnouncementPOJO {

    private String message,teacherName, time , tag;

    public AnnouncementPOJO(String message,String teacherName,String time, String tag){
        this.message=message;
        this.teacherName=teacherName;
        this.time=time;
        this.tag=tag;
        }

    public String getMessage() {
        return message;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTime() {
        return time;
    }

    public String getTag() {
        return tag;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
