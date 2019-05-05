package core;

import data.Database;

import java.util.Vector;

public class GradingSystem {

    public User currentUser;
    public Course currentCourse;

    public boolean login(String username, String password){

        User user;
        if ((user = Database.getUser(username,password)) != null){
            currentUser = user;
            return true;
        }
        return false;
    }

    public Vector<String> getCourseNameList(){
        Vector<String> namelist = Database.getCourseNameList(this.currentUser);
        return namelist;
    }

}
