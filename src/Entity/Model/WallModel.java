package Entity.Model;

public class WallModel extends ObjectModel {

    public WallModel(int x, int y, int num) {
        super(x, y, 32 * num, 32);
    }

    public int getEndX() {
        return x + width - 90;
    }

    public int getEndY() {
        return y - 70;
    }
}
