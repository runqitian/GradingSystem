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
            Vector<String> courseList = gradingSystem.getUserCourseList();
            mainFrame.loginPanel.hidePanel();
            mainFrame.classPanel.showPanel();
        }
        return success;
    }

    public void saveWeightChange(){

    }

    public Object[] loadCourseSummary(String courseName){
        //todo
        gradingSystem.buildCurrentCourse(courseName);
        return gradingSystem.showClassInfo();
    }

    public Object[] loadCourseList(){
        //todo
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (Object obj: gradingSystem.getUserCourseList()){
            Vector<Object> row = new Vector<Object>();
            row.add(obj);
            data.add(row);
        }
        Vector<Object> header = new Vector<Object>();
        header.add("");
        Object[] objs  = {data,header};
        return objs;
    }

    public void deleteCourse(String courseName){

    }

    public void createCourse(String courseName){

    }

    public void importStudent(String courseName){

    }

    public void uploadGradingSelected(Vector<String> Names){
        Object[] objs = gradingSystem.showGradingInfo(Names);
        System.out.println(objs);
    }



    


}
