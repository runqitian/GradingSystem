package gui;

import javax.swing.*;
import java.awt.*;

public class ChartPanel extends JPanel {

    GridBagConstraints gbc = new GridBagConstraints();

    public ChartPanel(){
        this.setBackground(Color.BLUE);
        this.setLayout(new GridBagLayout());
        init();
    }

    public void init(){
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.RED);
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.GRAY);
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.CYAN);
        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.GREEN);
        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.RED);

        JPanel panel6 = new JPanel();
        panel6.setBackground(Color.YELLOW);
        JPanel panel7 = new JPanel();
        panel7.setBackground(Color.BLACK);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        this.add(infoPanel, gbc);
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        gbc.gridwidth = 2;
//        gbc.weightx = 1;
//        this.add(tablePanel, gbc);
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.gridx = 3;
//        gbc.gridy = 0;
//        gbc.gridwidth = 1;
//        gbc.weightx = 2;
//        this.add(panel3, gbc);
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.weightx = 1;
//        gbc.gridwidth = 1;
//        this.add(panel4, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(panel5, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(panel6, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 3;
        gbc.gridy = 1;
        this.add(panel7, gbc);
    }
}
