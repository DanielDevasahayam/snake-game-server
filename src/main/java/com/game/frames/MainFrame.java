package com.game.frames;

import com.game.panels.GamePanel;
import com.game.panels.LoginPanel;
import com.game.panels.MainMenuPanel;
import com.game.panels.SearchingPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {

    private JPanel container;

    public MainFrame() {
        setTitle("Snake Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        container = new LoginPanel(this);
//        container = new TestingGamePanel("C:\\Daniel\\Tech\\SpringProjects\\spring-auth\\SnakeGame\\resources\\files\\Input.txt");
        add(container);
        setVisible(true);
    }

    public void switchTo(String name) {
        switch (name) {
            case "GAME" -> createGamePanel();
            case "LOGIN" -> createLoginPanel();
            case "SEARCH" -> createSearchingPanel();
            case "MENU" -> createMainMenuPanel();
        }
    }

    private void createMainMenuPanel() {
        this.add(new MainMenuPanel(this));
        this.remove(container);
        this.setTitle("Searching ... ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


    private void createLoginPanel() {

    }
    private void createSearchingPanel() {
        this.add(new SearchingPanel());
        this.remove(container);
        this.setTitle("Searching ... ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void createGamePanel() {
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
