package com.game.panels;

import com.game.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {

    public RegisterPanel(MainFrame frame) {
        setLayout(new GridLayout(5, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        JPasswordField confirm = new JPasswordField();

        JButton registerBtn = new JButton("Create Account");
        JButton backBtn = new JButton("Back");

        registerBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Account Created! Go login.")
        );
        backBtn.addActionListener(e -> frame.switchTo("LOGIN"));

        add(new JLabel("New Username:"));
        add(username);
        add(new JLabel("New Password:"));
        add(password);
        add(new JLabel("Confirm Password:"));
        add(confirm);
        add(registerBtn);
        add(backBtn);
    }
}
