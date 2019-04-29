package gui;

import main.Main;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    MainFrame mainFrame;

    public ScorePanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        init();
    }

    public void init(){
        this.setLayout(new GridBagLayout());
        JPanel infoPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        infoPanel.setBackground(Color.RED);
        tablePanel.setBackground(Color.BLUE);
        tablePanel.setBorder(BorderFactory.createEtchedBorder());
        this.add(infoPanel,new GBC(0,0,1,1,0.2,1));
        this.add(tablePanel,new GBC(1,0,1,1,0.8,1));
    }

}
