package developer.com.sunit.duly.model;

public class NoticePOJO {
    private String dept,title,uploadedBy,body,timeStamp;
    public NoticePOJO(){}
    public NoticePOJO(String dept,String title,String uploadedBy,String body,String timeStamp)
    {
        this.dept=dept;
        this.title=title;
        this.uploadedBy=uploadedBy;
        this.body=body;
        this.timeStamp=timeStamp;
    }

    public String getBody() {
        return body;
    }

    public String getDept() {
        return dept;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return timeStamp;
    }

    public String getTeacher() {
        return uploadedBy;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTime(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTeacher(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

}
