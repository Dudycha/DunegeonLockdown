package cz.jandudycha.game.event.displayStuff;



import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.Random;

public class CoinBilanceChanged extends ItemToDisplay {

    private int amtCoinToDisplay = 0, yForCoinAnimation = 462, clock;
    private boolean once = true;


    public CoinBilanceChanged(Handler handler, int x, int y) {
        super(handler, x, y);
        Random rnd = new Random();
        super.setX(20 + rnd.nextInt(40));

    }

    @Override
    public void update() {
        if (once) {
            calculateCoinAnimation();
            once = false;
        }
        if (clock == 5) {
            yForCoinAnimation--;
            clock = 0;
        } else {
            clock++;
        }
        if (yForCoinAnimation < 442) {
            super.setReadyToRemove(true);
            yForCoinAnimation = 462;
        }

    }

    private void calculateCoinAnimation() {
        amtCoinToDisplay = getHandler().getGame().getPlayer().getCoins() - getHandler().getGame().getCoinAmtLastTime();
        getHandler().getGame().getPlayer().setCoinsEarned(getHandler().getGame().getPlayer().getCoinsEarned() + amtCoinToDisplay);
        getHandler().getGame().getPlayer().setScore(getHandler().getGame().getPlayer().getScore() + amtCoinToDisplay);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.setFont(Assets.smallFont);
        g.drawString("   +" + amtCoinToDisplay, super.getX(), yForCoinAnimation);

    }


}
