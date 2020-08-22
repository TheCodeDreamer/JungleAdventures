package GameStates;


import java.awt.*;

public abstract class GameState {
    protected GameStateManager gsm;
    protected Background bg;
    protected boolean initial;

    public abstract void update();

    public abstract void draw(Graphics2D g);

    public abstract void keyPressed(int key);

    public abstract void keyReleased(int key);

    public abstract void keyTyped(char key);

    public abstract void init();

    public abstract boolean isInit();
}
