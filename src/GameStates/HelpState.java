package GameStates;

import Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HelpState extends GameState {
    public HelpState(GameStateManager gsm) {
        this.gsm = gsm;
        bg = new Background("/images/bg/help.jpg");
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("RULES", GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT / 4 - 10);
        g.drawString("JUMP - W ", GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT / 2 - 60);
        g.drawString("LEFT - A", GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT / 2 - 20);
        g.drawString("RIGHT - D", GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT / 2 + 20);
        g.drawString("SHOOT - E", GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT / 2 + 60);
        g.drawString("RESTART - R", GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT / 2 + 100);
        g.drawString("Back to menu - ESC", GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT / 2 + 140);
    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_ESCAPE) {
            gsm.setState(0);
        }
    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void keyTyped(char key) {

    }

    @Override
    public void init() {
        initial = true;
    }

    @Override
    public boolean isInit() {
        return initial;
    }
}
