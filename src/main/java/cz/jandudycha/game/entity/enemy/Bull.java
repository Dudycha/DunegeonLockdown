package cz.jandudycha.game.entity.enemy;


import cz.jandudycha.game.event.dropItems.Reward;
import cz.jandudycha.game.event.dropItems.RewardType;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.Random;

public class Bull extends Enemy {

    private final Handler handler;
    private final int BULLDEFAULTHP = 400;
    private boolean bullFacingLeft;
    private int clock1 = 0, clock2 = 0, clockForTurning = 0, clockSpeed = 4;
    private int turnAnimationTick = 0, hitAnimationTick = 0, moveAnimation = 0, deadAnimation = 0;
    private boolean deadAnimationPlayed = false;
    private boolean turning = false;
    private boolean wantToGoLeft = false;
    private boolean setTick = true;
    private boolean hitToPlayer = false;
    private boolean downStuck, upStuck;
    private boolean redayToJump1 = false, redayToJump2 = false;
    private int downStuckCounter = 0, upStuckCounter = 0;
    private int jumpTick = 0;
    private final int[] jump = {7, 9, 12, 12, 12, 11, 10, 9, 9, 7, 6, 5, 5, 5, 3, 2, 2, 1};
    private final Random rnd = new Random();

    public Bull(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        super.setHP(BULLDEFAULTHP);
    }

    @Override
    public void update() {
        checkForFellOffMap();
        boundsUpdate();
        checkForHitToPlayer();
        checkForBeeingStuck();
        if (hitToPlayer) {
            tickHitAnimation();
        } else {
            positionUpdate();
        }

        super.despawn();

        if (clock1 == clockSpeed) {
            clock1 = 0;
            if (moveAnimation == Assets.bullRight.length - 1) {
                moveAnimation = 0;
            } else {
                moveAnimation++;
            }

            if (!super.isAlive()) {
                clockSpeed = 5;
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
        handler.getGame().getPlayer().setScore(handler.getGame().getPlayer().getScore() + 40);
        handler.getGame().getPlayer().setEnemiesKilled(handler.getGame().getPlayer().getEnemiesKilled() + 1);
        int roll = rnd.nextInt(100);
        if (roll > -1 && roll < 10) {
            handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.coin));
        } else if (roll > 9 && roll < 30) {
            handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.goldNuget));
        } else if (roll > 29 && roll < 100) {
            handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.goldBar));
        }

    }

    private void checkForBeeingStuck() {
        if (getEnemyHitBox().intersects(handler.getGame().getWorld().getMiddleDownStuck()) &&
                handler.getGame().getPlayer().getPlayerMovement().getHitBoxPlayer().intersects(handler.getGame().getWorld().getMiddleUpStuck())) {

            downStuckCounter++;
        } else {
            downStuckCounter = 0;
        }

        if (getEnemyHitBox().intersects(handler.getGame().getWorld().getMiddleUpStuck()) &&
                handler.getGame().getPlayer().getPlayerMovement().getHitBoxPlayer().intersects(handler.getGame().getWorld().getMiddleDownStuck())) {


            upStuckCounter++;
        } else {
            upStuckCounter = 0;
        }

        if (downStuckCounter > 100) {
            downStuck = true;
        }
        if (upStuckCounter > 80) {
            upStuck = true;
        }

        if (getEnemyHitBox().intersects(handler.getGame().getWorld().getTurnPoint()) && downStuck) {
            downStuck = false;
            redayToJump1 = true;
        }
        if (getEnemyHitBox().intersects(handler.getGame().getWorld().getTurnPoint()) && upStuck) {
            upStuck = false;

        }
    }

    private void tickHitAnimation() {
        if (clock2 == 5) {
            clock2 = 0;
            hitAnimationTick++;
            if (hitAnimationTick > Assets.bullAttackLeft.length - 1) {
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

    @Override
    public void render(Graphics g) {
        int shift32pix = 32;
        int shiftForCentering = -40;

        if (super.isAlive()) {
            if (hitToPlayer) {
                shiftForCentering = -50;
                if (bullFacingLeft) {
                    g.drawImage(Assets.bullAttackLeft[hitAnimationTick][0], super.getX(), super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackLeft[hitAnimationTick][1], super.getX() + shift32pix, super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackLeft[hitAnimationTick][2], super.getX() + shift32pix * 2, super.getY() + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullAttackLeft[hitAnimationTick][3], super.getX(), super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackLeft[hitAnimationTick][4], super.getX() + shift32pix, super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackLeft[hitAnimationTick][5], super.getX() + shift32pix * 2, super.getY() + shift32pix + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullAttackLeft[hitAnimationTick][6], super.getX(), super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackLeft[hitAnimationTick][7], super.getX() + shift32pix, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackLeft[hitAnimationTick][8], super.getX() + shift32pix * 2, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                } else {
                    g.drawImage(Assets.bullAttackRight[hitAnimationTick][0], super.getX(), super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackRight[hitAnimationTick][1], super.getX() + shift32pix, super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackRight[hitAnimationTick][2], super.getX() + shift32pix * 2, super.getY() + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullAttackRight[hitAnimationTick][3], super.getX(), super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackRight[hitAnimationTick][4], super.getX() + shift32pix, super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackRight[hitAnimationTick][5], super.getX() + shift32pix * 2, super.getY() + shift32pix + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullAttackRight[hitAnimationTick][6], super.getX(), super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackRight[hitAnimationTick][7], super.getX() + shift32pix, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullAttackRight[hitAnimationTick][8], super.getX() + shift32pix * 2, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);

                }
            } else {
                if (turning) {
                    g.drawImage(Assets.bullTurning[turnAnimationTick][0], super.getX(), super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullTurning[turnAnimationTick][1], super.getX() + shift32pix, super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullTurning[turnAnimationTick][2], super.getX() + shift32pix * 2, super.getY() + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullTurning[turnAnimationTick][3], super.getX(), super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullTurning[turnAnimationTick][4], super.getX() + shift32pix, super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullTurning[turnAnimationTick][5], super.getX() + shift32pix * 2, super.getY() + shift32pix + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullTurning[turnAnimationTick][6], super.getX(), super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullTurning[turnAnimationTick][7], super.getX() + shift32pix, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullTurning[turnAnimationTick][8], super.getX() + shift32pix * 2, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                } else {
                    if (!bullFacingLeft) {
                        g.drawImage(Assets.bullRight[moveAnimation][0], super.getX(), super.getY() + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullRight[moveAnimation][1], super.getX() + shift32pix, super.getY() + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullRight[moveAnimation][2], super.getX() + shift32pix * 2, super.getY() + shiftForCentering, 32, 32, null);

                        g.drawImage(Assets.bullRight[moveAnimation][3], super.getX(), super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullRight[moveAnimation][4], super.getX() + shift32pix, super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullRight[moveAnimation][5], super.getX() + shift32pix * 2, super.getY() + shift32pix + shiftForCentering, 32, 32, null);

                        g.drawImage(Assets.bullRight[moveAnimation][6], super.getX(), super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullRight[moveAnimation][7], super.getX() + shift32pix, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullRight[moveAnimation][8], super.getX() + shift32pix * 2, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);

                    } else {
                        g.drawImage(Assets.bullLeft[moveAnimation][0], super.getX(), super.getY() + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullLeft[moveAnimation][1], super.getX() + shift32pix, super.getY() + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullLeft[moveAnimation][2], super.getX() + shift32pix * 2, super.getY() + shiftForCentering, 32, 32, null);

                        g.drawImage(Assets.bullLeft[moveAnimation][3], super.getX(), super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullLeft[moveAnimation][4], super.getX() + shift32pix, super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullLeft[moveAnimation][5], super.getX() + shift32pix * 2, super.getY() + shift32pix + shiftForCentering, 32, 32, null);

                        g.drawImage(Assets.bullLeft[moveAnimation][6], super.getX(), super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullLeft[moveAnimation][7], super.getX() + shift32pix, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                        g.drawImage(Assets.bullLeft[moveAnimation][8], super.getX() + shift32pix * 2, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    }
                }
            }
            drawHPbar(g);

        } else {
            if (!deadAnimationPlayed) {
                if (bullFacingLeft) {
                    g.drawImage(Assets.bullDeathLeft[deadAnimation][0], super.getX(), super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[deadAnimation][1], super.getX() + shift32pix, super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[deadAnimation][2], super.getX() + shift32pix * 2, super.getY() + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullDeathLeft[deadAnimation][3], super.getX(), super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[deadAnimation][4], super.getX() + shift32pix, super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[deadAnimation][5], super.getX() + shift32pix * 2, super.getY() + shift32pix + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullDeathLeft[deadAnimation][6], super.getX(), super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[deadAnimation][7], super.getX() + shift32pix, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[deadAnimation][8], super.getX() + shift32pix * 2, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);

                } else {
                    g.drawImage(Assets.bullDeathRight[deadAnimation][0], super.getX(), super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[deadAnimation][1], super.getX() + shift32pix, super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[deadAnimation][2], super.getX() + shift32pix * 2, super.getY() + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullDeathRight[deadAnimation][3], super.getX(), super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[deadAnimation][4], super.getX() + shift32pix, super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[deadAnimation][5], super.getX() + shift32pix * 2, super.getY() + shift32pix + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullDeathRight[deadAnimation][6], super.getX(), super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[deadAnimation][7], super.getX() + shift32pix, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[deadAnimation][8], super.getX() + shift32pix * 2, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);

                }
            } else {
                if (bullFacingLeft) {
                    g.drawImage(Assets.bullDeathLeft[4][0], super.getX(), super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[4][1], super.getX() + shift32pix, super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[4][2], super.getX() + shift32pix * 2, super.getY() + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullDeathLeft[4][3], super.getX(), super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[4][4], super.getX() + shift32pix, super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[4][5], super.getX() + shift32pix * 2, super.getY() + shift32pix + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullDeathLeft[4][6], super.getX(), super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[4][7], super.getX() + shift32pix, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathLeft[4][8], super.getX() + shift32pix * 2, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                } else {
                    g.drawImage(Assets.bullDeathRight[4][0], super.getX(), super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[4][1], super.getX() + shift32pix, super.getY() + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[4][2], super.getX() + shift32pix * 2, super.getY() + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullDeathRight[4][3], super.getX(), super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[4][4], super.getX() + shift32pix, super.getY() + shift32pix + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[4][5], super.getX() + shift32pix * 2, super.getY() + shift32pix + shiftForCentering, 32, 32, null);

                    g.drawImage(Assets.bullDeathRight[4][6], super.getX(), super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[4][7], super.getX() + shift32pix, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                    g.drawImage(Assets.bullDeathRight[4][8], super.getX() + shift32pix * 2, super.getY() + shift32pix * 2 + shiftForCentering, 32, 32, null);
                }
            }
        }

 /*
        g.setColor(Color.white);
        g.fillRect(super.getX() + 2, super.getY() - 23, 90, 65); // HITBOX

        g.setColor(Color.red);
        g.drawLine(getHitVectorsOnPosition(0), getHitVectorsOnPosition(1), getHitVectorsOnPosition(2), getHitVectorsOnPosition(3));
        g.drawLine(getHitVectorsOnPosition(4), getHitVectorsOnPosition(5), getHitVectorsOnPosition(6), getHitVectorsOnPosition(7));


        g.fillRect(super.getX() + 17, super.getY() - 26, 60, 5);  //TOP
        g.fillRect(super.getX() + 17, super.getY() + 29, 60, 5);  //BOTTOM
        g.fillRect(super.getX() + 76, super.getY() + 12, 5, 20); //RIGHT
        g.fillRect(super.getX() + 14, super.getY() + 12, 5, 20);  //LEFT

         */

    }


    private void drawHPbar(Graphics g) {
        g.setColor(Color.RED);
        for (int i = 0; i < getHP() / 4; i++) {
            g.fillRect(super.getX() + i - 4, super.getY() + 38, 1, 2);
        }

    }

    private void positionUpdate() {
        if (super.isAlive()) {
            wantToGoLeft = super.getX() > handler.getGame().getPlayer().getX();
            checkForTurn();
        }

        jumpManager();


        if (rightIntersection() || leftIntersection()) {
            super.setY(super.getY() - 5);


        }
        if (bottomIntersection()) {
            super.setY(super.getY() + super.getGRAVITY());
        }


    }

    private void jumpManager() {
        if (redayToJump1 && getEnemyHitBox().intersects(handler.getGame().getWorld().getJumpPoint())) {
            redayToJump2 = true;
        }
        if (redayToJump2) {
            setY(getY() - jump[jumpTick]);
            jumpTick++;
            if (jumpTick == jump.length) {
                jumpTick = 0;
                redayToJump1 = false;
                redayToJump2 = false;
            }
        }

    }

    private void checkForTurn() {
        if (downStuck || upStuck) {
            wantToGoLeft = true;
        }

        if (!turning) {
            if ((bullFacingLeft && !wantToGoLeft) || (!bullFacingLeft && wantToGoLeft)) {
                turning = true;
            } else {
                if (bullFacingLeft && !leftIntersection()) {
                    super.setX(super.getX() - 3);
                }
                if (!bullFacingLeft && !rightIntersection()) {
                    super.setX(super.getX() + 3);
                }
            }
        } else {

            if (clockForTurning == 5) {
                clockForTurning = 0;

                if (bullFacingLeft) {

                    if (setTick) {
                        turnAnimationTick = 8;
                        setTick = false;
                    }
                    turnAnimationTick--;
                    if (turnAnimationTick < 0) {
                        turnAnimationTick = 0;
                        turning = false;
                        bullFacingLeft = false;
                        setTick = true;
                    }
                } else {
                    turnAnimationTick++;
                    if (turnAnimationTick > 7) {
                        turning = false;
                        bullFacingLeft = true;
                        turnAnimationTick = 7;
                    }
                }
            } else {
                clockForTurning++;
            }
        }
    }

    private void boundsUpdate() {

        super.setEnemyHitBox(new Rectangle(super.getX() + 2, super.getY() - 23, 90, 65));
        super.setDownBounds(new Rectangle(super.getX() + 17, super.getY() + 29, 60, 5));
        super.setRightBounds(new Rectangle(super.getX() + 76, super.getY() + 12, 5, 20));
        super.setLeftBounds(new Rectangle(super.getX() + 14, super.getY() + 12, 5, 20));

        super.setHitVectors(0, super.getX() + 2); // x1
        super.setHitVectors(1, super.getY() - 22); // y1
        super.setHitVectors(2, super.getX() + 90); // x2
        super.setHitVectors(3, super.getY() + 31); // y2

        super.setHitVectors(4, super.getX() + 90); // x11
        super.setHitVectors(5, super.getY() - 22); // y11
        super.setHitVectors(6, super.getX() + 2); // x22
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
