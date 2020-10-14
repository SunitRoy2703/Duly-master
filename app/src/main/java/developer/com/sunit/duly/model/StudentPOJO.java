package developer.com.sunit.duly.model;

public class StudentPOJO {
    private String name,roll;
    public StudentPOJO(){}
    public StudentPOJO(String name,String roll)
    {
        this.name=name;
        this.roll=roll;
    }


    public void setRoll(String roll) {
        this.roll = roll;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }
}
