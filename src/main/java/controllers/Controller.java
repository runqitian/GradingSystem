package controllers;

import core.GradingSystem;
import gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    MainFrame mainFrame;
    GradingSystem gradingSystem;
    String helloWorld = "Hello, World!";

    public Controller(MainFrame mainFrame, GradingSystem gradingSystem){
        this.mainFrame = mainFrame;
        this.gradingSystem = gradingSystem;
        mainFrame.setController(this);
        System.out.println("this is controller");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // empty
    }

    public String test(){
        return helloWorld;
    }
}
