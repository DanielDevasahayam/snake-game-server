package com.game.panels;

import com.game.dto.MatchResultDTO;
import com.game.dto.UserDataDTO;
import com.game.frames.MainFrame;
import com.game.network.CustomWebSocketClient;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

public class SearchingPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel statusLabel;
    private Timer timer;
    private int dotCount = 0;
    private JLabel gifLabel;

    private JLabel toastLabel;
    private UserDataDTO userDTO;
    private CustomWebSocketClient customWebSocketClient = new CustomWebSocketClient();

    public SearchingPanel(MainFrame mainFrame, UserDataDTO userDTO) {
        this.mainFrame = mainFrame;
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
        this.userDTO = userDTO;
        startDotAnimation();
        MatchResultDTO matchResultDTO = searchForPlayers();
        if (matchResultDTO != null) {
            statusLabel.setText("Match Found");
            statusLabel.setForeground(Color.GREEN);
            statusLabel.setFont(new Font("Arial", Font.BOLD, 22));
            try {
                Thread.sleep(2000);
            } catch (Exception e) {

            }
            SwingUtilities.invokeLater(() -> {
                mainFrame.remove(this);   // remove searching panel
                mainFrame.switchTo("GAME", userDTO);
            });

        }

    }


    public MatchResultDTO searchForPlayers() {
        try {
            return customWebSocketClient.connectToWebSocket(userDTO);

        } catch (Exception e) {

        }
        return null;
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
