package com.zyz.game.bnb;

import java.util.Random;

/**
 * @author 张远卓
 * @date 2019/5/3
 */
public enum Dir {
    UP, DOWN, LEFT, RIGHT;

    private static Random random = new Random();

    static Dir getRandomDir() {
        int i = random.nextInt(4);
        switch (i) {
            case 0:
                return LEFT;
            case 1:
                return UP;
            case 2:
                return RIGHT;
            case 3:
                return DOWN;

            default:
                return DOWN;
        }
    }
}
