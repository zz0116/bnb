package com.zyz.game.bnb;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 张远卓
 * @date 2019/5/3
 */
@Component
public class GameFrame extends Frame {
    private Random random = new Random();
    private Image offScreenImage = null;
    List<Role> roles = new ArrayList<>();
    List<Bubble> bubbles = new ArrayList<>();
    List<Wave> waves = new ArrayList<>();
    Role myRole = new Role(this, Group.GOOD, 100, 200, Dir.DOWN, true, false);
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public GameFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("泡泡堂");
        setVisible(true);

        roles.add(myRole);
        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void start() {
        roles = new ArrayList<>();
        bubbles = new ArrayList<>();
        waves = new ArrayList<>();
        roles.add(myRole);

        for (int i = 0; i < 6; i++) {
            roles.add(new Role(this, Group.BAD, random.nextInt(GAME_WIDTH), random.nextInt(GAME_HEIGHT), Dir.getRandomDir(), true, true));
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("敌人的数量: " + (roles.size() - 1), 10, 50);

        for (int i = 0; i < bubbles.size(); i++) {
            Bubble bubble = bubbles.get(i);
            bubble.paint(g);
        }
        for (int i = 0; i < waves.size(); i++) {
            Wave wave = waves.get(i);
            wave.paint(g);
            for (int j = 0; j < roles.size(); j++) {
                Role role = roles.get(j);
                if (wave.collideWith(role)) {
                    // TODO: 角色死亡效果
                }
            }
        }

        for (int j = 0; j < roles.size(); j++) {
            Role role = roles.get(j);
            role.paint(g);
        }
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_F5:
                    start();
                    break;
                case KeyEvent.VK_SPACE:
                    myRole.fire();
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;

                default:
                    break;
            }
            setMyRoleDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;

                default:
                    break;
            }
            setMyRoleDir();
        }

        private void setMyRoleDir() {

            if (!bL && !bU && !bR && !bD) {
                myRole.setMoving(false);
            } else {
                myRole.setMoving(true);

                if (bL) {
                    myRole.setDir(Dir.LEFT);
                }
                if (bU) {
                    myRole.setDir(Dir.UP);
                }
                if (bR) {
                    myRole.setDir(Dir.RIGHT);
                }
                if (bD) {
                    myRole.setDir(Dir.DOWN);
                }
            }
        }
    }

}
