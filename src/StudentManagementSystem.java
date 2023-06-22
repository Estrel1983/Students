import java.math.BigInteger;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentManagementSystem {
    private ArrayList <Student> studentList;

    public void addStudent () throws IncorrectNameException, IncorrectGradeException, SQLException {
        Scanner sc1 = new Scanner(System.in);
        System.out.print("Enter Student's name: ");
        String name = checkName(sc1.nextLine());
        System.out.print("Enter Student's age: ");
        Integer age = Integer.parseInt(sc1.nextLine());
        System.out.print("Enter Student's grade (Failed, Passed, Exit): ");
        String grade = checkGrade(sc1.nextLine());
        Student student = new Student(wrkJDBC.getId(), name, age, grade);
        System.out.println(student);
        wrkJDBC.writeDb(student);

    }
    public void displayAll () throws SQLException{
        studentList = wrkJDBC.searchAll();
        for (Student s: studentList){
            System.out.println(s);
        }
        studentList.removeAll(studentList);
    }
    public void searchStByID () throws SQLException{
        studentList = null;
        System.out.println("Enter ID for searching");
        Scanner sc = new Scanner(System.in);
        studentList = wrkJDBC.searchOne (sc.nextInt());
        if (studentList != null){
            for (Student s : studentList)
                System.out.println(s);
        }
        else System.out.println("Student not found");
    }

    public void searchByName () throws IncorrectNameException, SQLException {
        studentList = null;
        System.out.println("Enter student Name for searching");
        Scanner sc = new Scanner(System.in);
        studentList = wrkJDBC.searchOne (checkName(sc.nextLine()));
        if (studentList != null){
            for (Student s : studentList)
                System.out.println(s);
        }
        else System.out.println("Student not found");
    }
    public void searchByGrade () throws SQLException, IncorrectGradeException {
        studentList = null;
        System.out.println("Enter student Grade for searching");
        Scanner sc = new Scanner(System.in);
        studentList = wrkJDBC.searchOne (checkGrade(sc.nextLine()), "grade");
        if (studentList != null){
            for (Student s : studentList)
                System.out.println(s);
        }
        else System.out.println("Student not found");
    }
    public void deleteStudents () throws SQLException {
        wrkJDBC.deleteList(studentList);
        for (Student s : studentList) {
            System.out.println("Student with " + s.shortStr() + " deleted");
        }
        studentList = null;
    }
    public int getListSize(){
        if (studentList != null){
            return studentList.size();
        }
        return 0;
    }
    public void printList (){
        for (Student st : studentList)
            System.out.println(st);
    }
    public void clearList(){
        studentList = null;
    }



    private static String checkName(String s) throws IncorrectNameException{
        Pattern pt = Pattern.compile("[^a-zA-Z\\s]");
        Matcher m = pt.matcher(s);
        while (m.find()){
            System.out.println(m.start() + " " + m.end());
            throw new IncorrectNameException(s);
        }
        return s;
    }
    private static String checkGrade (String s) throws IncorrectGradeException{
        switch (s){
            case "Failed":
            case "Passed":
            case "Exit":
                return s;
            default:
                throw new IncorrectGradeException(s);
        }
    }
}

abstract class StudentException extends Exception{
    public abstract String getDescr();
}

class IncorrectNameException extends StudentException{
    static String descr= "Incorrect name - ";
    private String wrongName;

    IncorrectNameException (String s){
        wrongName = s;
    }
    public String getDescr () {
        return descr + wrongName;
    }
}

class IncorrectGradeException extends StudentException{
    static String descr= "Incorrect grade - ";
    private String wrongGrade;

    IncorrectGradeException (String s){
        wrongGrade = s;
    }
    public String getDescr () {
        return descr + wrongGrade;
    }
}
