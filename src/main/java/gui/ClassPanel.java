package gui;

import core.Course;
import tools.Tools;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Vector;

public class ClassPanel extends JPanel implements ActionListener{

    API api;

    JPanel selectPanel;
    JPanel mainPanel;
    JTable courseListTable;
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
//    JFileChooser fileChooser;


    MyTableModel categoryModel;
    MyTableModel courseListModel;
    JLabel courseListLabel;
    JLabel courseTitle;



    public ClassPanel(API api){

        this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setEnabled(false);

        this.api = api;
        init();
    }

    public void showPanel(){
        this.setVisible(true);
        this.setEnabled(true);
        refreshPage();
    }

    public void hidePanel(){
        this.setVisible(false);
        this.setEnabled(false);
    }

    public void init(){

        selectPanel = new JPanel();
        mainPanel = new JPanel();

        courseListLabel = new JLabel("Course List");
        courseListModel = new MyTableModel();
        courseListTable = new JTable(courseListModel);

        subMainPanel = new JPanel();

        createNewBtn = new JButton("create new");
        templateBtn = new JButton("template");
        deleteBtn = new JButton("delete");


        courseTitle = new JLabel("course1");
        importBtn = new JButton("import");
        settingBtn = new JButton("setting");
        classReportBtn = new JButton("report");
        studentManageBtn = new JButton("student");
        gradeSelectedBtn = new JButton("grade");
        categoryModel = new MyTableModel();
        categoryTable = new JTable(categoryModel);



        selectPanel.setLayout(new GridBagLayout());
        mainPanel.setLayout(new GridBagLayout());
        subMainPanel.setLayout(new GridBagLayout());

        initSelectPanel();
        initMainPanel();
//        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        this.add(selectPanel,new GBC(0,0,1,1,0.2,1));
        this.add(mainPanel,new GBC(1,0,1,1,0.8,1));
    }

    public void initSelectPanel(){

        this.selectPanel.setBackground(Color.LIGHT_GRAY);
        courseListLabel.setFont(new Font("TimesRoman", Font.PLAIN, 32));

        Vector<Object> colName = new Vector<Object>();
        Vector<Vector<Object>> courseNames = new Vector<Vector<Object>>();
        colName.add("couse list");
        Vector<Object> course1 = new Vector<Object>();
        course1.add("Java");
        Vector<Object> course2 = new Vector<Object>();
        course2.add("Python");
        courseNames.add(course1);
        courseNames.add(course2);

        courseListModel.setDataVector(courseNames, colName,new Vector<Integer>());
//        courseListModel.fireTableDataChanged();
        Tools.beautifyJTable(courseListTable, true, 25,30);
        courseListTable.setBackground(Color.LIGHT_GRAY);

        selectPanel.add(courseListLabel,new GBC(0,0,1,1,0,0.08, GridBagConstraints.NONE,GridBagConstraints.CENTER,new Insets(5,0,0,0)));
        selectPanel.add(courseListTable,new GBC(0,1,1,1,1,0.92));
    }

    public void initMainPanel(){

        initSubMainPanel();

        createNewBtn.setPreferredSize(new Dimension(120,40));
        templateBtn.setPreferredSize(new Dimension(120,40));
        deleteBtn.setPreferredSize(new Dimension(120,40));

        mainPanel.add(subMainPanel, new GBC(0,0,4,1,4,9));
        mainPanel.add(createNewBtn, new GBC(0,1,1,1,1,1, GridBagConstraints.NONE, new Insets(20,0,20,0)));
        mainPanel.add(templateBtn, new GBC(1,1,1,1,1,1, GridBagConstraints.NONE,  new Insets(20,0,20,0)));
        mainPanel.add(new Label(""), new GBC(2,1,1,1,1,1, GridBagConstraints.NONE,  new Insets(20,0,20,0)));
        mainPanel.add(deleteBtn, new GBC(3,1,1,1,1,1, GridBagConstraints.NONE,  new Insets(20,0,20,0)));

    }

    public void initSubMainPanel(){

        this.courseTitle.setFont(new Font("TimesRoman", Font.PLAIN, 35));

        this.importBtn.setPreferredSize(new Dimension(120,40));
        this.importBtn.setActionCommand("import");
        this.importBtn.addActionListener(this);

        this.settingBtn.setPreferredSize(new Dimension(120,40));
        this.classReportBtn.setPreferredSize(new Dimension(120,40));
        this.studentManageBtn.setPreferredSize(new Dimension(120,40));
        this.gradeSelectedBtn.setPreferredSize(new Dimension(120,40));

        this.gradeSelectedBtn.setActionCommand("grade_selected");
        this.gradeSelectedBtn.addActionListener(this);

        this.settingBtn.setActionCommand("set_weight");
        this.settingBtn.addActionListener(this);

        Tools.beautifyJTable(categoryTable,true,25,30);


        subMainPanel.add(courseTitle,new GBC(0,0,8,1,0.8,1,GridBagConstraints.NONE,GridBagConstraints.LINE_START,new Insets(5,60,0,0)));
        subMainPanel.add(importBtn, new GBC(8,0,1,1,0.1,1, GridBagConstraints.NONE, new Insets(20,0,0,0)));
        subMainPanel.add(settingBtn, new GBC(9,0,1,1,0.1,1, GridBagConstraints.NONE, new Insets(20,0,0,0)));
        subMainPanel.add(categoryTable,new GBC(0,1,14,1,1,10,GridBagConstraints.HORIZONTAL));
        subMainPanel.add(new Label(""), new GBC(0,2,7,1,0.7,1));
        subMainPanel.add(classReportBtn, new GBC(7,2,1,1,0.1,1, GridBagConstraints.NONE, new Insets(0,0,20,0)));
        subMainPanel.add(studentManageBtn, new GBC(8,2,1,1,0.1,1, GridBagConstraints.NONE, new Insets(0,0,20,0)));
        subMainPanel.add(gradeSelectedBtn, new GBC(9,2,1,1,0.1,1, GridBagConstraints.NONE, new Insets(0,0,20,0)));

    }

    public void refreshPage(){
        Object[] tableData = api.loadCourseList();
        Vector<Vector<Object>> courseNamelist = (Vector<Vector<Object>>)tableData[0];
        Vector<Integer> ne1 = new Vector<Integer>();
        ne1.add(0);
        this.courseListModel.setDataVector((Vector<Vector<Object>>)tableData[0],(Vector<Object>)tableData[1],ne1);
        Object[] summaryTableData = api.loadCourseSummary(courseNamelist.get(0).get(0).toString());
        Vector<Integer> ne2 = new Vector<Integer>();
        ne2.add(0);
        ne2.add(1);
        Vector<Vector<Object>> data = (Vector<Vector<Object>>) summaryTableData[0];
        Vector<Object> header = (Vector<Object>) summaryTableData[1];
        for (Vector<Object> row : data){
            row.add(new Boolean(false));
        }
        header.add("checkbox");
        Vector<Class> typeArray = new Vector<Class>();
        typeArray.add(Object.class);
        typeArray.add(Object.class);
        typeArray.add(Boolean.class);
        this.categoryModel.setDataVector(data,header,ne2,typeArray);
        categoryModel.fireTableDataChanged();
        courseListModel.fireTableDataChanged();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("import")){

        }
        else if(e.getActionCommand().equals("refresh")){

        }else if(e.getActionCommand().equals("grade_selected")){
            Vector<String> result = new Vector<String>();
            Vector<Integer> rows = new Vector<Integer>();
            for (int i=0; i< categoryModel.getRowCount(); i++){
                if (new Boolean(categoryModel.getValueAt(i,2).toString())){
                    rows.add(i);
                }
            }
//            System.out.println("rows:" + rows.size());
            for (int row : rows){
                result.add(this.categoryModel.getValueAt(row,1).toString());
            }
//            System.out.println("class grade selected" + result);
            api.uploadGradingSelected(result);
        }
        else if(e.getActionCommand().equals("set_weight")){
            api.classToWeightPanel();
        }
    }
}

