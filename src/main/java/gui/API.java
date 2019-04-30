package gui;

import core.GradingSystem;

import javax.swing.*;

public class API {
    private MainFrame mainFrame;
    private GradingSystem gradingSystem;

    public API(MainFrame mainFrame, GradingSystem gradingSystem){
        this.mainFrame = mainFrame;
        this.gradingSystem = gradingSystem;
    }

    public JTable loadGradingSummaryTable(){
        gradingSystem.getGradingSummary();
        return null;
    }

    public boolean login(String username, String password){
        boolean success = gradingSystem.login(username,password);
        if (success){
            System.out.println("yes");
            mainFrame.loginPanel.hidePanel();
            mainFrame.classPanel.showPanel();
        }
        return success;
    }
}
