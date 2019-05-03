package gui;

import core.GradingSystem;
import tools.Tools;

import javax.swing.*;
import java.util.Vector;

public class API {
    private MainFrame mainFrame;
    private GradingSystem gradingSystem;

    public API(MainFrame mainFrame, GradingSystem gradingSystem){
        this.mainFrame = mainFrame;
        this.gradingSystem = gradingSystem;
    }

    public boolean login(String username, String password){
        boolean success = gradingSystem.login(username,password);
        if (success){
            // load and initialize
            mainFrame.loginPanel.hidePanel();
            mainFrame.classPanel.showPanel();
        }
        return success;
    }

    public void saveWeightChange(){

    }

    public Object[] loadCourseSummary(){
        gradingSystem.showClassInfo();

        return null;
    }

    public Object[] loadCourseList(){
        //todo
        return null;
    }

    public void deleteCourse(String courseName){

    }

    public void createCourse(String courseName){

    }

    public void importStudent(String courseName){

    }



    


}
