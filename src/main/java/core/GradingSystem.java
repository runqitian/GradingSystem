package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GradingSystem {

    User loginUser;

    Course currentCourse;

    List<String> courseNameList = new ArrayList<String>();


    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    public List<String> getCourseNameList() {
        return courseNameList;
    }

    public void setCourseNameList(List<String> courseNameList) {
        this.courseNameList = courseNameList;
    }

    public boolean login(String username, String password){
        User user = new User(username,password);
        boolean success = user.checkExist();
        if (success){
            this.loginUser = user;
            this.courseNameList = user.loadUserCourseName();
        }else{
            this.loginUser = null;
            this.courseNameList = null;
        }
        return success;
    }

    public Vector<Vector<Object>> getGradingSummary(){

        Vector<Vector<Object>> result = new Vector<Vector<Object>>();
        return result;
    }

}
