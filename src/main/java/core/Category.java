package core;

import java.util.ArrayList;
import java.util.List;

public class Category {

    String categoryName;
    Double weight;
    List<SubCategory> subCategoryList;

    public Category(String categoryName, Double weight) {
        this.categoryName = categoryName;
        this.weight = weight;
        this.subCategoryList = new ArrayList<SubCategory>();
    }


}
