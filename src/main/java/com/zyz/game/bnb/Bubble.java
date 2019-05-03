package com.zyz.game.bnb;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * @author 张远卓
 * @date 2019/5/3
 */
@Data
@AllArgsConstructor
public class Bubble {
    private GameFrame gf;

    private Group group;

    private int x;

    private int y;

    private boolean living;

    private int show;

    private int start;

    private int end;

    static final int WIDTH = 20, HEIGHT = 20;

    public void paint(Graphics g) {
        show++;

        if (show >= start && show <= end) {
            living = true;
        } else {
            living = false;
        }

        if (!living) {
            gf.bubbles.remove(this);
        }

        g.setColor(Color.YELLOW);
        g.fillArc(x, y, WIDTH, HEIGHT, 0, 360);
    }
}
