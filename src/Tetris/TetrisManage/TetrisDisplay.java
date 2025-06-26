package Tetris.TetrisManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TetrisDisplay {
    private final TetrisPanel gamePanel = new TetrisPanel();
    private final JFrame TetrisFrame = new JFrame();

    public TetrisDisplay(){}
    public TetrisDisplay(Runnable onExit) {
        // Tetris Frame setup
        TetrisFrame.setTitle("Tetris");
        TetrisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        TetrisFrame.setResizable(false);

        TetrisFrame.setLayout(new BorderLayout());

        // Center: the game panel
        TetrisFrame.add(gamePanel, BorderLayout.CENTER);

        // South: exit button
        JPanel controlPanel = new JPanel();
        JButton exitBtn = new JButton("Quit");
        exitBtn.addActionListener((ActionEvent e) -> {
            gamePanel.stopGame();
            TetrisFrame.dispose();  // dispose instead of exit
        });
        controlPanel.add(exitBtn);
        TetrisFrame.add(controlPanel, BorderLayout.SOUTH);

        // Add listener to reopen GameVerseManager.gameMenu
        TetrisFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (onExit != null) {
                    onExit.run(); // reopen menu
                }
            }
        });

        // finalize
        TetrisFrame.pack();
        TetrisFrame.setLocationRelativeTo(null);
        TetrisFrame.setVisible(true);
        gamePanel.LaunchGame();
    }
}
