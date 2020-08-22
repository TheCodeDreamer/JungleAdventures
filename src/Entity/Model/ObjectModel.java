package Entity.Model;

import java.awt.*;

public class ObjectModel {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int startX;
    protected Rectangle hitBox;

    public ObjectModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        startX = x;
        this.width = width;
        this.height = height;
        hitBox = new Rectangle(x, y, width, height);
    }
    public ObjectModel(int x,int y){
        this.x = x;
        this.y = y;
        startX = x;
    }
    public void set(int cameraX) {
        x = startX + cameraX;
        hitBox.x = x;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(int x, int y, int width, int height) {
        hitBox = new Rectangle(x, y, width, height);
    }
}
