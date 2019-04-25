package gui;

import controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // controller
    Controller controller;

    // window size and postion
    private final static int window_width = 900;
    private final static int window_height = 540;
    private static int window_x;
    private static int window_y;

    //panels included
    private LoginPanel loginPanel;


    // put the window to central on your computer
    static{
        initWindowPosition();
    }

    public MainFrame(){
        // window setttings
        this.setBounds(window_x, window_y, window_width, window_height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up panels
        loginPanel = new LoginPanel(controller);
        loginPanel.setBounds(0, 0, 500, 500);
        this.add(loginPanel);
        loginPanel.setEnabled(true);
        loginPanel.setVisible(true);
        System.out.println("this is MainFrame");
        this.setVisible(true);
        this.setEnabled(true);


    }

    public void setController(Controller controller){
        this.controller = controller;
        System.out.println("this is setController() in MainFrame");
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

    public void test(){
        System.out.println(controller.test());
    }

}
