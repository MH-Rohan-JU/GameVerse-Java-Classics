package GameVerseManager;

import DataBase.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class login extends JFrame implements ActionListener{
        JLabel userLabel, passLabel;
        JTextField userField;
        JPasswordField passField;
        JButton loginBtn, registerBtn;

        Connection conn;

        public login() {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_db", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            setTitle("Login");
            setSize(700, 600);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Set background image using setContentPane()
            ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/images/log_bg.jpg"));
            JLabel background = new JLabel(backgroundImage);
            setContentPane(background); // This sets the background correctly
            background.setLayout(null); // Must set layout here for absolute positioning


            int centerX = getWidth() / 2 - 100;


            userLabel = new JLabel("Username:");
            userLabel.setBounds(centerX - 130, 180, 150, 30);
            background.add(userLabel);

            userField = new JTextField();
            userField.setBounds(centerX, 180, 200, 30);
            background.add(userField);

            passLabel = new JLabel("Password:");
            passLabel.setBounds(centerX - 130, 230, 150, 30);
            background.add(passLabel);

            passField = new JPasswordField();
            passField.setBounds(centerX, 230, 200, 30);
            background.add(passField);

//new
            // Style buttons
            loginBtn = new JButton("Login");
            loginBtn.setBounds(centerX + 50, 300, 100, 40);
            loginBtn.setFocusPainted(false);
            loginBtn.setBackground(new Color(230, 240, 255));
            loginBtn.setForeground(Color.BLACK);
            loginBtn.addActionListener(this);
            background.add(loginBtn);

            registerBtn = new JButton("Register");
            registerBtn.setBounds(centerX + 30, 360, 140, 40);
            registerBtn.setFocusPainted(false);
            registerBtn.setBackground(new Color(200, 220, 255));
            registerBtn.setForeground(Color.BLACK);
            registerBtn.addActionListener(this);
            background.add(registerBtn);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginBtn) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                try {
                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Login Successful!");
                        dispose();

                        // fetch the full “name” column for display
                        String fullName = DatabaseManager.getName();
                        if (fullName == null) {
                            fullName = username;  // fallback
                        }

                        new gameMenu(fullName);

                } else {
                        JOptionPane.showMessageDialog(this, "Invalid Credentials!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == registerBtn) {
                dispose();
                new registration();

        }
    }
}
