package com.chd.tank;


public class Main {
    /**
     * 初识 Frame
     * test
     */
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
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