import Entity.View.Player;
import Entity.View.Tent;
import GameStates.LevelStateModel;
import org.junit.BeforeClass;

import static org.junit.Assert.*;

public class LevelStateModelTest {
    LevelStateModel lsm= new LevelStateModel();
    int size_blocks = 17;
    int size_spikes =17;
    int size_enemies =5;
    int size_coins = 5;

    @BeforeClass
    public static void init(){
        LevelStateModel.makeLevel();
    }

    @org.junit.Test
    public void TestLevel() {
        assertEquals(size_blocks, LevelStateModel.getFloors().size());
        assertEquals(size_spikes, LevelStateModel.getSpikes().size());
        assertEquals(size_coins, LevelStateModel.getCoins().size());
        assertEquals(size_enemies, LevelStateModel.getEnemies().size());
    }
    @org.junit.Test
    public void TestEnd() {
        Player player = new Player(100,100);
        Tent tent = new Tent(70,100);
        lsm.setPlayer(player);
        lsm.setTent(tent);
        assertTrue(lsm.isEnd());
    }
    @org.junit.Test
    public void TestCoins() {
        assertEquals(size_coins, LevelStateModel.MAX_COINS);
    }
    @org.junit.Test
    public void TestBullets() {
        assertEquals(size_enemies*3+5, LevelStateModel.MAX_BULLETS);
    }

    @org.junit.Test
    public void TestKey() {
        Player player = new Player(100,100);
        lsm.setPlayer(player);
        lsm.keyPressed(0x41);
        assertTrue(player.getKeyLeft());
    }

}