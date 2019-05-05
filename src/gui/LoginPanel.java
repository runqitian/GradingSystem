package gui;


import com.sun.corba.se.impl.ior.GenericIdentifiable;
import main.Main;
import sun.awt.image.GifImageDecoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {

    API api;

    // user
    JLabel userLabel;
    JTextField usernameInput;
    JLabel pwdLabel;
    JPasswordField pwdInput;
    JButton loginBtn;

    public LoginPanel(API api){
        this.setVisible(false);
        this.setEnabled(false);

        this.api = api;
//        this.setBackground(Color.CYAN);
        this.setLayout(new GridBagLayout());
        init();
    }

    public void showPanel(){
        this.setVisible(true);
        this.setEnabled(true);
    }

    public void hidePanel(){
        this.setVisible(false);
        this.setEnabled(false);
    }


    private void init(){

        userLabel = new JLabel("username");
        userLabel.setPreferredSize(new Dimension(100,30));
        usernameInput = new JTextField();
        usernameInput.setPreferredSize(new Dimension(200,30));
        pwdLabel = new JLabel("password");
        pwdLabel.setPreferredSize(new Dimension(100,30));
        pwdInput = new JPasswordField();
        pwdInput.setPreferredSize(new Dimension(200,30));
        loginBtn = new JButton("login");
        loginBtn.setActionCommand("login");
        loginBtn.addActionListener(this);

        this.add(userLabel, new GBC(0,0,1,1,0,0));
        this.add(usernameInput, new GBC(1,0,1,1,0,0));
        this.add(pwdLabel, new GBC(0,1,1,1,0,0));
        this.add(pwdInput, new GBC(1,1,1,1,0,0));
        this.add(loginBtn, new GBC(1,2,1,1,0,0, GridBagConstraints.NONE,GridBagConstraints.LINE_END));

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("login")){
            String password = new String(this.pwdInput.getPassword());
            boolean success = api.login(this.usernameInput.getText(),password);
            if (!success){
                JOptionPane.showMessageDialog(this,"login failed!");
            }
        }
    }
}
