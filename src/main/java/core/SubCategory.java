package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class SubCategory extends Category{
    Category category;
//    String subCategoryName;
//    Double weight;
    Integer maxGrade;
    Map<Student, Score> stuScore = new HashMap<Student, Score>();

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

    public boolean gradeStudent(Student stu, int score){
        if(!stuScore.containsKey(stu)){
            Score grade = new Score(maxGrade);
            if (grade.setScore(score)){
                stuScore.put(stu, grade);
            }
        }
        System.out.println("Student already have grade");
        return false;
    }

    public Integer getStudentGrade(Student stu){
        if(stuScore.containsKey(stu)){
            return stuScore.get(stu).getScore();
        }
        System.out.println("student not graded");
        return -1;
    }

    public boolean changeGrade(Student stu, int score){
        if(stuScore.containsKey(stu)){
            Score grade = new Score(maxGrade);
            if (grade.setScore(score)){
                return grade.equals(stuScore.replace(stu, grade));
                //warning: only for java 1.8+
            }
        }
        System.out.println("Student not graded");
        return false;
    }

    public boolean deleteGrade(Student stu){
        return stuScore.get(stu).equals(stuScore.remove(stu));
    }

    public Map<String, Integer> getAllGrades(){
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<Student, Score> stuS:
                stuScore.entrySet()) {
            String SID = stuS.getKey().getSID();
            Integer score = stuS.getValue().getScore();
            result.put(SID, score);
        }
        return result;
    }



}
