package GameStates;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    public static final int HELP = 2;
    public static final int EndLevel = 3;
    private ArrayList<GameState> gameStates;
    private int currentState;

    public GameStateManager() {
        gameStates = new ArrayList<GameState>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new LevelState(this));
        gameStates.add(new HelpState(this));
        gameStates.add(new LevelEndState(this));
    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update() {
        if (gameStates.get(currentState).isInit())
            gameStates.get(currentState).update();
    }

    public void draw(Graphics2D g) {
        if (gameStates.get(currentState).isInit())
            gameStates.get(currentState).draw(g);
    }

    public void keyTyped(char key) {
        gameStates.get(currentState).keyTyped(key);
    }

    public void keyPressed(int key) {
        gameStates.get(currentState).keyPressed(key);
    }

    public void keyReleased(int key) {
        gameStates.get(currentState).keyReleased(key);

    }
}
