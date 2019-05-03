package core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class SubCategory extends Category{
    Category category;
//    String subCategoryName;
//    Double weight;
    Integer maxGrade;
    Map<String, Score> stuScore = new HashMap<String, Score>();

    public SubCategory(Category category, String subCategoryName, Double weight, Integer maxGrade) {
        super(subCategoryName);
        this.category = category;
//        this.categoryName = subCategoryName;
        this.weight = weight;
        this.maxGrade = maxGrade;
    }

    public SubCategory(Category category, String subCategoryName) {
        super(subCategoryName);
        this.category = category;
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

    public void setMaxGrade(int maxGrade) {
        this.maxGrade = new Integer(maxGrade);
    }

    public boolean gradeStudent(String stuID, int score){
        if(!stuScore.containsKey(stuID)){
            Score grade = new Score(maxGrade);
            if (grade.setScore(score)){
                stuScore.put(stuID, grade);
            }
        }
        System.out.println("Student already have grade");
        return false;
    }

    public Double getStudentGrade(Student stu){
        if(stuScore.containsKey(stu)){
            return stuScore.get(stu).getScore();
        }
        System.out.println("student not graded");
        return -1.0;
    }

    public boolean changeGrade(String stuID, int score){
        if(stuScore.containsKey(stuID)){
            Score grade = new Score(maxGrade);
            if (grade.setScore(score)){
                return grade.equals(stuScore.replace(stuID, grade));
                //warning: only for java 1.8+
            }
        }
        System.out.println("Student not graded");
        return false;
    }

    public boolean deleteGrade(Student stu){
        return stuScore.get(stu).equals(stuScore.remove(stu));
    }

    public Map<String, Double> getAllGrades(){
        Map<String, Double> result = new HashMap<String, Double>();
        for (Map.Entry<String, Score> stuS:
                stuScore.entrySet()) {
            String SID = stuS.getKey();
            Double score = stuS.getValue().getScore();
            result.put(SID, score);
        }
        return result;
    }

    public void setStuScore(Map<String, String> stuScore) {
        Map<String, Score> grades = new HashMap<String, Score>();
        for (Map.Entry<String,String> ent: stuScore.entrySet()){
            String sID = ent.getKey();
            Double score = new Double(ent.getValue());
            Score grade = new Score(score);
            grades.put((String) sID, grade);
        }
        this.stuScore = grades;
    }

    public String toString(){
        return getName() + " : " + weight.toString();
    }
}
