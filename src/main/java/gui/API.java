package gui;

import core.GradingSystem;
import org.omg.CORBA.OBJ_ADAPTER;
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

    public void saveWeightChange(Vector<Vector<Object>> catWeights, Vector<Vector<Object>> subWeights){
        gradingSystem.modifyWeights(catWeights);
        gradingSystem.modifyWeights(subWeights);
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

        System.out.println(objs[0]);
        Vector<Vector<Object>> data = (Vector<Vector<Object>>)objs[0];
        Vector<Object> header = (Vector<Object>)objs[1];
        System.out.println(data);
        System.out.println(header);

        mainFrame.classPanel.hidePanel();
        mainFrame.gradingPanel.showPanel(data,header);
    }


    public void classToWeightPanel(){
        Object[] catObjs = gradingSystem.showCategory();
        Object[] subObjs = gradingSystem.showSubCategories();
        Vector<Vector<Object>> catData = (Vector<Vector<Object>>)catObjs[0];
        Vector<Object> catHeader = (Vector<Object>)catObjs[1];
        Vector<Vector<Object>> subData = (Vector<Vector<Object>>)subObjs[0];
        Vector<Object> subHeader = (Vector<Object>)subObjs[1];
        System.out.println(catData);
        System.out.println(subData);
        mainFrame.classPanel.hidePanel();
        mainFrame.weightPanel.showPanel(catData,catHeader,subData,subHeader);
    }



    public void weightToClassPanel(){
        this.mainFrame.weightPanel.hidePanel();
        this.mainFrame.classPanel.showPanel();
    }
    


}
