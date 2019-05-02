package core;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;

    private List<String> courseNameList = new ArrayList<String>();


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean checkExist(){
        boolean success = DatabaseStorage.logIn(this.username,this.password);
        return success;
    }

    public List<String> loadUserCourseName(){
        courseNameList.addAll(DatabaseStorage.loadCourseNameList(username));
        return courseNameList;
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

    public List<String> getCourseNameList() {
        return courseNameList;
    }

    public void setCourseNameList(List<String> courseNameList) {
        this.courseNameList = courseNameList;
    }
}
