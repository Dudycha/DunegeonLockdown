package cz.jandudycha.game.main.states;




import cz.jandudycha.game.entity.player.Player;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.main.KeyInput;
import cz.jandudycha.game.main.RenderLayer;
import cz.jandudycha.game.main.gameOver.ScoreManager;

import java.awt.*;

public abstract class State {

    private static State currentState = null;

    protected RenderLayer renderLayer;
    protected KeyInput keyInput;

    public State(RenderLayer renderLayer, KeyInput keyInput) {
        this.renderLayer = renderLayer;
        this.keyInput = keyInput;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }


    public KeyInput getKeyInput() {
        return keyInput;
    }

    public abstract void update();

    public abstract Player getPlayer();

    public abstract ScoreManager getScoreManager();

    /*
    protected ScoreManager getScoreManager() {
        return null;
    }*/

    public abstract Events getEvents();

    public abstract int getCoin(); // moc moc moc špatně ;) Už vím jak na to, ale nechce se mi to celé předělávat :P

    public abstract void setCoin(int amt);

    public abstract void setAmmo(ItemType itemType, int amt);

    public abstract void unlockGun(int numOfGun);

    public abstract boolean isUnlocked(int numOfGun);

    public abstract void render(Graphics g);


}
