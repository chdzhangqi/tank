package com.chd.tank;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private int x;
    private int y;
    private TankFrame tf;
    private Dir dir;
    private boolean live = true;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {

        if (!this.live){
            tf.bullets.remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
        move();
    }

    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT){
            live = false;
        }
    }
}
