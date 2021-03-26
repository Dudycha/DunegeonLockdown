package cz.jandudycha.game.entity.enemy;



import cz.jandudycha.game.event.dropItems.Reward;
import cz.jandudycha.game.event.dropItems.RewardType;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.Random;

public class Goblin extends Enemy {

    private final Handler handler;
    private final int GOBLINDEFAULTHP = 60;
    private int moveAnimation = 0, hitAnimationTick = 0;
    private boolean goblinFacingLeft;
    private int clock1 = 0, clock2 = 0;
    private boolean deadAnimationPlayed = false;
    private int deadAnimation = 0;
    private boolean hitToPlayer = false;
    private final Random rnd = new Random();
    private boolean forcingDirection = false, forcedDirectionRight = false;
    private int upStuckCounter = 0, downStuckCounter = 0;
    private int jumpTick = 0;
    private final int[] jump = {10, 12, 13, 13, 12, 11, 11, 10, 10, 9, 8, 7, 7, 6, 5, 5, 5, 4, 4, 2};
    private boolean aboutToJump = false, aboutToJump2 = false, aboutToJump3 = false, upStuck = false;


    public Goblin(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        super.setVELOCITY(3);
        super.setHP(GOBLINDEFAULTHP);
    }

    @Override
    public void update() {
        checkForFellOffMap();
        boundsUpdate();
        checkForHitToPlayer();
        if (hitToPlayer) {
            tickHitAnimation();
        } else {
            positionUpdate();
        }

        super.despawn();

        if (clock1 == 7) {
            clock1 = 0;
            if (moveAnimation == Assets.goblinRight.length - 1) {
                moveAnimation = 0;
            } else {
                moveAnimation++;
            }

            if (!super.isAlive()) {

                if (deadAnimation < 4) {
                    deadAnimation++;
                } else {
                    deadAnimationPlayed = true;
                }
            }
        } else {
            clock1++;
        }

        if (getHP() <= 0) {
            if (super.isAlive()) {
                reward();
            }
            super.setAlive(false);
        }

    }

    private void reward() {
        handler.getGame().getPlayer().setScore(handler.getGame().getPlayer().getScore() + 18);
        handler.getGame().getPlayer().setEnemiesKilled(handler.getGame().getPlayer().getEnemiesKilled() + 1);
        int roll = rnd.nextInt(100);
        if (roll < 80) {
            roll = rnd.nextInt(100);
            if (roll > -1 && roll < 40) {
                handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.coin));
            } else if (roll > 39 && roll < 95) {
                handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.goldNuget));
            } else if (roll > 94 && roll < 100) {
                handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.goldBar));
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (super.isAlive()) {
            if (hitToPlayer) {
                if (goblinFacingLeft) {
                    g.drawImage(Assets.goblinAttackLeft[hitAnimationTick][0], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.goblinAttackLeft[hitAnimationTick][1], super.getX(), super.getY() - 13, 45, 45, null);
                } else {
                    g.drawImage(Assets.goblinAttackRight[hitAnimationTick][0], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.goblinAttackRight[hitAnimationTick][1], super.getX(), super.getY() - 13, 45, 45, null);
                }
            } else {
                if (goblinFacingLeft) {
                    g.drawImage(Assets.goblinLeft[moveAnimation][0], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.goblinLeft[moveAnimation][1], super.getX(), super.getY() - 13, 45, 45, null);
                } else {
                    g.drawImage(Assets.goblinRight[moveAnimation][0], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.goblinRight[moveAnimation][1], super.getX(), super.getY() - 13, 45, 45, null);
                }


            }
            drawHPbar(g);
        } else {
            if (!deadAnimationPlayed) {
                if (goblinFacingLeft) {
                    g.drawImage(Assets.goblinDeathLeft[deadAnimation][0], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.goblinDeathLeft[deadAnimation][1], super.getX(), super.getY() - 13, 45, 45, null);
                } else {
                    g.drawImage(Assets.goblinDeathRight[deadAnimation][0], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.goblinDeathRight[deadAnimation][1], super.getX(), super.getY() - 13, 45, 45, null);
                }
            } else {
                if (goblinFacingLeft) {
                    g.drawImage(Assets.goblinDeathLeft[6][1], super.getX(), super.getY() - 13, 45, 45, null);
                } else {
                    g.drawImage(Assets.goblinDeathRight[6][1], super.getX(), super.getY() - 13, 45, 45, null);
                }

            }

        }

/*
        g.setColor(Color.white);
        g.fillRect(super.getX() + 8, super.getY() -13, 30, 45);



        g.drawLine(getHitVectorsOnPosition(0), getHitVectorsOnPosition(1), getHitVectorsOnPosition(2), getHitVectorsOnPosition(3));
        g.drawLine(getHitVectorsOnPosition(4), getHitVectorsOnPosition(5), getHitVectorsOnPosition(6), getHitVectorsOnPosition(7));


        g.setColor(Color.red);
        g.fillRect(super.getX() + 5, super.getY() -26, 30, 5);
        g.fillRect(super.getX() + 5, super.getY() + 32, 31, 5);
        g.fillRect(super.getX() + 35, super.getY() -23, 5, 55);
        g.fillRect(super.getX() + 2, super.getY() -23, 5, 55);
*/


    }

    private void drawHPbar(Graphics g) {

        g.setColor(Color.RED);
        for (int i = 0; i < getHP(); i++) {
            g.fillRect(super.getX() + i - 4, super.getY() + 38, 1, 2);
        }
    }

    private void tickHitAnimation() {
        if (clock2 == 2) {
            clock2 = 0;
            hitAnimationTick++;
            if (hitAnimationTick > Assets.goblinAttackLeft.length - 1) {
                hitToPlayer = false;
                hitAnimationTick = 0;
            }
        } else {
            clock2++;
        }
        if (bottomIntersection()) {
            super.setY(super.getY() + super.getGRAVITY());
        }
    }

    private void checkForHitToPlayer() {
        if (getEnemyHitBox().intersects(handler.getGame().getPlayer().getPlayerMovement().getHitBoxPlayer()) && !hitToPlayer && isAlive()) {
            handler.getGame().getPlayer().setHitpoints(handler.getGame().getPlayer().getHitpoints() - 1);
            handler.getGame().getPlayer().setTotalDMGrecieved(handler.getGame().getPlayer().getTotalDMGrecieved() + 1);
            hitToPlayer = true;
        }
    }

    private void positionUpdate() {
        checkForStuck();
        gravityControl();
        if (isAlive()) {
            if (!forcingDirection) {
                if (super.isAlive()) {
                    if (isStillAfterSpawning()) {
                        if (super.getX() > handler.getGame().getPlayer().getX() && !leftIntersection()) {
                            super.setX(super.getX() - 2);
                            goblinFacingLeft = true;
                        }
                        if (super.getX() < handler.getGame().getPlayer().getX() && !rightIntersection()) {
                            super.setX(super.getX() + 2);
                            goblinFacingLeft = false;
                        }
                    }


                }
            } else {
                if (forcedDirectionRight) {
                    if (!rightIntersection()) {
                        super.setX(super.getX() + 2);
                        goblinFacingLeft = false;
                    }
                } else {
                    if (!leftIntersection()) {
                        super.setX(super.getX() - 2);
                        goblinFacingLeft = true;
                    }
                }
            }
        }
        jumpManager();

        if (bottomIntersection()) {
            super.setY(super.getY() + super.getGRAVITY());
        } else {
            setStillAfterSpawning(false);
        }


    }

    private void jumpManager() {
        if (upStuck && super.getEnemyHitBox().intersects(handler.getGame().getWorld().getTurnPointGoblinLeft()) || super.getEnemyHitBox().intersects(handler.getGame().getWorld().getTurnPointForGoblins())) {
            forcingDirection = false;
        }
        if (aboutToJump && (super.getEnemyHitBox().intersects(handler.getGame().getWorld().getTurnPointGoblinLeft()) || super.getEnemyHitBox().intersects(handler.getGame().getWorld().getTurnPointForGoblins()))) {
            aboutToJump2 = true;
            forcingDirection = false;
            aboutToJump = false;
        }
        if (aboutToJump2) {

            if (super.getEnemyHitBox().intersects(handler.getGame().getWorld().getJumpPoint()) || super.getEnemyHitBox().intersects(handler.getGame().getWorld().getJumpPointRight())) {
                aboutToJump3 = true;
                aboutToJump2 = false;
            }

        }


        if (aboutToJump3) {
            setY(getY() - jump[jumpTick]);
            jumpTick++;
            if (jumpTick == jump.length) {
                jumpTick = 0;
                aboutToJump3 = false;

            }
        }
    }

    private void gravityControl() {
        if (rightIntersection() || leftIntersection()) {
            super.setY(super.getY() - 5);

        }
    }

    private void checkForStuck() {
        if (handler.getGame().getPlayer().getPlayerMovement().getHitBoxPlayer().intersects(handler.getGame().getWorld().getMiddleUpStuck()) &&
                super.getEnemyHitBox().intersects(handler.getGame().getWorld().getMiddleDownStuck()))// hrac je nahore a enemy dole
        {
            upStuckCounter++;
            if (upStuckCounter > 40) {   // jak dlouho se musi enemy a player nachazet na stuck pozicich
                forcingDirection = true;
                aboutToJump = true;

                // pokud se enemy nachazi za pulkou propasti
                forcedDirectionRight = super.getX() > 490;
            }

        } else {
            upStuckCounter = 0;
        }

        if (handler.getGame().getPlayer().getPlayerMovement().getHitBoxPlayer().intersects(handler.getGame().getWorld().getMiddleDownStuck()) &&
                super.getEnemyHitBox().intersects(handler.getGame().getWorld().getMiddleUpStuck()))// hrac je nahore a enemy dole
        {
            downStuckCounter++;
            if (downStuckCounter > 40) {   // jak dlouho se musi enemy a player nachazet na stuck pozicich
                forcingDirection = true;
                upStuck = true;

                // pokud se enemy nachazi za pulkou propasti
                forcedDirectionRight = super.getX() > 490;
            }

        } else {
            downStuckCounter = 0;
        }


    }


    private void boundsUpdate() {

        super.setEnemyHitBox(new Rectangle(super.getX() + 8, super.getY() - 13, 30, 45));
        super.setDownBounds(new Rectangle(super.getX() + 5, super.getY() + 32, 31, 5));
        super.setRightBounds(new Rectangle(super.getX() + 35, super.getY() - 23, 5, 55));
        super.setLeftBounds(new Rectangle(super.getX() + 2, super.getY() - 23, 5, 55));

        super.setHitVectors(0, super.getX() + 5); // x1
        super.setHitVectors(1, super.getY() - 12); // y1
        super.setHitVectors(2, super.getX() + 37); // x2
        super.setHitVectors(3, super.getY() + 31); // y2

        super.setHitVectors(4, super.getX() + 37); // x11
        super.setHitVectors(5, super.getY() - 12); // y11
        super.setHitVectors(6, super.getX() + 7); // x22
        super.setHitVectors(7, super.getY() + 31); // y22
    }

    public boolean bottomIntersection() {
        for (Rectangle rec : handler.getGame().getWorld().getListBarier()) {
            if (rec.intersects(super.getDownBounds())) {
                return false;
            }
        }
        return true;
    }

    public boolean rightIntersection() {
        for (Rectangle rec : handler.getGame().getWorld().getListBarier()) {
            if (rec.intersects(super.getRightBounds())) {
                return true;
            }
        }
        return false;
    }

    public boolean leftIntersection() {
        for (Rectangle rec : handler.getGame().getWorld().getListBarier()) {
            if (rec.intersects(super.getLeftBounds())) {
                return true;
            }
        }
        return false;
    }
}
