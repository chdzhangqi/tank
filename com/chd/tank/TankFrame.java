package com.chd.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    private Tank myTank = new Tank(200, 200, Dir.DOWN, this);
    List<Bullet> bullets = new ArrayList<>();
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    private Image offScreenImage = null;
    TankFrame() {
        setTitle("tank war");
        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
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
    public void update(Graphics g) {
        /**
         * 游戏双缓冲技术，闪烁的问题就是一条一条的刷新出现问题。
         * repaint  -->  update(截获) --> paint
         * 在内存中有张图片，先将内存中的图片给画上，然后屏幕上一次性将内存的图片画出来。
         */
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c= g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:"+ bullets.size(), 10, 60);
        g.setColor(c);
        myTank.paint(g);
//        for(Bullet b:bullets){
//            b.paint(g);
//        }
        for(int i = 0; i < bullets.size(); ++i){
            bullets.get(i).paint(g);
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
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
            }
            changeDir();
        }

        private void changeDir() {
            if (!bL && !bR && !bU && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bU) myTank.setDir(Dir.UP);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}