package gui;

import java.awt.*;

public class GBC extends GridBagConstraints {

    public GBC(int gridx,int gridy){
        super( );
        this.fill = GridBagConstraints.BOTH;
        this.gridx = gridx;
        this.gridy = gridy;
    }

    public GBC(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty){
        super();
        this.fill = GridBagConstraints.BOTH;
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.weightx = weightx;
        this.weighty = weighty;
        this.anchor =  GridBagConstraints.CENTER;
    }

    public GBC(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill){
        super();
        this.fill = fill;
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.weightx = weightx;
        this.weighty = weighty;
        this.anchor =  GridBagConstraints.CENTER;
    }

    public GBC(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill, int anchor){
        super();
        this.fill = fill;
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.weightx = weightx;
        this.weighty = weighty;
        this.anchor =  anchor;
    }

    public GBC(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill, Insets insets){
        super();
        this.fill = fill;
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.weightx = weightx;
        this.weighty = weighty;
        this.anchor =  GridBagConstraints.CENTER;
        this.insets = insets;
    }

    public GBC(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill, int anchor, Insets insets){
        super();
        this.fill = fill;
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        this.weightx = weightx;
        this.weighty = weighty;
        this.anchor =  anchor;
        this.insets = insets;
    }


}
