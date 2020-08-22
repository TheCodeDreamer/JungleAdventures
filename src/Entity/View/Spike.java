package Entity.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spike extends Block {
    public Spike(int x, int y) {
        super(x, y);
        bm.setHitBox(x,y,32,28);
        try {
            BufferedImage image = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/images/entity/spike.png"
                    )
            );
            block = image;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
