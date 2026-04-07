package com.game.frames;


import com.game.panels.GamePanel;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {

        JPanel leftPanel = new GamePanel(600, 600,25, 75, "PLAYER1");
        JPanel rightPanel = new GamePanel(600, 600,25, 75, "PLAYER2");

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                leftPanel,
                rightPanel
        );

        //if I get a data, dont depend on user actions instead use the DTO
        //else depend on user actions

        splitPane.setDividerLocation(600); // initial split position
        splitPane.setResizeWeight(0.5);    // equal resizing

        this.add(splitPane);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
