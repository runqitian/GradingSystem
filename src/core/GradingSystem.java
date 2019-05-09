package core;

import data.Database;
import org.omg.CORBA.OBJ_ADAPTER;
import tools.Tools;

import javax.xml.crypto.Data;
import java.util.Vector;

public class GradingSystem {

    public User currentUser;
    public Course currentCourse;

    public boolean login(String username, String password){

        User user;
        if ((user = Database.getUser(username,password)) != null){
            currentUser = user;
            Vector<Vector<Object>> namelist = getCourseNameList();
            if (namelist.size() == 0){
                currentCourse = null;
            }else{
                currentCourse = Course.loadCourseInfoFromDatabase(currentUser,namelist.get(0).get(0).toString());
            }
            return true;
        }
        return false;
    }

    public Vector<Vector<Object>> getCourseNameList(){
        Vector<Vector<Object>> namelist = Database.getCourseNameList(this.currentUser);
        return namelist;
    }

    public boolean addCategory(String categoryName){
        for (Category category: currentCourse.getCategories()){
            if (category.getName().equals(categoryName)){
                return false;
            }
        }
        Database.insertCategory(currentCourse.getId(), categoryName, 0);
        currentCourse = Course.loadCourseInfoFromDatabase(currentUser,currentCourse.getName());
        return true;
    }

    public boolean addSubCategory(String subCategoryName, String categoryName){
        for(SubCategory subCategory: currentCourse.getSubCategories()){
            if (subCategory.getName().equals(subCategoryName)){
                return false;
            }
        }
        Database.insertSubCategory(Category.getCategoryByName(categoryName, currentCourse.getCategories()).getId(), subCategoryName,0,100, currentCourse.getId(), currentCourse.getStudents());
        currentCourse = Course.loadCourseInfoFromDatabase(currentUser,currentCourse.getName());
        return true;
    }

    public boolean deleteCategory(String categoryName){
        for (Category category: currentCourse.getCategories()){
            if (category.getName().equals(categoryName)){
                Database.deleteCategory(category.getId(),currentCourse.subCategories);
                currentCourse = Course.loadCourseInfoFromDatabase(currentUser,currentCourse.getName());
                return true;
            }
        }
        return false;
    }

    public boolean deleteSubCategory(String subCategoryName){
        for(SubCategory subCategory: currentCourse.getSubCategories()){
            if (subCategory.getName().equals(subCategoryName)){
                Database.deleteSubCategory(subCategory.getId());
                currentCourse = Course.loadCourseInfoFromDatabase(currentUser,currentCourse.getName());
                return true;
            }
        }
        return false;
    }

    public void updateCourse(){
        currentCourse = Course.loadCourseInfoFromDatabase(currentUser,currentCourse.getName());
        calculateTotalPoints();
    }

    public void changeCategoryWeight(Vector<Vector<Object>> data){
        for (Vector<Object> row: data){
            Category rowCat = Category.getCategoryByName(row.get(0).toString(),currentCourse.getCategories());
            Database.updateCategory(rowCat.getId(), new Double(row.get(1).toString()));
        }
    }

    public void changeSubCategoryWeight(Vector<Vector<Object>> data){
        for (Vector<Object> row: data){
            SubCategory rowSub = SubCategory.getSubCategoryByName(row.get(1).toString(),currentCourse.getSubCategories());
            Database.updateSubCategory(rowSub.getId(), new Double(row.get(2).toString()), new Double(row.get(3).toString()));
        }
    }

    public Vector<Vector<Object>> getSeletedGrades(Vector<String> subNames){
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        Vector<Integer> idxs = new Vector<Integer>();
        idxs.add(0);
        for (String subName: subNames) {
            for (int i = 0; i < currentCourse.getHeader().length; i++) {
                if (currentCourse.getHeader()[i].toString().equals(subName)) {
                    idxs.add(i);
                }
            }
        }
        for (int i=0; i<currentCourse.getGradingTable().length; i++){
            Vector<Object> row = new Vector<Object>();
            for (int j=0; j<idxs.size(); j++){
                row.add(currentCourse.getGradingTable()[i][j]);
            }
            data.add(row);
        }
        return data;
    }

    public void updateGrading(Vector<Vector<Object>> data, Vector<Object> header){
        Vector<Integer> subids = new Vector<Integer>();
        for (int i=1; i<header.size(); i++) {
            subids.add(SubCategory.getSubCategoryByName(header.get(i).toString(), currentCourse.getSubCategories()).getId());
        }
        for (int i=0; i<data.size(); i++){
            String sid = currentCourse.students.get(i).getId();
            Vector<Object> row = data.get(i);
            for (int j=1; j<row.size(); j++){
                Database.updateSingleGrading(sid,subids.get(j-1),new Double(row.get(j).toString()));
            }
        }
    }

    public void addComment(String sid, String comment){
        Database.addComment(sid, currentCourse.getId(), comment);
    }

    public void importStudents(String filepath){
        Vector<Student> importStudents = Tools.importStudents(filepath);
        Vector<Integer> subids = new Vector<Integer>();
        for (SubCategory sub:currentCourse.getSubCategories()){
            subids.add(sub.getId());
        }
        for (Student student: importStudents){
            Database.addStudent(currentCourse.getId(),student,subids);
        }
    }

    public void calculateTotalPoints(){
        for (int i=0; i<currentCourse.students.size(); i++){
            Tools.calculateTotal(currentCourse.categories,currentCourse.subCategories, currentCourse.gradingTable[i], currentCourse.header, currentCourse.students.get(i));
        }
    }
}
