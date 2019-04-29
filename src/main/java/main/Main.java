package main;

import core.GradingSystem;
import core.SubCategory;
import gui.MainFrame;
import core.DatabaseStorage;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
//        GradingSystem gradingSystem = new GradingSystem();
//        MainFrame mainFrame = new MainFrame();
//        Controller controller = new Controller(mainFrame, gradingSystem);
//        GradingSystem gradingSystem = new GradingSystem();
//        MainFrame mainFrame = new MainFrame();
//        mainFrame.test();
        DatabaseStorage.connectToDB();
//        DatabaseStorage.addSubCategory("assignment2",30.00,10.00,new ArrayList<SubCategory>(),"hw",100.00,new HashMap<String, Double>());
//        DatabaseStorage.addSubCategory("exam1",30.00,10.00,new ArrayList<SubCategory>(),"exam",100.00,new HashMap<String, Double>());

//        System.out.println(DatabaseStorage.findSubCategory("assignment1", "hw"));
        System.out.println(DatabaseStorage.getAllSubCategories());

    }

}
