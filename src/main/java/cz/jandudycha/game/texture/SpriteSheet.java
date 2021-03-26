package cz.jandudycha.game.texture;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private final BufferedImage image;
    private final int subImageWidth, subImageHeight;

    public SpriteSheet(BufferedImage image, int subImageWidth, int subImageHeight) {
        this.image = image;
        this.subImageWidth = subImageWidth;
        this.subImageHeight = subImageHeight;
    }

    public BufferedImage crop(int x, int y) {
        return image.getSubimage(x, y, subImageWidth, subImageHeight);
    }

}
