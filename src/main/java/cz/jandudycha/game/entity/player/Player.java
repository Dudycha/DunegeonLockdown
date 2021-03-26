package cz.jandudycha.game.entity.player;



import cz.jandudycha.game.entity.Entity;
import cz.jandudycha.game.entity.player.playerAttack.PlayerAttack;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;


public class Player extends Entity {
    private final PlayerMovement playerMovement;
    private final PlayerAnimation playerAnimation;
    private final PlayerAttack playerAttack;
    private boolean receivedHit = false;
    private int HPlast = getHitpoints();
    private int clock = 0;
    private int coins = 0, score = 0, enemiesKilled = 0, totalDMGrecieved = 0,bulletsFired = 0,coinsEarned = 0;


    public Player(Handler handler, int x, int y) {
        super(handler, x, y);
        super.setHitpoints(18); // deffault = 18
        playerMovement = new PlayerMovement(handler, this, super.getX(), super.getY());
        playerAnimation = new PlayerAnimation(handler, super.getX(), super.getY());
        playerAttack = new PlayerAttack(handler);
    }

    @Override
    public void update() {
        playerMovement.update();
        super.setX(playerMovement.getX());
        super.setY(playerMovement.getY());
        playerAnimation.update();
        playerAttack.update();
        checkForHitpointChange();
        if(playerMovement.getHitBoxPlayer().intersects(getHandler().getGame().getWorld().getInstaKillBox())){
            super.setHitpoints(0);
        }
    }

    private void checkForHitpointChange() {
        if (HPlast > getHitpoints()) {
            receivedHit = true;

        }
        if (receivedHit) {
            if (clock == 3) {
                clock = 0;
                receivedHit = false;
            } else {
                clock++;
            }
        }
        HPlast = getHitpoints();
    }


    @Override
    public void render(Graphics g) {
        playerAnimation.render(g);
        playerAttack.render(g);
        drawHP(g);
        g.setColor(Color.green);
    }



    private void drawHP(Graphics g) {
        int posun = 30;
        int vychozi = 737;
        int HP = getHitpoints();
        int vysledna = vychozi;
        while (HP > 0) {
            if (HP >= 2) {
                vysledna += posun;
                g.drawImage(Assets.srdce, vysledna, 507, null);
                HP = HP - 2;
            } else {
                vysledna += posun;
                g.drawImage(Assets.pulSrdce, vysledna, 507, null);
                HP--;
            }
        }

    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public PlayerMovement getPlayerMovement() {
        return playerMovement;
    }

    public PlayerAttack getPlayerAttack() {
        return playerAttack;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public boolean isReceivedHit() {
        return receivedHit;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    public int getBulletsFired() {
        return bulletsFired;
    }

    public int getTotalDMGrecieved() {
        return totalDMGrecieved;
    }

    public void setTotalDMGrecieved(int totalDMGrecieved) {
        this.totalDMGrecieved = totalDMGrecieved;
    }

    public int getCoinsEarned() {
        return coinsEarned;
    }

    public void setCoinsEarned(int coinsEarned) {
        this.coinsEarned = coinsEarned;
    }

    public void setBulletsFired(int bulletsFired) {
        this.bulletsFired = bulletsFired;
    }
}
