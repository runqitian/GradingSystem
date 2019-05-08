package gui;


import core.GradingSystem;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // API class
    API api;

    // window size and postion
    private final static int window_width = 900;
    private final static int window_height = 540;
    private static int window_x;
    private static int window_y;

    //panels included
    public LoginPanel loginPanel;
    public ClassPanel classPanel;
    public WeightPanel weightPanel;
    public GradingPanel gradingPanel;


    // put the window to central on your computer
    static{
        initWindowPosition();
    }

    public MainFrame(GradingSystem gradingSystem){

        // initialize api
        api = new API(this, gradingSystem);

        // window setttings
        this.setBounds(window_x, window_y, window_width, window_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addPanels();
    }


    public void addPanels(){
        //set up panels
        loginPanel = new LoginPanel(api);
        this.add(loginPanel);
        loginPanel.setBounds(0, 0, 900, 540);
        classPanel = new ClassPanel(api);
        this.add(classPanel);
        classPanel.setBounds(0, 0, 900, 540);
        weightPanel = new WeightPanel(api);
        this.add(weightPanel);
        weightPanel.setBounds(0,0,900,540);
        gradingPanel = new GradingPanel(api);
        this.add(gradingPanel);
        gradingPanel.setBounds(0,0,900,540);


        loginPanel.showPanel();
//        classPanel.showPanel();
//        weightPanel.showPanel();
//        gradingPanel.showPanel();

        this.setVisible(true);
        this.setEnabled(true);
    }

    // calculate the central position of the window based on your system
    public static void initWindowPosition(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        window_x = (screenWidth - window_width) /2;
        window_y = (screenHeight - window_height) / 2;
    }


}
