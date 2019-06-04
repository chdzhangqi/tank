package com.chd.tank;

import java.awt.*;

public class Tank {
    private int x;
    private int y;
    public static final int WIDTH = ResourceMgr.tankL.getWidth();
    public static final int HEIGHT = ResourceMgr.tankL.getHeight();
    private TankFrame tf;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private boolean moving = false;
    private boolean live = true;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
        }
        move();

    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        int centerX = x + WIDTH / 2 - Bullet.WIDTH / 2;
        int centerY = y + HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(centerX, centerY, this.dir, this.tf));
    }

    private void dead() {
        live = false;
    }

    private void move() {
        if (!this.live) {
            tf.enemies.remove(this);
        }
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

    public void collapse(Bullet bullet) {
        Rectangle tRect = new Rectangle(x, y, WIDTH, HEIGHT);
        Rectangle bRect = new Rectangle(bullet.getX(), bullet.getY(), Bullet.WIDTH, Bullet.HEIGHT);
        if (tRect.intersects(bRect)) {
            dead();
            bullet.dead();
        }
    }
}
