package com.game.panels;

import com.game.apiclient.ApiClient;
import com.game.dto.HTTPResponseDTO;
import com.game.dto.LoginRequestDTO;
import com.game.dto.UserDataDTO;
import com.game.frames.MainFrame;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class LoginPanel extends JPanel {

    private JTextField email;
    private JPasswordField password;

    public LoginPanel(MainFrame frame) {
        setLayout(new GridLayout(4, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        email = new JTextField();
        password = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        loginBtn.addActionListener(e -> {

            String email = this.email.getText();
            String password = new String(this.password.getPassword());

            LoginRequestDTO request = new LoginRequestDTO(email , password);
            System.out.println("email : " + email + " password : " + password);

            try {
                HTTPResponseDTO response = ApiClient.post(
                        "http://localhost:8080/auth/login",
                        request
                );
                JsonObject jsonObject = JsonParser.parseString(response.getMessage())
                        .getAsJsonObject();

                if (response != null && response.getStatusCode() == 200) {
                    JOptionPane.showMessageDialog(this, "Success");
                    frame.switchTo("MENU", UserDataDTO.builder()
                            .id(jsonObject.get("id").getAsLong())
                            .username(jsonObject.get("username").getAsString())
                            .build());
                } else {
                    JOptionPane.showMessageDialog(this, "Failure : " + response);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        registerBtn.addActionListener(e -> frame.switchTo("GAME", null));

        add(new JLabel("Username:"));
        add(email);
        add(new JLabel("Password:"));
        add(password);
        add(loginBtn);
        add(registerBtn);
    }

//    private void handleLogin(MainFrame frame) {
//        if (username.getText().equals("player") &&
//                new String(password.getPassword()).equals("1234")) {
//            frame.startGame();
//        } else {
//            JOptionPane.showMessageDialog(this, "Invalid Credentials!");
//        }
//    }
}
