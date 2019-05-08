package gui;

import com.sun.codemodel.internal.JOp;
import core.Course;
import tools.Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.VolatileCallSite;
import java.util.HashMap;
import java.util.Vector;

public class WeightPanel extends JPanel implements ActionListener {

    API api;

    JPanel titlePanel;
    MyTableModel cModel;
    MyTableModel sModel;
    JTable cTable;
    JTable sTable;
    Vector<Object> cHeader;
    Vector<Object> sHeader;
    Integer[] cNotEditable = {0};
    Integer[] sNotEditable = {0,1};

    JScrollPane categoryPane;
    JScrollPane subcategoryPane;


    JPanel downPanel;
    JButton saveBtn;
    JButton cancelBtn;
    JTextField categoryInput;
    JButton categoryAddBtn;
    JButton categoryDelBtn;
    JComboBox subBelong;
    JTextField subInput;
    JButton subAddBtn;
    JButton subDelBtn;


    public WeightPanel(API api){
        this.api = api;

//        this.setBackground(Color.CYAN);
        this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setEnabled(false);
        init();
    }

    public void init(){

        cHeader = new Vector<Object>();
        sHeader = new Vector<Object>();
        cHeader.add("Category");
        cHeader.add("Weight");
        sHeader.add("Category");
        sHeader.add("SubCategory");
        sHeader.add("Weight");
        sHeader.add("Max Score");
        cModel = new MyTableModel(new Vector<Vector<Object>>(),cHeader,cNotEditable);
        sModel = new MyTableModel(new Vector<Vector<Object>>(),sHeader,sNotEditable);

        titlePanel = new JPanel();
        cTable = new JTable(cModel);
        sTable = new JTable(sModel);
        downPanel = new JPanel();
        categoryInput = new JTextField();
        categoryAddBtn = new JButton("add");
        categoryDelBtn = new JButton("delete");
        subBelong = new JComboBox();
        subInput = new JTextField();
        subAddBtn = new JButton("add");
        subDelBtn = new JButton("delete");
        saveBtn = new JButton("save");
        cancelBtn = new JButton("cancel");

        saveBtn.setPreferredSize(new Dimension(70,40));
        cancelBtn.setPreferredSize(new Dimension(70,40));

        saveBtn.setActionCommand("save_change");
        saveBtn.addActionListener(this);
        cancelBtn.setActionCommand("cancel");
        cancelBtn.addActionListener(this);

        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setLayout(new GridBagLayout());
        JLabel titleLabel = new JLabel("Change Weight");
        titleLabel.setFont(new Font("TimesRoman", Font.BOLD, 35));
        titlePanel.add(titleLabel, new GBC(0,0,1,1,0.5,1,GridBagConstraints.NONE,GridBagConstraints.LINE_START, new Insets(0,60,0,0)));
        titlePanel.add(saveBtn, new GBC(1,0,1,1,0.5,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END, new Insets(0,0,0,170 )));
        titlePanel.add(cancelBtn, new GBC(1,0,1,1,0.5,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END, new Insets(0,0,0,70 )));
        Tools.beautifyJTable(cTable, true, 20,50);
        Tools.beautifyJTable(sTable, true, 20,50);
        categoryPane = new JScrollPane(cTable);
        subcategoryPane = new JScrollPane(sTable , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        categoryInput.setPreferredSize(new Dimension(120,35));
        categoryAddBtn.setPreferredSize(new Dimension(70,40));
        categoryDelBtn.setPreferredSize(new Dimension(70,40));
        subInput.setPreferredSize(new Dimension(120,35));
        subAddBtn.setPreferredSize(new Dimension(70,40));
        subBelong.setPreferredSize(new Dimension(120,40));
        subDelBtn.setPreferredSize(new Dimension(70,40));

        categoryDelBtn.setActionCommand("delete_category");
        categoryDelBtn.addActionListener(this);
        categoryAddBtn.setActionCommand("add_category");
        categoryAddBtn.addActionListener(this);
        subAddBtn.setActionCommand("add_subcategory");
        subAddBtn.addActionListener(this);
        subDelBtn.setActionCommand("delete_subcategory");
        subDelBtn.addActionListener(this);

//        downPanel.setBackground(Color.GRAY);
        downPanel.setLayout(new GridBagLayout());

        this.add(titlePanel,new GBC(0,0,2,1,1,0.2,GridBagConstraints.BOTH));
        this.add(categoryPane, new GBC(0,1,1,1,0.4,0.6,GridBagConstraints.BOTH,GridBagConstraints.CENTER, new Insets(10,55,10,55)));
        this.add(subcategoryPane, new GBC(1,1,1,1,0.6,0.6,GridBagConstraints.BOTH,GridBagConstraints.CENTER, new Insets(10,55,10,55)));

        downPanel.add(categoryInput, new GBC(0,0,1,1,0.4,1, GridBagConstraints.NONE, GridBagConstraints.LINE_END, new Insets(10,0,10,215)));
        downPanel.add(categoryAddBtn, new GBC(0,0,1,1,0.4,1,GridBagConstraints.NONE, GridBagConstraints.LINE_END, new Insets(10,0,10,135)));
        downPanel.add(categoryDelBtn, new GBC(0,0,1,1,0.4,1,GridBagConstraints.NONE, GridBagConstraints.LINE_END, new Insets(10,0,10,55)));
        downPanel.add(subBelong, new GBC(1,0,1,1,0.6,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END,new Insets(10,0,10,345)));
        downPanel.add(subInput, new GBC(1,0,1,1,0.6,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END,new Insets(10,0,10,215)));
        downPanel.add(subAddBtn, new GBC(1,0,1,1,0.6,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END,new Insets(10,0,10,135)));
        downPanel.add(subDelBtn, new GBC(1,0,1,1,0.6,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END,new Insets(10,0,10,55)));
        this.add(downPanel, new GBC(0,2,5,1,1,0.2));
    }

    public void showPanel(){
        this.setVisible(true);
        this.setEnabled(true);
    }

    public void hidePanel(){
        this.setVisible(false);
        this.setEnabled(false);
    }

    public void refreshAndLoad(Vector<Vector<Object>> catData, Vector<Object> catHeader, Vector<Vector<Object>> subData, Vector<Object> subHeader){
        this.cModel.setDataVector(catData,catHeader,cNotEditable);
        this.sModel.setDataVector(subData,subHeader,sNotEditable);
        this.cModel.fireTableDataChanged();
        this.sModel.fireTableDataChanged();
    }

    public boolean checkLegal(){
        try {
            int cRows = this.cTable.getRowCount();
            int sRows = this.sTable.getRowCount();
            for (int i = 0; i < cRows; i++) {
                Double cWeight = new Double(cTable.getValueAt(i, 1).toString());
                if (cWeight < 0){
                    throw new Exception();
                }
            }
            for (int i = 0; i < sRows; i++){
                Double sMax = new Double(sTable.getValueAt(i,2).toString());
                Double sWeight = new Double(sTable.getValueAt(i,3).toString());
                if (sWeight < 0 || sMax < 0){
                    throw new Exception();
                }
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Vector<Vector<Object>> pushWeightChange(){
        Vector<Vector<Object>> pushData = new Vector<Vector<Object>>();
        int cRows = this.cTable.getRowCount();
        int sRows = this.sTable.getRowCount();
        for (int i=0; i<cRows; i++){
            String cName = cTable.getValueAt(i,0).toString();
            Double cWeight = new Double(cTable.getValueAt(i,1).toString());
            Vector<Object> row = new Vector<Object>();
            row.add(cName);
            row.add(cWeight);
            pushData.add(row);
        }

        for (int i=0; i<sRows; i++){
            String sName = sTable.getValueAt(i, 1).toString();
            Double sMax = new Double(sTable.getValueAt(i,2).toString());
            Double sWeight = new Double(sTable.getValueAt(i,3).toString());
//            String pushKey = cName + "/" + sName;
            Vector<Object> row = new Vector<Object>();
            row.add(sName);
            row.add(sWeight);
            row.add(sMax);
            pushData.add(row);
        }
        return pushData;
    }

//    public void refresh_new(){
//        categoryInput.setText("");
//        subInput.setText("");
//        this.cModel.setDataVector(new Vector<Vector<Object>>(),cHeader, cNotEditable);
//        this.sModel.setDataVector(new Vector<Vector<Object>>(),sHeader, sNotEditable);
//        this.cModel.fireTableDataChanged();
//        this.sModel.fireTableDataChanged();
//        this.subBelong.removeAllItems();
//    }

//    public void loadData(Vector<Vector<Object>> cData,Vector<Vector<Object>> sData){
//        this.cModel.setDataVector(cData, cHeader, cNotEditable);
//        this.sModel.setDataVector(sData, sHeader, sNotEditable);
//        this.cModel.fireTableDataChanged();
//        this.sModel.fireTableDataChanged();
//        this.subBelong.removeAllItems();
//        for (int i=0; i<cData.size(); i++){
//            this.subBelong.addItem(cData.get(i).get(0).toString());
//        }
//
//    }

    public void refresh_load(Course course){
        categoryInput.setText("");
        subInput.setText("");
        this.cModel.setDataVector(course.getCategoryWeight(), cHeader, cNotEditable);
        this.sModel.setDataVector(course.getSubCategoryWeight(), sHeader, sNotEditable);
        this.cModel.fireTableDataChanged();
        this.sModel.fireTableDataChanged();
        this.subBelong.removeAllItems();
        for (int i=0; i<course.getCategoryWeight().size(); i++){
            this.subBelong.addItem(course.getCategoryWeight().get(i).get(0).toString());
        }
    }



    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add_category")){
            if (api.addCategory(this.categoryInput.getText())){
                JOptionPane.showMessageDialog(this,"add new category!");
            }else{
                JOptionPane.showMessageDialog(this,"operation failed!");
            }
        }
        else if (e.getActionCommand().equals("add_subcategory")){
            if (api.addSubCategory(this.subInput.getText(),this.subBelong.getSelectedItem().toString())){
                JOptionPane.showMessageDialog(this,"add new subcategory!");
            }else{
                JOptionPane.showMessageDialog(this,"operation failed!");
            }
        }
        else if (e.getActionCommand().equals("delete_category")){
            if (api.deleteCategory(this.categoryInput.getText())){
                JOptionPane.showMessageDialog(this,"delete success!");
            }else{
                JOptionPane.showMessageDialog(this,"operation failed!");
            }
        }
        else if (e.getActionCommand().equals("delete_subcategory")){
            if (api.deleteSubCategory(this.subInput.getText())){
                JOptionPane.showMessageDialog(this,"delete success!");
            }else{
                JOptionPane.showMessageDialog(this,"operation failed!");
            }
        }
        else if (e.getActionCommand().equals("save_change")){
            checkLegal();
            cModel.fireTableDataChanged();
            sModel.fireTableDataChanged();
            api.saveWeightChange(cModel.getDataVector(),sModel.getDataVector());
            JOptionPane.showMessageDialog(this,"changes saved!");
        }
        else if (e.getActionCommand().equals("cancel")){
            api.weightToClassPanel();
        }

    }
}
