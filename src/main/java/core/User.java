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
}
