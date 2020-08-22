package Entity.View;

import Entity.Model.EnemyModel;
import GameStates.LevelStateModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemy extends Person {
    private EnemyModel em;
    private BufferedImage attack;
    private BufferedImage[] animationAttack;

    public Enemy(int x, int y) {
        super(7, 64);
        animationAttack = new BufferedImage[7];
        try {
            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/images/entity/enemy.png"
                    )
            );
            for (int i = 0; i < animation.length; i++) {
                animation[i] = spritesheet.getSubimage(i * step + 15, 387, 30, 55);
                animationAttack[i] = spritesheet.getSubimage(i * step + 15, 447, 30, 55);
            }
            dead = spritesheet.getSubimage(8 * step + 5, 407, 55, 30);
            attack = spritesheet.getSubimage(step + 5, 447, 30, 55);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        model = new EnemyModel(x, y, animation[0].getWidth(), animation[0].getHeight());
        em = (EnemyModel) model;
    }
    public void setWall(Wall wall){
        em.setWall(wall);
    }
    public void update() {
        em.update();
        index = em.getInd() % animation.length;
    }
    public int getCurHearts(){
       return em.getCurHearts();
    }
    public void checkAttack(Player player) {
        em.checkAttack(player);
    }

    public void set(double cameraX) {
        em.set(cameraX);
    }

    public void draw(Graphics2D gtd) {
        if (em.isAttack()) {
            if (em.isRight())
                gtd.drawImage(animationAttack[index], em.getX(), em.getY(), null);
            else {
                gtd.drawImage(animationAttack[index], em.getX() + em.getWidth() + 5, em.getY(), -em.getWidth(), em.getHeight(), null);
            }
        } else if (!em.isDead()) {
            gtd.drawRect(em.getHitBox().x, em.getHitBox().y - 10, em.getHitBox().width, 3);
            gtd.setColor(Color.RED);
            gtd.fillRect(em.getHitBox().x + 1, em.getHitBox().y - 9, (em.getHitBox().width - ((LevelStateModel.MAX_HEARTS - em.getCurHearts()) * 5) - 1), 2);
            gtd.setColor(Color.BLACK);
            if (em.isRight())
                gtd.drawImage(animation[index], em.getX(), em.getY(), null);
            else {
                gtd.drawImage(animation[index], em.getX() + em.getWidth() + 5, em.getY(), -em.getWidth(), em.getHeight(), null);
            }
        } else {
            if (!em.isRight())
                gtd.drawImage(dead, em.getX() - 15, em.getY() + dead.getHeight(), null);
            else {
                gtd.drawImage(dead, em.getX() + dead.getWidth(), em.getY() + dead.getHeight(), -dead.getWidth(), dead.getHeight(), null);
            }
        }
    }

    public void removeHeart() {
        em.removeHeart();
    }

    public Rectangle getHitBox() {
        return em.getHitBox();
    }

    public boolean isDead() {
        return em.isDead();
    }
}
