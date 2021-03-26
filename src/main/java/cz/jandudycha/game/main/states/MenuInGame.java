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

public class MenuInGame extends State {
    private final RenderLayer renderLayer;
    private final UIManager uiManager;

    public MenuInGame(final RenderLayer rendrLayer, KeyInput keyInput) {
        super(rendrLayer, keyInput);
        this.renderLayer = rendrLayer;
        uiManager = new UIManager();

        uiManager.addObject(new UIImageButton(460, 135, 135, 70, Assets.btnResume, new ClickListener() {
            @Override
            public void onClick() {
                renderLayer.getKeyInput().setUIManager(null);
                State.setState(rendrLayer.getGame());
            }
        }));

        uiManager.addObject(new UIImageButton(460, 215, 135, 70, Assets.btnRestart, new ClickListener() {
            @Override
            public void onClick() {

                rendrLayer.newGame();
                renderLayer.getKeyInput().setUIManager(null);
                State.setState(rendrLayer.getGame());
            }
        }));

        uiManager.addObject(new UIImageButton(460, 295, 135, 70, Assets.btnMenu, new ClickListener() {
            @Override
            public void onClick() {
                rendrLayer.newGame();
                renderLayer.getKeyInput().setUIManager(null);
                State.setState(rendrLayer.getMenu());
            }
        }));

    }

    @Override
    public void update() {
        renderLayer.getKeyInput().setUIManager(uiManager);
        uiManager.update();
        if (keyInput.isEscape() && renderLayer.getKeyInput().isWasEscape()) {
            renderLayer.getKeyInput().setwasEscape(true);
            renderLayer.getKeyInput().setUIManager(null);
            State.setState(renderLayer.getGame());

        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menu_inGame, 0, 0, null);
        uiManager.render(g);

    }

    @Override
    public int getCoin() {
        return 0;
    }

    @Override
    public void setCoin(int amt) {

    }

    @Override
    public void setAmmo(ItemType itemType, int amt) {

    }
    @Override
    public void unlockGun(int numOfGun) {

    }

    @Override
    public Events getEvents() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }
    @Override
    public boolean isUnlocked(int numOfGun) {
        return false;
    }
    @Override
    public ScoreManager getScoreManager() {
        return null;
    }
}
