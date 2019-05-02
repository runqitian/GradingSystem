package gui;

import core.GradingSystem;
import tools.Tools;

import javax.swing.*;

public class API {
    private MainFrame mainFrame;
    private GradingSystem gradingSystem;

    public API(MainFrame mainFrame, GradingSystem gradingSystem){
        this.mainFrame = mainFrame;
        this.gradingSystem = gradingSystem;
    }

    public boolean login(String username, String password){
        boolean success = gradingSystem.login(username,password);
        if (success){
            // load and initialize
            mainFrame.loginPanel.hidePanel();
            mainFrame.classPanel.showPanel();
        }
        return success;
    }

    


}
