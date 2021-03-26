package cz.jandudycha.game.entity.enemy;



import cz.jandudycha.game.entity.Entity;
import cz.jandudycha.game.main.Handler;

import java.awt.*;



public abstract class Enemy extends Entity {

    private final Handler handler;
    private Rectangle enemyHitBox;
    private Rectangle  downBounds, rightBounds, leftBounds;
    private boolean alive = true;
    private int HP;
    private int despawnTimer = 300;
    private boolean stillAfterSpawning = true;
    private final int[] hitVectors = new int[8];


    public Enemy(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        enemyHitBox = new Rectangle(super.getX() + 5, super.getY() + 7, 32, 26);
    }

    @Override
    public abstract void update();


    @Override
    public abstract void render(Graphics g);


    public void despawn() {
        if (!isAlive()) {
            despawnTimer--;
        }
    }

    public void checkForFellOffMap() {
        if (enemyHitBox.intersects(handler.getGame().getWorld().getInstaKillBox())) {
            alive = false;
        }
    }

    public Rectangle getEnemyHitBox() {
        return enemyHitBox;
    }

    public void setEnemyHitBox(Rectangle enemyrHitBox) {
        this.enemyHitBox = enemyrHitBox;
    }


    public Rectangle getDownBounds() {
        return downBounds;
    }

    public void setDownBounds(Rectangle downBounds) {
        this.downBounds = downBounds;
    }

    public Rectangle getRightBounds() {
        return rightBounds;
    }

    public void setRightBounds(Rectangle rightBounds) {
        this.rightBounds = rightBounds;
    }

    public Rectangle getLeftBounds() {
        return leftBounds;
    }

    public void setLeftBounds(Rectangle leftBounds) {
        this.leftBounds = leftBounds;
    }

    public int getHitVectorsOnPosition(int position) {
        return hitVectors[position];
    }

    public void setHitVectors(int position, int value) {
        this.hitVectors[position] = value;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void reciveHit(int hitFor) {
        HP = HP - hitFor;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getDespawnTimer() {
        return despawnTimer;
    }

    public boolean isStillAfterSpawning() {
        return !stillAfterSpawning;
    }

    public void setDespawnTimer(int despawnTimer) {
        this.despawnTimer = despawnTimer;
    }

    public void setStillAfterSpawning(boolean stillAfterSpawning) {
        this.stillAfterSpawning = stillAfterSpawning;
    }
}
