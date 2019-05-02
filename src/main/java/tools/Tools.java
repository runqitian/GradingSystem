package tools;

import gui.MyTableCellHeaderRenderer;
import gui.MyTableCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class Tools {
    public static Object[][] vector2DToArray2D(Vector<Vector<Object>> input){
        Object[][] data = new Object[input.size()][input.get(0).size()];
        for(int i=0; i<input.size();i++){
            for(int j=0; j<input.get(0).size(); j++){
                data[i][j] = input.get(i).get(j);
            }
        }
        return data;
    }

    public static Object[][] list1DToArray2D(List<Object> input){
        Object[][] output = new Object[input.size()][1];
        for (int i=0; i<input.size(); i++){
            output[i][0] = input.get(i);
        }
        return output;
    }

    public static void beautifyJTable(JTable table, boolean autosize, int fontsize, int rowHeight){
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer(fontsize));
        table.getTableHeader().setDefaultRenderer(new MyTableCellHeaderRenderer());
        table.setRowHeight(rowHeight);
        table.getTableHeader().setBackground(Color.GRAY);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        if (!autosize){
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }



}
