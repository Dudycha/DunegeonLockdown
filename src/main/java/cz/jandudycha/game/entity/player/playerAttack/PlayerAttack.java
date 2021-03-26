package cz.jandudycha.game.entity.player.playerAttack;





import cz.jandudycha.game.entity.player.playerAttack.weapon.*;
import cz.jandudycha.game.main.Handler;

import java.awt.*;

public class PlayerAttack {


    private final Handler handler;
    private final Sword sword;
    private final Pistol1911 pistol1911;
    private final SMG smg;
    private final ShotGun shotGun;
    private final AK47 ak47;
    private final DesertEagle desertEagle;
    private int gunInUse = 0;
    private boolean wheenUsed = false;

    public PlayerAttack(Handler handler) {
        this.handler = handler;
        sword = new Sword(handler);
        pistol1911 = new Pistol1911(handler);
        smg = new SMG(handler);
        shotGun = new ShotGun(handler);
        ak47 = new AK47(handler);
        desertEagle = new DesertEagle(handler);

        sword.setUnlocked(true);
      //  pistol1911.setUnlocked(true);
       // smg.setUnlocked(true);
      //  smg.setAmmoInReserve(500);
      //    shotGun.setUnlocked(true);

     //   ak47.setUnlocked(true);
     //   ak47.setAmmoInReserve(200);

      //  desertEagle.setUnlocked(true);

    }


    public void update() {

        // deff:sword  1:pistol  2:shotgun  3:SMG  4:AK47
        chceckForGunInUse();

        switch (gunInUse) {
            case 1: {
                pistol1911.update();
                break;
            }
            case 2: {
                shotGun.update();
                break;
            }
            case 3: {
                smg.update();
                break;
            }
            case 4: {
                ak47.update();
                break;
            }
            case 5: {
                desertEagle.update();
                break;
            }
            default: {
                sword.update();
                break;
            }


        }


    }

    private void chceckForGunInUse() {
        if (handler.getGame().getKeyInput().isNumberChanged()) {
            handler.getGame().getKeyInput().setNumberChanged(false);
            wheenUsed = false;
        }
        int lastPressed = handler.getGame().getKeyInput().getnumberPressed();
        if (!wheenUsed) {
            if (isUnlocked(lastPressed)) {
                gunInUse = lastPressed;
            }
        }

        if (handler.getGame().getKeyInput().getWheelMove() != 0) {

            if (handler.getGame().getKeyInput().getWheelMove() > 0) {

                if (gunInUse > 0) {

                    int pom = gunInUse;
                    pom--;
                    while (!handler.getGame().isUnlocked(pom) && pom >= 0) {
                        pom--;
                    }
                    gunInUse = pom;
                    wheenUsed = true;

                }
            } else {
                if (gunInUse < 5) {
                    int pom = gunInUse;
                    pom++;
                    while (!handler.getGame().isUnlocked(pom) && pom < 6) {
                        pom++;
                    }
                    if (handler.getGame().isUnlocked(pom)) {
                        gunInUse = pom;
                        wheenUsed = true;
                    }
                }


            }
        }


    }

    private boolean isUnlocked(int getnumberPressed) {
        switch (getnumberPressed) {
            case 0: {
                return true;
            }
            case 1: {
                return pistol1911.isUnlocked();
            }
            case 2: {
                return shotGun.isUnlocked();
            }
            case 3: {
                return smg.isUnlocked();
            }
            case 4: {
                return ak47.isUnlocked();
            }
            case 5: {
                return desertEagle.isUnlocked();
            }


        }
        return false;
    }

    public void unlock(int unlockGun) {
        switch (unlockGun) {

            case 1: {
                pistol1911.setUnlocked(true);
                break;
            }
            case 2: {
                shotGun.setUnlocked(true);
                break;
            }
            case 3: {
                smg.setUnlocked(true);
                break;
            }
            case 4: {
                ak47.setUnlocked(true);
                break;
            }
            case 5: {
                desertEagle.setUnlocked(true);
                break;
            }

        }
    }

    public void render(Graphics g) {
        switch (gunInUse) {
            case 1: {
                pistol1911.render(g);
                break;
            }
            case 2: {
                shotGun.render(g);
                break;
            }
            case 3: {
                smg.render(g);
                break;
            }
            case 4: {
                ak47.render(g);
                break;
            }
            case 5: {
                desertEagle.render(g);
                break;
            }
            default: {
                sword.render(g);
                break;
            }


        }
    }


    public Pistol1911 getPistol1911() {
        return pistol1911;
    }

    public SMG getSmg() {
        return smg;
    }

    public ShotGun getShotGun() {
        return shotGun;
    }

    public AK47 getAk47() {
        return ak47;
    }

    public DesertEagle getDesertEagle() {
        return desertEagle;
    }
}
