package core;

import java.util.Vector;

public class Category {

    private int id;
    private String name;
    private Double weight;

    Vector<SubCategory> subCategories = new Vector<SubCategory>();

    public Category(int id, String name, Double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public Vector<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Vector<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public static Category getCategoryByName(String categoryName, Vector<Category> categories){
        for(Category category: categories){
            if (category.getName().equals(categoryName)){
                return category;
            }
        }
        return null;
    }
}
