package gui;

import controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    // controller is an ActionListener
    Controller controller;

    // layout
    GridBagConstraints layout;

    // user
    JLabel userLabel;
    JTextField username;
    JLabel pwdLabel;
    JTextField pwd;

    public LoginPanel(Controller controller){
        this.controller = controller;
//        this.setLayout(new GridBagLayout());
        init();
//        this.setVisible(true);
//        this.setEnabled(true);
    }

    private void init(){
        // initialize layout
//        this.layout = new GridBagConstraints();
        this.setBackground(Color.BLUE);
//        this.setLayout(null);
//         initialize panel
        this.userLabel = new JLabel("user");
        this.username = new JTextField();
        this.pwdLabel = new JLabel("password");
        this.pwd = new JTextField();
//        this.add(userLabel);
//        this.add(pwd);
        JPanel inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(300,200));
        inputPanel.add(userLabel);
        inputPanel.add(username);
        inputPanel.add(pwdLabel);
        inputPanel.add(pwd);
        inputPanel.setBackground(Color.RED);
        inputPanel.setEnabled(true);
        inputPanel.setVisible(true);

        this.add(inputPanel);
        System.out.println("yes");


//        this.add(inputPanel);


    }



}
