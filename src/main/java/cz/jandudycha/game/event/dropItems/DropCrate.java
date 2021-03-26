package cz.jandudycha.game.event.dropItems;




import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.Random;

public class DropCrate {

    private final Handler handler;
    private final Random rnd;
    private final int x;
    private int y;
    private ItemType itemType;
    private final int GRAVITY = 1;
    private Rectangle collisionBox;
    private boolean onGround = false;
    private boolean tooken = false;


    public DropCrate(Handler handler, ItemType itemType) {
        this.handler = handler;
        this.itemType = itemType;
        rnd = new Random();
        y = -50;
        x = rnd.nextInt(900) + 50;
        collisionBox = new Rectangle(x, y, 32, 32);
        if (itemType == ItemType.random) {
            chooseItem();
        }
    }

    private void chooseItem() {
        boolean done = false;
        if (handler.getGame().isUnlocked(1) || handler.getGame().isUnlocked(2) ||
                handler.getGame().isUnlocked(3) || handler.getGame().isUnlocked(4) || handler.getGame().isUnlocked(5)) {
            while (!done) {
                int rndVal = rnd.nextInt(5) + 1;
                if (handler.getGame().isUnlocked(rndVal) && rndVal == 1) {
                    itemType = ItemType.ammoBox9mm;
                    done = true;
                }
                if (handler.getGame().isUnlocked(rndVal) && rndVal == 2) {
                    itemType = ItemType.ammoBoxBuckShot;
                    done = true;
                }
                if (handler.getGame().isUnlocked(rndVal) && rndVal == 3) {
                    itemType = ItemType.ammoBoxSMG;
                    done = true;
                }
                if (handler.getGame().isUnlocked(rndVal) && rndVal == 4) {
                    itemType = ItemType.ammoBox556;
                    done = true;
                }
                if (handler.getGame().isUnlocked(rndVal) && rndVal == 5) {
                    itemType = ItemType.ammoBox50EA;
                    done = true;
                }

            }
        } else {
            itemType = ItemType.ammoBox9mm;
        }


    }

    public void update() {
        collisionBox = new Rectangle(x, y, 32, 32);

        if (!onGround) {
            if (itemType != ItemType.apple) {
                y += GRAVITY;
            } else {
                y += 3;
            }
            for (Rectangle worldBarier : handler.getGame().getWorld().getListBarier()) {
                if (worldBarier.intersects(collisionBox)) {
                    onGround = true;
                }
            }
        }

        if (handler.getGame().getPlayer().getPlayerMovement().getHitBoxPlayer().intersects(collisionBox)) {

            switch (itemType) {
                case ammoBox9mm: {
                    handler.getGame().getPlayer().getPlayerAttack().getPistol1911().addAmmoReserve(50);
                    tooken = true;
                    break;
                }
                case ammoBoxSMG: {
                    handler.getGame().getPlayer().getPlayerAttack().getSmg().addAmmoReserve(105);
                    tooken = true;
                    break;
                }
                case ammoBoxBuckShot: {
                    handler.getGame().getPlayer().getPlayerAttack().getShotGun().addAmmoReserve(24);
                    tooken = true;
                    break;
                }
                case ammoBox556: {
                    handler.getGame().getPlayer().getPlayerAttack().getAk47().addAmmoReserve(90);
                    tooken = true;
                    break;
                }
                case ammoBox50EA: {
                    handler.getGame().getPlayer().getPlayerAttack().getDesertEagle().addAmmoReserve(20);
                    tooken = true;
                    break;
                }
                case goldNuget: {
                    handler.getGame().getPlayer().setCoins(handler.getGame().getPlayer().getCoins() + 40);
                    tooken = true;
                    break;
                }
                case goldBar: {
                    handler.getGame().getPlayer().setCoins(handler.getGame().getPlayer().getCoins() + 80);
                    tooken = true;
                    break;
                }
                case apple: {
                    handler.getGame().getPlayer().setHitpoints(handler.getGame().getPlayer().getHitpoints() + 4);
                    if (handler.getGame().getPlayer().getHitpoints() > 18) {
                        handler.getGame().getPlayer().setHitpoints(18);
                    }
                    tooken = true;
                    break;
                }
                case medKit: {
                    handler.getGame().getPlayer().setHitpoints(18);
                    tooken = true;
                    break;
                }
            }
        }

    }

    public void render(Graphics g) {
        if (!onGround && itemType != ItemType.apple) {
            g.drawImage(Assets.parachute, x - 9, y - 32 - 18, 50, 50, null);
        }

        switch (itemType) {
            case ammoBox9mm: {
                g.drawImage(Assets.ammoBox9mm, x, y, null);
                break;
            }
            case ammoBoxSMG: {
                g.drawImage(Assets.ammoBoxSMG, x, y, null);
                break;
            }
            case ammoBoxBuckShot: {
                g.drawImage(Assets.ammoBoxBuckShot, x, y, null);
                break;
            }
            case ammoBox556: {
                g.drawImage(Assets.ammoBox556, x, y, null);
                break;
            }
            case ammoBox50EA: {
                g.drawImage(Assets.ammoBox50EA, x, y, null);
                break;
            }
            case goldNuget: {
                g.drawImage(Assets.goldnugetBox, x, y, null);

                break;
            }
            case goldBar: {
                g.drawImage(Assets.goldBarBox, x, y, null);
                break;
            }
            case apple: {
                g.drawImage(Assets.apple, x, y, null);
                break;
            }
            case medKit: {
                g.drawImage(Assets.medkit, x, y, null);
                break;
            }
        }

    }

    public boolean isTooken() {
        return tooken;
    }
}
