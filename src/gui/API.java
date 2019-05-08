package gui;

import core.GradingSystem;
import core.Student;
import core.SubCategory;
import org.omg.CORBA.OBJ_ADAPTER;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class API {
    private MainFrame mainFrame;
    private GradingSystem gradingSystem;

    public API(MainFrame mainFrame, GradingSystem gradingSystem){
        this.mainFrame = mainFrame;
        this.gradingSystem = gradingSystem;
    }

    public boolean login(String username, String password){
        boolean flag = gradingSystem.login(username,password);
        if (flag == true){
            loginToClassPanel();
            mainFrame.classPanel.refreshPage(gradingSystem.currentCourse);
        }
        return flag;
    }

    public void loginToClassPanel(){
        mainFrame.loginPanel.hidePanel();
        mainFrame.classPanel.showPanel();
    }

    public void classToWeightPanel(){
        mainFrame.classPanel.hidePanel();
        mainFrame.weightPanel.showPanel();
        mainFrame.weightPanel.refresh_load(gradingSystem.currentCourse);
    }

    public Vector<Vector<Object>> getCourseNameList(){
        return gradingSystem.getCourseNameList();
    }

    public Vector<Object> getCourseNamelistHeader(){
        Vector<Object> header = new Vector<Object>();
        header.add("Courses");
        return header;
    }

    public boolean addCategory(String categoryName){
        if (gradingSystem.addCategory(categoryName)){
            mainFrame.weightPanel.refresh_load(gradingSystem.currentCourse);
            return true;
        }
        return false;
    }

    public boolean addSubCategory(String subCategoryName, String categoryName){
        if (gradingSystem.addSubCategory(subCategoryName,categoryName)){
            mainFrame.weightPanel.refresh_load(gradingSystem.currentCourse);
            return true;
        }
        return false;
    }

    public boolean deleteCategory(String categoryName){
        if (gradingSystem.deleteCategory(categoryName)){
            mainFrame.weightPanel.refresh_load(gradingSystem.currentCourse);
            return true;
        }
        return false;
    }

    public  boolean deleteSubCategory(String subCategoryName){
        if (gradingSystem.deleteSubCategory(subCategoryName)){
            mainFrame.weightPanel.refresh_load(gradingSystem.currentCourse);
            return true;
        }
        return false;
    }

    public void saveWeightChange(Vector<Vector<Object>> catWeights, Vector<Vector<Object>> subWeights){
        System.out.println("save weight change");
        gradingSystem.changeCategoryWeight(catWeights);
        gradingSystem.changeSubCategoryWeight(subWeights);
        gradingSystem.updateCourse();
        mainFrame.weightPanel.refresh_load(gradingSystem.currentCourse);
    }

    public void weightToClassPanel(){
        mainFrame.weightPanel.hidePanel();
        mainFrame.classPanel.showPanel();
        mainFrame.classPanel.refreshPage(gradingSystem.currentCourse);
    }

    public void gradeSelected(Vector<String> subNames){
        Vector<Vector<Object>> data = gradingSystem.getSeletedGrades(subNames);
        Vector<Object> header = new Vector<Object>();
        header.add("student");
        for (String item: subNames){
            header.add(item);
        }
        mainFrame.classPanel.hidePanel();
        mainFrame.gradingPanel.showPanel();
        gradingSystem.updateCourse();
        mainFrame.gradingPanel.refreshPage(data,header,gradingSystem.currentCourse.getStudents());
    }

    public Vector<Vector<Object>> getGradeData(Vector<String> subNames){
        Vector<Vector<Object>> data = gradingSystem.getSeletedGrades(subNames);
        return data;
    }

    public Vector<Double> getMaxGrades(Vector<String> subNames){
        Vector<Double> maxGrades = new Vector<Double>();
        for (int i=0; i<subNames.size(); i++){
            maxGrades.add(getSubCategoryMaxScore(subNames.get(i)));
        }
        return maxGrades;
    }

    public void gradingToClassPanel(){
        mainFrame.gradingPanel.hidePanel();
        mainFrame.classPanel.showPanel();
        gradingSystem.updateCourse();
        mainFrame.classPanel.refreshPage(gradingSystem.currentCourse);
    }

    public Double getSubCategoryMaxScore(String subName){
        SubCategory sub = SubCategory.getSubCategoryByName(subName, gradingSystem.currentCourse.getSubCategories());
        return sub.getMax();
    }

    public void saveGrading(Vector<Vector<Object>> data, Vector<Object> header){
        gradingSystem.updateGrading(data,header);
    }

    public void addComment(String sid, String comment){
        gradingSystem.addComment(sid, comment);
        gradingSystem.updateCourse();
        mainFrame.gradingPanel.students = gradingSystem.currentCourse.getStudents();
    }


}
