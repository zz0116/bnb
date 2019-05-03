package com.zyz.game.bnb;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张远卓
 * @date 2019/5/3
 */
@Data
@AllArgsConstructor
public class Wave {
    private GameFrame gf;

    private Group group;

    private int x;

    private int y;

    private int length;

    private boolean living;

    private int show;

    private int start;

    private int end;

    static final int WIDTH = 20, HEIGHT = 20;

    // 记录冲击波的格子
    static List<Rectangle> wavesRec = new ArrayList<>();

    public void paint(Graphics g) {
        show++;

        if (show >= start && show <= end) {
            living = true;
        } else {
            living = false;
        }
        if (!living) {
            return;
        }

        g.setColor(Color.YELLOW);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public boolean collideWith(Role role) {
        if (!living || group == role.getGroup()) return false;

        Rectangle roleRec = new Rectangle(role.getX(), role.getY(), Role.WIDTH, Role.HEIGHT);
        Rectangle waveRec = new Rectangle(x, y, WIDTH, HEIGHT);

        if (roleRec.intersects(waveRec)) {
            role.die();
            return true;
        }
        return false;
    }
}
