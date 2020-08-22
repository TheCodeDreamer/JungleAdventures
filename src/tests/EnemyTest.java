import Entity.Model.EnemyModel;
import Entity.View.*;
import GameStates.LevelStateModel;

import static org.junit.Assert.*;

public class EnemyTest {
    Enemy enemy  = new Enemy(300,100);


    @org.junit.Test
    public void Attack() throws InterruptedException {
        enemy.setWall(new Wall(290,150,1));
        Player player  = new Player(300,100);
        Thread.sleep(1000);
        enemy.checkAttack(player);
        assertEquals(2,player.getCurHearts());
    }

    @org.junit.Test
    public void isMove() {
        enemy.setWall(new Wall(290,150,1));
        int x = enemy.getHitBox().x;
        enemy.update();
        assertNotEquals(x,enemy.getHitBox().x);
    }

    @org.junit.Test
    public void checkCollision() {
        Floor floor = new Floor(300, 100, 1);
        assertTrue(enemy.getHitBox().intersects(floor.getHitBox()));
    }

}