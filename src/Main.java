import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static StudentManagementSystem sms = new StudentManagementSystem();
    private static boolean exit = true;
    public static void main(String[] args) throws Exception {

        while (exit) {
            showMessage();
            readCh();
        }
        pExit();
    }


    private static void clearMessage(){
        System.out.println("Choose your destiny:");
        System.out.println("1 - Create new table (demo)");
        System.out.println("2 - Add new Student");
        System.out.println("3 - Search Student by Id");
        System.out.println("4 - Search Student by Name");
        System.out.println("5 - Search Student by Grade");
        System.out.println("6 - Show all Students");
        System.out.println("7 - Delete Student");
        System.out.println("8 - Delete table (demo)");
        System.out.println("0 - Exit Program");
    }
    private static void noClearMessage (){
        System.out.println("You have " + sms.getListSize() + " Students in Search List");
        System.out.println("What do you want to do with them?");
        System.out.println("1 - Print");
        System.out.println("2 - Delete");
        System.out.println("3 - Clear the List");
    }
    private static void showMessage (){
        if (sms.getListSize() > 0)
            noClearMessage();
        else clearMessage();
    }

    private static void readCh(){
        Scanner sc = new Scanner(System.in);
        int i;
        do {
            i = Integer.parseInt(sc.nextLine());
        } while (!((sms.getListSize() > 0 && i > 0 && i < 4) || (sms.getListSize() == 0 && i >=0 && i < 9)));
        if (sms.getListSize() > 0) {
            smallOpt(i);
        }
        else bigOpt(i);
    }
    public static void smallOpt(int i){
        switch (i){
            case 1:
                sms.printList();
                break;
            case 2:
                try {
                    sms.deleteStudents();
                } catch (SQLException e) {
                    System.out.println("Something wrong " + e.getSQLState());
                }
                break;
            case 3:
                sms.clearList();
        }
    }

    public static void bigOpt (int i){
        switch (i){
            case 1:
                System.out.println("Creating new table");
                try {
                    wrkJDBC.createTable();
                } catch (SQLException e) {
                    System.out.println("something wrong " + e.getSQLState());
                }
                break;
            case 2:
                try {
                    sms.addStudent();
                } catch (StudentException e) {
                    System.out.println(e.getDescr());
                } catch (SQLException e) {
                    System.out.println(e.getErrorCode());
                }
                break;
            case 3:
                try {
                    sms.searchStByID();
                } catch (SQLException e){
                    System.out.println(e.getErrorCode());
                }
                break;
            case 4:
                try {
                    sms.searchByName();
                } catch (SQLException e){
                    System.out.println(e.getSQLState());
                } catch (StudentException e){
                    System.out.println(e.getDescr());
                }
                break;
            case 5:
                try {
                    sms.searchByGrade();
                } catch (SQLException e){
                    System.out.println(e.getErrorCode());
                } catch (StudentException e){
                    System.out.println(e.getDescr());
                }
                break;
            case 6:
                try {
                    sms.displayAll();
                } catch (SQLException e) {
                    System.out.println(e.getErrorCode());
                }
                break;
            case 7:
                System.out.println("No student to delete. First perform the search");
                break;
            case 8:
                System.out.println("Deleting Students table");
                try {
                    wrkJDBC.deleteTable();
                } catch (SQLException e){
                    System.out.println(e.getSQLState());
                }
                break;
            case 0:
                exit = false;
        }
    }

    private static void pExit() throws SQLException {
        wrkJDBC.stopAll();
    }
}