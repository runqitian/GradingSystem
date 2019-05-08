package gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

public class MyTableModel extends DefaultTableModel {

    Integer[] notEditable;

    Class[] typeArray;

    public MyTableModel(){
        super();
    }

    public MyTableModel(Vector<Vector<Object>> data, Vector<Object> header, Integer[] notEditable){
        super(data,header);
        this.notEditable = notEditable;
        if (data.size()>0){
            int size = data.get(0).size();
            typeArray = new Class[size];
            for (int i=0; i<size; i++){
                typeArray[i] = Object.class;
            }
        }
    }

    public MyTableModel(Object[][] data, Object[] header, Integer[] notEditable){
        super(data,header);
        this.notEditable = notEditable;
        if (data.length>0){
            int size = data[0].length;
            typeArray = new Class[size];
            for (int i=0; i<size; i++){
                typeArray[i] = Object.class;
            }
        }
    }

    public MyTableModel(Vector<Vector<Object>> data, Vector<Object> header, Integer[] notEditable,Class[] typeArray){
        super(data,header);
        this.notEditable = notEditable;
        this.typeArray = typeArray;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        for (Integer item : notEditable){
            if (item == column){
                return false;
            }
        }
        return true;
    }

    public void setDataVector(Vector<Vector<Object>> data, Vector<Object> header, Integer[] notEditable, Class[] typeArray) {
        super.setDataVector(data, header);
        this.notEditable = notEditable;
        this.typeArray = typeArray;
    }

    public void setDataVector(Vector<Vector<Object>> data, Vector<Object> header, Integer[] notEditable){
        super.setDataVector(data, header);
        this.notEditable = notEditable;
        if (data.size()>0){
            int size = data.get(0).size();
            typeArray = new Class[size];
            for (int i=0; i<size; i++){
                typeArray[i] = Object.class;
            }
        }
    }

    public void setDataVector(Object[][] data, Object[] header, Integer[] notEditable){
        super.setDataVector(data,header);
        this.notEditable = notEditable;
        if (data.length>0){
            int size = data[0].length;
            typeArray = new Class[size];
            for (int i=0; i<size; i++){
                typeArray[i] = Object.class;
            }
        }
    }

    public Class getColumnClass(int columnIndex) {
        return typeArray[columnIndex];// 返回每一列的数据类型
    }
}
