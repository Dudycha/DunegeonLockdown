package cz.jandudycha.game.ui;


import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private final BufferedImage[] images;
    private final ClickListener clicker;


    public UIImageButton(int x, int y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

        if (isHovering()) {
            g.drawImage(images[1], x, y, width, height, null);
        } else {
            g.drawImage(images[0], x, y, width, height, null);
        }
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
