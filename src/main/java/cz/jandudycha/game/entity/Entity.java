package cz.jandudycha.game.entity;



import cz.jandudycha.game.main.Handler;

import java.awt.*;

public abstract class Entity {

    private int x;
    private int y;
    private int hitpoints = 9;
    private final Handler handler;
    private int VELOCITY = 4;
    private final int GRAVITY = 3;

    public Entity(Handler handler, int x, int y) {
        this.handler = handler;
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVELOCITY(int VELOCITY) {
        this.VELOCITY = VELOCITY;
    }

    public Handler getHandler() {
        return handler;
    }

    public int getVELOCITY() {
        return VELOCITY;
    }

    public int getGRAVITY() {
        return GRAVITY;
    }
}
