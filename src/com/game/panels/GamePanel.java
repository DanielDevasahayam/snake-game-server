package com.game.panels;

import com.game.objects.Apple;
import objects.objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class GamePanel extends BasePanel implements ActionListener {

    private final String playerName;

    private final int GAME_UNITS = (this.screenwidth * this.screenheight) / this.unitSize;

    private int[] x = new int[GAME_UNITS];

    private int[] y = new int[GAME_UNITS];

    private int applesEaten;

    private Apple apple;

    private Snake snake;

    private boolean running = false;

    private Timer timer;

    private Random random;


    public GamePanel(int screenheight, int screenwidth, int unitSize, int delay, String playerName) {
        super(screenheight, screenwidth, unitSize, delay);
        this.apple = new Apple(0,0);
        this.snake = new Snake('R',2);
        this.playerName = playerName;
        runGame();
    }

//    public GamePanel(DataDTO dataDTO) {
//        this.copYourData(dataDTO);
//    }

//    private void copYourData(DataDTO dataDTO) {
//        this.unitSize = dataDTO.getUnitSize();
//        this.delay = dataDTO.getDelay();
//        this.apple = new Apple(dataDTO.(), dataDTO.getApplePosY());
//        this.snake = new Snake(dataDTO.getDirection(), dataDTO.getBodyParts());
//        this.playerName = dataDTO.getPlayerName();
//        this.runGame();
//    }

    public void runGame() {
        random = new Random();
        this.setPreferredSize(new Dimension(this.screenwidth, this.screenheight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }


    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(this.delay, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (!running) {
            gameOver(g);
        }

        for (int i = 0; i < this.screenheight / this.unitSize; i++) {
            g.drawLine(i * this.unitSize, 0, i * this.unitSize, this.screenheight);
            g.drawLine(0, i * this.unitSize, this.screenwidth, i * this.unitSize);
        }
        g.setColor(Color.red);
        g.fillOval(apple.getPosX(), apple.getPosY(), this.unitSize, this.unitSize);

        for (int i = 0; i < snake.getBodyParts(); i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], this.unitSize, this.unitSize);
            } else {
                g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                g.fillRect(x[i], y[i], this.unitSize, this.unitSize);
            }
        }
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.setColor(Color.WHITE);

        g.drawString("Score: " + applesEaten,  10, metrics.getAscent() + 10);

        // 10px from right edge
        // 10px from top

        g.drawString("Player: ", getWidth() - metrics.stringWidth("Player: ") - 10, metrics.getAscent() + 10);
    }

    public void move() {
        for (int i = snake.getBodyParts(); i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (snake.getDirection()) {
            case 'U' -> y[0] -= unitSize;
            case 'D' -> y[0] += unitSize;
            case 'L' -> x[0] -= unitSize;
            case 'R' -> x[0] += unitSize;
        }
    }

    public void newApple() {
        apple.setPosX(random.nextInt((int)this.screenwidth/this.unitSize )*this.unitSize);
        apple.setPosY(random.nextInt((int)this.screenheight/this.unitSize)*this.unitSize);
    }


    public void checkApple() {
        if (x[0] == apple.getPosX() && y[0] == apple.getPosY()) {
            snake.setBodyParts(snake.getBodyParts() + 1);
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        for (int i = snake.getBodyParts(); i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
            }
        }
        if (x[0] < 0 || x[0] > this.screenwidth || y[0] < 0 || y[0] > this.screenheight) {
            running = false;
        }

        if (!running) { timer.stop(); }
    }

    public void gameOver(Graphics g) {
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.setColor(Color.red);
        g.drawString("Game Over", (this.screenwidth - metrics.stringWidth("Game Over")) / 2, this.screenheight / 2);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
        if (!running) {
            return;
        }

        move();
        checkApple();
        checkCollisions();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != 'R') {
                        snake.setDirection('L');
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != 'L') {
                        snake.setDirection('R');
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != 'D') {
                        snake.setDirection('U');
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != 'U') {
                        snake.setDirection('D');
                    }
                    break;
            }
        }
    }
}
