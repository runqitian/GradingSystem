package gui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

public class MyTableModel extends DefaultTableModel {

    Vector<Integer> notEditable;

    public Vector<Class> typeArray = new Vector<Class>();

    public MyTableModel(){
        super();
    }

    public MyTableModel(Vector<Vector<Object>> data, Vector<Object> header, Vector<Integer> notEditable){
        super(data,header);
        this.notEditable = notEditable;
        this.typeArray = new Vector<Class>();
        if (data.size()>0){
            int size = data.get(0).size();
            for (int i=0; i<size; i++){
                typeArray.add(Object.class);
            }
        }
    }

    public MyTableModel(Vector<Vector<Object>> data, Vector<Object> header, Vector<Integer> notEditable,Vector<Class> typeArray){
        super(data,header);
        this.notEditable = notEditable;
        this.typeArray = typeArray;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (notEditable.contains(column)){
            return false;
        }
        return true;
    }

    public void setDataVector(Vector<Vector<Object>> data, Vector<Object> header, Vector<Integer> notEditable, Vector<Class> typeArray) {
        System.out.println(data);
        System.out.println(header);
        super.setDataVector(data, header);
        this.notEditable = notEditable;
        this.typeArray = typeArray;
    }

    public void setDataVector(Vector<Vector<Object>> data, Vector<Object> header, Vector<Integer> notEditable){
        this.notEditable = notEditable;
        this.typeArray = new Vector<Class>();
        if (data.size()>0){
            int size = data.get(0).size();
            super.setDataVector(data, header);
            for (int i=0; i<size; i++){
                typeArray.add(Object.class);
            }
        }
    }

    public Class getColumnClass(int columnIndex) {
        return typeArray.get(columnIndex);// 返回每一列的数据类型
    }
}
