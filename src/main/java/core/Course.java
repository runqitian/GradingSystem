package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Course {

    // unique
    String courseName;

    List<Category> categoryList = new ArrayList<Category>();
    List<SubCategory> subCategoryList = new ArrayList<SubCategory>();

    public Course(String courseName){
        this.courseName = courseName;
    }

    public Vector<Vector<Object>> getAll(){
        Vector<Vector<Object>> result = DatabaseStorage.getAllSubCategories();
        return result;
    }

    public void addCategory(String a){

    }
}
