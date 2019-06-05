package com.chd.tank;

import java.awt.*;

public class Explode {
    private int x;
    private int y;
    private int count = 0;
    private TankFrame tf;
    private static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private static final int WIDTH = ResourceMgr.explodes[0].getWidth();

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[count], x, y, null);
        count++;
        if (count == 16) {
            count = 0;
        }

    }

}
