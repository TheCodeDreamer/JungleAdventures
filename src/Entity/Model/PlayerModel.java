package Entity.Model;


import Entity.View.Bullet;
import Entity.View.Enemy;
import GameStates.LevelStateModel;
import Main.GamePanel;

import java.util.ArrayList;


public class PlayerModel extends PersonModel {
    private ArrayList<Bullet> bullets;
    private boolean keyLeft;
    private boolean keyRight;
    private boolean keyUP;
    private boolean isJump;
    private long blinkTimer;
    private boolean blinking;
    private int curBullets;


    public PlayerModel(int x, int y, int width, int height) {
        super(x, y, width, height);
        setHitBox(x, y, width - 30, height);
        bullets = new ArrayList<>();
        curBullets = LevelStateModel.MAX_BULLETS;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public int getCurBullets() {
        return curBullets;
    }

    public boolean isJump() {
        return isJump;
    }

    public long getBlinkTimer() {
        return blinkTimer;
    }

    public boolean isBlinking() {
        return blinking;
    }



    public boolean isKeyLeft() {
        return keyLeft;
    }

    public void setKeyRight(boolean keyRight) {
        this.keyRight = keyRight;
    }

    public void setKeyUP(boolean keyUP) {
        this.keyUP = keyUP;
    }

    public double getSpeed() {
        return xspeed;
    }

    @Override
    public void removeHeart() {
        if (blinking)
            return;
        curHearts--;
        blinking = true;
        blinkTimer = System.currentTimeMillis();
        if (curHearts == 0) {
            isDead = true;
            xspeed = 0;
            blinking = false;
        }

    }

    public void checkAttacks(ArrayList<Enemy> enemies) {
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                if (bullets.get(i).getHitBox().intersects(enemies.get(j).getHitBox()) && !enemies.get(j).isDead()) {
                    bullets.remove(i);
                    enemies.get(j).removeHeart();
                    break;
                }
            }
        }
    }

    public void shoot() {
        if (curBullets != 0) {
            if (isRight) {
                bullets.add(new Bullet(x + width, y + 18, isRight));
            } else {
                bullets.add(new Bullet(x - hitBox.width - 18, y + 18, isRight));
            }
            curBullets--;
        }
    }

    private void dead() {
        isDead = true;
        xspeed = 0;
        curHearts = 0;
    }

    public void reset() {
        super.reset();
        curBullets = LevelStateModel.MAX_BULLETS;
    }

    @Override
    public void update() {
        if (!isDead) {
            timer += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            isStay = false;
            if (blinking) {
                long elapsed = System.currentTimeMillis() - blinkTimer;
                if (elapsed > 1000) {
                    blinking = false;
                }
            }
            if ((keyLeft && keyRight) || (!keyLeft && !keyRight)) {
                xspeed *= 0.8;
                isStay = true;

            } else if (keyLeft) {
                isRight = false;
                xspeed--;
                if (timer > 100) {
                    index++;
                    timer = 0;
                }

            } else if (keyRight) {
                isRight = true;
                xspeed++;
                if (timer > 100) {
                    index++;
                    timer = 0;
                }

            }
            if (xspeed > 0 && xspeed < 0.75)
                xspeed = 0;
            if (xspeed < 0 && xspeed > -0.75)
                xspeed = 0;
            if (xspeed > 3)
                xspeed = 3;
            if (xspeed < -3)
                xspeed = -3;
            if (keyUP) {
                isJump = true;
                hitBox.y++;
                for (int i = 0; i < LevelStateModel.getFloors().size(); i++) {
                    if (LevelStateModel.getFloors().get(i).getHitBox().intersects(hitBox))
                        yspeed = -6;
                }
                hitBox.y--;
            }
            yspeed += 0.3;
            checkCollisions();
            if (hitBox.y >= GamePanel.HEIGHT) {
                dead();
            }
            LevelStateModel.setCameraX((int) -xspeed);
            y += yspeed;
            hitBox.x = x;
            hitBox.y = y;
            for (int i = 0; i < LevelStateModel.getCoins().size(); i++) {
                if (hitBox.intersects(LevelStateModel.getCoins().get(i).getHitBox()) && LevelStateModel.getCoins().get(i).isVisible()) {
                    LevelStateModel.addCoin();
                    LevelStateModel.getCoins().get(i).setVisible(false);
                }
            }
            for (int i = 0; i < LevelStateModel.getSpikes().size(); i++) {
                if (hitBox.intersects(LevelStateModel.getSpikes().get(i).getHitBox())) {
                    dead();
                    return;
                }
            }
            for (int i = 0; i < bullets.size(); i++) {
                for (int j = 0; j < LevelStateModel.getFloors().size(); j++) {
                    if (bullets.get(i).getHitBox().intersects(LevelStateModel.getFloors().get(j).getHitBox()) || bullets.get(i).getHitBox().x > bullets.get(i).getStartX() + GamePanel.WIDTH / 2) {
                        bullets.remove(i);
                        break;
                    }
                }
            }
            for (Bullet bullet : bullets) {
                bullet.set((int) xspeed);
            }
        }
    }

    public boolean isStay() {
        return isStay;
    }

    public void checkCollisions() {
        hitBox.x += xspeed;
        for (int i = 0; i < LevelStateModel.getFloors().size(); i++) {
            if (hitBox.intersects(LevelStateModel.getFloors().get(i).getHitBox())) {
                hitBox.x -= xspeed;
                while (!LevelStateModel.getFloors().get(i).getHitBox().intersects(hitBox)) {
                    hitBox.x += Math.signum(xspeed);
                }
                hitBox.x -= Math.signum(xspeed);
                LevelStateModel.setCameraX(x - hitBox.x);
                xspeed = 0;
                x = hitBox.x;
                isJump = false;
            }
        }

        hitBox.y += yspeed;
        for (int i = 0; i < LevelStateModel.getFloors().size(); i++) {
            if (hitBox.intersects(LevelStateModel.getFloors().get(i).getHitBox())) {
                hitBox.y -= yspeed;
                while (!hitBox.intersects(LevelStateModel.getFloors().get(i).getHitBox())) {
                    hitBox.y += Math.signum(yspeed);
                }
                hitBox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
                isJump = false;
            }
        }
    }

    public String getHearts() {
        return "" + curHearts;
    }

    public boolean getKeyLeft() {
        return keyLeft;
    }

    public void setKeyLeft(boolean keyLeft) {
        this.keyLeft = keyLeft;
    }
}
