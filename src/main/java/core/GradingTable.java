package core;

import java.util.List;
import java.util.Vector;

public class GradingTable {
    Course course;
    Vector<SubCategory> cols = new Vector<SubCategory>();
    Vector<Vector<Object>> table = new Vector<Vector<Object>>();

    public GradingTable(List<Student> students, List<SubCategory> subCategories){

        for (SubCategory sub : subCategories){
            cols.add(sub);
        }
        //sub start index: 0
        //score start index: 1
        for(Student stu: students){
            Vector<Object> row = new Vector<Object>();
            row.add(stu);
            for (SubCategory sub : subCategories){
                row.add(new Integer(0));
            }
            table.add(row);
        }
    }

}



