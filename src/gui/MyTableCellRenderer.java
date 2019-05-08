package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyTableCellRenderer extends DefaultTableCellRenderer {

    public MyTableCellRenderer(){
        super();
        this.setHorizontalAlignment(JLabel.CENTER);
//        this.setFont(new Font("TimesRoman", Font.PLAIN, 50));
    }

    public MyTableCellRenderer(int fontSize){
        super();
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
    }



}
