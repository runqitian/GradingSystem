package data;

import core.*;

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

    public static Vector<Vector<Object>> getCourseNameList(User user){
        String query = "select name from Course where belong='%s'";
        query = String.format(query, user.getUsername());
        Vector<Vector<Object>> namelist = new Vector<Vector<Object>>();
        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                String name = rs.getString(1);
                Vector<Object> row = new Vector<Object>();
                row.add(name);
                namelist.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return namelist;
    }

    public static Course getSpecificCourse(String username, String coursename){
        int courseID = 0;
        String query = "select id from Course where belong='%s' and name='%s'";
        query = String.format(query, username, coursename);
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()){
                courseID = rs.getInt(1);
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Vector<Category> categories = new Vector<Category>();
        query = "select id,name,weight from Category where belong=%s";
        query = String.format(query, new Integer(courseID).toString());
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                categories.add(new Category(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Vector<SubCategory> subCategories = new Vector<SubCategory>();
        for (Category category: categories){
            query = "select id, name, weight, max from SubCategory where belong=%s";
            query = String.format(query, new Integer(category.getId()).toString());
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    SubCategory sub = new SubCategory(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),category);
                    subCategories.add(sub);
                    category.getSubCategories().add(sub);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        query = "select id,name,email,comments from (student INNER JOIN (select * from student_course where cid=%s) as tab2 ON student.id=tab2.sid);";
        query = String.format(query, new Integer(courseID).toString());
        Vector<Student> students = new Vector<Student>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Student student = new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        query = "select tab3.name, tab4.name, tab3.grading from (select tab2.name as name,tab1.subid as subid,tab1.grading as grading from " +
//                "(select student, subid, grade from grading where courseid=%s) as tab1 INNER JOIN (select id, name from student) as tab2 on tab1.student=tab2.id) as tab3  " +
//                "INNER JOIN (select id,name from subcategory) as tab4 on tab3.subid = tab4.id";
//        query = String.format(query,new Integer(courseID).toString());
        Object[][] gradingTable = new Object[students.size()][subCategories.size()+1];
        for (int i=0; i<students.size(); i++){
            Student student = students.get(i);
            gradingTable[i][0] = student.getName();
            query = "select subid,grade from grading where courseid=%s and student='%s'";
            query = String.format(query, new Integer(courseID).toString(),student.getId());
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()){
                    for (int j=0; j<subCategories.size(); j++){
                        int subid = rs.getInt(1);
                        if (subCategories.get(j).sameID(subid)){
                            gradingTable[i][j+1] = rs.getDouble(2);
                            break;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Object[] header = new Object[subCategories.size()+1];
        header[0] = "name";
        for (int i=0; i<subCategories.size(); i++){
            header[i+1] = subCategories.get(i).getName();
        }
        Course course = new Course(courseID, coursename, categories, subCategories, students, gradingTable, header);
        return course;
    }

    public static boolean insertCategory(int courseID, String name,double weight){
        String query = "insert into Category(name,weight,belong) values(?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,name);
            pst.setDouble(2,weight);
            pst.setInt(3,courseID);
            return pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertSubCategory(int categoryID, String name,double weight, double max, int courseID, Vector<Student> students){
        String query = "insert into SubCategory(name,weight,max,belong) values(?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,name);
            pst.setDouble(2,weight);
            pst.setDouble(3,max);
            pst.setInt(4,categoryID);
            pst.execute();
            query = "select id from subcategory where name=? and belong=?";
            pst = conn.prepareStatement(query);
            pst.setString(1,name);
            pst.setInt(2, categoryID);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int subid = rs.getInt(1);
            System.out.println(students);
            for (Student stu: students){
                query = "insert into Grading(student,courseid,subid, grade) values(?,?,?,0)";
                pst = conn.prepareStatement(query);
                pst.setString(1,stu.getId());
                pst.setInt(2,courseID);
                pst.setInt(3,subid);
                pst.execute();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertCourse(String username, String coursename){
        String query = "insert into Course(name,belong) values(?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, coursename);
            pst.setString(2, username);
            return pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteCategory(int id, Vector<SubCategory> subCategories){
        String query = "delete from Category where id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            query = "delete from SubCategory where belong=?";
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            for (SubCategory sub: subCategories){
                if (sub.getBelong().getId() == id){
                    query = "delete from Grading where subid=?";
                    pst = conn.prepareStatement(query);
                    pst.setInt(1, sub.getId());
                    pst.execute();
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteSubCategory(int id){
        String query = "delete from SubCategory where id=?;";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            query = "delete from Grading where subid=?";
            pst = conn.prepareStatement(query);
            pst.setInt(1,id);
            pst.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void updateCategory(int id, double weight){
        String query = "update Category set weight=? where id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDouble(1, weight);
            pst.setInt(2, id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateSubCategory(int id, double weight, double max){
        String query = "update SubCategory set weight=?, max=? where id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDouble(1, weight);
            pst.setDouble(2,max);
            pst.setInt(3, id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateSingleGrading(String sid, int subid, Double grade){
        String query = "update Grading set grade=? where student=?  and subid=?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDouble(1, grade);
            pst.setString(2,sid);
            pst.setInt(3, subid);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addComment(String sid, int courseid, String comment){
        String input = comment + "\n";
        String query = "select comments from student_course where sid=? and cid=?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,sid);
            pst.setInt(2, courseid);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                input = input.concat(rs.getString(1));
            }
            query = "update student_course set comments=? where sid=? and cid=?";
            pst = conn.prepareStatement(query);
            pst.setString(1, input);
            pst.setString(2,sid);
            pst.setInt(3, courseid);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(int courseID, Student student, Vector<Integer> subids){
        String query = "insert into student(id,name, email) values(?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,student.getId());
            pst.setString(2,student.getName());
            pst.setString(3, student.getEmail());
            pst.execute();
            query = "insert into student_course values(?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setInt(1,courseID);
            pst.setString(2,student.getId());
            pst.setString(3,student.getComment());
            pst.execute();
            for (Integer item: subids){
                query = "insert into grading values(?,?,?,?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, student.getId());
                pst.setInt(2,courseID);
                pst.setInt(3,item);
                pst.setDouble(4,0);
                pst.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
