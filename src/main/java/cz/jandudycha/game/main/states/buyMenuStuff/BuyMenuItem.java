package cz.jandudycha.game.main.states.buyMenuStuff;




import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.main.states.BuyMenu;
import cz.jandudycha.game.texture.Assets;
import cz.jandudycha.game.ui.ClickListener;
import cz.jandudycha.game.ui.UIImageButton;
import cz.jandudycha.game.ui.UIManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BuyMenuItem {
    private final BufferedImage[] itemImage;
    private final int bulkAmount, price;
    private final int x;
    private int y;
    private final int width;
    private int shift = 0;
    private final int pictureShift;
    private final int shiftAdd;
    private final String name;
    private final UIManager uiManager;
    private final boolean ammo;
    private final ItemType itemType;

    public BuyMenuItem(final BuyMenu buyMenu, UIManager uiManager, int x, int y, BufferedImage[] itemImage, int pistureShift, String name, int bulkAmount,
                       int price, int width, boolean ammo, final ItemType itemType) {
        this.uiManager = uiManager;
        this.x = x;
        this.y = y;
        this.name = name;
        this.pictureShift = pistureShift;
        this.itemImage = itemImage;
        this.bulkAmount = bulkAmount;
        this.price = price;
        this.width = width;
        this.ammo = ammo;
        this.itemType = itemType;

        if (ammo) {
            shiftAdd = 15;
        } else {
            shiftAdd = 32;
        }

        uiManager.addObject(new UIImageButton(x + width + 10, y, 60, 30, Assets.btnBuy, new ClickListener() {
            @Override
            public void onClick() {
                buyMenu.buy(itemType);
            }
        }));

    }

    public void update(int index, int columnNumber, int columnLength) {
        index += columnNumber * columnLength;
        uiManager.getObjects().get(index).setY(y);
        uiManager.getObjects().get(index).updateBounds();

    }

    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawString(name, x + 20, y - 7);

        g.drawImage(Assets.coin, x + width - 25, y - 5, null);
        if (price < 10) {
            g.drawString("" + price, x + width - 30, y + 18);
        } else if (price < 100) {
            g.drawString("" + price, x + width - 38, y + 18);
        } else {
            g.drawString("" + price, x + width - 47, y + 18);
        }
        if (ammo) {
            g.setFont(Assets.smallFont);
            g.drawString(bulkAmount + " x", x + width - 30, y + 32);
            g.setFont(Assets.gunFont);
        }

        for (BufferedImage bufferedImage : itemImage) {
            g.drawImage(bufferedImage, x + shift + pictureShift, y, null);
            shift += shiftAdd;
        }
        shift = 0;

        g.setColor(Color.BLACK);
        g.drawLine(x, y + 37, x + width, y + 37);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBulkAmount() {
        return bulkAmount;
    }

    public int getPrice() {
        return price;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
