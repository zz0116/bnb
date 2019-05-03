package com.zyz.game.bnb;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * @author 张远卓
 * @date 2019/5/3
 */
@Data
@AllArgsConstructor
public class Role {
    private GameFrame gf;

    private Group group;

    private int x;

    private int y;

    private Dir dir;

    private boolean living;

    private boolean moving;

    static final int WIDTH = 20, HEIGHT = 20;

    static final int SPEED = 4;

    static final Random RANDOM = new Random();

    public void paint(Graphics g) {
        if (!living) gf.roles.remove(this);

        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);

        move();
    }

    private void move() {
        if (!moving) return;

        if (group == Group.BAD && RANDOM.nextInt(100) > 95) {
            dir = Dir.getRandomDir();
        }

        switch (dir) {
            case LEFT:
                x -= SPEED;
                if (x <= 0) {
                    if (group == Group.BAD) {
                        dir = Dir.RIGHT;
                    } else {
                        x = 0;
                    }
                }
                break;
            case RIGHT:
                x += SPEED;
                if (x >= GameFrame.GAME_WIDTH - WIDTH) {
                    if (group == Group.BAD) {
                        dir = Dir.LEFT;
                    } else {
                        x = GameFrame.GAME_WIDTH - WIDTH;
                    }
                }
                break;
            case DOWN:
                y += SPEED;
                if (y >= GameFrame.GAME_HEIGHT - HEIGHT) {
                    if (group == Group.BAD) {
                        dir = Dir.UP;
                    } else {
                        y = GameFrame.GAME_HEIGHT - HEIGHT;
                    }
                }
                break;
            case UP:
                y -= SPEED;
                if (y <= HEIGHT) {
                    if (group == Group.BAD) {
                        dir = Dir.DOWN;
                    } else {
                        y = HEIGHT;
                    }
                }
                break;
        }
    }

    public void fire() {
        gf.bubbles.add(new Bubble(gf, group, x, y, false, 0, 0, 120));
        int length = 120;
        int show = 0;
        int start = 90;
        int end = 120;
        for (int i = x - length / 2; i < x + Wave.WIDTH / 2 + length / 2; i += Wave.WIDTH) {
            gf.waves.add(new Wave(gf, group, i, y, length, false, show, start, end));
        }
        for (int j = y - length / 2; j < y + Wave.HEIGHT / 2 + length / 2; j += Wave.HEIGHT) {
            gf.waves.add(new Wave(gf, group, x, j, length, false, show, start, end));
        }
    }

    public void die() {
        living = false;
    }
}