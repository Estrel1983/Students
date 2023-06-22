import java.sql.*;
import java.util.ArrayList;

public class wrkJDBC {
    static final String DATABASE_URL = "jdbc:postgresql://localhost/students";
    static final String DATABASE_NAME = "postgres";
    static final String DATABASE_PAS = "postgres";
    static Connection connection = null;
    static Statement statement;
    static {
        System.out.println("Registering JDBC driver...");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_NAME, DATABASE_PAS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static boolean createTable () throws SQLException {
        if (!statement.execute("CREATE TABLE students("+
                "id SERIAL NOT NULL, " +
                "name VARCHAR (50) NOT NULL," +
                "age INT NOT NULL," +
                "grade VARCHAR (10) NOT NULL," +
                "PRIMARY KEY (id)" +
                ");" )){
            System.out.println("table @students@ created successfully");
            return true;
        }
        return false;
    }
    public static boolean deleteTable () throws SQLException{
        if( !statement.execute("DROP TABLE students;")) {
            System.out.println("table @students@ is droped" );
            return true;
        }
        return false;
    }
    public static void stopAll () throws SQLException {
        statement.close();
        connection.close();
    }


    public static void  writeDb (Student st) throws SQLException {
        statement.executeUpdate("INSERT INTO students (name, age, grade)" +
                "VALUES ('" + st.getName() + "'," +
                st.getAge() + ",'" + st.getGrade()
                + "');");
        System.out.println("student - " + st.getName() + " inserted successfully");
    }
    public static Integer getId() throws SQLException {
        ResultSet sql = statement.executeQuery("SELECT MAX (id) FROM students");
        while (sql.next()){
            return sql.getInt(1) + 1;
        }
        return 1;
    }

    public static ArrayList<Student> searchAll() throws SQLException{
        ResultSet sql  = statement.executeQuery("SELECT * FROM students");
        ArrayList <Student> answer = new ArrayList<>();
        while (sql.next()){
            answer.add(new Student(sql.getInt("id"), sql.getString("name"), sql.getInt("age"),
                    sql.getString("grade")));
        }
        return answer;
    }
    public static ArrayList <Student> searchOne (Integer id) throws SQLException {
        ResultSet sql = statement.executeQuery("SELECT * FROM students WHERE id = " + id + ";");
        ArrayList <Student> answer = new ArrayList<>();
        while (sql.next()){
            Student st = new Student(sql.getInt("id"), sql.getString("name"), sql.getInt("age"),
                sql.getString("grade"));
            answer.add(st);
        }
        if (answer.size() > 0)
            return answer;
        return null;
    }
    public static ArrayList <Student> searchOne (String name) throws SQLException {
        ResultSet sql = statement.executeQuery("SELECT * FROM students WHERE name LIKE '" + name + "';");
        ArrayList <Student> answer = new ArrayList<>();
        while (sql.next()){
            Student st = new Student(sql.getInt("id"), sql.getString("name"), sql.getInt("age"),
                    sql.getString("grade"));
            answer.add(st);
            }
        if (answer.size() > 0)
            return answer;
        return null;
    }
    public static ArrayList <Student> searchOne (String grade, String type) throws SQLException {
        ResultSet sql = statement.executeQuery("SELECT * FROM students WHERE grade LIKE '" + grade + "';");
        ArrayList <Student> answer = new ArrayList<>();
        while (sql.next()){
            Student st = new Student(sql.getInt("id"), sql.getString("name"), sql.getInt("age"),
                    sql.getString("grade"));
            answer.add(st);
        }
        if (answer.size() > 0)
            return answer;
        return null;
    }
    public static void deleteList (ArrayList<Student> studentList) throws SQLException {
        for (Student s: studentList){
            statement.executeUpdate("DELETE FROM students WHERE id = " + s.getStudentId() + ";");
        }
    }
}
