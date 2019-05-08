package core;

import data.Database;

import java.util.Vector;

public class Course {

    int id;
    String name;
    Vector<Category> categories;
    Vector<SubCategory> subCategories;
    Vector<Student> students;
    Object[][] gradingTable;
    Object[] header;

    public static Course loadCourseInfoFromDatabase(User user, String courseName){
        if (Database.getCourseNameList(user).size() == 0){
            return null;
        }
        return Database.getSpecificCourse(user.getUsername(),courseName);
    }

    public Course(int id, String name, Vector<Category> categories, Vector<SubCategory> subCategories, Vector<Student> students, Object[][] gradingTable, Object[] header) {
        this.id = id;
        this.name = name;
        this.categories = categories;
        this.subCategories = subCategories;
        this.students = students;
        this.gradingTable = gradingTable;
        this.header = header;
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

    public Vector<Category> getCategories() {
        return categories;
    }

    public void setCategories(Vector<Category> categories) {
        this.categories = categories;
    }

    public Vector<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Vector<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public Object[][] getGradingTable() {
        return gradingTable;
    }

    public void setGradingTable(Object[][] gradingTable) {
        this.gradingTable = gradingTable;
    }

    public Object[] getHeader() {
        return header;
    }

    public void setHeader(Object[] header) {
        this.header = header;
    }

    public Vector<Student> getStudents() {
        return students;
    }

    public void setStudents(Vector<Student> students) {
        this.students = students;
    }

    public Vector<Vector<Object>> getSelecteSubCategoryTableModelData(){
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        System.out.println(subCategories.size());
        for (SubCategory subcategory: subCategories){
            Vector<Object> row = new Vector<Object>();
            row.add(subcategory.getBelong().getName());
            row.add(subcategory.getName());
            row.add(new Boolean(false));
            data.add(row);
        }
        System.out.println(data);
        return data;
    }

    public Vector<Object> getSelecteSubCategoryTableModelHeader(){
        Vector<Object> header = new Vector<Object>();
        header.add("category");
        header.add("subcategory");
        header.add("choose");
        return header;
    }

    public Vector<Vector<Object>> getCategoryWeight(){
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (Category category: categories){
            Vector<Object> row = new Vector<Object>();
            row.add(category.getName());
            row.add(category.getWeight());
            data.add(row);
        }
        return data;
    }

    public Vector<Object> getCategoryWeightHeader(){
        Vector<Object> header = new Vector<Object>();
        header.add("category");
        header.add("weight");
        return header;
    }

    public Vector<Vector<Object>> getSubCategoryWeight(){
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for (SubCategory subCategory: subCategories){
            Vector<Object> row = new Vector<Object>();
            row.add(subCategory.getBelong().getName());
            row.add(subCategory.getName());
            row.add(subCategory.getWeight());
            row.add(subCategory.getMax());
            data.add(row);
        }
        return data;
    }

    public Vector<Object> getSubCategoryWeightHeader(){
        Vector<Object> header = new Vector<Object>();
        header.add("category");
        header.add("subcategory");
        header.add("weight");
        header.add("max grade");
        return header;
    }

}
