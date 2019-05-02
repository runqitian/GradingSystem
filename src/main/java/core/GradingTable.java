package core;

import java.util.List;
import java.util.Vector;

public class GradingTable {

    Vector<SubCategory> cols = new Vector<SubCategory>();
    Vector<Vector<Object>> table = new Vector<Vector<Object>>();

    public GradingTable(List<Student> students, List<SubCategory> subCategories){

        for (SubCategory sub : subCategories){
            cols.add(sub);
        }

        for(int i=0; i<students.size(); i++){
            Vector<Object> row = new Vector<Object>();
            row.add(students.get(i));
            for (int j=0; j<subCategories.size(); j++){
                row.add(new Double(0));
            }
            table.add(row);
        }
    }

}



