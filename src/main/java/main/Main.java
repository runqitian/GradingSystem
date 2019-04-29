package main;

import controllers.Controller;
import core.GradingSystem;
import gui.MainFrame;
import core.DatabaseStorage;

public class Main {

    public static void main(String[] args) {
        GradingSystem gradingSystem = new GradingSystem();
        MainFrame mainFrame = new MainFrame();
//        Controller controller = new Controller(mainFrame, gradingSystem);
//        mainFrame.test();
        DatabaseStorage.connectToDB();

    }

}