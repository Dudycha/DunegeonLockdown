package cz.jandudycha.game.main.states;





import cz.jandudycha.game.entity.player.Player;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.main.KeyInput;
import cz.jandudycha.game.main.RenderLayer;
import cz.jandudycha.game.main.World;
import cz.jandudycha.game.main.gameOver.ScoreManager;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;

public class Game extends State {

    private final Player player;
    private final World world;
    private final Events events;
    private int coinAmtLastTime = 0;
    private boolean gameOver = false;


    public Game(RenderLayer renderLayer, KeyInput keyInput) {

        super(renderLayer, keyInput);
        final Handler handler = new Handler(this);
        Assets.init();
        world = new World();
        player = new Player(handler, 200, 200);
        events = new Events(handler);
    }


    public void update() {
        if (!gameOver) {
            coinAmtLastTime = player.getCoins();
            player.update();
            events.update();
            if (keyInput.isEscape() && renderLayer.getKeyInput().isWasEscape()) {
                renderLayer.getKeyInput().setwasEscape(true);
                renderLayer.getKeyInput().setUIManager(null);
                State.setState(renderLayer.getMenuInGame());
            }
            if (keyInput.isbButton() && renderLayer.getKeyInput().isWasbButton()) {
                renderLayer.getKeyInput().setWasbButton(true);
                renderLayer.getKeyInput().setUIManager(null);
                State.setState(renderLayer.getBuyMenu());
            }
            coinAmtLastTime = player.getCoins();
            if (player.getHitpoints() <= 0 || events.isLevelListComplete()) {
                gameOver = true;
            }
        } else {
            renderLayer.getKeyInput().setUIManager(null);
            State.setState(renderLayer.getGameOver());
        }
    }


    public void render(Graphics g) {
        if (gameOver) {
            g.drawImage(Assets.backround, 0, 0, null);
        } else {
            if (player.isReceivedHit()) {
                g.drawImage(Assets.backroundHit, 0, 0, null);
            } else {
                g.drawImage(Assets.backround, 0, 0, null);
            }
        }
        g.drawImage(Assets.BorderFrame1000x550, 0, 0, null);
        world.render(g); // TESTING ONLY
        events.render(g);
        player.render(g);


        drawCoinAmt(g);
        drawScore(g);
        g.drawImage(Assets.middleSlabChains, 424, 0, null);
    }

    private void drawScore(Graphics g) {
        g.setFont(Assets.smallFont);
        g.setColor(Color.orange);
        g.drawString("Score: " + player.getScore(), 465, 20);
    }

    private void drawCoinAmt(Graphics g) {
        g.drawImage(Assets.coins, 17, 460, 27, 27, null);
        g.setColor(Color.orange);
        g.setFont(Assets.mediumFont);
        g.drawString("" + player.getCoins(), 50, 482);

    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public Events getEvents() {
        return events;
    }

    public int getCoinAmtLastTime() {
        return coinAmtLastTime;
    }

    @Override
    public int getCoin() {
        return player.getCoins();
    }

    @Override
    public void setCoin(int amt) {
        player.setCoins(amt);
    }

    @Override
    public void setAmmo(ItemType itemType, int amt) {

        switch (itemType) {
            case ammoBox9mm: {
                player.getPlayerAttack().getPistol1911().setAmmoInReserve(player.getPlayerAttack().getPistol1911().getAmmoInReserve() + amt);
                break;
            }
            case ammoBoxSMG: {
                player.getPlayerAttack().getSmg().setAmmoInReserve(player.getPlayerAttack().getSmg().getAmmoInReserve() + amt);
                break;
            }
            case ammoBoxBuckShot: {
                player.getPlayerAttack().getShotGun().setAmmoInReserve(player.getPlayerAttack().getShotGun().getAmmoInReserve() + amt);
                break;
            }
            case ammoBox556: {
                player.getPlayerAttack().getAk47().setAmmoInReserve(player.getPlayerAttack().getAk47().getAmmoInReserve() + amt);
                break;
            }
            case ammoBox50EA: {
                player.getPlayerAttack().getDesertEagle().setAmmoInReserve(player.getPlayerAttack().getDesertEagle().getAmmoInReserve() + amt);
                break;
            }


        }

    }

    @Override
    public void unlockGun(int numOfGun) {
        player.getPlayerAttack().unlock(numOfGun);
    }
    @Override
    public ScoreManager getScoreManager() {
        return null;
    }
    @Override

    public boolean isUnlocked(int numOfGun) {
        switch (numOfGun) {
            case 1: {
                return player.getPlayerAttack().getPistol1911().isUnlocked();

            }
            case 2: {
                return player.getPlayerAttack().getShotGun().isUnlocked();

            }
            case 3: {
                return player.getPlayerAttack().getSmg().isUnlocked();

            }
            case 4: {
                return player.getPlayerAttack().getAk47().isUnlocked();

            }
            case 5: {
                return player.getPlayerAttack().getDesertEagle().isUnlocked();

            }


        }
        return false;
    }
}
