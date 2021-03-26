package cz.jandudycha.game.main.states;




import cz.jandudycha.game.entity.player.Player;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.main.KeyInput;
import cz.jandudycha.game.main.RenderLayer;
import cz.jandudycha.game.main.gameOver.ScoreManager;
import cz.jandudycha.game.main.states.buyMenuStuff.Column;
import cz.jandudycha.game.texture.Assets;
import cz.jandudycha.game.ui.UIManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BuyMenu extends State {
    private int clock = 0;
    private int messageColor = 0;
    private String outcomeMessage = "";
    private final RenderLayer renderLayer;
    private final UIManager uiManager;
    private final Column columnGuns;
    private final Column columnAmmo;


    BufferedImage[] pistolAmmo = {Assets.bullet9mm, Assets.bullet9mm};
    BufferedImage[] shotgunAmmo = {Assets.gaugeBuckshot, Assets.gaugeBuckshot, Assets.gaugeBuckshot};
    BufferedImage[] SMGAmmo = {Assets.bullet9mm, Assets.bullet9mm, Assets.bullet9mm, Assets.bullet9mm};
    BufferedImage[] AKAmmo = {Assets.bullet556, Assets.bullet556, Assets.bullet556, Assets.bullet556};
    BufferedImage[] deagleAmmo = {Assets.bullet50EA};

    public BuyMenu(RenderLayer rendrLayer, KeyInput keyInput) {
        super(rendrLayer, keyInput);
        this.renderLayer = rendrLayer;
        uiManager = new UIManager();
        columnAmmo = new Column( 580, 110, 120);
        columnGuns = new Column( 290, 110, 200);

        fillColumns();

    }

    private void fillColumns() {


        columnGuns.addItem(this, uiManager, Assets.pistol1911MainPicture, 25, "     1911", 32, 10, false, ItemType.pistol1911);
        columnGuns.addItem(this, uiManager, Assets.shoutgunMainPicture, -8, "     Shotgun", 32, 120, false, ItemType.shotGun);
        columnGuns.addItem(this, uiManager, Assets.SMGMainPicture, 7, "     SMG", 32, 150, false, ItemType.SMG);
        columnGuns.addItem(this, uiManager, Assets.AK_47MainPictureSmall, 0, "     AK-47", 32, 300, false, ItemType.AK47);
        columnGuns.addItem(this, uiManager, Assets.desertEagleMainPicture, 25, "Desert Eagle", 32, 500, false, ItemType.desertEagle);


        columnAmmo.addItem(this, uiManager, pistolAmmo, 20, "pistol rounds", 30, 5, true, ItemType.ammoBox9mm);
        columnAmmo.addItem(this, uiManager, shotgunAmmo, 20, "shotgun rounds", 25, 20, true, ItemType.ammoBoxBuckShot);
        columnAmmo.addItem(this, uiManager, SMGAmmo, 10, "SMG rounds", 60, 10, true, ItemType.ammoBoxSMG);
        columnAmmo.addItem(this, uiManager, AKAmmo, 10, "7.62 rounds", 30, 20, true, ItemType.ammoBox556);
        columnAmmo.addItem(this, uiManager, deagleAmmo, 30, "50.EA rounds", 8, 50, true, ItemType.ammoBox50EA);
    }

    @Override
    public void update() {
        renderLayer.getKeyInput().setUIManager(uiManager);
        if (keyInput.isbButton() && renderLayer.getKeyInput().isWasbButton()) {
            renderLayer.getKeyInput().setWasbButton(true);
            renderLayer.getKeyInput().setUIManager(null);
            outcomeMessage = "";
            State.setState(renderLayer.getGame());
        }

        if (!outcomeMessage.equals("")) {
            if (clock == 50) {
                clock = 0;
                outcomeMessage = "";
            } else {
                clock++;
            }
        } else {
            clock = 0;
        }

        columnAmmo.update(0, columnAmmo.getBuyMenuItems().size());
        columnGuns.update(1, columnGuns.getBuyMenuItems().size());
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.buy_menu, 0, 0, null);
        g.setFont(Assets.coinFont);
        g.drawImage(Assets.coins, 285, 37, null);
        g.setColor(Color.orange);
        g.drawString("" + renderLayer.getGame().getCoin(), 340, 64);
        g.setFont(Assets.gunFont);

        columnAmmo.render(g);
        columnGuns.render(g);
        uiManager.render(g);

        displayMessage(g);


        g.setFont(Assets.coinFont);
    }

    private void displayMessage(Graphics g) {
        if (messageColor == 0) {
            g.setColor(new Color(0, 198, 0));
        } else if (messageColor == 1) {
            g.setColor(new Color(198, 0, 0));
        } else {
            g.setColor(new Color(255, 199, 0));
        }
        g.drawString(outcomeMessage, 430, 60);


    }

    public void buy(ItemType itemType) {
        clock = 0;
        switch (itemType) {

            case pistol1911: {
                if (!renderLayer.getGame().isUnlocked(1)) {
                    if (renderLayer.getGame().getCoin() >= columnGuns.getBuyMenuItems().get(columnGuns.getPositionOfItem(ItemType.pistol1911)).getPrice()) {
                        outcomeMessage = "Item successfully bought!";
                        messageColor = 0;
                        renderLayer.getGame().setCoin(renderLayer.getGame().getCoin() - columnGuns.getBuyMenuItems().get(columnGuns.getPositionOfItem(ItemType.pistol1911)).getPrice());
                        renderLayer.getGame().unlockGun(1);
                    } else {
                        outcomeMessage = "Not enough money!";
                        messageColor = 1;
                    }
                } else {
                    outcomeMessage = "You already own this gun!";
                    messageColor = 2;
                }
                break;
            }
            case shotGun: {
                if (!renderLayer.getGame().isUnlocked(2)) {
                    if (renderLayer.getGame().getCoin() >= columnGuns.getBuyMenuItems().get(columnGuns.getPositionOfItem(ItemType.shotGun)).getPrice()) {
                        outcomeMessage = "Item successfully bought!";
                        messageColor = 0;
                        renderLayer.getGame().setCoin(renderLayer.getGame().getCoin() - columnGuns.getBuyMenuItems().get(columnGuns.getPositionOfItem(ItemType.shotGun)).getPrice());
                        renderLayer.getGame().unlockGun(2);
                    } else {
                        outcomeMessage = "Not enough money!";
                        messageColor = 1;
                    }
                } else {
                    outcomeMessage = "You already own this gun!";
                    messageColor = 2;
                }
                break;
            }
            case SMG: {
                if (!renderLayer.getGame().isUnlocked(3)) {

                    if (renderLayer.getGame().getCoin() >= columnGuns.getBuyMenuItems().get(columnGuns.getPositionOfItem(ItemType.SMG)).getPrice()) {
                        outcomeMessage = "Item successfully bought!";
                        messageColor = 0;
                        renderLayer.getGame().setCoin(renderLayer.getGame().getCoin() - columnGuns.getBuyMenuItems().get(columnGuns.getPositionOfItem(ItemType.SMG)).getPrice());
                        renderLayer.getGame().unlockGun(3);
                    } else {
                        outcomeMessage = "Not enough money!";
                        messageColor = 1;
                    }
                } else {
                    outcomeMessage = "You already own this gun!";
                    messageColor = 2;
                }
                break;
            }


            case AK47: {
                if (!renderLayer.getGame().isUnlocked(4)) {
                    if (renderLayer.getGame().getCoin() >= columnGuns.getBuyMenuItems().get(columnGuns.getPositionOfItem(ItemType.AK47)).getPrice()) {
                        outcomeMessage = "Item successfully bought!";
                        messageColor = 0;
                        renderLayer.getGame().setCoin(renderLayer.getGame().getCoin() - columnGuns.getBuyMenuItems().get(columnGuns.getPositionOfItem(ItemType.AK47)).getPrice());
                        renderLayer.getGame().unlockGun(4);
                    } else {
                        outcomeMessage = "Not enough money!";
                        messageColor = 1;
                    }
                } else {
                    outcomeMessage = "You already own this gun!";
                    messageColor = 2;
                }
                break;
            }
            case desertEagle: {
                if (!renderLayer.getGame().isUnlocked(5)) {

                    if (renderLayer.getGame().getCoin() >= columnGuns.getBuyMenuItems().get(columnGuns.getPositionOfItem(ItemType.desertEagle)).getPrice()) {
                        outcomeMessage = "Item successfully bought!";
                        messageColor = 0;
                        renderLayer.getGame().setCoin(renderLayer.getGame().getCoin() - columnGuns.getBuyMenuItems().get(columnGuns.getPositionOfItem(ItemType.desertEagle)).getPrice());
                        renderLayer.getGame().unlockGun(5);
                    } else {
                        outcomeMessage = "Not enough money!";
                        messageColor = 1;
                    }
                } else {
                    outcomeMessage = "You already own this gun!";
                    messageColor = 2;
                }
                break;
            }
            case ammoBox9mm: {
                if (renderLayer.getGame().isUnlocked(1)) {
                    if (renderLayer.getGame().getCoin() >= columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBox9mm)).getPrice()) {
                        outcomeMessage = "Item successfully bought!";
                        messageColor = 0;
                        renderLayer.getGame().setCoin(renderLayer.getGame().getCoin() - columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBox9mm)).getPrice());
                        renderLayer.getGame().setAmmo(ItemType.ammoBox9mm, columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBox9mm)).getBulkAmount());
                    } else {
                        outcomeMessage = "Not enough money!";
                        messageColor = 1;
                    }
                } else {
                    outcomeMessage = "Buy gun for this ammo first!";
                    messageColor = 2;
                }
                break;
            }
            case ammoBoxBuckShot: {
                if (renderLayer.getGame().isUnlocked(2)) {
                    if (renderLayer.getGame().getCoin() >= columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBoxBuckShot)).getPrice()) {
                        outcomeMessage = "Item successfully bought!";
                        messageColor = 0;
                        renderLayer.getGame().setCoin(renderLayer.getGame().getCoin() - columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBoxBuckShot)).getPrice());
                        renderLayer.getGame().setAmmo(ItemType.ammoBoxBuckShot, columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBoxBuckShot)).getBulkAmount());

                    } else {
                        outcomeMessage = "Not enough money!";
                        messageColor = 1;
                    }
                } else {
                    outcomeMessage = "Buy gun for this ammo first!";
                    messageColor = 2;
                }
                break;
            }
            case ammoBox556: {
                if (renderLayer.getGame().isUnlocked(4)) {
                    if (renderLayer.getGame().getCoin() >= columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBox556)).getPrice()) {
                        outcomeMessage = "Item successfully bought!";
                        messageColor = 0;
                        renderLayer.getGame().setCoin(renderLayer.getGame().getCoin() - columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBox556)).getPrice());
                        renderLayer.getGame().setAmmo(ItemType.ammoBox556, columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBox556)).getBulkAmount());

                    } else {
                        outcomeMessage = "Not enough money!";
                        messageColor = 1;
                    }
                } else {
                    outcomeMessage = "Buy gun for this ammo first!";
                    messageColor = 2;
                }
                break;
            }
            case ammoBox50EA: {
                if (renderLayer.getGame().isUnlocked(5)) {
                    if (renderLayer.getGame().getCoin() >= columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBox50EA)).getPrice()) {
                        outcomeMessage = "Item successfully bought!";
                        messageColor = 0;
                        renderLayer.getGame().setCoin(renderLayer.getGame().getCoin() - columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBox50EA)).getPrice());
                        renderLayer.getGame().setAmmo(ItemType.ammoBox50EA, columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBox50EA)).getBulkAmount());

                    } else {
                        outcomeMessage = "Not enough money!";
                        messageColor = 1;
                    }
                } else {
                    outcomeMessage = "Buy gun for this ammo first!";
                    messageColor = 2;
                }
                break;
            }
            case ammoBoxSMG: {
                if (renderLayer.getGame().isUnlocked(3)) {
                    if (renderLayer.getGame().getCoin() >= columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBoxSMG)).getPrice()) {
                        outcomeMessage = "Item successfully bought!";
                        messageColor = 0;
                        renderLayer.getGame().setCoin(renderLayer.getGame().getCoin() - columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBoxSMG)).getPrice());
                        renderLayer.getGame().setAmmo(ItemType.ammoBoxSMG, columnAmmo.getBuyMenuItems().get(columnAmmo.getPositionOfItem(ItemType.ammoBoxSMG)).getBulkAmount());


                    } else {
                        outcomeMessage = "Not enough money!";
                        messageColor = 1;
                    }
                } else {
                    outcomeMessage = "Buy gun for this ammo first!";
                    messageColor = 2;
                }
                break;
            }
        }

    }


    @Override
    public void setCoin(int amt) {

    }

    @Override
    public int getCoin() {
        return 0;
    }

    @Override
    public void setAmmo(ItemType itemType, int amt) {

    }

    @Override
    public void unlockGun(int numOfGun) {

    }

    @Override
    public Events getEvents() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }
    @Override
    public boolean isUnlocked(int numOfGun) {
        return false;
    }

    @Override
    public ScoreManager getScoreManager() {
        return null;
    }
}
