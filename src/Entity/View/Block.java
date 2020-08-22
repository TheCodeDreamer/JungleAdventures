package Entity.View;

import Entity.Model.ObjectModel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block {
    protected ObjectModel bm;
    protected BufferedImage block;

    public Block(int x, int y, int width, int height) {
        bm = new ObjectModel(x, y, width, height);

    }
    public Block(int x, int y) {
        bm = new ObjectModel(x, y);
    }

    public Rectangle getHitBox() {
        return bm.getHitBox();
    }
    public int getX(){
      return  bm.getX();
    }
    public int getY(){
        return bm.getY();
    }
    public void set(int cameraX) {
        bm.set(cameraX);
    }

    public void draw(Graphics2D gtd) {
        gtd.drawImage(block, bm.getX(), bm.getY(), null);
    }
}
