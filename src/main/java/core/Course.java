package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Course {

    // unique
    String courseName;

    Vector<Category> categoryList = new Vector<Category>();
    Vector<SubCategory> subCategoryList = new Vector<SubCategory>();

    public Course(String courseName){
        this.courseName = courseName;
    }

//    public Vector<Vector<Object>> getAll(){
//        Vector<Vector<Object>> result = DatabaseStorage.getAllSubCategories();
//        return result;
//    }

    public boolean addCategory(String CategoryName, Double Weight){
        Category category = new Category(CategoryName, Weight);
//        this .categoryList.addElement(category);
        return this.categoryList.add(category);
    }

    public boolean deleteCategory(String CategoryName){
        for (Category category:
                this.categoryList) {
            if(category.getCategoryName() == CategoryName){
                for (SubCategory subcategory:
                        this.subCategoryList) {
                    if(subcategory.getCategory().getCategoryName() == CategoryName){
                        this.categoryList.remove(subcategory);
                    }
                }
                return categoryList.remove(category);

            }
        }
        return false;
    }

    public boolean addSubCategory(String CategoryName, String SubName, Double Weight, Integer MaxGrade){
        for (Category category:
             categoryList) {
            if(category.getCategoryName() == CategoryName){
                SubCategory sub = new SubCategory(category, SubName, Weight, MaxGrade);
                return subCategoryList.add(sub);
            }
        }
        return false;
    }
    public boolean deleteSubCategory(String SubName){
        for (SubCategory subcategory:
                subCategoryList) {
            if(subcategory.getCategoryName() == SubName){
                return subCategoryList.remove(subcategory);
            }
        }
        return false;
    }

    public Integer getCategoryScore(){
        //todo
        return null;
    }

}
