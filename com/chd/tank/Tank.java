package com.chd.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x;
    private int y;
    public static final int WIDTH = ResourceMgr.tankL.getWidth();
    public static final int HEIGHT = ResourceMgr.tankL.getHeight();
    private TankFrame tf;
    private Dir dir;
    private static final int SPEED = 5;
    private boolean moving = false;
    private boolean live = true;
    private Group group;
    private Random random = new Random();
    private Rectangle tRect = new Rectangle();

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        tRect.x = x;
        tRect.y = y;
        tRect.height = HEIGHT;
        tRect.width = WIDTH;
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
        if (random.nextInt(10) > 8 && group.equals(Group.BAD)) {
            fire(Group.BAD);
            if (random.nextInt(10) > 8)
                setDir(Dir.values()[random.nextInt(Dir.values().length)]);
        }
        move();

    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire(Group g) {
        int centerX = x + WIDTH / 2 - Bullet.WIDTH / 2;
        int centerY = y + HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(centerX, centerY, this.dir, g, this.tf));
    }

    private void dead() {
        live = false;
        tf.explodes.add(new Explode(x, y, tf));
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
        edgeDetect();
        tRect.x = x;
        tRect.y = y;
    }

    private void edgeDetect() {
        if (group.equals(Group.BAD)) {
            if (x <= 0)
                setDir(Dir.RIGHT);
            if (x >= TankFrame.GAME_WIDTH - WIDTH)
                setDir(Dir.LEFT);
            if (y <= 0)
                setDir(Dir.DOWN);
            if (y >= TankFrame.GAME_HEIGHT - HEIGHT)
                setDir(Dir.UP);
        } else if (group.equals(Group.GOOD)) {
            if (x <= 0)
                x = 0;
            if (x >= TankFrame.GAME_WIDTH - WIDTH)
                x = TankFrame.GAME_WIDTH - WIDTH;
            if (y <= 0)
                y = 0;
            if (y >= TankFrame.GAME_HEIGHT - HEIGHT)
                y = TankFrame.GAME_HEIGHT - HEIGHT;
        }
    }

    public void collapse(Bullet bullet) {
        if (group.equals(bullet.getGroup())) {
            return;
        }
        if (tRect.intersects(bullet.bRect)) {
            dead();
            bullet.dead();
        }
    }
}
