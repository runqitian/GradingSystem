package data;

import core.Student;
import core.User;

import java.sql.*;
import java.util.Vector;

public class Database {

    static final String url = "jdbc:mysql://localhost:3306/GradingSystem?user=root&password=trq371402&serverTimezone=GMT";
    static Connection conn;
    static {

        // used to connect to mysql

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String username, String password){
        String query = "select username, password from User where username='%s'";
        query = String.format(query, username);
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                String name = rs.getString(1);
                String pwd = rs.getString(2);
                if (pwd.equals(password)){
                    return new User(name,pwd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Vector<String> getCourseNameList(User user){
        String query = "select name from Course where belong='%s'";
        query = String.format(query, user.getUsername());
        Vector<String> namelist = new Vector<String>();
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                String name = rs.getString(1);
                namelist.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return namelist;
    }

//    public static Student getStudent(){
//        String query = "select * from Student";
//        try {
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while (rs.next()){
//                String id = rs.getString(0);
//                String name = rs.getString(1);
//                String email = rs.getString(2);
//                Student student = new Student(id,name,email);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}
