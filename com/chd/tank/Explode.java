package com.chd.tank;

import java.awt.*;

public class Explode {
    private int x;
    private int y;
    private int count = 0;
    private static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private static final int WIDTH = ResourceMgr.explodes[0].getWidth();

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[count], x, y, null);
        count++;
        if (count == 16) {
            count = 0;
        }

    }

}
