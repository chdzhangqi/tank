package com.chd.tank;


public class Main {
    /**
     * 初识 Frame
     *
     */
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        for (int i = 0; i < 5; ++i){
            tankFrame.enemies.add(new Tank(100 + i * 100, 100,Dir.DOWN, tankFrame));
        }
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tankFrame.repaint();
        }
    }
}