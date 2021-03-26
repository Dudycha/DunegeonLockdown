package cz.jandudycha.game.main.gameOver;


import cz.jandudycha.game.entity.player.Player;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.main.KeyInput;
import cz.jandudycha.game.main.RenderLayer;
import cz.jandudycha.game.main.states.State;
import cz.jandudycha.game.texture.Assets;
import cz.jandudycha.game.ui.UIImageButton;
import cz.jandudycha.game.ui.UIManager;

import java.awt.*;

public class GameOver extends State {
    private final UIManager uiManager;
    private int clock = 0;
    private boolean written = false;
    private final ScoreManager scoreManager = new ScoreManager();

    public GameOver(final RenderLayer rendrLayer, KeyInput keyInput) {
        super(rendrLayer, keyInput);
        uiManager = new UIManager();

        uiManager.addObject(new UIImageButton(400, 270, 100, 50, Assets.btnRestart, () -> {
            clock = 0;
            rendrLayer.newGame();
            renderLayer.getKeyInput().setUIManager(null);
            State.setState(rendrLayer.getGame());
            written = false;
        }));
        uiManager.addObject(new UIImageButton(520, 270, 100, 50, Assets.btnMenu, () -> {
            clock = 0;
            rendrLayer.newGame();
            renderLayer.getKeyInput().setUIManager(null);
            State.setState(rendrLayer.getMenu());
            written = false;
        }));
    }

    @Override
    public void update() {
        clock++;
        scoreManager.update();
        if (!written) {
            scoreManager.newScore(renderLayer.getGame().getPlayer().getScore()
                    , renderLayer.getGame().getPlayer().getEnemiesKilled(),
                    renderLayer.getGame().getPlayer().getBulletsFired(),
                    renderLayer.getGame().getPlayer().getTotalDMGrecieved(),
                    renderLayer.getGame().getPlayer().getCoinsEarned());
            written = true;
        }
        if (clock > 80) {
            renderLayer.getKeyInput().setUIManager(uiManager);
            uiManager.update();
        }


    }

    @Override
    public void render(Graphics g) {
        if (clock > 80) {
            uiManager.render(g);
        }
        if (clock > 120) {
            g.drawImage(Assets.menu_end_Game, 0, 0, null);
        }
        if (clock > 140) {
            g.setColor(Color.ORANGE);
            g.setFont(Assets.mediumFont);
            g.drawString("Your score:        " + renderLayer.getGame().getPlayer().getScore(), 430, 365);
        }
        if (clock > 160) {
            g.setColor(new Color(150, 0, 0));
            g.drawString("Enemies killed: " + renderLayer.getGame().getPlayer().getEnemiesKilled(), 430, 385);
        }
        if (clock > 180) {
            g.setColor(new Color(255, 141, 50));
            g.drawString("Bullets fired:      " + renderLayer.getGame().getPlayer().getBulletsFired(), 430, 405);
        }
        if (clock > 200) {
            g.setColor(Color.red);
            g.drawString("DMG received:  " + renderLayer.getGame().getPlayer().getTotalDMGrecieved(), 430, 425);
        }
        if (clock > 220) {
            g.setColor(Color.orange);
            g.drawString("Coins earned:   " + renderLayer.getGame().getPlayer().getCoinsEarned(), 430, 445);
        }

        g.setFont(Assets.bigFont);
        if (renderLayer.getGame().getEvents().isLevelListComplete()) {
            g.setColor(new Color(0, 150, 0));
            g.drawString("YOU WON !!!", 370, 250);
        } else {
            g.setColor(new Color(150, 0, 0));
            g.drawString("GAME OVER", 370, 250);
        }

        if (renderLayer.getGame().getEvents().isLevelListComplete()) {
            drawVictoyPcture(g);
        } else {
            drawSkull(g);
        }

    }

    private void drawVictoyPcture(Graphics g) {
        g.drawImage(Assets.victoryPicture[0], 440, 50, 50, 50, null);
        g.drawImage(Assets.victoryPicture[1], 440 + 50, 50, 50, 50, null);
        g.drawImage(Assets.victoryPicture[2], 440 + 100, 50, 50, 50, null);
        g.drawImage(Assets.victoryPicture[3], 440, 50 + 50, 50, 50, null);
        g.drawImage(Assets.victoryPicture[4], 440 + 50, 50 + 50, 50, 50, null);
        g.drawImage(Assets.victoryPicture[5], 440 + 100, 50 + 50, 50, 50, null);
        g.drawImage(Assets.victoryPicture[6], 440, 50 + 100, 50, 50, null);
        g.drawImage(Assets.victoryPicture[7], 440 + 50, 50 + 100, 50, 50, null);
        g.drawImage(Assets.victoryPicture[8], 440 + 100, 50 + 100, 50, 50, null);
    }

    private void drawSkull(Graphics g) {


        g.drawImage(Assets.skull[0], 440, 50, 70, 70, null);
        g.drawImage(Assets.skull[1], 440 + 70, 50, 70, 70, null);
        g.drawImage(Assets.skull[2], 440, 50 + 70, 70, 70, null);
        g.drawImage(Assets.skull[3], 440 + 70, 50 + 70, 70, 70, null);


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
    public boolean isUnlocked(int numOfGun) {
        return false;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    @Override
    public Player getPlayer() {
        return null;
    }
}
