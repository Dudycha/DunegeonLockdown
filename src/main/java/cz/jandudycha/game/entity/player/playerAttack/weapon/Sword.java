package cz.jandudycha.game.entity.player.playerAttack.weapon;




import cz.jandudycha.game.entity.enemy.Enemy;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;

public class Sword extends Weapon {

    private int swordAnimationTick = 0;
    private boolean attacking, swordAttackTick = true;

    public Sword(Handler handler) {
        super(handler);

    }

    public void update() {
        super.setX(getHandler().getGame().getPlayer().getX());
        super.setY(getHandler().getGame().getPlayer().getY());
        if (getHandler().getGame().getKeyInput().isLeftMouse()) {
            attacking = true;
        }

        if (attacking) {
            if (swordAttackTick) {
                calculateHitLine();
                swordAttackTick = false;
            }

            swordAnimationTick++;
            if (swordAnimationTick == Assets.swordRight.length) {
                attacking = false;
                swordAnimationTick = 0;
                swordAttackTick = true;
            }
        }

    }

    private void calculateHitLine() {

        Rectangle swordHitBox;
        if (getHandler().getGame().getPlayer().getPlayerMovement().isPlayerFacingRight()) {
            swordHitBox = new Rectangle(getX() + 30, getY() + 23, 55, 35);
        } else {
            swordHitBox = new Rectangle(getX() - 20, getY() + 23, 55, 35);
        }

        for (Enemy enemy : getHandler().getGame().getEvents().getListOfEnemies()) {
            if (enemy.getEnemyHitBox().intersects(swordHitBox) && enemy.isAlive()) {
                enemy.reciveHit(5);
                break;
            }
        }


    }

    public void render(Graphics g) {
        if (attacking) {
            if (getHandler().getGame().getPlayer().getPlayerMovement().isPlayerFacingRight()) {
                g.drawImage(Assets.swordRight[swordAnimationTick], super.getX() + 43, super.getY() - 13, 64, 64, null);
            } else {
                g.drawImage(Assets.swordLeft[swordAnimationTick], super.getX() - 43, super.getY() - 13, 64, 64, null);
            }

        }
        renderWeaponPictureAndAmmo(g);

    }

    @Override
    public void renderWeaponPictureAndAmmo(Graphics g) {
        for (int i = 0; i < 5; i++) {
            g.drawImage(Assets.swordMainPicture[i], 350 + (i * 32), 507, null);
        }
        g.setColor(Color.yellow);
        g.setFont(Assets.coinFont);
        g.drawString("AMMO : Infinite", 17, 530);

    }



}
