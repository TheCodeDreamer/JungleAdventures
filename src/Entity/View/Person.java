package Entity.View;

import Entity.Model.PersonModel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Person {
    protected PersonModel model;
    protected int step;
    protected int index;
    protected BufferedImage dead;
    protected BufferedImage[] animation;

    public Person(int num, int step) {
        animation = new BufferedImage[num];
        this.step = step;

    }

    public boolean isStay() {
        return model.isStay();
    }

    public void removeHeart() {
        model.removeHeart();
    }

    public void update() {
        model.update();
        index = model.getInd() % animation.length;
    }

    public void reset() {
        model.reset();
    }

    public Rectangle getHitBox() {
        return model.getHitBox();
    }

    public boolean isDead() {
        return model.isDead();
    }
}
