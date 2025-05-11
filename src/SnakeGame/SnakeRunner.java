package SnakeGame;

import javax.swing.*;

public class SnakeRunner {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        SnakeGame snakeGame = new SnakeGame();

        window.setTitle("Snake Game");
        window.setBounds(600, 200, 700, 600);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(snakeGame);
    }
}