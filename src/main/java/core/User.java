package core;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;

    private List<Course> courseList = new ArrayList<Course>();


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean checkExist(){
        boolean success = DatabaseStorage.logIn(this.username,this.password);
        return success;
    }

    public List<Course> loadUserConfig(){
        courseList.addAll(DatabaseStorage.loadCourseList(username));
        return courseList;
    }
}
