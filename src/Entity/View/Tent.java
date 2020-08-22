package Entity.View;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Tent extends Block {
    public Tent(int x, int y) {
        super(x, y);
        bm.setHitBox(x + 33, y + 3, 22, 66);
        try {
            block = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/images/entity/tent.png"
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(int cameraX) {
        bm.set(cameraX);
        bm.getHitBox().x = bm.getX() + 33;
    }
}
