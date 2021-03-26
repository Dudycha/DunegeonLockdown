package cz.jandudycha.game.entity.player.playerAttack;


import cz.jandudycha.game.main.Handler;

public class PlayerJump {
    private final Handler handler;
    private int y;
    private boolean goForJump = false;
    private boolean goForSecondJump = false;
    private final int[] jump = {10, 12, 12, 12, 10, 10, 10, 9, 8, 8, 8, 6, 5, 5, 4, 3, 3, 3, 3, 3};
    private int jumpTick = 0;
    private boolean secondJumpPossible = false;
    private boolean pom = false;

    public PlayerJump(Handler handler) {
        this.handler = handler;
    }

    public void update() {


        if (goForJump) {
            y = jump[jumpTick];
            jumpTick++;
            if (jumpTick == 10) {
                secondJumpPossible = true;
            }
            if (jumpTick == jump.length) {
                jumpTick = 0;
                goForJump = false;
                y = 0;
            }
        }
        if (goForSecondJump) {
            y = jump[jumpTick];
            jumpTick++;
            if (jumpTick == jump.length) {
                jumpTick = 0;
                goForSecondJump = false;
                secondJumpPossible = false;
                y = 0;
            }
        }
        pom = handler.getGame().getKeyInput().isSpace();
    }


//            handler.getGame().getPlayer().getPlayerMovement().leftIntersection()
//            handler.getGame().getPlayer().getPlayerMovement().rightIntersection()


    public void jump() {
        if (handler.getGame().getPlayer().getPlayerMovement().bottomIntersection()
                || handler.getGame().getPlayer().getPlayerMovement().getRightBounds().intersects(handler.getGame().getWorld().getFallPitRight())
                || handler.getGame().getPlayer().getPlayerMovement().getLeftBounds().intersects(handler.getGame().getWorld().getFallPitLeft())) {
            goForJump = true;
        }
        if (!handler.getGame().getPlayer().getPlayerMovement().bottomIntersection() && secondJumpPossible && !goForSecondJump && !pom) {
            goForSecondJump = true;
            goForJump = false;
            jumpTick = 0;
        }
    }

    public int getYJump() {
        return y;
    }
}
