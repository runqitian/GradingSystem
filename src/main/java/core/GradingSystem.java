package core;

import gui.MyTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public boolean addCourse(String className){
        if(courseNameList.contains(className)){
            System.out.println("already have class of this name!");
            return false;
        }
        Course newclass = new Course(className);
        this.currentCourse = newclass;
        return true;
    }

    public void deleteCourse(String name){
        if(courseNameList.remove(name)){
            //todo Database things
        }
    }

    public boolean addCategory(String Name){
        return currentCourse.addCategory(Name);
    }

    public boolean addSubCategory(String cateName, String subName){
        return currentCourse.addSubCategory(cateName, subName);
    }


    public boolean modifyWeights(Vector<Vector<Object>> weights){
        for (Vector<Object> weight:
             weights) {
            if(weight.size() == 2){
                //cate
                try {
                    currentCourse.modifyCategory((String) weight.get(0), (Double) weight.get(1));
                }catch (Exception e){
                    System.out.println(e);
                }

            }else if (weight.size() ==3){
                //subcate
                try {
                    currentCourse.modifySubCategory((String) weight.get(0), (Double) weight.get(1), (Integer) weight.get(2));
                }catch (Exception e){
                    System.out.println(e);
                }
            }
            System.out.println("Wrong table of weights");
            return false;
        }
        return true;
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

    public Object[] showCategory(){
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
        Object[] results = {data,header,notE};
//        MyTableModel table = new MyTableModel(data, header, notE);
        return results;
    }

    public Object[] showSubCategories(){
        List<SubCategory> subcates = currentCourse.getSubCategoryList();
        Vector<Object> header = new Vector<Object>();
        header.add("CateName");
        header.add("SubName");
        header.add("Weight");
        header.add("MaxPossible");
        Vector<Integer> notE = new Vector<Integer>();
        notE.add(0);
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (SubCategory subcate:
                subcates) {
            Vector<Object> row = new Vector<>();
            row.add(subcate.getCategory().getName());
            row.add(subcate.getName());
            row.add(subcate.getWeight());
            row.add(subcate.getMaxGrade());
            data.add(row);
        }
        Object[] results = {data,header,notE};
//        MyTableModel table = new MyTableModel(data, header, notE);
        return results;
    }


    public Object[] showGradingInfo(List<String> Names){
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
            return new Object[0];
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
        Object[] results = {data,header,notE};
//        MyTableModel table = new MyTableModel(data, header, notE);
        return results;
    }

    public boolean importStudentList(String filename){
        //todo
        return false;
    }

}
