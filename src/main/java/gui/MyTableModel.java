package gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

public class MyTableModel extends DefaultTableModel {

    Vector<Integer> notEditable;

    public MyTableModel(){
        super();
    }

    public MyTableModel(Vector<Vector<Object>> data, Vector<Object> header, Vector<Integer> notEditable){
        super(data,header);
        this.notEditable = notEditable;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (notEditable.contains(column)){
            return false;
        }
        return true;
    }

    public void setDataVector(Vector<Vector<Object>> data, Vector<Object> header, Vector<Integer> notEditable) {
        super.setDataVector(data, header);
        this.notEditable = notEditable;
    }
}
