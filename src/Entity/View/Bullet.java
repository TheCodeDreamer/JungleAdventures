package Entity.View;

import Entity.Model.BulletModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet {
    private BulletModel bm;
    private BufferedImage image;

    public Bullet(int x, int y, boolean right) {
        bm = new BulletModel(x, y, right);
        try {
            BufferedImage spriteimage = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/images/entity/bullet.png"
                    )
            );
            image = spriteimage.getSubimage(0, 3, 10, 3);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public int getStartX() {
        return bm.getStartX();
    }

    public void set(int offset) {
        bm.set(offset);
    }

    public Rectangle getHitBox() {
        return bm.getHitBox();
    }

    public void draw(Graphics2D gtd) {
        gtd.drawImage(image, bm.getX(), bm.getY(), null);
    }


}
