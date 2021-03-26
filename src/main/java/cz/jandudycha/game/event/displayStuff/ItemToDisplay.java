package cz.jandudycha.game.event.displayStuff;




import cz.jandudycha.game.main.Handler;

import java.awt.*;

public abstract class ItemToDisplay {
    private int x, y;
    private final Handler handler;
    private boolean readyToRemove = false;

    public ItemToDisplay(Handler handler, int x, int y) {
        this.handler = handler;
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Handler getHandler() {
        return handler;
    }

    public boolean isReadyToRemove() {
        return readyToRemove;
    }

    public void setReadyToRemove(boolean readyToRemove) {
        this.readyToRemove = readyToRemove;
    }
}
