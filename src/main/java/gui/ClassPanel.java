package gui;

import core.Course;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassPanel extends JPanel implements ActionListener{

    API api;

    JPanel selectPanel;
    JPanel mainPanel;
    JTable courseList;
    JPanel subMainPanel;
    JButton createNewBtn;
    JButton templateBtn;
    JButton deleteBtn;
    JButton importBtn;
    JButton settingBtn;
    JTable categoryTable;
    JButton classReportBtn;
    JButton studentManageBtn;
    JButton gradeSelectedBtn;
    JFileChooser fileChooser;

    DefaultTableModel courseListModel;


    // test
    // test initial
    Course course = new Course("ood");



    DefaultTableCellRenderer tcr   =   new   DefaultTableCellRenderer();

    public ClassPanel(API api){

        this.setVisible(false);
        this.setEnabled(false);

        this.api = api;
        init();
    }

    public void showPanel(){
        this.setVisible(true);
        this.setEnabled(true);
    }

    public void hidePanel(){
        this.setVisible(false);
        this.setEnabled(false);
    }

    public void init(){


        tcr.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new GridBagLayout());
        this.selectPanel = new JPanel();
        this.mainPanel = new JPanel();
        selectPanel.setBackground(Color.WHITE);
//        selectPanel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        this.add(selectPanel,new GBC(0,0,1,1,0.2,1));
        this.add(mainPanel,new GBC(1,0,1,1,0.8,1));
        initSelectPanel();
        initMainPanel();
    }

    public void initSelectPanel(){
        selectPanel.setLayout(new GridBagLayout());

        String[] colName = {"course"};
        Object[][] courseNames = {{"course1"},{"course2"}};

        courseListModel = new DefaultTableModel(courseNames,colName);

        DefaultTableCellRenderer r   =   new   DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);

        this.courseList = new JTable(courseListModel);
        this.courseList.setRowHeight(30);

        courseList.setDefaultRenderer(Object.class,   r);

        JLabel lb1 = new JLabel("Course List",JLabel.CENTER);

        selectPanel.add(lb1,new GBC(0,0,1,1,0,0.08, GridBagConstraints.BOTH,  new Insets(20,0,20,0)));
        selectPanel.add(courseList,new GBC(0,1,1,1,1,0.92));
    }

    public void initMainPanel(){
        this.mainPanel.setLayout(new GridBagLayout());
        this.subMainPanel = new JPanel();
        this.subMainPanel.setBackground(Color.WHITE);
        subMainPanel.setBorder(BorderFactory.createEtchedBorder());

        initSubMainPanel();

        this.createNewBtn = new JButton("create new");
        this.templateBtn = new JButton("template");
        this.deleteBtn = new JButton("delete");

        mainPanel.add(subMainPanel, new GBC(0,0,4,1,4,9));
        mainPanel.add(createNewBtn, new GBC(0,1,1,1,1,1, GridBagConstraints.NONE, new Insets(20,0,20,0)));
        mainPanel.add(templateBtn, new GBC(1,1,1,1,1,1, GridBagConstraints.NONE,  new Insets(20,0,20,0)));
        mainPanel.add(new Label(""), new GBC(2,1,1,1,1,1, GridBagConstraints.NONE,  new Insets(20,0,20,0)));
        mainPanel.add(deleteBtn, new GBC(3,1,1,1,1,1, GridBagConstraints.NONE,  new Insets(20,0,20,0)));

    }

    public void initSubMainPanel(){
        subMainPanel.setLayout(new GridBagLayout());
        JLabel lb1 = new JLabel("course1", JLabel.CENTER);
        importBtn = new JButton("import");
        settingBtn = new JButton("setting");
        classReportBtn = new JButton("report");
        studentManageBtn = new JButton("student");
        gradeSelectedBtn = new JButton("grade");

        this.importBtn.setActionCommand("import");
        this.importBtn.addActionListener(this);

        CategoryTableModel myModel = new CategoryTableModel();

        // JTable
        categoryTable = new JTable(myModel);
        categoryTable.setDefaultRenderer(Object.class, tcr);
        categoryTable.setRowHeight(30);


        subMainPanel.add(lb1,new GBC(0,0,8,1,0.8,1,GridBagConstraints.BOTH,new Insets(20,0,0,0)));
        subMainPanel.add(importBtn, new GBC(8,0,1,1,0.1,1, GridBagConstraints.NONE, new Insets(20,0,0,0)));
        subMainPanel.add(settingBtn, new GBC(9,0,1,1,0.1,1, GridBagConstraints.NONE, new Insets(20,0,0,0)));
        subMainPanel.add(categoryTable,new GBC(0,1,14,1,1,10,GridBagConstraints.HORIZONTAL));
        subMainPanel.add(new Label(""), new GBC(0,2,7,1,0.7,1));
        subMainPanel.add(classReportBtn, new GBC(7,2,1,1,0.1,1, GridBagConstraints.NONE, new Insets(0,0,20,0)));
        subMainPanel.add(studentManageBtn, new GBC(8,2,1,1,0.1,1, GridBagConstraints.NONE, new Insets(0,0,20,0)));
        subMainPanel.add(gradeSelectedBtn, new GBC(9,2,1,1,0.1,1, GridBagConstraints.NONE, new Insets(0,0,20,0)));
//        subMainPanel.add(new Button("hello"), new GBC(0,3,1,1,0.1,1, GridBagConstraints.NONE));
//        subMainPanel.add(new Button("hello"), new GBC(1,3,1,1,0.1,1, GridBagConstraints.NONE));
//        subMainPanel.add(new Button("hello"), new GBC(2,3,1,1,0.1,1, GridBagConstraints.NONE));
//        subMainPanel.add(new Button("hello"), new GBC(3,3,1,1,0.1,1, GridBagConstraints.NONE));
//        subMainPanel.add(new Button("hello"), new GBC(4,3,1,1,0.1,1, GridBagConstraints.NONE));
//        subMainPanel.add(new Button("hello"), new GBC(5,3,1,1,0.1,1, GridBagConstraints.NONE));
//        subMainPanel.add(new Button("hello"), new GBC(6,3,1,1,0.1,1, GridBagConstraints.NONE));
//        subMainPanel.add(new Button("hello"), new GBC(7,3,1,1,0.1,1, GridBagConstraints.NONE));
//        subMainPanel.add(new Button("hello"), new GBC(8,3,1,1,0.1,1, GridBagConstraints.NONE));
//        subMainPanel.add(new Button("hello"), new GBC(9,3,1,1,0.1,1, GridBagConstraints.NONE));
    }

    public void updateCourseList(Object[][] courseNames){
        String[] colName = {"course"};
        this.courseListModel.setDataVector(courseNames,colName);
        this.courseListModel.fireTableDataChanged();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("import")){
            //
            Object[][] courseNames = {{"demo1"},{"demo2"}};
            updateCourseList(courseNames);
        }
    }
}

