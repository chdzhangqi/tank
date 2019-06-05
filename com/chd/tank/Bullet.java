package com.chd.tank;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x;
    private int y;
    private TankFrame tf;
    private Dir dir;
    private boolean live = true;
    private Group group;
    Rectangle bRect = new Rectangle();

    public Bullet(int x, int y, Dir dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        bRect.x = x;
        bRect.y = y;
        bRect.width= WIDTH;
        bRect.height = HEIGHT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }

    public void paint(Graphics g) {

        if (!this.live){
            tf.bullets.remove(this);
        }
        switch (dir){
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
        }
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
        bRect.x = x;
        bRect.y = y;
        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT){
            live = false;
        }
    }

    public void dead() {
        live = false;
    }
}
