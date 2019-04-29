package gui;

import controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    Controller controller;

    public ScorePanel(Controller controller){
        this.controller = controller;
        init();
    }

    public void init(){
        this.setLayout(new GridBagLayout());
        JPanel infoPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        infoPanel.setBackground(Color.RED);
        tablePanel.setBackground(Color.BLUE);
        this.add(infoPanel,new GBC(0,0,1,1,0.2,1));
        this.add(tablePanel,new GBC(1,0,1,1,0.8,1));
    }


}
