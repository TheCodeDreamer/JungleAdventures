package Entity.Model;

public class CoinModel extends ObjectModel {

    private boolean visible = true;
    private int index;
    private long timer;
    private long lastTime;

    public CoinModel(int x, int y) {
        super(x, y, 16, 16);
        lastTime = System.currentTimeMillis();
    }

    public void set(int cameraX) {
        super.set(cameraX);
    }

    public int getInd() {
        return index;
    }

    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if (timer > 100) {
            index++;
            timer = 0;
        }
        index %= 6;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
