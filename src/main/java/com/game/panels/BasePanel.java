package com.game.panels;

import javax.swing.*;

abstract public class BasePanel extends JPanel {
    final int screenwidth;
    final int screenheight;
    final int unitSize;
    final int delay;

    public BasePanel(int screenheight, int screenwidth, int unitSize, int delay) {
        this.screenheight = screenheight;
        this.screenwidth = screenwidth;
        this.unitSize = unitSize;
        this.delay = delay;
    }

}
