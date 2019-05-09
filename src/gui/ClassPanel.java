package gui;

import core.Course;
import jdk.nashorn.internal.scripts.JO;
import tools.Tools;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Vector;

public class ClassPanel extends JPanel implements ActionListener{

    API api;

    JPanel selectPanel;
    JPanel mainPanel;
    JTable courseListTable;
    JPanel subMainPanel;
    JTextField courseInput;
    JButton createNewBtn;
    JButton templateBtn;
    JButton deleteBtn;
    JButton importBtn;
    JButton settingBtn;
    JTable categoryTable;
    JScrollPane categoryPane;
    JButton classReportBtn;
//    JButton studentManageBtn;
    JButton gradeSelectedBtn;

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

        courseInput = new JTextField();
        createNewBtn = new JButton("create new");
        templateBtn = new JButton("template");
        deleteBtn = new JButton("delete");


        courseTitle = new JLabel("course1");
        importBtn = new JButton("import student");
        settingBtn = new JButton("setting");
        classReportBtn = new JButton("report");
//        studentManageBtn = new JButton("student");
        gradeSelectedBtn = new JButton("grade");
        categoryModel = new MyTableModel();
        categoryTable = new JTable(categoryModel);
        this.categoryPane = new JScrollPane(categoryTable);

        selectPanel.setLayout(new GridBagLayout());
        mainPanel.setLayout(new GridBagLayout());
        subMainPanel.setLayout(new GridBagLayout());

        initSelectPanel();
        initMainPanel();
        this.add(selectPanel,new GBC(0,0,1,1,0.2,1));
        this.add(mainPanel,new GBC(1,0,1,1,0.8,1));
    }

    public void initSelectPanel(){

        this.selectPanel.setBackground(Color.LIGHT_GRAY);
        courseListLabel.setFont(new Font("TimesRoman", Font.PLAIN, 32));

        Vector<Object> colName = new Vector<Object>();
        Vector<Vector<Object>> courseNames = new Vector<Vector<Object>>();
        Integer[] notEditable = {0};
        courseListModel.setDataVector(courseNames, colName,notEditable);
//        courseListModel.fireTableDataChanged();
        Tools.beautifyJTable(courseListTable, true, 25,30);
        courseListTable.setBackground(Color.LIGHT_GRAY);

        this.courseListTable.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(MouseEvent ev){
                changeCourse();
            }
        });

        selectPanel.add(courseListLabel,new GBC(0,0,1,1,0,0.08, GridBagConstraints.NONE,GridBagConstraints.CENTER,new Insets(5,0,0,0)));
        selectPanel.add(courseListTable,new GBC(0,1,1,1,1,0.92));
    }

    public void initMainPanel(){

        initSubMainPanel();

//        courseInput.setPreferredSize(new Dimension(60,40));
        courseInput.setMinimumSize(new Dimension(120,30));
        createNewBtn.setPreferredSize(new Dimension(60,40));
        templateBtn.setPreferredSize(new Dimension(60,40));
        deleteBtn.setPreferredSize(new Dimension(60,40));

        createNewBtn.setActionCommand("create_course");
        createNewBtn.addActionListener(this);
        deleteBtn.setActionCommand("delete_course");
        deleteBtn.addActionListener(this);
        templateBtn.setActionCommand("create_course_template");
        templateBtn.addActionListener(this);

        mainPanel.add(subMainPanel, new GBC(0,0,5,1,5,9));
        mainPanel.add(courseInput, new GBC(0,1,1,1,1,1, GridBagConstraints.NONE, new Insets(20,0,20,0)));
        mainPanel.add(createNewBtn, new GBC(1,1,1,1,1,1, GridBagConstraints.NONE, new Insets(20,0,20,0)));
        mainPanel.add(templateBtn, new GBC(2,1,1,1,1,1, GridBagConstraints.NONE,  new Insets(20,0,20,0)));
        mainPanel.add(new Label(""), new GBC(3,1,1,1,1,1, GridBagConstraints.NONE,  new Insets(20,0,20,0)));
        mainPanel.add(deleteBtn, new GBC(4,1,1,1,1,1, GridBagConstraints.NONE,  new Insets(20,0,20,0)));

    }

    public void initSubMainPanel(){

        this.courseTitle.setFont(new Font("TimesRoman", Font.PLAIN, 35));

        this.importBtn.setPreferredSize(new Dimension(120,40));
        this.importBtn.setActionCommand("import_student");
        this.importBtn.addActionListener(this);

        this.settingBtn.setPreferredSize(new Dimension(120,40));
        this.classReportBtn.setPreferredSize(new Dimension(120,40));
//        this.studentManageBtn.setPreferredSize(new Dimension(120,40));
        this.gradeSelectedBtn.setPreferredSize(new Dimension(120,40));

        this.gradeSelectedBtn.setActionCommand("grade_selected");
        this.gradeSelectedBtn.addActionListener(this);

        this.settingBtn.setActionCommand("set_weight");
        this.settingBtn.addActionListener(this);

        this.classReportBtn.setActionCommand("report");
        this.classReportBtn.addActionListener(this);

        Tools.beautifyJTable(categoryTable,true,25,30);
        this.categoryPane.setPreferredSize(new Dimension(600,400));


        subMainPanel.add(courseTitle,new GBC(0,0,8,1,0.8,1,GridBagConstraints.NONE,GridBagConstraints.LINE_START,new Insets(5,60,0,0)));
        subMainPanel.add(importBtn, new GBC(8,0,1,1,0.1,1, GridBagConstraints.NONE, new Insets(20,0,0,0)));
        subMainPanel.add(settingBtn, new GBC(9,0,1,1,0.1,1, GridBagConstraints.NONE, new Insets(20,0,0,0)));
        subMainPanel.add(categoryPane,new GBC(0,1,10,1,1,10,GridBagConstraints.BOTH));
        subMainPanel.add(new Label(""), new GBC(0,2,7,1,0.7,1));
        subMainPanel.add(classReportBtn, new GBC(8,2,1,1,0.1,1, GridBagConstraints.NONE, new Insets(0,0,20,0)));
//        subMainPanel.add(studentManageBtn, new GBC(8,2,1,1,0.1,1, GridBagConstraints.NONE, new Insets(0,0,20,0)));
        subMainPanel.add(gradeSelectedBtn, new GBC(9,2,1,1,0.1,1, GridBagConstraints.NONE, new Insets(0,0,20,0)));

    }

    public void refreshPage(Course course){
        Integer[] notEditable = {0};
        this.courseListModel.setDataVector(api.getCourseNameList(),api.getCourseNamelistHeader(),notEditable);
        this.courseTitle.setText(course.getName());
        Integer[] ne = {0,1};
        Class[] types = {Object.class,Object.class,Boolean.class};
        this.categoryModel.setDataVector(course.getSelecteSubCategoryTableModelData(),course.getSelecteSubCategoryTableModelHeader(),ne,types);

    }

    public void changeCourse(){
        String name = this.courseListTable.getValueAt(this.courseListTable.getSelectedRow(),0).toString();
        api.changeCourse(name);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("set_weight")){
            api.classToWeightPanel();
        }
        else if (e.getActionCommand().equals("grade_selected")){
            Vector<String> result = new Vector<String>();
            Vector<Integer> rows = new Vector<Integer>();
            for (int i=0; i< categoryModel.getRowCount(); i++){
                if (new Boolean(categoryModel.getValueAt(i,2).toString())){
                    rows.add(i);
                }
            }
            for (int row : rows){
                result.add(this.categoryModel.getValueAt(row,1).toString());
            }
//            System.out.println("class grade selected" + result);
            api.gradeSelected(result);
        }
        else if (e.getActionCommand().equals("import_student")){
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jfc.showDialog(new JLabel(), "选择");
            File file=jfc.getSelectedFile();
            System.out.println(file.getAbsoluteFile());
            api.importStudents(file.getAbsolutePath());
            JOptionPane.showMessageDialog(this,"import students successfully!");
        }
        else if (e.getActionCommand().equals("report")){
            api.generateReport();
            JOptionPane.showMessageDialog(this,"generate report successfully!");
        }
        else if (e.getActionCommand().equals("create_course")){
            String courseName = this.courseInput.getText();
            api.createCourse(courseName);
            Integer[] notEditable = {0};
            this.courseListModel.setDataVector(api.getCourseNameList(),api.getCourseNamelistHeader(),notEditable);
            JOptionPane.showMessageDialog(this,"create course successfully!");
        }
        else if (e.getActionCommand().equals("delete_course")){
            api.deleteCourse();
            JOptionPane.showMessageDialog(this,"delete course successfully!");
        }
        else if (e.getActionCommand().equals("create_course_template")){
            api.createFromTemplate(this.courseInput.getText());
            JOptionPane.showMessageDialog(this,"create from template successfully!");
        }
    }
}

