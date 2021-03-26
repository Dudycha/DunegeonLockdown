package cz.jandudycha.game.entity.player;




import cz.jandudycha.game.entity.player.playerAttack.PlayerJump;
import cz.jandudycha.game.main.Handler;

import java.awt.*;

public class PlayerMovement {


    private final Handler handler;
    private final Player player;
    private final PlayerJump playerJump;
    private int x;
    private int y;
    private Rectangle hitBoxPlayer;
    private Rectangle downBounds, rightBounds, leftBounds;
    private boolean playerFacingRight;


    public PlayerMovement(Handler handler, Player player, int x, int y) {
        this.handler = handler;
        this.player = player;
        this.x = x;
        this.y = y;
        playerJump = new PlayerJump(handler);

    }

    public void update() {
        hitBoxPlayer = new Rectangle(x + 17, y + 28, 31, 35);
        downBounds = new Rectangle(x + 17, y + 58, 31, 5);
        rightBounds = new Rectangle(x + 47, y + 30, 5, 30);
        leftBounds = new Rectangle(x + 13, y + 30, 5, 30);

        if (!bottomIntersection()) {
            y = y + player.getGRAVITY();
        }

        if (handler.getGame().getKeyInput().isLeft() && !leftIntersection()) {
            x = x - player.getVELOCITY();
            playerFacingRight = false;
        }
        if (handler.getGame().getKeyInput().isRight() && !rightIntersection()) {
            x = x + player.getVELOCITY();
            playerFacingRight = true;
        }

        if (handler.getGame().getKeyInput().isSpace()) {
            playerJump.jump();
        }
        playerJump.update();
        y = y - playerJump.getYJump();

    }


    public boolean bottomIntersection() {
        for (Rectangle rec : handler.getGame().getWorld().getListBarier()) {
            if (rec.intersects(downBounds)) {
                return true;
            }

        }
        return false;
    }


    public boolean rightIntersection() {
        for (Rectangle rec : handler.getGame().getWorld().getListBarier()) {
            if (rec.intersects(rightBounds)) {
                return true;
            }

        }
        for (Rectangle rec : handler.getGame().getWorld().getListBarierExtraForPlayer()) {
            if (rec.intersects(rightBounds)) {
                return true;
            }

        }
        return false;
    }

    public boolean leftIntersection() {
        for (Rectangle rec : handler.getGame().getWorld().getListBarier()) {
            if (rec.intersects(leftBounds)) {
                return true;
            }

        }
        for (Rectangle rec : handler.getGame().getWorld().getListBarierExtraForPlayer()) {
            if (rec.intersects(leftBounds)) {
                return true;
            }

        }
        return false;
    }

    public boolean isPlayerFacingRight() {
        return playerFacingRight;
    }

    public Rectangle getHitBoxPlayer() {
        return hitBoxPlayer;
    }


    public Rectangle getRightBounds() {
        return rightBounds;
    }

    public Rectangle getLeftBounds() {
        return leftBounds;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
