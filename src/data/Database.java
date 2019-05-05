package data;

import java.sql.*;

public class Database {

    static final String url = "jdbc:mysql://localhost:3306/GradingSystem?user=root&password=trq371402&serverTimezone=GMT";
    static final String user = "root";
    static final String pwd = "trq371402";
    static Connection conn;
    static {

        // used to connect to mysql

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Student getStudent(){

    }

}
