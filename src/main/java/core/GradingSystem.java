package core;

import gui.MyTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GradingSystem {

    User loginUser;

    Course currentCourse;

    List<String> courseNameList = new ArrayList<String>();

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

    public void addCourse(){
    //todo
    }

    public void deleteCourse(){
    //todo
    }

    public void modifyCourse(){
    //todo
    }

    public MyTableModel showClassInfo(){

//        List<Category> cates = currentCourse.getCategoryList();
        List<SubCategory> subcates = currentCourse.getSubCategoryList();

        Vector<Object> header = new Vector<Object>();
        Vector<Integer> notE = new Vector<Integer>();
        notE.add(0);
        notE.add(1);
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (SubCategory sub:
             subcates) {
            Vector<Object> row = new Vector<>();
            row.add(sub.getCategory().getName());
            row.add(sub.getName());
            data.add(row);
        }

        MyTableModel table = new MyTableModel(data, header, notE);
        return table;
    }

    public MyTableModel showCategory(){
        List<Category> cates = currentCourse.getCategoryList();
        Vector<Object> header = new Vector<Object>();
        header.add("Name");
        header.add("Weight");
        Vector<Integer> notE = new Vector<Integer>();
        notE.add(0);
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (Category cate:
                cates) {
            Vector<Object> row = new Vector<>();
            row.add(cate.getName());
            row.add(cate.getWeight());
            data.add(row);
        }

        MyTableModel table = new MyTableModel(data, header, notE);
        return table;
    }

    public MyTableModel showSubCategories(){
        List<SubCategory> subcates = currentCourse.getSubCategoryList();
        Vector<Object> header = new Vector<Object>();
        header.add("Name");
        header.add("Weight");
        header.add("MaxPossible");
        Vector<Integer> notE = new Vector<Integer>();
        notE.add(0);
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (SubCategory subcate:
                subcates) {
            Vector<Object> row = new Vector<>();
            row.add(subcate.getName());
            row.add(subcate.getWeight());
            row.add(subcate.getMaxGrade());
            data.add(row);
        }

        MyTableModel table = new MyTableModel(data, header, notE);
        return table;
    }

    public MyTableModel showGradingInfo(List<String> Names){
        List<SubCategory> subcates = currentCourse.getSubCategoryList();
        Vector<SubCategory> subs = new Vector<SubCategory>();
        for (String name:
             Names) {
            for (SubCategory sub:
             subcates) {
                if(sub.getName().equals(name)){
                    subs.add(sub);
                }
            }
        }
        if(Names.size()!=subs.size()){
            System.out.println("wrong title in show list");
            return new MyTableModel();
        }
        Vector<Object> header = new Vector<Object>();
        header.add("Student");
        for (SubCategory sub:
             subs) {
            header.add(sub.getName());
        }
        Vector<Integer> notE = new Vector<Integer>();
        notE.add(0);
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (Student student:
             currentCourse.getStudents()) {
            Vector<Object> row = new Vector<>();
            row.add(student.getName());
            for (SubCategory subcate:
                 subs) {
                row.add(subcate.getStudentGrade(student));
            }
        }
        MyTableModel table = new MyTableModel(data, header, notE);
        return table;
    }

    public boolean importStudentList(String filename){
        //todo
        return false;
    }

}
