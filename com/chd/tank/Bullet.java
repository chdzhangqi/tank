package com.chd.tank;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private int x;
    private int y;
    private int centerX;
    private int centerY;
    private TankFrame tf;
    private Dir dir;

    private boolean live = true;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getCenterX() {
        return this.x + WIDTH / 2;
    }

    public int getCenterY() {
        return this.y + HEIGHT / 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g) {

        if (!this.live) {
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
        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }
}
