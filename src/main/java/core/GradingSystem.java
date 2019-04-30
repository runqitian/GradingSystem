package core;

import java.util.List;
import java.util.Vector;

public class GradingSystem {

    User loginUser;

    Course currentCourse;

    List<Course> courseList;




    public boolean login(String username, String password){
        User user = new User(username,password);
        boolean success = user.checkExist();
        if (success){
            this.loginUser = user;
            this.courseList = user.loadUserConfig();
        }else{
            this.loginUser = null;
            this.courseList = null;
        }
        return success;
    }

    public Vector<Vector<Object>> getGradingSummary(){

        Vector<Vector<Object>> result = new Vector<Vector<Object>>();
        return result;
    }

}
