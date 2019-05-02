package core;

public class SubCategory extends Category{
    Category category;
//    String subCategoryName;
//    Double weight;
    Integer maxGrade;

    public SubCategory(Category category, String subCategoryName, Double weight, Integer maxGrade) {
        super(subCategoryName, weight);
        this.category = category;
        this.categoryName = subCategoryName;
        this.weight = weight;
        this.maxGrade = maxGrade;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(Integer maxGrade) {
        this.maxGrade = maxGrade;
    }
}
