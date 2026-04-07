package com.game.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        // Frame settings
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Username
        panel.add(new JLabel("email:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        // Password
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::handleLogin);
        panel.add(new JLabel()); // empty
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        username = "player";
        password = "1234";
        // SIMPLE CHECK — integrate your DTO later
        if (username.equals("player") && password.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");

            // Close laogin
            this.dispose();

            // Open game
            new GameFrame();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid credentials!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
