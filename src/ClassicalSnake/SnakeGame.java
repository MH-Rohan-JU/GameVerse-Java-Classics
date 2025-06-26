package ClassicalSnake;

import javax.swing.*;

public class SnakeGame{
    public SnakeGame(){
        JFrame frame=new JFrame("Classical Snake Game");
       // GameBoard board=new GameBoard(new GameController());

        //Snake menu
        SnakeMenu smenu = new SnakeMenu(frame);
        frame.setContentPane(smenu);
        frame.pack();

        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

    }
}
