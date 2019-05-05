package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MyTableCellHeaderRenderer extends DefaultTableCellRenderer {

    public MyTableCellHeaderRenderer(){
        super();
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setBackground(Color.LIGHT_GRAY);
    }

}
