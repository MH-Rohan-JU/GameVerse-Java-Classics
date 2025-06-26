package GameVerseManager;

import ClassicalSnake.SnakeMenu;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import DataBase.DatabaseManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


public class gameMenu extends JFrame implements ActionListener {

    // Buttons
    JButton snakeBtn, tetrisBtn, flappyBirdBtn, logoutBtn;
    JButton statisticsBtn;

    //Shared border for hover effect
    Border gameButtonBorder = BorderFactory.createLineBorder(Color.WHITE, 2);


    // Background Image
    JLabel backgroundLabel;

    private String playerName;
    public gameMenu(String fullName) {
        this.playerName = fullName;

        setTitle("Game Menu");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background image using setContentPane()
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/images/background.jpg")); // use absolute classpath
        JLabel background = new JLabel(backgroundImage);
        setContentPane(background); // This sets the background correctly
        background.setLayout(null); // Must set layout here for absolute positioning

        JLabel nameLabel = new JLabel("Welcome, " + playerName + "!");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
        nameLabel.setForeground(Color.BLUE);
        nameLabel.setBounds(0, 20, 800, 40); // Full width
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(nameLabel);


        // Snake Button
        ImageIcon snakeIcon = new ImageIcon(getClass().getResource("/images/snake_icon.png"));
        snakeBtn = new JButton(snakeIcon);
        snakeBtn.setBounds(80, 100, 200, 200);
        snakeBtn.setToolTipText("Play Snake");
        snakeBtn.setBorder(gameButtonBorder);
        snakeBtn.setContentAreaFilled(false);
        snakeBtn.setFocusPainted(false);
        snakeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        snakeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                snakeBtn.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                snakeBtn.setBorder(gameButtonBorder);
            }
        });

        snakeBtn.addActionListener(this);
        background.add(snakeBtn);

        // Tetris Button
        ImageIcon tetrisIcon = new ImageIcon(getClass().getResource("/images/tetris_icon.png"));
        tetrisBtn = new JButton(tetrisIcon);
        tetrisBtn.setBounds(285, 100, 200, 200);
        tetrisBtn.setToolTipText("Play Tetris");
        tetrisBtn.setBorder(gameButtonBorder);
        tetrisBtn.setContentAreaFilled(false);
        tetrisBtn.setFocusPainted(false);
        tetrisBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        tetrisBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tetrisBtn.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                tetrisBtn.setBorder(gameButtonBorder);
            }
        });
        tetrisBtn.addActionListener(this);
        background.add(tetrisBtn);

        // Flappy Bird Button
        ImageIcon flappyBirdIcon = new ImageIcon(getClass().getResource("/images/flappy_bird_icon.png"));
        flappyBirdBtn = new JButton(flappyBirdIcon);
        flappyBirdBtn.setBounds(490, 100, 200, 200);
        flappyBirdBtn.setToolTipText("Play Flappy Bird");
        flappyBirdBtn.setBorder(gameButtonBorder);
        flappyBirdBtn.setContentAreaFilled(false);
        flappyBirdBtn.setFocusPainted(false);
        flappyBirdBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        flappyBirdBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                flappyBirdBtn.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                flappyBirdBtn.setBorder(gameButtonBorder);
            }
        });
        flappyBirdBtn.addActionListener(this);
        background.add(flappyBirdBtn);

        // Common style
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 18);
        Color baseColor = new Color(60, 120, 200);
        Color hoverColor = new Color(90, 150, 255);

        // Common style
        Font cbuttonFont = new Font("Segoe UI", Font.BOLD, 18);
        Color cbaseColor = new Color(60, 120, 200);
        Color choverColor = new Color(90, 150, 255);

//  Statistics Button
        statisticsBtn = new JButton("Statistics");
        statisticsBtn.setBounds(300, 530, 200, 50);
        statisticsBtn.setFocusPainted(false);
        statisticsBtn.setFont(cbuttonFont);
        statisticsBtn.setForeground(Color.WHITE);
        statisticsBtn.setBackground(cbaseColor);
        statisticsBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        statisticsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        statisticsBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                statisticsBtn.setBackground(choverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                statisticsBtn.setBackground(cbaseColor);
            }
        });
        statisticsBtn.addActionListener(this);
        background.add(statisticsBtn);

//  Logout Button
        logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(300, 600, 200, 50);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setFont(cbuttonFont);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setBackground(new Color(180, 50, 50));
        logoutBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logoutBtn.setBackground(new Color(220, 80, 80));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logoutBtn.setBackground(new Color(180, 50, 50));
            }
        });
        logoutBtn.addActionListener(this);
        background.add(logoutBtn);


        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == snakeBtn) {
            dispose(); // Close game menu
            JFrame snakeFrame = new JFrame("Snake Game");
            snakeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            snakeFrame.setSize(800, 600);
            snakeFrame.setLocationRelativeTo(null);

            SnakeMenu snakeMenu = new SnakeMenu(snakeFrame);
            snakeFrame.setContentPane(snakeMenu);
            snakeFrame.setVisible(true);

            // Add window listener to reopen GameVerseManager.gameMenu when snake window is closed
            snakeFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    new gameMenu(DatabaseManager.getName());// reopen game menu
                }
            });

        } else if (e.getSource() == tetrisBtn) {
            dispose(); // Close game menu

            // Launch Tetris with callback to reopen menu on close
            SwingUtilities.invokeLater(() -> {
                new Tetris.TetrisManage.TetrisDisplay(() -> {
                    new gameMenu(getName()); // reopen game menu after Tetris closes
                });
            });

    } else if (e.getSource() == flappyBirdBtn) {
            dispose(); // Close Game Menu
            JFrame flappyFrame = new JFrame("Flappy Bird");
            flappyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            flappyFrame.setSize(800, 600);
            flappyFrame.setResizable(false);
            flappyFrame.setLocationRelativeTo(null);

            FlappyBird.GamePanel gamePanel = new FlappyBird.GamePanel(FlappyBird.MainMenu.getHighScore(), flappyFrame);
            flappyFrame.setContentPane(gamePanel);
            flappyFrame.setVisible(true);

            // When Flappy Bird window is closed, re-open Game Menu
            flappyFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    new gameMenu(getName());
                }
            });

        } else if (e.getSource() == statisticsBtn) {
            int snakeScore = DatabaseManager.getHighScore(playerName, "Snake");
            int tetrisScore = DatabaseManager.getHighScore(playerName, "Tetris");
            int flappyScore = DatabaseManager.getHighScore(playerName, "FlappyBird");

            // Create dataset
            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("Snake", 20);
            dataset.setValue("Tetris", 26);
            dataset.setValue("Flappy Bird", 13);

            // Create chart
            JFreeChart chart = ChartFactory.createPieChart(
                    "High Scores for " + playerName, dataset, true, true, false
            );

            // Create panel
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(500, 400));

            // Show in dialog
            JDialog dialog = new JDialog(this, "Game Statistics", true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setContentPane(chartPanel);
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        } else if (e.getSource() == logoutBtn) {
            // Logout and return to Login screen
            dispose();
            new login(); // Assuming the Login class is in the GameVerseManager.login package
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new gameMenu("k"));
    }
}