package GameVerseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class registration extends JFrame implements ActionListener{

        JLabel nameLabel, userLabel, passLabel, emailLabel, ageLabel, genderLabel;
        JTextField nameField, userField, emailField, ageField;
        JPasswordField passField;
        JComboBox<String> genderBox;
        JButton registerBtn, backBtn;

        Connection conn;

    public registration() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game_db", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setTitle("Registration");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Set background image
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/images/log_bg.jpg")); // ensure image is in /images/
        JLabel background = new JLabel(backgroundImage);
        setContentPane(background);
        background.setLayout(null);

        // Shared styling constants
        int formWidth = 300;
        int labelX = 200;
        int fieldX = 300;
        int startY = 80;
        int gap = 45;
        int height = 30;

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // Name
        nameLabel = new JLabel("Name:", SwingConstants.RIGHT);
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(labelX, startY, 80, height);
        background.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(fieldFont);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setBounds(fieldX, startY, formWidth, height);
        background.add(nameField);

        // Username
        userLabel = new JLabel("Username:", SwingConstants.RIGHT);
        userLabel.setFont(labelFont);
        userLabel.setBounds(labelX, startY += gap, 80, height);
        background.add(userLabel);

        userField = new JTextField();
        userField.setFont(fieldFont);
        userField.setHorizontalAlignment(JTextField.CENTER);
        userField.setBounds(fieldX, startY, formWidth, height);
        background.add(userField);

        // Password
        passLabel = new JLabel("Password:", SwingConstants.RIGHT);
        passLabel.setFont(labelFont);
        passLabel.setBounds(labelX, startY += gap, 80, height);
        background.add(passLabel);

        passField = new JPasswordField();
        passField.setFont(fieldFont);
        passField.setHorizontalAlignment(JPasswordField.CENTER);
        passField.setBounds(fieldX, startY, formWidth, height);
        background.add(passField);

        // Email
        emailLabel = new JLabel("Email:", SwingConstants.RIGHT);
        emailLabel.setFont(labelFont);
        emailLabel.setBounds(labelX, startY += gap, 80, height);
        background.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(fieldFont);
        emailField.setHorizontalAlignment(JTextField.CENTER);
        emailField.setBounds(fieldX, startY, formWidth, height);
        background.add(emailField);

        // Age
        ageLabel = new JLabel("Age:", SwingConstants.RIGHT);
        ageLabel.setFont(labelFont);
        ageLabel.setBounds(labelX, startY += gap, 80, height);
        background.add(ageLabel);

        ageField = new JTextField();
        ageField.setFont(fieldFont);
        ageField.setHorizontalAlignment(JTextField.CENTER);
        ageField.setBounds(fieldX, startY, formWidth, height);
        background.add(ageField);

        // Gender
        genderLabel = new JLabel("Gender:", SwingConstants.RIGHT);
        genderLabel.setFont(labelFont);
        genderLabel.setBounds(labelX, startY += gap, 80, height);
        background.add(genderLabel);

        String[] genders = {"Male", "Female", "Other"};
        genderBox = new JComboBox<>(genders);
        genderBox.setFont(fieldFont);
        genderBox.setBounds(fieldX, startY, formWidth, height);
        background.add(genderBox);

        // Buttons
        registerBtn = new JButton("Register");
        registerBtn.setBounds(fieldX + 30, startY += gap + 20, 110, 40);
        registerBtn.setFocusPainted(false);
        registerBtn.setBackground(new Color(187, 222, 251)); // Light sky blue
        registerBtn.setForeground(Color.BLACK);
        registerBtn.addActionListener(this);
        background.add(registerBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(fieldX + 170, startY, 100, 40);
        backBtn.setFocusPainted(false);
        backBtn.setBackground(new Color(255, 213, 213)); // Soft pink
        backBtn.setForeground(Color.BLACK);
        backBtn.addActionListener(this);
        background.add(backBtn);

        setVisible(true);
    }


    @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registerBtn) {
                String name = nameField.getText();
                String username = userField.getText();
                String password = new String(passField.getPassword());
                String email = emailField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = (String) genderBox.getSelectedItem();

                try {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, username, password, email, age, gender) VALUES (?, ?, ?, ?, ?, ?)");
                    ps.setString(1, name);
                    ps.setString(2, username);
                    ps.setString(3, password);
                    ps.setString(4, email);
                    ps.setInt(5, age);
                    ps.setString(6, gender);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Registration Successful!");
                    dispose();
                    new login();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error: Username already exists.");
                }
            } else if (e.getSource() == backBtn) {
                dispose();
                new login();

        }
    }
}
