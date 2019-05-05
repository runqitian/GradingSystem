package gui;

import core.GradingSystem;
import core.Student;
import core.SubCategory;
import org.omg.CORBA.OBJ_ADAPTER;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class API {
    private MainFrame mainFrame;
    private GradingSystem gradingSystem;

    public API(MainFrame mainFrame, GradingSystem gradingSystem){
        this.mainFrame = mainFrame;
        this.gradingSystem = gradingSystem;
    }

    public boolean login(String username, String password){
        return gradingSystem.login(username,password);
    }

    public Vector<String> getCourseNameList(){
        return gradingSystem.getCourseNameList();
    }



}
