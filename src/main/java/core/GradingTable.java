package core;

import java.util.List;
import java.util.Vector;

public class GradingTable {

    Vector<Vector<Object>> table;

    public GradingTable(List<Student> students, List<SubCategory> subCategories){
        for(int i=0; i<students.size(); i++){
            Vector<Object> row = new Vector<Object>();
            row.add(students.get(i));
            for (int j=0; j<subCategories.size(); j++){
                row.add(new GradeEntity(subCategories.get(j),0.0));
            }
            table.add(row);
        }
    }

    public void getGUITable(){

    }

    public void updateGradingTable(){

    }



}

class GradeEntity {

    SubCategory belong;
    Double score;

    public GradeEntity(SubCategory belong, Double score){
        this.belong = belong;
        this.score = score;
    }

}


