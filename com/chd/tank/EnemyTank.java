package com.chd.tank;

import java.awt.*;

public class EnemyTank extends Tank {
    public EnemyTank(int x, int y, Dir dir, Color tankColor, TankFrame tf) {
        super(x, y, dir, tankColor, tf);
    }


    public void dead() {
        for (Bullet bullet : getTf().bullets) {
            if ((bullet.getCenterX() - getCenterX() * 2 + (bullet.getCenterY() - getCenterY()) * 2
                    < (getWIDTH() / 2 + Bullet.WIDTH / 2) * 2)) {
                bullet.setLive(false);
                setLive(false);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        dead();
        if (!isLive()) {
            getTf().enemies.remove(this);
        }
        super.paint(g);
    }
}


