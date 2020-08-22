package GameStates;

import Main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class LevelEndState extends GameState {


    private int currentChoice = 0;
    private String[] options = {"Menu", "Choose Level", "Quit"};
    private Color titleColor;
    private Font titleFont;
    private Font font;

    public LevelEndState(GameStateManager gsm) {
        this.gsm = gsm;
        bg = new Background("/images/bg/menubg.jpg");
        titleColor = Color.WHITE;
        titleFont = new Font("Comic Sans MS", Font.BOLD, 30);
        font = new Font("Comic Sans MS", Font.BOLD, 20);
    }


    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("LEVEL COMPLETE", GamePanel.WIDTH / 3 - 10, GamePanel.HEIGHT / 4 + 50);
        g.drawString("You collected " + LevelStateModel.getCurCoins() + "/" + LevelStateModel.MAX_COINS + " coins", GamePanel.WIDTH / 3 - 50, GamePanel.HEIGHT / 4 + 90);
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.BLACK);

            } else
                g.setColor(Color.WHITE);
            g.drawString(options[i], GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2 + 15 + i * 40);
        }

    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.MENUSTATE);
        } else if (currentChoice == 1) {
            JFileChooser fc = new JFileChooser("C:\\Users\\Дмитрий\\IdeaProjects\\myFirstGame\\levels");
            int choose = fc.showOpenDialog(null);
            if (choose == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                if (f != null) {
                    LevelStateModel.setLvl("levels/" + f.getName());
                }
                gsm.setState(GameStateManager.LEVEL1STATE);
            }
        } else if (currentChoice == 2)
            System.exit(0);
    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_ENTER) {
            select();
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            currentChoice--;
            if (currentChoice == -1)
                currentChoice = options.length - 1;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            currentChoice++;
            if (currentChoice == options.length)
                currentChoice = 0;
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
    }

    @Override
    public boolean isInit() {
        return true;
    }
}
