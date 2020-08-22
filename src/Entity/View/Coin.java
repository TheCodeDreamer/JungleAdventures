package Entity.View;

import Entity.Model.CoinModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Coin {
    private static BufferedImage[] coin;
    private CoinModel cm;

    public Coin(int x, int y) {
        cm = new CoinModel(x, y);
        coin = new BufferedImage[6];
        try {
            BufferedImage image = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/images/entity/coin.png"
                    )
            );
            for (int i = 0; i < coin.length; i++) {
                coin[i] = image.getSubimage(i * 16, 0, 16, 16);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getHitBox() {
        return cm.getHitBox();
    }

    public void set(int cameraX) {
        cm.set(cameraX);
    }

    public boolean isVisible() {
        return cm.isVisible();
    }

    public void setVisible(boolean visible) {
        cm.setVisible(visible);
    }

    public void update() {
        cm.update();
    }

    public void draw(Graphics2D gtd) {
        if (cm.isVisible())
            gtd.drawImage(coin[cm.getInd()], cm.getX(), cm.getY(), null);

    }
}
