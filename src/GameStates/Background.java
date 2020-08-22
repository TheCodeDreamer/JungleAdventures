package GameStates;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {

    private BufferedImage image;
    private double x;
    private double y;


    public Background(String s) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        x = 0;
        y = 0;
    }

    public void update(double dx) {
        x += dx;
        while (x <= -GamePanel.WIDTH) x += GamePanel.WIDTH;
        while (x >= GamePanel.WIDTH) x -= GamePanel.WIDTH;
    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, null);
        if (x < 0) {
            g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y, null);

        }
        if (x > 0) {
            g.drawImage(image, (int) x - GamePanel.WIDTH, (int) y, null);

        }
    }
}
