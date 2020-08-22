package Entity.View;

import Entity.Model.WallModel;

import java.awt.*;
import java.util.ArrayList;

public class Wall {
    private ArrayList<Block> wall;
    private WallModel wm;

    public Wall(int x, int y, int num) {
        wm = new WallModel(x, y, num);
        wall = new ArrayList<>();
        if (num == 1)
            wall.add(new Floor(x, y,1));
        else {
            for (int i = 0; i < num; i++) {
                if (i == 0)
                    wall.add(new Floor(x + i * 32, y, 0));
                else if (i == num - 1)
                    wall.add(new Floor(x + i * 32, y, 2));
                else
                    wall.add(new Floor(x + i * 32, y, 1));
            }
        }
    }

    public int getSize() {
        return wall.size();
    }

    public int getEndX() {
        return wm.getEndX();
    }

    public int getEndY() {
        return wm.getEndY();
    }

    public Rectangle getHitBox() {
        return wm.getHitBox();
    }

    public void set(int cameraX) {
        wm.set(cameraX);
        for (int i = 0; i < wall.size(); i++) {
            wall.get(i).set(cameraX);
        }
    }

    public void draw(Graphics2D gtd) {
        for (int i = 0; i < wall.size(); i++) {
            wall.get(i).draw(gtd);
        }
    }
}
