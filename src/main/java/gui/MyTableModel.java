package gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

public class MyTableModel extends AbstractTableModel {

    Vector<Vector<Object>> data;
    Vector<Object> header;
    Vector<Integer> notEditable;

    public MyTableModel(){
        this.data = new Vector<Vector<Object>>();
        Vector<Object> row = new Vector<Object>();
        row.add("111111111");
        row.add("2");
        row.add("333333333");
        row.add("4");
        row.add("5");
        data.add(row);
        data.add(row);
        data.add(row);
        header = new Vector<Object>();
        header.add("h1111111111");
        header.add("h2");
        header.add("h3");
        header.add("h4444444444");
        header.add("h5");
        notEditable = new Vector<Integer>();
        notEditable.add(0);
        notEditable.add(1);
    }

    public MyTableModel(Vector<Vector<Object>> data,Vector<Object> header, Vector<Integer> notEditable){
        this.header = header;
        this.data = data;
        this.notEditable = notEditable;
    }

//    public MyTableModel(Object[][] data, Object[] header, Object[] notEditable){
//        this.data = new Vector<Vector<Object>>();
//        for(int i=0; i<data.length; i++){
//            this.data.add(new Vector<Object>(Arrays.asList(data[i])));
//        }
//    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return header.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    public void setDataVector(Vector<Vector<Object>> data, Vector<Object> header, Vector<Integer> notEditable){
        this.header = header;
        this.data = data;
        this.notEditable = notEditable;
    }

    @Override
    public String getColumnName(int column) {
        return header.get(column).toString();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (notEditable.contains(columnIndex)){
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex).set(columnIndex, aValue);
    }
}
