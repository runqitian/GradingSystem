package core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class SubCategory extends Category{
    Category category;
//    String subCategoryName;
//    Double weight;
    Double maxGrade;
    Map<String, Score> stuScore = new HashMap<String, Score>();

    public SubCategory(Category category, String subCategoryName, Double weight, Double maxGrade) {
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

    public Double getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(double maxGrade) {
        this.maxGrade = new Double(maxGrade);
    }

    public boolean gradeStudent(String stuID, double score){
        if(!stuScore.containsKey(stuID)){
            Score grade = new Score(maxGrade);
            if (grade.setScore(score)){
                stuScore.put(stuID, grade);
            }
        }
        System.out.println("Student already have grade");
        return false;
    }

    public Double getStudentGrade(String id){
        if(stuScore.containsKey(id)){
            return stuScore.get(id).getScore();
        }
        System.out.println("student not graded");
        return 0.0;
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

    public void setStuScore(Map<String, Double> stuScore2) {
        Map<String, Score> grades = new HashMap<String, Score>();
        for (Map.Entry<String,Double> ent: stuScore2.entrySet()){
            String sID = ent.getKey();
            System.out.println(ent.getValue());
            System.out.println(ent.getValue().toString());
            Double score = new Double(ent.getValue());
            Score grade = new Score(maxGrade);
            grade.setScore(score);
            grades.put(sID, grade);
//            System.out.println(" in " + grades.get("U92094105"));
        }
        this.stuScore = grades;

//        System.out.println("out " + grades.get("U92094105"));
//        System.out.println("grades " + new Double(.getScore()).toString());
//        System.out.println("chrs" + grades.get("U92094105").toString());
    }

    public void updateGrades(Vector<Object> grades){
        int idx = 0;
        for (Map.Entry<String,Score> ent: stuScore.entrySet()){
            ent.getValue().setScore(new Double(grades.get(idx).toString()));
        }

    }

    public String toString(){
        return getName() + " : " + weight.toString();
    }
}
