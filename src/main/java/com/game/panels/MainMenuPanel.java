package com.game.panels;

import com.game.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    private JButton startButton;
    private JToggleButton musicToggle;
    private JButton quitButton;

    private MainFrame mainFrame;
    public MainMenuPanel(MainFrame frame) {
        this.mainFrame = frame;
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;

        // Start Game Button
        startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(200, 40));
        startButton.addActionListener(e -> mainFrame.switchTo("SEARCH"));
        styleButton(startButton);
        gbc.gridy = 0;
        add(startButton, gbc);

        // Music Toggle Button
        musicToggle = new JToggleButton("Music: OFF");
        musicToggle.setPreferredSize(new Dimension(200, 40));
        styleButton(musicToggle);
        gbc.gridy = 1;
        add(musicToggle, gbc);

        // Quit Game Button
        quitButton = new JButton("Quit Game");
        quitButton.setPreferredSize(new Dimension(200, 40));
        styleButton(quitButton);
        gbc.gridy = 2;
        add(quitButton, gbc);

        // Toggle label
        musicToggle.addActionListener(e -> {
            if (musicToggle.isSelected())
                musicToggle.setText("Music: ON");
            else
                musicToggle.setText("Music: OFF");
        });

        // Quit Game Logic
        quitButton.addActionListener(e -> System.exit(0));
    }

    private void styleButton(AbstractButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
    }

    // Public getters
    public JButton getStartButton() {
        return startButton;
    }

    public JToggleButton getMusicToggle() {
        return musicToggle;
    }

    public JButton getQuitButton() {
        return quitButton;
    }
}
