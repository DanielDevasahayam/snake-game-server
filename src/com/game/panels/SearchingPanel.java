package com.game.panels;

import com.game.network.WebSocketClient;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SearchingPanel extends JPanel {

    private JLabel statusLabel;
    private Timer timer;
    private int dotCount = 0;
    private JLabel gifLabel;

    public SearchingPanel() {

        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30)); // dark theme

        // ---- Status Label ----
        statusLabel = new JLabel("Searching for players");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 22));

//        loadGif();  // load GIF properly

        // ---- Layout Setup ----
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridy = 0;
        add(statusLabel, gbc);

        gbc.gridy = 1;
//        add(gifLabel, gbc);

        startDotAnimation();
        searchForPlayers();
    }

    public void searchForPlayers() {
        new WebSocketClient().connect();
    }




    private void loadGif() {
        try {
            String gifUrlString = "https://media.tenor.com/On7kvXhzml4AAAAj/loading-gif.gif";

            URL gifUrl = new URL(gifUrlString);
            ImageIcon gifIcon = new ImageIcon(gifUrl);

            gifLabel = new JLabel(gifIcon);

        } catch (Exception e) {
            e.printStackTrace();
            gifLabel = new JLabel("Loading...");
            gifLabel.setForeground(Color.WHITE);
        }
    }

    private void startDotAnimation() {
        timer = new Timer(450, e -> {
            dotCount = (dotCount + 1) % 4;
            String dots = ".".repeat(dotCount);
            statusLabel.setText("Searching for players" + dots);
        });
        timer.start();
    }
}
