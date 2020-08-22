package Entity.Model;

import GameStates.LevelStateModel;

public abstract class PersonModel extends ObjectModel {
    protected int startY;
    protected double xspeed;
    protected double yspeed;
    protected long timer;
    protected long lastTime;
    protected int index;
    protected boolean isDead;
    protected boolean isStay;
    protected int curHearts;
    protected boolean isRight = true;

    public PersonModel(int x, int y, int width, int height) {
        super(x, y, width, height);
        startY = y;
        lastTime = System.currentTimeMillis();
        isDead = false;
        curHearts=3;
    }

    public int getCurHearts() {
        return curHearts;
    }

    public int getInd() {
        return index;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public boolean isStay() {
        return isStay;
    }

    public boolean isRight() {
        return isRight;
    }


    public void reset() {
        x = startX;
        y = startY;
        xspeed = 0;
        yspeed = 0;
        hitBox.x = x;
        hitBox.y = y;
        isDead = false;
        isRight = true;
        curHearts = LevelStateModel.MAX_HEARTS;
    }

    public abstract void update();

    public abstract void removeHeart();

    public boolean isDead() {
        return isDead;
    }


}
