package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class User {

    private String username;
    private String password;
    private Vector<String> courseNameList = new Vector<String>();


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean checkExist(){
        boolean success = DatabaseStorage.logIn(this.username,this.password);
        return true;
//        return success;
    }

    public void loadUserCourseName(){
        courseNameList.addAll(DatabaseStorage.loadCourseNameList(username));
//        this.courseNameList.add("Python");
//        this.courseNameList.add("Java");
//        this.courseNameList.add("C++");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Vector<String> getCourseNameList() {
        return courseNameList;
    }

    public void setCourseNameList(Vector<String> courseNameList) {
        this.courseNameList = courseNameList;
    }
}
