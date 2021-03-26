package cz.jandudycha.game.main.states;




import cz.jandudycha.game.entity.player.Player;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.main.KeyInput;
import cz.jandudycha.game.main.RenderLayer;
import cz.jandudycha.game.main.gameOver.ScoreManager;
import cz.jandudycha.game.texture.Assets;
import cz.jandudycha.game.ui.ClickListener;
import cz.jandudycha.game.ui.UIImageButton;
import cz.jandudycha.game.ui.UIManager;

import java.awt.*;

public class Setings extends State {

    private final RenderLayer renderLayer;
    private UIManager uiManager;

    public Setings(final RenderLayer renderLayer, KeyInput keyInput) {
        super(renderLayer, keyInput);
        this.renderLayer = renderLayer;
        uiManager = new UIManager();
        uiManager.addObject(new UIImageButton(460, 360, 135, 70, Assets.btnBack, new ClickListener() {
            @Override
            public void onClick() {
                renderLayer.getKeyInput().setUIManager(null);
                State.setState(renderLayer.getMenu());
            }
        }));
    }

    @Override
    public void update() {
        renderLayer.getKeyInput().setUIManager(uiManager);
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(renderLayer.getForeground());
        g.fillRect(0, 0, 1050, 550);
        g.drawImage(Assets.menu_settings, 0, 0, null);
        uiManager.render(g);
    }


    @Override
    public void setCoin(int amt) {

    }

    @Override
    public int getCoin() {
        return 0;
    }

    @Override
    public void setAmmo(ItemType itemType, int amt) {

    }

    @Override
    public void unlockGun(int numOfGun) {

    }

    @Override
    public boolean isUnlocked(int numOfGun) {
        return false;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public Events getEvents() {
        return null;
    }

    @Override
    public ScoreManager getScoreManager() {
        return null;
    }
}
