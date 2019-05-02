package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Course {

    // unique
    String courseName;

    Vector<Student> students = new Vector<Student>();
    Vector<Category> categoryList = new Vector<Category>();
    Vector<SubCategory> subCategoryList = new Vector<SubCategory>();

    public Course(String courseName){
        this.courseName = courseName;
    }

//    public Vector<Vector<Object>> getAll(){
//        Vector<Vector<Object>> result = DatabaseStorage.getAllSubCategories();
//        return result;
//    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Vector<Student> getStudents() {
        return students;
    }

    public void setStudents(Vector<Student> students) {
        this.students = students;
    }

    public Vector<Category> getCategoryList() {
        return categoryList;
    }

    public Vector<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public boolean addCategory(String CategoryName, Double Weight){
        Category category = new Category(CategoryName, Weight);
//        this .categoryList.addElement(category);
        return this.categoryList.add(category);
    }

    public boolean deleteCategory(String CategoryName){
        for (Category category:
                this.categoryList) {
            if(category.getName() == CategoryName){
                for (SubCategory subcategory:
                        this.subCategoryList) {
                    if(subcategory.getCategory().getName() == CategoryName){
                        this.categoryList.remove(subcategory);
                    }
                }
                return categoryList.remove(category);
            }
        }
        return false;
    }

    public void modifyCategory(){
        //todo
    }

    public boolean addSubCategory(String CategoryName, String SubName, Double Weight, Integer MaxGrade){
        for (Category category:
             categoryList) {
            if(category.getName() == CategoryName){
                SubCategory sub = new SubCategory(category, SubName, Weight, MaxGrade);
                return subCategoryList.add(sub);
            }
        }
        return false;
    }

    public boolean deleteSubCategory(String SubName){
        for (SubCategory subcategory:
                subCategoryList) {
            if(subcategory.getName() == SubName){
                return subCategoryList.remove(subcategory);
            }
        }
        return false;
    }

    public void modifySubCategory(){
        //todo check score
    }


}
