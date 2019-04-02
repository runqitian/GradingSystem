package gui.frames;

import gui.panels.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

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
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set up panels
        loginPanel = new LoginPanel();

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
