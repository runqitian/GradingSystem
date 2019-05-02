package gui;

import tools.Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeightPanel extends JPanel implements ActionListener {

    API api;

    JPanel titlePanel;
    JTable cTable;
    JTable sTable;
    JScrollPane categoryPane;
    JScrollPane subcategoryPane;


    JPanel downPanel;
    JButton saveBtn;
    JButton cancelBtn;
    JTextField categoryInput;
    JButton categoryAddBtn;
    JComboBox subBelong;
    JTextField subInput;
    JButton subAddBtn;


    public WeightPanel(API api){
        this.api = api;

//        this.setBackground(Color.CYAN);
        this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setEnabled(false);
        init();
    }

    public void init(){
        MyTableModel cModel = new MyTableModel();
        MyTableModel sModel = new MyTableModel();

        titlePanel = new JPanel();
        cTable = new JTable(cModel);
        sTable = new JTable(sModel);
        downPanel = new JPanel();
        categoryInput = new JTextField();
        categoryAddBtn = new JButton("add");
        subBelong = new JComboBox();
        subInput = new JTextField();
        subAddBtn = new JButton("add");
        saveBtn = new JButton("save");
        cancelBtn = new JButton("cancel");

        saveBtn.setPreferredSize(new Dimension(70,40));
        cancelBtn.setPreferredSize(new Dimension(70,40));

        titlePanel.setBackground(Color.LIGHT_GRAY);
        titlePanel.setLayout(new GridBagLayout());
        JLabel titleLabel = new JLabel("Change Weight");
        titleLabel.setFont(new Font("TimesRoman", Font.BOLD, 35));
        titlePanel.add(titleLabel, new GBC(0,0,1,1,0.5,1,GridBagConstraints.NONE,GridBagConstraints.LINE_START, new Insets(0,60,0,0)));
        titlePanel.add(saveBtn, new GBC(1,0,1,1,0.5,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END, new Insets(0,0,0,170 )));
        titlePanel.add(cancelBtn, new GBC(1,0,1,1,0.5,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END, new Insets(0,0,0,70 )));
        Tools.beautifyJTable(cTable);
        Tools.beautifyJTable(sTable);
        categoryPane = new JScrollPane(cTable);
        subcategoryPane = new JScrollPane(sTable , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        categoryInput.setPreferredSize(new Dimension(120,35));
        categoryAddBtn.setPreferredSize(new Dimension(70,40));
        subInput.setPreferredSize(new Dimension(120,35));
        subAddBtn.setPreferredSize(new Dimension(70,40));
        subBelong.setPreferredSize(new Dimension(120,40));

//        downPanel.setBackground(Color.GRAY);
        downPanel.setLayout(new GridBagLayout());

        this.add(titlePanel,new GBC(0,0,2,1,1,0.2,GridBagConstraints.BOTH));
        this.add(categoryPane, new GBC(0,1,1,1,0.4,0.6,GridBagConstraints.BOTH,GridBagConstraints.CENTER, new Insets(10,55,10,55)));
        this.add(subcategoryPane, new GBC(1,1,1,1,0.6,0.6,GridBagConstraints.BOTH,GridBagConstraints.CENTER, new Insets(10,55,10,55)));

        downPanel.add(categoryInput, new GBC(0,0,1,1,0.4,1, GridBagConstraints.NONE, GridBagConstraints.LINE_END, new Insets(10,0,10,155)));
        downPanel.add(categoryAddBtn, new GBC(0,0,1,1,0.4,1,GridBagConstraints.NONE, GridBagConstraints.LINE_END, new Insets(10,0,10,55)));
        downPanel.add(subBelong, new GBC(1,0,1,1,0.6,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END,new Insets(10,0,10,285)));
        downPanel.add(subInput, new GBC(1,0,1,1,0.6,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END,new Insets(10,0,10,145)));
        downPanel.add(subAddBtn, new GBC(1,0,1,1,0.6,1,GridBagConstraints.NONE,GridBagConstraints.LINE_END,new Insets(10,0,10,55)));
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


    public void actionPerformed(ActionEvent e) {

    }
}
