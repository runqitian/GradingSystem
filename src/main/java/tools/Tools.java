package tools;

import java.util.Vector;

public class Tools {
    public static Object[][] vectorTo2DArray(Vector<Vector<Object>> input){
        Object[][] data = new Object[input.size()][input.get(0).size()];
        for(int i=0; i<input.size();i++){
            for(int j=0; j<input.get(0).size(); j++){
                data[i][j] = input.get(i).get(j);
            }
        }
        return data;
    }
}
