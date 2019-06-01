package com.chd.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    private int x = 20;
    private int y = 20;
    private static final int SPEED = 10;
    private Dir dir = Dir.DOWN;

    TankFrame() {
        setTitle("tank war");
        setResizable(false);
        setSize(800, 600);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);  // 退出程序
            }
        });

        addKeyListener(new MyKeyListener()); // source register listener
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);

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

    class MyKeyListener extends KeyAdapter {


        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
            }
            changeDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
            }
            changeDir();
        }

        private void changeDir() {
            if (bL) dir = Dir.LEFT;
            if (bR) dir = Dir.RIGHT;
            if (bU) dir = Dir.UP;
            if (bD) dir = Dir.DOWN;
        }
    }
}