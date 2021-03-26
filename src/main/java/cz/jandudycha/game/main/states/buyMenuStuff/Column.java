package cz.jandudycha.game.main.states.buyMenuStuff;




import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.main.states.BuyMenu;
import cz.jandudycha.game.ui.UIManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Column {

    private final int x, y, width;
    private final ArrayList<BuyMenuItem> buyMenuItems = new ArrayList<>();

    public Column(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;

    }

    public void update(int columnNumber, int columnLength) {
        for (BuyMenuItem buyMenuItem : buyMenuItems) {
            buyMenuItem.update(buyMenuItems.indexOf(buyMenuItem), columnNumber, columnLength);
        }

        for (int i = 0; i < buyMenuItems.size(); i++) {
            if (i == 0) {
                buyMenuItems.get(i).setY(y);
            } else {
                int spacingBetweenItems = 70;
                buyMenuItems.get(i).setY(buyMenuItems.get(i - 1).getY() + spacingBetweenItems);
            }
        }
    }

    public void render(Graphics g) {
        for (BuyMenuItem buyMenuItem : buyMenuItems) {
            buyMenuItem.render(g);
        }
    }

    public void addItem(BuyMenu buyMenu, UIManager ui, BufferedImage[] images, int pictureShift, String name, int bulkAmmount, int price, boolean ammo, ItemType itemType) {
        buyMenuItems.add(new BuyMenuItem(buyMenu, ui, x, y, images, pictureShift, name, bulkAmmount, price, width, ammo, itemType));
    }

    public ArrayList<BuyMenuItem> getBuyMenuItems() {
        return buyMenuItems;
    }

    public int getPositionOfItem(ItemType itemType) {
        int position = 0;
        for (BuyMenuItem buyMenuItem : buyMenuItems) {
            if (buyMenuItem.getItemType() == itemType) {
                return position;
            } else {
                position++;
            }
        }
        return -1;
    }
}
