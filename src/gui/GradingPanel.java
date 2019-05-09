package gui;

import com.sun.deploy.panel.JreTableModel;
import core.Student;
import jdk.nashorn.internal.scripts.JO;
import main.Main;
import sun.rmi.server.InactiveGroupException;
import tools.Tools;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;
import java.awt.event.MouseEvent;

public class GradingPanel extends JPanel implements ActionListener {

    API api;

    JPanel infoPanel;
    JPanel tablePanel;
    JLabel nameLabel;
    JLabel emailLabel;
    JScrollPane commentPane;
    JTextArea commentArea;
    JTextField commentField;
    JButton commentBtn;
    JLabel totalScore;
    JLabel scoreLabel;
    JComboBox method;
    JButton backBtn;
    JButton saveBtn;

    JScrollPane gradingPane;
    JTable gradingTable;
    MyTableModel gradingModel;
    Vector<Student> studentVector;
    Vector<Vector<Object>> gradeCache;
    Vector<Object> headerCache;
    // "plus" "minus" "percentage"
    String gradeForm;

    Vector<Student> students = new Vector<Student>();

    public GradingPanel(API api){
        this.api = api;
        this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setEnabled(false);

        init();
    }

    public void init(){
        infoPanel = new JPanel();
        tablePanel = new JPanel();
        nameLabel  = new JLabel("");
        emailLabel = new JLabel("");
        commentArea = new JTextArea();
        commentPane = new JScrollPane(commentArea);
        commentField = new JTextField();
        commentBtn = new JButton("comment");
        totalScore = new JLabel("");
        scoreLabel = new JLabel("");
        method = new JComboBox();
        backBtn = new JButton("back");
        saveBtn = new JButton("save");
        gradingModel = new MyTableModel();
        gradingTable = new JTable(gradingModel);
        gradingPane = new JScrollPane(gradingTable);
        this.studentVector = new Vector<Student>();
        gradeCache = new Vector<Vector<Object>>();
        headerCache = new Vector<Object>();

        Tools.beautifyJTable(gradingTable, true, 20,50);

        infoPanel.setBackground(Color.LIGHT_GRAY);
        infoPanel.setMaximumSize(new Dimension(315,540));
        tablePanel.setBackground(Color.GRAY);
//        tablePanel.setBorder(BorderFactory.createEtchedBorder());
        this.add(infoPanel,new GBC(0,0,1,1,0.35,1));
        this.add(tablePanel,new GBC(1,0,1,1,0.65,1));

        nameLabel.setFont(new Font("TimesRoman", Font.BOLD, 25));
        nameLabel.setMaximumSize(new Dimension(0,40));
        emailLabel.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        emailLabel.setMaximumSize(new Dimension(0,40));
        commentArea.setEditable(false);
        commentPane.setPreferredSize(new Dimension(60,160));
        commentField.setPreferredSize(new Dimension(60,30));
        commentBtn.setPreferredSize(new Dimension(45,30));
        totalScore.setFont(new Font("TimesRoman", Font.BOLD, 25));
        totalScore.setMaximumSize(new Dimension(0,40));
        scoreLabel.setFont(new Font("TimesRoman", Font.BOLD, 25));
        scoreLabel.setMaximumSize(new Dimension(0,40));
        method.addItem("absolute");
        method.addItem("minus");
        method.addItem("percentage");
        method.setPreferredSize(new Dimension(60,40));
        backBtn.setPreferredSize(new Dimension(60,30));
        saveBtn.setPreferredSize(new Dimension(60,30));

        method.setActionCommand("grade_view");
        method.addActionListener(this);
        backBtn.setActionCommand("back");
        backBtn.addActionListener(this);
        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(this);
        commentBtn.setActionCommand("comment");
        commentBtn.addActionListener(this);

        infoPanel.setLayout(new GridBagLayout());
        infoPanel.add(nameLabel,new GBC(0,0,1,1,1,0.05,GridBagConstraints.NONE, GridBagConstraints.CENTER, new Insets(40,0,0,0)));
        infoPanel.add(emailLabel, new GBC(0,1,1,1,1,0.05,GridBagConstraints.NONE, GridBagConstraints.CENTER));
        infoPanel.add(totalScore, new GBC(0,2,1,1,1,0.1,GridBagConstraints.NONE, GridBagConstraints.CENTER));
        infoPanel.add(commentPane, new GBC(0,3,1,1,1,0.4,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,5,0,5)));
        infoPanel.add(commentField, new GBC(0,3,1,1,1,0.4,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, new Insets(190,5,0,5)));
        infoPanel.add(commentBtn, new GBC(0,3,1,1,1,0.4,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(250,15,0,15)));
        infoPanel.add(scoreLabel, new GBC(0,4,1,1,1,0.1,GridBagConstraints.NONE, GridBagConstraints.CENTER));
        infoPanel.add(method, new GBC(0,5,1,1,1,0.1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,15,0,15)));
        infoPanel.add(saveBtn, new GBC(0,6,1,1,1,0.1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,15,0,15)));
        infoPanel.add(backBtn, new GBC(0,7,1,1,1,0.1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,15,0,15)));



        tablePanel.setLayout(new GridBagLayout());
        tablePanel.add(gradingPane, new GBC(0,0,1,1,1,1));


        Tools.formattingGradingTable(gradingTable);
        gradingTable.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(MouseEvent ev){
                refreshStudentInfo();
            }
        });

    }

    public void refreshStudentInfo(){
        if (gradingTable.getSelectedColumn() == 0){
            return;
        }
        Student selectedSt = students.get(gradingTable.getSelectedRow());
        Double maxScore = api.getSubCategoryMaxScore(gradingModel.getColumnName(gradingTable.getSelectedColumn()));
        String name = selectedSt.getName();
        String email = selectedSt.getEmail();
        String comment = selectedSt.getComment();
        String scoreDetail = gradingModel.getValueAt(gradingTable.getSelectedRow(),gradingTable.getSelectedColumn()).toString() + "/" + maxScore.toString();
        nameLabel.setText(name);
        emailLabel.setText(email);
        commentArea.setText(comment);
        System.out.println(selectedSt.getTotalGrade());
        scoreLabel.setText(scoreDetail);
        totalScore.setText(selectedSt.getTotalGrade().toString());
        commentField.setText("");
    }

    public void transformGrade(String signal){

    }

    public void updateInfoPanelBySelected(){
        int rowIdx = this.gradingTable.getSelectedRow();
        int colIdx = this.gradingTable.getSelectedColumn();
        Student currentStudent = studentVector.get(rowIdx);
    }

    public void updateTable(Vector<Vector<Object>> data, Vector<Object> header){
        gradeCache = data;
        headerCache = header;
        Integer[] notEditable = {0};
        this.gradingModel.setDataVector(data,header,notEditable);
    }

    public void showPanel(){
        this.setVisible(true);
        this.setEnabled(true);
    }

    public void hidePanel(){
        this.setVisible(false);
        this.setEnabled(false);
    }

    public void refreshPage(Vector<Vector<Object>> data, Vector<Object> header, Vector<Student> students){
        this.students = students;
        Integer[] ne = {0};
        this.method.setSelectedIndex(0);
        this.gradingModel.setDataVector(data,header,ne);
        Tools.formattingGradingTable(gradingTable);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")){
            api.gradingToClassPanel();
        }
        else if (e.getActionCommand().equals("save")){
            gradingModel.fireTableDataChanged();
            Vector<Object> header = new Vector<Object>();
            Vector<String> subNames = new Vector<String>();
            for (int i=0; i<gradingModel.getColumnCount(); i++){
                if (i>0){
                    subNames.add(gradingModel.getColumnName(i));
                }
                header.add(gradingModel.getColumnName(i));
            }
            Vector<Double> maxGrades = api.getMaxGrades(subNames);
            Vector<Vector<Object>> data = this.gradingModel.getDataVector();
            if (method.getSelectedItem().equals("absolute")){
                api.saveGrading(data,header);
            }
            else if(method.getSelectedItem().equals("percentage")){
                for (int i=0; i<data.size(); i++){
                    for (int j=1; j<header.size(); j++){
                        data.get(i).set(j, Math.round(Double.parseDouble(data.get(i).get(j).toString())*maxGrades.get(j-1))/100.0);
                    }
                }
                api.saveGrading(data,header);
            }else if(method.getSelectedItem().equals("minus")){
                for (int i=0; i<data.size(); i++){
                    for (int j=1; j<header.size(); j++){
                        data.get(i).set(j, maxGrades.get(j-1)+Double.parseDouble(data.get(i).get(j).toString()));
                    }
                }
                api.saveGrading(data,header);
            }

        }
        else if (e.getActionCommand().equals("comment")){
            Student selectedSt = students.get(gradingTable.getSelectedRow());
            api.addComment(selectedSt.getId(),this.commentField.getText());
            refreshStudentInfo();
            JOptionPane.showMessageDialog(this,"add comment successfully!");
        }
        else if (e.getActionCommand().equals("grade_view")){
            String flag = this.method.getSelectedItem().toString();
            Vector<String> subNames = new Vector<String>();
            Vector<Object> header = new Vector<Object>();
            header.add("student");
            for (int i=1; i<gradingModel.getColumnCount(); i++){
                subNames.add(gradingTable.getColumnName(i));
                header.add(gradingTable.getColumnName(i));
            }
            Vector<Vector<Object>> data = api.getGradeData(subNames);
            Vector<Double> maxGrades = api.getMaxGrades(subNames);
            if (flag.equals("percentage")){
                for (int i=0; i<maxGrades.size(); i++){
                    Double maxGrade = maxGrades.get(i);
                    for (int j=0; j<data.size(); j++){
                        data.get(j).set(i+1,new Double(Math.round(Double.parseDouble(data.get(j).get(i+1).toString())/maxGrade*10000)/100.0));
                    }
                }
                Integer[] ne = {0};
                this.gradingModel.setDataVector(data,header,ne);
                Tools.formattingGradingTable(gradingTable);
            }
            else if (flag.equals("minus")){
                for (int i=0; i<maxGrades.size(); i++){
                    Double maxGrade = maxGrades.get(i);
                    for (int j=0; j<data.size(); j++){
                        data.get(j).set(i+1,Double.parseDouble(data.get(j).get(i+1).toString())-maxGrade);
                    }
                }
                Integer[] ne = {0};
                this.gradingModel.setDataVector(data,header,ne);
                Tools.formattingGradingTable(gradingTable);
            }
            else if (flag.equals("absolute")){
                Integer[] ne = {0};
                this.gradingModel.setDataVector(data,header,ne);
                Tools.formattingGradingTable(gradingTable);
            }

        }
    }
}
