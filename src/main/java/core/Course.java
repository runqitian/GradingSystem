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

    public boolean addCategory(String CategoryName){
        Category category = new Category(CategoryName);
//        this .categoryList.addElement(category);
        return this.categoryList.add(category);
    }

    public void setCategoryList(Vector<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void setSubCategoryList(Vector<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
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

    public boolean modifyCategory(String cateName, Double newWeight){
        for (Category cate:
             this.getCategoryList()) {
            if(cate.getName().equals(cateName)){
                cate.setWeight(newWeight);
                return true;
            }
        }
        System.out.println("no such Cate");
        return false;
        
    }

    public boolean addSubCategory(String CategoryName, String SubName){
        for (Category category:
             categoryList) {
            if(category.getName() == CategoryName){
                SubCategory sub = new SubCategory(category, SubName);
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

    public boolean modifySubCategory(String subName, Double newWeight, Integer maxpossible){
        for (SubCategory subcate:
                this.getSubCategoryList()) {
            if(subcate.getName().equals(subName)){
                subcate.setWeight(newWeight);
                subcate.setMaxGrade(maxpossible);
                return true;
            }
        }
        System.out.println("no such subCate");
        return false;
    }


}
