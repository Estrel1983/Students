import java.math.BigInteger;

public class Student {


    private Integer studentId;
    private String name;
    private Integer age;
    private String grade;

    public Student(Integer studentId, String name, Integer age, String grade) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString(){
        return "Id - " + this.studentId + ", name - " + this.name + ", age - " + this.age + ", grade - \"" +
                this.grade + "\"";
    }
    public String shortStr(){
        return "Name - " + this.name + "\"";
    }
}
