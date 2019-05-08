package tools;

import gui.MyTableCellHeaderRenderer;
import gui.MyTableCellRenderer;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Enumeration;
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

    public static Vector<Object> array1DToVector1D(Object[] arr){
        Vector<Object> vec = new Vector<Object>();
        for (Object obj: arr){
            vec.add(arr);
        }
        return vec;
    }

    public static void beautifyJTable(JTable table, boolean autosize, int fontsize, int rowHeight){
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer(fontsize));
        table.getTableHeader().setDefaultRenderer(new MyTableCellHeaderRenderer());
        table.setRowHeight(rowHeight);
        table.getTableHeader().setBackground(Color.GRAY);
//        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        if (!autosize){
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }

    public static void formattingGradingTable(JTable table){
        int fontsize = 25;
        int rowHeight = 30;
        table.setDefaultRenderer(Object.class, new MyTableCellRenderer(fontsize));
        table.getTableHeader().setDefaultRenderer(new MyTableCellHeaderRenderer());
        table.setRowHeight(rowHeight);
        table.getTableHeader().setBackground(Color.GRAY);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        if (table.getColumnCount()<4){
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }else {
            Enumeration<TableColumn> enr = table.getColumnModel().getColumns();
            while(enr.hasMoreElements()){
                enr.nextElement().setPreferredWidth(200);
            }
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }




}
