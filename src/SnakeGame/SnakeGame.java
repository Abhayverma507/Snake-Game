package SnakeGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements KeyListener, ActionListener  {

    Timer timer;

    Deque<Rectangle> snakeArray = new LinkedList<>();

    String operation = " ";

    Random random = new Random();

    int fruitPosX = random.nextInt(500 ) +  100;

    int fruitPosY = random.nextInt(400 ) +  100;

    int score = 0;

    public SnakeGame() {
        snakeArray.add(new Rectangle(350, 300, 20, 20));
        snakeArray.add(new Rectangle(350, 322, 20, 20));
        snakeArray.add(new Rectangle(350, 344, 20, 20));
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(100, this);
        timer.start();
    }

    public void paint(Graphics g) {

        //background
        g.setColor(Color.black);
        g.fillRect(0, 0, 700, 600);

        // draw snake
        for(Rectangle rectangle : snakeArray) {
            g.setColor(Color.blue);
            g.fillRect(rectangle.x, rectangle.y, rectangle.height, rectangle.width);
        }

        // draw circle
        g.setColor(Color.red);
        g.fillOval(fruitPosX, fruitPosY, 20, 15);

//		g.setColor(Color.red);
//		g.fillOval(300, 100, 20, 15);
//
//		g.setColor(Color.red);
//		g.fillOval(300, 400, 20, 15);

        // score
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Your Score", 550, 60);

        // draw player scores which player earns point
        g.setColor(Color.PINK);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString(score + " ", 550, 100);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();


        // new Rectangle add in front of snake array during up button
        if(operation.equals("up")){
            Rectangle firstRectangleFromSnakeArray = snakeArray.getFirst();
            int xPosition = firstRectangleFromSnakeArray.x;
            int yPosition = firstRectangleFromSnakeArray.y;
            snakeArray.pollLast();
            Rectangle newRectangleForFirstPositionInSnakeArrayDuringUpClick = new Rectangle(
                    xPosition, yPosition - 22, 20, 20);
            snakeArray.addFirst(newRectangleForFirstPositionInSnakeArrayDuringUpClick);
        }
        // new Rectangle add in front of snake array during right button
        else if(operation.equals("right")){
            Rectangle firstRectangleFromSnakeArray = snakeArray.getFirst();
            int xPosition = firstRectangleFromSnakeArray.x;
            int yPosition = firstRectangleFromSnakeArray.y;
            snakeArray.pollLast();
            Rectangle newRectangleForFirstPositionInSnakeArrayDuringRightClick = new Rectangle(
                    xPosition + 22, yPosition, 20, 20);
            snakeArray.addFirst(newRectangleForFirstPositionInSnakeArrayDuringRightClick);
        }
        // new Rectangle add in front of snake array during left button
        else if(operation.equals("left")){
            Rectangle firstRectangleFromSnakeArray = snakeArray.getFirst();
            int xPosition = firstRectangleFromSnakeArray.x;
            int yPosition = firstRectangleFromSnakeArray.y;
            snakeArray.pollLast();
            Rectangle newRectangleForFirstPositionInSnakeArrayDuringLeftClick = new Rectangle(
                    xPosition - 22, yPosition, 20, 20);
            snakeArray.addFirst(newRectangleForFirstPositionInSnakeArrayDuringLeftClick);
        }
        // new Rectangle add in front of snake array during down button
        else if(operation.equals("down")){
            Rectangle firstRectangleFromSnakeArray = snakeArray.getFirst();
            int xPosition = firstRectangleFromSnakeArray.x;
            int yPosition = firstRectangleFromSnakeArray.y;
            snakeArray.pollLast();
            Rectangle newRectangleForFirstPositionInSnakeArrayDuringDownClick = new Rectangle(
                    xPosition, yPosition + 22, 20, 20);
            snakeArray.addFirst(newRectangleForFirstPositionInSnakeArrayDuringDownClick);
        }

        Rectangle firstRectangleFromSnakeArrayForEatingFruit = snakeArray.getFirst();
        int xFirstRectanglePosition = firstRectangleFromSnakeArrayForEatingFruit.x;
        int yFirstRectanglePosition = firstRectangleFromSnakeArrayForEatingFruit.y;

        // snake eats fruits
        if(firstRectangleFromSnakeArrayForEatingFruit.intersects(new Rectangle(fruitPosX, fruitPosY, 20, 15))){

            fruitPosX = random.nextInt(500 ) +  100;

            fruitPosY = random.nextInt(400 ) +  100;

            // new rectangle add in front of snake array with increase x-axis
            if(operation.equals("right")){
                Rectangle newRectangleForAddInSnakeArrayInFirstPosition = new Rectangle(
                        xFirstRectanglePosition + 22, yFirstRectanglePosition, 20, 20
                );
                snakeArray.addFirst(newRectangleForAddInSnakeArrayInFirstPosition);
            }
            // new rectangle add in front of snake array with decrease x-axis
            else if(operation.equals("left")){
                Rectangle newRectangleForAddInSnakeArrayInFirstPosition = new Rectangle(
                        xFirstRectanglePosition - 22, yFirstRectanglePosition, 20, 20
                );
                snakeArray.addFirst(newRectangleForAddInSnakeArrayInFirstPosition);
            }
            // new rectangle add in front of snake array with decrease y-axis
            else if(operation.equals("up")){
                Rectangle newRectangleForAddInSnakeArrayInFirstPosition = new Rectangle(
                        xFirstRectanglePosition, yFirstRectanglePosition - 22, 20, 20
                );
                snakeArray.addFirst(newRectangleForAddInSnakeArrayInFirstPosition);
            }
            // new rectangle add in front of snake array with increase y-axis
            else if(operation.equals("down")){
                Rectangle newRectangleForAddInSnakeArrayInFirstPosition = new Rectangle(
                        xFirstRectanglePosition, yFirstRectanglePosition + 22, 20, 20
                );
                snakeArray.addFirst(newRectangleForAddInSnakeArrayInFirstPosition);
            }
            score++;

        }

        // snake will stop when snake head will touch own body
        int a = 0;
        Rectangle firstRectangleFromSnakeArray = snakeArray.getFirst();
        for(Rectangle rectangle : snakeArray){

            if(a == 1 && firstRectangleFromSnakeArray.intersects(rectangle)){
                timer.stop();
            }else{
                a  = 1;
            }

        }

        // if snake will cross 700 x-axis snake position will start from 10 x-axis
        if(snakeArray.getFirst().x > 700){
            snakeArray.getFirst().x = 10;
        }
        // if snake will cross 10 x-axis snake position will start from 700 x-axis
        if(snakeArray.getFirst().x < 10){
            snakeArray.getFirst().x = 700;
        }
        // if snake will cross 10 y-axis snake position will start from 700 y-axis
        if(snakeArray.getFirst().y < 10){
            snakeArray.getFirst().y = 600;
        }
        // if snake will cross 700 y-axis snake position will start from 10 y-axis
        if(snakeArray.getFirst().y > 700){
            snakeArray.getFirst().y = 10;
        }




        repaint();

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_UP){
            // for check condition :-
            // down button doesn't work during up button
            if(!operation.equals("down")){
                operation = "up";
            }
        }
        else if(event.getKeyCode() == KeyEvent.VK_DOWN){
            // down can't click when game will start
            if(!operation.equals(" ")){
                // for check condition :-
                // up button doesn't work during down button
                if(!operation.equals("up")){
                    operation = "down";
                }
            }
        }
        else if(event.getKeyCode() == KeyEvent.VK_RIGHT){
            // for check condition :-
            // left button doesn't work during right button
            if(!operation.equals("left")){
                operation = "right";
            }
        }
        else if(event.getKeyCode() == KeyEvent.VK_LEFT){
            // for check condition :-
            // left button doesn't work during right button
            if(!operation.equals("right")){
                operation = "left";
            }
        }
        else{
            timer.stop();
        }

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }


}
