package cz.jandudycha.game.main.states;


import cz.jandudycha.game.entity.player.Player;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.main.KeyInput;
import cz.jandudycha.game.main.RenderLayer;
import cz.jandudycha.game.main.gameOver.ScoreManager;
import cz.jandudycha.game.texture.Assets;
import cz.jandudycha.game.ui.UIImageButton;
import cz.jandudycha.game.ui.UIManager;

import java.awt.*;

public class ScoreBoard extends State {

    private final RenderLayer renderLayer;
    private final UIManager uiManager;


    public ScoreBoard(final RenderLayer renderLayer, KeyInput keyInput) {
        super(renderLayer, keyInput);
        this.renderLayer = renderLayer;
        uiManager = new UIManager();


        uiManager.addObject(new UIImageButton(350, 460, 135, 50, Assets.btnBack, () -> {
            renderLayer.getKeyInput().setUIManager(null);
            State.setState(renderLayer.getMenu());
        }));

        uiManager.addObject(new UIImageButton(560, 460, 135, 50,
                Assets.btnClearData, () -> renderLayer.getGameOver().getScoreManager().clear()));
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
        g.drawImage(Assets.menu_scoreBoard, 0, 0, null);
        uiManager.render(g);
        drawScores(g);
    }

    private void drawScores(Graphics g) {

        if (renderLayer.getGameOver().getScoreManager().getRecords().getListOfRecords().size() == 0) {
            g.setColor(new Color(140, 0, 0));
            g.setFont(Assets.coinFont);
            g.drawString("NO DATA", 475, 200);
        } else {
            int yShift = 150, xShift = 300;
            g.setFont(Assets.gunFont);

            for (int i = 0; i < renderLayer.getGameOver().getScoreManager().getRecords().getListOfRecords().size(); i++) {

                if (i < 10) {
                    g.setColor(new Color(150, 150, 150));
                    g.drawString(renderLayer.getGameOver().getScoreManager().getRecords().getListOfRecords().get(i).getTimeStamp(), xShift - 10, yShift);

                    g.setColor(Color.ORANGE);
                    g.drawString(String.valueOf(renderLayer.getGameOver().getScoreManager().getRecords().getListOfRecords().get(i).getScore()), xShift + 200, yShift);

                    g.setColor(new Color(255, 141, 50));
                    g.drawString(String.valueOf(renderLayer.getGameOver().getScoreManager().getRecords().getListOfRecords().get(i).getBulletsFired()), xShift + 275, yShift);

                    g.setColor(Color.red);
                    g.drawString(String.valueOf(renderLayer.getGameOver().getScoreManager().getRecords().getListOfRecords().get(i).getDMGreceived()), xShift + 350, yShift);

                    g.setColor(Color.orange);
                    g.drawString(String.valueOf(renderLayer.getGameOver().getScoreManager().getRecords().getListOfRecords().get(i).getCoinsEarned()), xShift + 420, yShift);

                    yShift += 30;
                }
            }
        }
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
