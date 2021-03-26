package cz.jandudycha.game.event.dropItems;




import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;

public class Reward {

    private final int x;
    private int y;
    private final Handler handler;
    private Rectangle hitBox;
    private boolean pickedUp;
    private int coinValue;
    private final RewardType rewardType;

    public Reward(Handler handler, int x, int y, RewardType rewardType) {
        this.x = x;
        this.y = y;
        this.handler = handler;
        this.rewardType = rewardType;
        hitBox = new Rectangle(x - 6, y - 6, 12, 12);
        if (rewardType == RewardType.coin) {
            coinValue = 1;
        } else if (rewardType == RewardType.goldNuget) {
            coinValue = 5;
        } else if (rewardType == RewardType.goldBar) {
            coinValue = 15;
        }

    }

    public void update() {
        positionUpdate();
        pickedUp();
    }

    private void positionUpdate() {
        updateHitBox();
        if (!bottomIntersection()) {
            y = y + 2;
        }
    }

    public boolean bottomIntersection() {
        for (Rectangle rec : handler.getGame().getWorld().getListBarier()) {
            if (rec.intersects(hitBox)) {
                return true;
            }

        }
        return false;
    }


    public void render(Graphics g) {
        if (rewardType == RewardType.coin) {
            g.drawImage(Assets.coin, x + 9, y, 20, 20, null);
        } else if (rewardType == RewardType.goldNuget) {
            g.drawImage(Assets.goldNuget, x + 9, y, 20, 20, null);
        } else if (rewardType == RewardType.goldBar) {
            g.drawImage(Assets.goldBar, x + 9, y+4, 20, 20, null);
        }

    }


    public void updateHitBox() {
        hitBox = new Rectangle(x + 16, y + 10, 6, 6);
    }

    private void pickedUp() {
        if (handler.getGame().getPlayer().getPlayerMovement().getHitBoxPlayer().intersects(hitBox)) {
            setPickedUp(true);
            handler.getGame().getPlayer().setCoins(handler.getGame().getPlayer().getCoins() + coinValue);
        }
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
