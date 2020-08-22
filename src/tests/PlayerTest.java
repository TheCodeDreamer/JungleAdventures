import Entity.View.*;
import GameStates.LevelStateModel;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player = new Player(100, 100);


    @org.junit.Test
    public void dieBySpike() {
        Spike spike = new Spike(100, 100);
        LevelStateModel.getSpikes().add(spike);
        player.update();
        assertTrue(player.isDead());
    }

    @org.junit.Test
    public void collectCoin() {
        Coin coin = new Coin(100, 100);
        LevelStateModel.getCoins().add(coin);
        player.update();
        assertFalse(coin.isVisible());
    }

    @org.junit.Test
    public void checkCollision() {
        Floor floor = new Floor(100, 100, 1);
        assertTrue(player.getHitBox().intersects(floor.getHitBox()));
    }
    @org.junit.Test
    public void checkAttack() {
        Enemy enemy = new Enemy(130,100);
        enemy.setWall(new Wall(100,150,3));
        player.shoot();
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        player.checkAttacks(enemies);
        assertEquals(2,enemy.getCurHearts());
    }

    @org.junit.Test
    public void isJump(){
        LevelStateModel lsm = new LevelStateModel();
        lsm.setPlayer(player);
        lsm.keyPressed(0x57);
        player.update();
        assertTrue(player.isJump());
    }

}