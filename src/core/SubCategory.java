package core;

import java.util.Vector;

public class SubCategory {

    private int id;
    private String name;
    private Double weight;
    private Double max;

    private Category belong;

    public SubCategory(int id, String name, Double weight, Double max, Category belong) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.max = max;
        this.belong = belong;
    }

    public boolean sameID(int id){
        if (this.id == id){
            return true;
        }
        return false;
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

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Category getBelong() {
        return belong;
    }

    public void setBelong(Category belong) {
        this.belong = belong;
    }

    public static SubCategory getSubCategoryByName(String name, Vector<SubCategory> subCategories){
        for (SubCategory sub: subCategories){
            if (sub.getName().equals(name)){
                return sub;
            }
        }
        return null;
    }
}
