package Entity.Model;

public class BulletModel extends ObjectModel {
    private int speed;

    public BulletModel(int x, int y, boolean right) {
        super(x, y, 10, 3);
        speed = 5;
        if (!right)
            speed *= -1;
    }

    @Override
    public void set(int offset) {
        x = x - offset + speed;
        hitBox.x = x;
    }

    public int getStartX() {
        return startX;
    }

}
