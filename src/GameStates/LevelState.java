package GameStates;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LevelState extends GameState {

    private LevelStateModel lsm;
    private Background bg;
    private BufferedImage heart;
    private BufferedImage bullet;
    private BufferedImage coin;

    public LevelState(GameStateManager gsm) {
        lsm = new LevelStateModel();
        this.gsm = gsm;
        bg = new Background("/images/bg/bg.png");
        try {
            heart = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/images/entity/heart.png"
                    )
            );
            bullet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/images/entity/bullet_icon.png"
                    )
            );
            coin = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/images/entity/coin.png"
                    )
            ).getSubimage(0, 0, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void init() {
        lsm.init();
        initial = true;
    }

    @Override
    public boolean isInit() {
        return initial;
    }

    @Override
    public void update() {
        lsm.update();
        bg.update(-lsm.getPlayer().getSpeed() * 0.2);
        if (lsm.isEnd()) {
            initial = false;
            gsm.setState(GameStateManager.EndLevel);
        }

    }

    public void reset() {
        bg.reset();
        lsm.reset();
    }


    @Override
    public void draw(Graphics2D g) {
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        bg.draw(g);
        Graphics2D gtd = g;
        lsm.getTent().draw(gtd);
        g.drawImage(heart, 20, 16, null);
        g.drawString(lsm.getPlayer().getHearts() + "/" + LevelStateModel.MAX_HEARTS, 40, 32);
        gtd.drawImage(coin, 20, 45, null);
        g.drawString(LevelStateModel.getCurCoins() + "/" + LevelStateModel.MAX_COINS, 40, 60);
        gtd.drawImage(bullet, 17, 70, null);
        g.drawString(lsm.getPlayer().getCurBullets() + "/" + LevelStateModel.MAX_BULLETS, 40, 90);
        for (int i = 0; i < LevelStateModel.getEnemies().size(); i++) {
            LevelStateModel.getEnemies().get(i).draw(gtd);
        }
        for (int i = 0; i < LevelStateModel.getFloors().size(); i++) {
            LevelStateModel.getFloors().get(i).draw(gtd);
        }
        for (int i = 0; i < LevelStateModel.getSpikes().size(); i++) {
            LevelStateModel.getSpikes().get(i).draw(gtd);
        }
        for (int i = 0; i < LevelStateModel.getCoins().size(); i++) {
            LevelStateModel.getCoins().get(i).draw(gtd);
        }
        lsm.getPlayer().draw(gtd);
    }

    @Override
    public void keyPressed(int e) {
        lsm.keyPressed(e);
        if (e == KeyEvent.VK_ESCAPE) {
            gsm.setState(0);
        }
        if (e == KeyEvent.VK_R) {
            reset();
        }
    }

    @Override
    public void keyReleased(int e) {

        lsm.keyReleased(e);
    }

    public void keyTyped(char e) {
        lsm.keyTyped(e);
    }

}
