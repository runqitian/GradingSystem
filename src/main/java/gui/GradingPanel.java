package gui;

import com.sun.deploy.panel.JreTableModel;
import core.Student;
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
    JLabel scoreLabel;
    JComboBox method;
    JButton weightBtn;
    JButton backBtn;
    JButton saveBtn;

    JScrollPane gradingPane;
    JTable gradingTable;
    MyTableModel gradingModel;
    Vector<Integer> notEditable;
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
        nameLabel  = new JLabel("name");
        emailLabel = new JLabel("email");
        commentArea = new JTextArea();
        commentPane = new JScrollPane(commentArea);
        commentField = new JTextField();
        commentBtn = new JButton("comment");
        scoreLabel = new JLabel("score");
        method = new JComboBox();
        weightBtn = new JButton("change weight");
        backBtn = new JButton("back");
        saveBtn = new JButton("save");
        gradingModel = new MyTableModel();
        notEditable = new Vector<Integer>();
        gradingTable = new JTable(gradingModel);
        gradingPane = new JScrollPane(gradingTable);
        notEditable.add(0);
        this.studentVector = new Vector<Student>();
        gradeCache = new Vector<Vector<Object>>();
        headerCache = new Vector<Object>();

        Tools.beautifyJTable(gradingTable, true, 20,50);

        infoPanel.setBackground(Color.LIGHT_GRAY);
        tablePanel.setBackground(Color.GRAY);
//        tablePanel.setBorder(BorderFactory.createEtchedBorder());
        this.add(infoPanel,new GBC(0,0,1,1,0.2,1));
        this.add(tablePanel,new GBC(1,0,1,1,0.8,1));

        nameLabel.setFont(new Font("TimesRoman", Font.BOLD, 30));
        emailLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        commentArea.setEditable(false);
        commentPane.setPreferredSize(new Dimension(60,160));
        commentField.setPreferredSize(new Dimension(60,30));
        commentBtn.setPreferredSize(new Dimension(45,30));
        scoreLabel.setFont(new Font("TimesRoman", Font.BOLD, 40));
        method.setPreferredSize(new Dimension(60,40));
        weightBtn.setPreferredSize(new Dimension(60,30));
        backBtn.setPreferredSize(new Dimension(60,30));
        saveBtn.setPreferredSize(new Dimension(60,30));

        backBtn.setActionCommand("back");
        backBtn.addActionListener(this);
        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(this);

        infoPanel.setLayout(new GridBagLayout());
        infoPanel.add(nameLabel,new GBC(0,0,1,1,1,0.05,GridBagConstraints.NONE, GridBagConstraints.CENTER));
        infoPanel.add(emailLabel, new GBC(0,1,1,1,1,0.05,GridBagConstraints.NONE, GridBagConstraints.CENTER));
        infoPanel.add(commentPane, new GBC(0,2,1,1,1,0.4,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,5,0,5)));
        infoPanel.add(commentField, new GBC(0,2,1,1,1,0.4,GridBagConstraints.HORIZONTAL,GridBagConstraints.CENTER, new Insets(190,5,0,5)));
        infoPanel.add(commentBtn, new GBC(0,2,1,1,1,0.4,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(250,15,0,15)));
        infoPanel.add(scoreLabel, new GBC(0,3,1,1,1,0.1,GridBagConstraints.NONE, GridBagConstraints.CENTER));
        infoPanel.add(method, new GBC(0,4,1,1,1,0.1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,15,0,15)));
        infoPanel.add(weightBtn, new GBC(0,5,1,1,1,0.1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,15,0,15)));
        infoPanel.add(weightBtn, new GBC(0,6,1,1,1,0.1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,15,0,15)));
        infoPanel.add(backBtn, new GBC(0,7,1,1,1,0.1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,15,0,15)));
        infoPanel.add(saveBtn, new GBC(0,8,1,1,1,0.1,GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0,15,0,15)));


        tablePanel.setLayout(new GridBagLayout());
        tablePanel.add(gradingPane, new GBC(0,0,1,1,1,1));

        gradingTable.addMouseListener(new java.awt.event.MouseAdapter(){public void mouseClicked(MouseEvent ev){

            Student selectedSt = students.get(gradingTable.getSelectedRow());

            Double maxScore = api.getMaxScore(gradingModel.getColumnName(gradingTable.getSelectedColumn()));
            String name = selectedSt.getName();
            String email = selectedSt.getEmail();
            String comment = selectedSt.getStuComment();
            String scoreDetail = gradingModel.getValueAt(gradingTable.getSelectedRow(),gradingTable.getSelectedColumn()).toString() + "/" + maxScore.toString();
            nameLabel.setText(name);
            emailLabel.setText(email);
            commentArea.setText(comment);
            scoreLabel.setText(scoreDetail);

        }


    });

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
        this.gradingModel.setDataVector(data,header,this.notEditable);
    }

    public void showPanel(Vector<Vector<Object>> data, Vector<Object> header,Vector<Student> students){
        this.students = students;
        refreshPage(data,header);
        this.setVisible(true);
        this.setEnabled(true);
    }

    public void hidePanel(){
        this.setVisible(false);
        this.setEnabled(false);
    }

    public void refreshPage(Vector<Vector<Object>> data, Vector<Object> header){
        this.gradingModel.setDataVector(data,header,new Vector<Integer>());

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("update_table")){
//            api.getGradingData

        }
        else if (e.getActionCommand().equals("update_info_by_selected")) {
//            Student selectedSt = students.get(gradingTable.getSelectedRow());
//
//            Double maxScore = api.getMaxScore(this.gradingModel.getColumnName(gradingTable.getSelectedColumn()));
//            String name = selectedSt.getName();
//            String email = selectedSt.getEmail();
//            String comment = "";
//            String scoreDetail = gradingModel.getValueAt(gradingTable.getSelectedRow(),gradingTable.getSelectedColumn()).toString() + "/" + maxScore.toString();
//            this.nameLabel.setText(name);
//            this.emailLabel.setText(email);
//            this.commentArea.setText(comment);
//            this.scoreLabel.setText(scoreDetail);

        }
        else if(e.getActionCommand().equals("back")){
            api.gradingToClassPanel();
        }
        else if(e.getActionCommand().equals("save")){
            HashMap<String,Vector<Object>> output = new HashMap<String,Vector<Object>>();
            Vector<Vector<Object>> processed = new Vector<Vector<Object>>();
            for (int i=1; i<gradingModel.getColumnCount(); i++){
                Vector<Object> objs = new Vector<Object>();
                processed.add(objs);
                output.put(gradingModel.getColumnName(i).toString(),objs);
            }
            Vector<Vector<Object>> data = gradingModel.getDataVector();
            for (int i=0; i<data.size(); i++){
                Vector<Object> row = data.get(i);
                for (int j=1; j<row.size(); j++){
                    processed.get(j-1).add(data.get(i).get(j));
                }
            }
            System.out.println("the shape of data");
            System.out.println(processed.size());
            System.out.println(processed.get(0).size());
            api.saveGrading(output);
        }
    }
}
