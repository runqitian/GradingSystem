package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeightPanel extends JPanel implements ActionListener {

    API api;

    JTable cTable;
    JTable sTable;

    public WeightPanel(API api){
        this.api = api;

        this.setBackground(Color.CYAN);
        this.setLayout(new GridBagLayout());

        init();
    }

    public void init(){


        MyTableModel cModel = new MyTableModel();
        MyTableModel sModel = new MyTableModel();
//        DefaultTableModel cTableModel = new DefaultTableModel(categoryNames,categoryCol);
//        DefaultTableModel sTableModel = new DefaultTableModel(subcategoryNames,subcategoryCol);

        cTable = new JTable(cModel);
        sTable = new JTable(sModel);
//        categoryWeightTable.setDefaultRenderer(Object.class,new MyTableCellRenderer());
//        subcategoryWeightTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
//        categoryWeightTable.getTableHeader().setDefaultRenderer(new MyTableCellHeaderRenderer());
//        subcategoryWeightTable.getTableHeader().setDefaultRenderer(new MyTableCellHeaderRenderer());
////        categoryWeightTable.getTableHeader().setBackground(Color.GREEN);
//        categoryWeightTable.setRowHeight(50);
//        subcategoryWeightTable.setRowHeight(50);
//        categoryWeightTable.setFont(new Font("TimesRoman", Font.PLAIN, 22));
//        subcategoryWeightTable.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        cTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
        cTable.getTableHeader().setDefaultRenderer(new MyTableCellHeaderRenderer());
        cTable.setRowHeight(50);
        cTable.getTableHeader().setBackground(Color.GRAY);
        cTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        cTable.setGridColor(Color.LIGHT_GRAY);
        cTable.setShowGrid(true);
        cTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
        sTable.getTableHeader().setDefaultRenderer(new MyTableCellHeaderRenderer());
        sTable.setRowHeight(30);
        sTable.getTableHeader().setBackground(Color.GRAY);
        sTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        sTable.setGridColor(Color.LIGHT_GRAY);
        sTable.setShowGrid(true);
        sTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//        sTable.getTableHeader().setDefaultRenderer(new MyTableCellHeaderRenderer());
//        sTable.setDefaultRenderer(Object.class, new MyTableCellRenderer());
//        sTable.setRowHeight(30);
//        cTable.setFont(new Font("TimesRoman", Font.PLAIN, 22));

        cTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        sTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane categoryPane = new JScrollPane(cTable);
        categoryPane.setPreferredSize(new Dimension(300,400));
        JScrollPane subcategoryPane = new JScrollPane(sTable , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        subcategoryPane.setPreferredSize(new Dimension(300,400));
        subcategoryPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.add(categoryPane, new GBC(0,1,1,1,0.5,0.8,GridBagConstraints.NONE,GridBagConstraints.CENTER));
        this.add(subcategoryPane, new GBC(1,1,1,1,0.5,0.8,GridBagConstraints.NONE,GridBagConstraints.CENTER));
    }

    public void showPanel(){
        this.setVisible(true);
        this.setEnabled(true);
    }

    public void hidePanel(){
        this.setVisible(false);
        this.setEnabled(false);
    }


    public void actionPerformed(ActionEvent e) {

    }
}
