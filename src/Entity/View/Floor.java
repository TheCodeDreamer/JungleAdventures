package Entity.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Floor extends Block {
    BufferedImage blockStart;
    BufferedImage blockEnd;
    private int pos;

    public Floor(int x, int y, int pos) {
        super(x, y);
        bm.setHitBox(x,y,32,32);
        try {
            BufferedImage image = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/images/entity/sheet.png"
                    )
            );
            block = image.getSubimage(160, 32, 32, 32);
            blockStart = image.getSubimage(128, 32, 32, 32);
            blockEnd = image.getSubimage(192, 32, 32, 32);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pos = pos;
    }

    @Override
    public void draw(Graphics2D gtd) {
        if (pos == 0)
            gtd.drawImage(blockStart, bm.getX(), bm.getY(), null);
        else if (pos == 2)
            gtd.drawImage(blockEnd, bm.getX(), bm.getY(), null);
        else
            gtd.drawImage(block, bm.getX(), bm.getY(), null);

    }
}
