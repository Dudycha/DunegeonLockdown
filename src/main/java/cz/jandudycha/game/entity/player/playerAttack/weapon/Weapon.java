package cz.jandudycha.game.entity.player.playerAttack.weapon;




import cz.jandudycha.game.entity.player.playerAttack.PositionOfGun;
import cz.jandudycha.game.main.Handler;

import java.awt.*;

public abstract class Weapon {

    private final Handler handler;
    private PositionOfGun positionOfGun;
    private int x, y;
    private int totalAmmo = 100, ammoInMagazine = 0;

    protected int ammoInReserve = 0;
    private boolean unlocked = false;

    int[][] arrayOfShotBounds = {{0, 283, 329, 283},
            {329, 283, 329, 330},
            {329, 330, 450, 330},
            {450, 330, 450, 490},
            {510, 490, 510, 330},
            {510, 330, 628, 330},
            {628, 330, 628, 277},
            {628, 277, 750, 277},
            {750, 276, 750, 226},
            {750, 226, 1035, 226},

            {415, 225, 565, 225},
            {565, 225, 565, 256},
            {565, 256, 415, 256},
            {415, 256, 415, 225},

    };



    public Weapon(Handler handler) {
        this.handler = handler;
    }

    public abstract void update();

    public abstract void render(Graphics g);


    public void renderWeaponPictureAndAmmo(Graphics g) {

    }

    public Handler getHandler() {
        return handler;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPositionOfGun(PositionOfGun positionOfGun) {
        this.positionOfGun = positionOfGun;
    }


    public int getLengthOfArrayOfShotBounds() {
        return arrayOfShotBounds.length;
    }
    public void addAmmoReserve(int amt){
        ammoInReserve += amt;
    }

    public int getAmmoInReserve() {
        return ammoInReserve;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public void setAmmoInReserve(int ammoInReserve) {
        this.ammoInReserve = ammoInReserve;
    }
}
