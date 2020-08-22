package Entity.Model;

import Entity.View.Player;
import Entity.View.Wall;
import GameStates.LevelStateModel;

public class EnemyModel extends PersonModel {
    private Wall wall;
    private boolean isAttack;
    private long timerAttack;
    private long lastTimeAttack;

    public EnemyModel(int x, int y, int width, int height) {
        super(x, y, width, height);
        setHitBox(x + 10, y + 5, width - 14, height);
        timerAttack = System.currentTimeMillis();
        xspeed = 1;
        for (int i = 0; i < LevelStateModel.getFloors().size(); i++) {
            wall = LevelStateModel.getFloors().get(i);
            if (hitBox.x >= wall.getHitBox().x && hitBox.x <= wall.getHitBox().x + wall.getHitBox().width && hitBox.y + hitBox.height + 50 > wall.getHitBox().y) {
                break;
            }
        }
        if (wall!= null && wall.getSize() == 1) {
            isStay = true;
            isRight = false;
        }

    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public void checkAttack(Player player) {
        if (hitBox.intersects(player.getHitBox()) && !isDead && !player.isDead()) {
            lastTimeAttack += System.currentTimeMillis() - timerAttack;
            timerAttack = System.currentTimeMillis();
            if (lastTimeAttack > 1000) {
                lastTimeAttack = 0;
                isAttack = true;
                player.removeHeart();
            }

        } else {
            isAttack = false;
        }
    }

    public void set(double cameraX) {
        x = x - (int) cameraX;
        hitBox.x = x + 10;
    }

    public void reset() {
        super.reset();
        xspeed = 1;
        lastTimeAttack = 0;
        timerAttack = System.currentTimeMillis();
    }

    @Override
    public void removeHeart() {
        curHearts--;
        if (curHearts == 0)
            isDead = true;

    }


    @Override
    public void update() {
        if (!isDead && !isStay) {
            timer += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            if (timer > 100) {
                index++;
                timer = 0;
            }
            yspeed += 0.3;
            checkCollisions();
            y += yspeed;
            x += xspeed;
            if ((hitBox.x <= wall.getHitBox().x) || ((hitBox.x + hitBox.width) >= (wall.getHitBox().x + wall.getHitBox().width))) {
                xspeed *= -1;
                x += xspeed * 5;
                isRight = !isRight;
            }
            hitBox.x = x;
            hitBox.y = y;
        }

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
                xspeed *= -1;
                isRight = !isRight;
                x = hitBox.x;
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
            }
        }
    }

    public boolean isAttack() {
        return isAttack;
    }
}
