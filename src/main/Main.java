package main;

import core.GradingSystem;
import data.Database;
import gui.MainFrame;

public class Main {

    public static void main(String[] args) {

        Database db = new Database();
        GradingSystem gradingSystem = new GradingSystem();
        MainFrame mainFrame = new MainFrame(gradingSystem);

    }

}
