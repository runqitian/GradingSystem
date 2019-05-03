package core;

import gui.MyTableModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class GradingSystem {

    public User loginUser;

    public Course currentCourse;

//    Vector<Course> allcourse = new Vector<>();

    public boolean login(String username, String password){
        User user = new User(username,password);
        boolean success = user.checkExist();
        if (success){
            user.loadUserCourseName();
            this.loginUser = user;
        }else{
            this.loginUser = null;
        }
        return success;
    }

    public boolean addCourse(String className){
        if(loginUser.getCourseNameList().contains(className)){
            System.out.println("already have class of this name!");
            return false;
        }
        Course newclass = new Course(className);
        loginUser.getCourseNameList().add(className);
        this.currentCourse = newclass;
//        return allcourse.add(newclass);
        return true;
    }



    public boolean addCategory(String Name){
        return currentCourse.addCategory(Name);
    }

    public boolean addSubCategory(String cateName, String subName){
        return currentCourse.addSubCategory(cateName, subName);
    }


    public boolean modifyWeights(Vector<Vector<Object>> weights){
        for (Vector<Object> weight: weights) {
            System.out.println(weight);
            if(weight.size() == 2){
                //cate
                currentCourse.modifyCategory(weight.get(0).toString(), new Double(weight.get(1).toString()));

            }else if (weight.size() == 4){
                //subcate
                currentCourse.modifySubCategory(weight.get(1).toString(), new Double(weight.get(2).toString()), new Integer(weight.get(3).toString()));
            }
        }
        DatabaseStorage.writeWeightsChange(currentCourse.getCourseName(), currentCourse.getCategoryList(), currentCourse.getSubCategoryList());
        return true;
    }

    public boolean loadScores(String subName){
        String cate = "";
        for (SubCategory sub:
             currentCourse.getSubCategoryList()) {
            if(sub.getName().equals(subName)){
                cate = sub.getCategory().getName();
                sub.setStuScore(DatabaseStorage.getGradesFor(this.currentCourse.getCourseName(), cate, subName));
                return true;
            }
        }
        System.out.println("no such subCate");
        return false;
    }

    public void buildCurrentCourse(String className){
        Course selectedCourse = new Course(className);
        System.out.println("crrCours" + selectedCourse.getCourseName());
        selectedCourse.setCategoryList(DatabaseStorage.getCategories(className));
        for (Category cate: selectedCourse.getCategoryList()) {
            selectedCourse.subCategoryList.addAll(DatabaseStorage.getSubCategories(className, cate.getName()));
        }

        selectedCourse.setStudents(DatabaseStorage.getAllStudents());
//        System.out.println("");
        this.currentCourse = selectedCourse;
    }

    public Object[] showClassInfo(){

        List<SubCategory> subcates = currentCourse.getSubCategoryList();

        Vector<Object> header = new Vector<Object>();
        header.add("Category");
        header.add("SubCategory");
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (SubCategory sub:
             subcates) {
            Vector<Object> row = new Vector<Object>();
            row.add(sub.getCategory().getName());
            row.add(sub.getName());
            data.add(row);
        }

        Object[] results = {data,header};
        return results;
    }

    public Object[] showCategory(){
        List<Category> cates = currentCourse.getCategoryList();
        System.out.println("cates" + cates);
        Vector<Object> header = new Vector<Object>();
        header.add("Name");
        header.add("Weight");
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (Category cate:
                cates) {
            Vector<Object> row = new Vector<Object>();
            row.add(cate.getName());
            row.add(cate.getWeight());
            data.add(row);
        }
        Object[] results = {data,header};

        return results;
    }

    public Object[] showSubCategories(){
        List<SubCategory> subcates = currentCourse.getSubCategoryList();
        Vector<Object> header = new Vector<Object>();
        header.add("CateName");
        header.add("SubName");
        header.add("Weight");
        header.add("MaxPossible");
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
        Object[] results = {data,header};

        return results;
    }

    public Vector<String> getUserCourseList(){
        return loginUser.getCourseNameList();
    }


    public Object[] showGradingInfo(Vector<String> Names){
        List<SubCategory> subcates = currentCourse.getSubCategoryList();
        Vector<SubCategory> subs = new Vector<SubCategory>();
        for (String name: Names) {
            for (SubCategory sub: subcates) {
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
            if(!loadScores(sub.getName())){
                System.out.println("wrongload");
            }
            header.add(sub.getName());
        }
        System.out.println("demmoooo" + currentCourse.getSubCategoryByName("Exam 1").getAllGrades());
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (Student student: currentCourse.getStudents()) {
            Vector<Object> row = new Vector<Object>();
            row.add(student.getName());
            for (SubCategory subcate: subs) {
                row.add(subcate.getStudentGrade(student.getSID()));
            }
            data.add(row);
        }
        System.out.println("output");
        System.out.println(data.get(0).get(0).toString() + data.get(0).get(1).toString());
        System.out.println(data.get(1).get(0).toString() + data.get(1).get(1).toString());
        Object[] results = {data,header,currentCourse.getStudents()};
        return results;
    }

    public boolean importStudentList(String filename){
        //todo
        return false;
    }

    public static Vector LoadStuInfo(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(/*todo*/"stutest.csv"));
            String line = null;
            Vector students=new Vector();   // Each element of this vector is a student like Stu1, Stu2
            while((line=reader.readLine())!=null) {
                String item[] = line.split(",");
                Vector row=new Vector();   // Each element of this vector is a
                for(int i=0;i<5;i=i+1) {
                    row.addElement(item[i]);
                }
                students.addElement(row);
            }
            //System.out.println(students); // students is the data we retrieve from csv file
            return students;
        }catch(Exception e){
            e.printStackTrace();
            Vector f=new Vector();
            return f;
        }
    }

    public void gradereport(Vector v){
        File csv=new File( currentCourse.getCourseName()+ "report.csv");
        if(csv.exists()){
            System.out.println("already exists!");
        }else {
            Object[] vList = v.toArray();
            Integer stunum = new Integer(0);
            stunum = vList.length;
            for (int i = 0; i < vList.length; i = i + 1) {
                String stest = vList[i].toString();
                String stest1 = new String();
                stest1 = stest.substring(1, stest.length() - 1);
                String stest2[] = stest1.split(",");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
                    for (int j = 0; j < stest2.length; j = j + 1) {
                        bw.write(stest2[j]);
                        bw.write(",");
                        bw.newLine();
                        bw.close();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



}
