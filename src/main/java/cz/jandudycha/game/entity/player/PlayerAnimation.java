package cz.jandudycha.game.entity.player;




import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;

public class PlayerAnimation {
    private int x;
    private int y;
    private final Handler handler;
    private int tick8 = 0;
    private int tick2 = 0;
    private int animTick = 0;



    public PlayerAnimation(Handler handler, int x, int y) {
        this.handler = handler;
        this.x = x;
        this.y = y;
    }

    public void update() {
        x = handler.getGame().getPlayer().getX();
        y = handler.getGame().getPlayer().getY();

        if (animTick == 10) {
            animTick = 0;

            if (tick8 == 7) {
                tick8 = 0;
            } else {
                tick8++;
            }

            if (tick2 == 1) {
                tick2 = 0;
            } else {
                tick2++;
            }

        } else {
            animTick++;

        }


    }

    public void render(Graphics g) {
        if (handler.getGame().getKeyInput().isRight() ||
                handler.getGame().getKeyInput().isLeft()) {
            if (handler.getGame().getPlayer().getPlayerMovement().isPlayerFacingRight()) {
                g.drawImage(Assets.playerMoveRight[tick2], x, y, 64, 64, null);
            } else {
                g.drawImage(Assets.playerMoveLeft[tick2], x, y, 64, 64, null);
            }
        } else {
            if (handler.getGame().getPlayer().getPlayerMovement().isPlayerFacingRight()) {
                g.drawImage(Assets.playerRightIdle[tick8], x, y, 64, 64, null);
            } else {
                g.drawImage(Assets.playerleftIdle[tick8], x, y, 64, 64, null);
            }
        }

    }
}
