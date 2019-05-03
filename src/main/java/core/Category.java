package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Category {

    protected String categoryName;
    protected Double weight;
//    Vector<SubCategory> subCategoryList;

    public Category(String categoryName) {
        this.categoryName = categoryName;
//        this.weight = weight;
//        this.subCategoryList = new Vector<SubCategory>();
    }

    public Category(String categoryName, Double weight) {
        this.categoryName = categoryName;
        this.weight = weight;
    }

    public String getName() {
        return categoryName;
    }

    public void setName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
//        public boolean addSubCategory(String SubName, Double Weight, Integer maxpossible){
//        SubCategory sub = new SubCategory(SubName, Weight, maxpossible);
//        return this.subCategoryList.add(sub);
//    }

//    public boolean deleteSubCategory(String subname){
//        if(subCategoryList.)
//    }
    public String toString(){
        return categoryName + " : " + weight.toString();
    }

}
