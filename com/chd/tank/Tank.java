package com.chd.tank;

import java.awt.*;

public class Tank {
    private int x;
    private int y;
    private TankFrame tf;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private static final int HEIGHT = 50;
    private static final int WIDTH = 50;
    private boolean moving = false;
    private Color tankColor;
    private boolean live = true;
    private int centerX;
    private int centerY;

    public int getCenterX() {
        return this.x + WIDTH / 2;
    }

    public int getCenterY() {
        return this.y + HEIGHT / 2;
    }

    public Tank(int x, int y, Dir dir, Color tankColor, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.tankColor = tankColor;
    }

    public TankFrame getTf() {
        return tf;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(tankColor);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(color);
        move();
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        tf.bullets.add(new Bullet(this.x, this.y, this.dir, this.tf));
    }

    private void move() {
        if (!moving)
            return;
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
    }
}
