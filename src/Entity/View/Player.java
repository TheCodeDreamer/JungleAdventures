package Entity.View;

import Entity.Model.PlayerModel;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Person {
    private PlayerModel pm;
    private BufferedImage jump;
    private BufferedImage dead;
    private BufferedImage stay;

    public Player(int x, int y) {
        super(6, 80);
        try {
            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/images/entity/player.png"
                    )
            );
            for (int i = 0; i < animation.length; i++) {
                animation[i] = spritesheet.getSubimage(i * step + 15, 115, 50, 65);

            }
            jump = spritesheet.getSubimage(2 * step + 15, 290, 65, 65);
            dead = spritesheet.getSubimage(6 * step + 15, 300, 90, 65);
            stay = spritesheet.getSubimage(3 * step + 23, 207, 55, 65);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model = new PlayerModel(x, y, animation[0].getWidth(), animation[0].getHeight());
        pm = (PlayerModel) model;
    }


    public void setKeyLeft(boolean keyLeft) {
        pm.setKeyLeft(keyLeft);
    }
    public boolean getKeyLeft() {
        return pm.getKeyLeft();
    }

    public void checkAttacks(ArrayList<Enemy> enemies) {
        pm.checkAttacks(enemies);
    }

    public void setKeyRight(boolean keyRight) {
        pm.setKeyRight(keyRight);
    }

    public void setKeyUP(boolean keyUP) {
        pm.setKeyUP(keyUP);
    }
    public int getCurHearts() {
        return pm.getCurHearts();
    }
    public int getCurBullets() {
        return pm.getCurBullets();
    }
    public boolean isJump(){
       return pm.isJump();
    }
    public void shoot() {
        pm.shoot();
    }

    public double getSpeed() {
        return pm.getSpeed();
    }

    public String getHearts() {
        return pm.getHearts();
    }

    public void draw(Graphics2D gtd) {

        if (isDead()) {
            if (model.isRight())
                gtd.drawImage(dead, model.getX() - 15, model.getY() + 5, null);
            else
                gtd.drawImage(dead, model.getX() + model.getWidth(), model.getY() + 5, -dead.getWidth(), dead.getHeight(), null);
            gtd.drawString("YOU DIED", GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT / 4);
            gtd.drawString("Press R to restart", GamePanel.WIDTH / 2 - 50, GamePanel.HEIGHT / 4 + 30);
        } else {
            if (pm.isBlinking()) {
                long elapsed = System.currentTimeMillis() - pm.getBlinkTimer();
                if (elapsed / 100 % 2 == 0) {
                    return;
                }
            }
            if (pm.isJump() && model.isRight()) {
                gtd.drawImage(jump, model.getX() - 15, model.getY(), null);
            } else if (pm.isJump() && !model.isRight()) {
                gtd.drawImage(jump, model.getX() + getHitBox().width + 15, model.getY(), -jump.getWidth(), jump.getHeight(), null);
            } else if (model.isStay() && model.isRight()) {
                gtd.drawImage(stay, model.getX(), model.getY(), null);
            } else if (model.isStay() && !model.isRight()) {
                gtd.drawImage(stay, model.getX() + getHitBox().width, model.getY(), -stay.getWidth(), stay.getHeight(), null);
            } else if (!pm.isKeyLeft())
                gtd.drawImage(animation[index], model.getX() - 15, model.getY(), null);
            else {
                gtd.drawImage(animation[index], model.getX() + getHitBox().width + 15, model.getY(), -model.getWidth(), model.getHeight(), null);
            }
            for (int i = 0; i < pm.getBullets().size(); i++) {
                pm.getBullets().get(i).draw(gtd);
            }
        }
    }


}
