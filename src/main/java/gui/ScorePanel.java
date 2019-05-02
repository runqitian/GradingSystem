package gui;

import main.Main;
import tools.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorePanel extends JPanel implements ActionListener {

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

    JScrollPane gradingPane;
    JTable gradingTable;

    public ScorePanel(API api){
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
        gradingTable = new JTable(new MyTableModel());
        gradingPane = new JScrollPane(gradingTable);

        Tools.beautifyJTable(gradingTable);

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

        tablePanel.setLayout(new GridBagLayout());
        tablePanel.add(gradingPane, new GBC(0,0,1,1,1,1));


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
