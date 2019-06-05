package com.chd.tank;


public class Main {
    /**
     * 初识 Frame
     */
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        int initCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initCount; ++i) {
            Tank enemy = new Tank(100 + i * 100, 100, Dir.DOWN, Group.BAD, tankFrame);
            enemy.setMoving(true);
            tankFrame.enemies.add(enemy);
        }
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tankFrame.repaint();
        }
    }
}