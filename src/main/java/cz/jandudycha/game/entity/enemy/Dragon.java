package cz.jandudycha.game.entity.enemy;



import cz.jandudycha.game.event.dropItems.DropCrate;
import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.event.dropItems.Reward;
import cz.jandudycha.game.event.dropItems.RewardType;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Dragon extends Enemy {

    private final int DRAGONDEFAULTHP = 1200, FIRE_BALL_INTERVAL = 200;
    private boolean dragonFacingLeft;
    private int clock1 = 0, clock2 = 0, clockForTurning = 0, clockSpeed = 6, fireBallClock = 0; //speed was 4
    private int turnAnimationTick = 0, hitAnimationTick = 0, moveAnimation = 0, deadAnimation = 0, fireBallAnimation = 0;
    private boolean deadAnimationPlayed = false;
    private boolean turning = false;
    private boolean wantToGoLeft = false;
    private boolean setTick = true;
    private boolean hitToPlayer = false;
    private boolean downStuck, upStuck;
    private boolean leftRightHitBoxShrink;
    private int downStuckCounter = 0, upStuckCounter = 0;
    private int fireballAttack = 0;
    private final ArrayList<FireBall> fireballs = new ArrayList<>();

    public Dragon(Handler handler, int x, int y) {
        super(handler, x, y);
        super.setHP(DRAGONDEFAULTHP);
        super.setDespawnTimer(500);
    }

    @Override
    public void update() {
        checkForFellOffMap();
        boundsUpdate();

        if (fireballAttack < FIRE_BALL_INTERVAL) {
            checkForHitToPlayer();
            checkForBeeingStuck();
            if (hitToPlayer) {
                tickHitAnimation();
            } else {
                positionUpdate();
            }
        } else {
            calculateFireballAttack();
        }
        super.despawn();

        tickMovement();


        if (getHP() <= 0) {
            if (super.isAlive()) {
                reward();
            }
            super.setAlive(false);
        }
        for (FireBall fireball : fireballs) {
            fireball.update();
            if (fireball.isRedayToRemove()) {
                fireballs.remove(fireball);
                break;
            }
        }
    }

    private void calculateFireballAttack() {
        if (fireBallClock == 10) {
            fireBallAnimation++;
            fireBallClock = 0;

            if (fireBallAnimation == 3 && isAlive()) {
                if (dragonFacingLeft) {
                    fireballs.add(new FireBall(getHandler(), getX() - 20, getY() - 40, getHandler().getGame().getPlayer().getX() + 30, getHandler().getGame().getPlayer().getY() + 30));

                } else {
                    fireballs.add(new FireBall(getHandler(), getX() + 20, getY() - 40, getHandler().getGame().getPlayer().getX() + 30, getHandler().getGame().getPlayer().getY() + 30));
                }

            }

            if (fireBallAnimation == Assets.dragonFireballRight.length) {
                Random rnd = new Random();
                if (rnd.nextInt(2) == 0) {
                    fireballAttack = FIRE_BALL_INTERVAL;
                } else {
                    fireballAttack = 0;
                }

                fireBallAnimation = 0;
            }
        } else {
            fireBallClock++;
        }
    }

    private void tickMovement() {
        if (clock1 == clockSpeed) {
            clock1 = 0;
            if (moveAnimation == Assets.dragonMovingRight.length - 1) {
                moveAnimation = 0;
            } else {
                moveAnimation++;
            }

            if (!super.isAlive()) {
                clockSpeed = 6;
                if (deadAnimation < Assets.dragonDeathRight.length - 1) {
                    deadAnimation++;
                } else {
                    deadAnimationPlayed = true;
                }
            }
        } else {
            clock1++;
        }
    }

    private void checkForBeeingStuck() {
        if (getEnemyHitBox().intersects(getHandler().getGame().getWorld().getMiddleDownStuck()) &&
                !getHandler().getGame().getPlayer().getPlayerMovement().getHitBoxPlayer().intersects(getHandler().getGame().getWorld().getMiddleDownStuck())) {

            downStuckCounter++;
        } else {
            downStuckCounter = 0;
        }

        if (getEnemyHitBox().intersects(getHandler().getGame().getWorld().getMiddleUpStuck()) &&
                !getHandler().getGame().getPlayer().getPlayerMovement().getHitBoxPlayer().intersects(getHandler().getGame().getWorld().getMiddleUpStuck())) {


            upStuckCounter++;
        } else {
            upStuckCounter = 0;
        }

        if (downStuckCounter > 80) {
            downStuck = true;
        }
        if (upStuckCounter > 80) {
            upStuck = true;
        }

        if (getEnemyHitBox().intersects(getHandler().getGame().getWorld().getTurnPointForSpiders()) && downStuck) {
            downStuck = false;
        }
        if (getEnemyHitBox().intersects(getHandler().getGame().getWorld().getTurnPointForSpiders()) && upStuck) {
            upStuck = false;
            leftRightHitBoxShrink = true;
        }
        if (getEnemyHitBox().intersects(getHandler().getGame().getWorld().getReliefForDragon())) {
            leftRightHitBoxShrink = false;
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

    @Override
    public void render(Graphics g) {
        int pictureShift = -85;

        if (super.isAlive()) {
            if (fireballAttack == FIRE_BALL_INTERVAL) {
                if (dragonFacingLeft) {
                    g.drawImage(Assets.dragonFireballLeft[fireBallAnimation], super.getX() + pictureShift, super.getY() + pictureShift, null);
                } else {
                    g.drawImage(Assets.dragonFireballRight[fireBallAnimation], super.getX() + pictureShift, super.getY() + pictureShift, null);
                }
            } else {
                if (hitToPlayer) {

                    if (dragonFacingLeft) {
                        g.drawImage(Assets.dragonAttackLeft[hitAnimationTick], super.getX() + pictureShift, super.getY() + pictureShift, null);
                    } else {
                        g.drawImage(Assets.dragonAttackRight[hitAnimationTick], super.getX() + pictureShift, super.getY() + pictureShift, null);
                    }
                } else {
                    if (turning) {
                        g.drawImage(Assets.dragonTurningLeft[turnAnimationTick], super.getX() + pictureShift, super.getY() + pictureShift, null);
                    } else {
                        if (!dragonFacingLeft) {
                            g.drawImage(Assets.dragonMovingRight[moveAnimation], super.getX() + pictureShift, super.getY() + pictureShift, null);
                        } else {
                            g.drawImage(Assets.dragonMovingLeft[moveAnimation], super.getX() + pictureShift, super.getY() + pictureShift, null);
                        }
                    }
                }
            }
            drawHPbar(g);

        } else {
            if (!deadAnimationPlayed) {
                if (dragonFacingLeft) {
                    g.drawImage(Assets.dragonDeathLeft[deadAnimation], super.getX() + pictureShift, super.getY() + pictureShift, null);

                } else {
                    g.drawImage(Assets.dragonDeathRight[deadAnimation], super.getX() + pictureShift, super.getY() + pictureShift, null);
                }
            } else {
                if (dragonFacingLeft) {
                    g.drawImage(Assets.dragonDeathLeft[Assets.dragonDeathRight.length - 1], super.getX() + pictureShift, super.getY() + pictureShift, null);
                } else {
                    g.drawImage(Assets.dragonDeathRight[Assets.dragonDeathRight.length - 1], super.getX() + pictureShift, super.getY() + pictureShift, null);
                }
            }
        }
        for (FireBall fireball : fireballs) {
            fireball.render(g);
        }
/*
        g.setColor(Color.GREEN);
        g.drawLine(getHitVectorsOnPosition(0), getHitVectorsOnPosition(1), getHitVectorsOnPosition(2), getHitVectorsOnPosition(3));
        g.drawLine(getHitVectorsOnPosition(4), getHitVectorsOnPosition(5), getHitVectorsOnPosition(6), getHitVectorsOnPosition(7));

        g.setColor(Color.blue);
        g.drawRect(super.getX(), super.getY(), 5, 5);//middle point

        g.setColor(Color.white);
        g.fillRect(super.getX() -40, super.getY() - 40, 70, 70);

        g.setColor(Color.red);
        g.fillRect(super.getX() - 40, super.getY() - 43, 70, 5);
        g.fillRect(super.getX() -40, super.getY() + 27, 70, 5);
        g.fillRect(super.getX() + 30, super.getY() - 38, 5, 65); //right
        g.fillRect(super.getX() - 43, super.getY() - 38, 5, 65); //left

*/

    }

    private void boundsUpdate() {

        super.setEnemyHitBox(new Rectangle(super.getX() - 40, super.getY() - 40, 70, 70));
        super.setDownBounds(new Rectangle(super.getX() - 40, super.getY() + 27, 70, 5));
        if (!leftRightHitBoxShrink) {
            super.setRightBounds(new Rectangle(super.getX() + 30, super.getY() - 38, 5, 65));
            super.setLeftBounds(new Rectangle(super.getX() - 43, super.getY() - 38, 5, 65));
        }
        if (turning) {
            super.setHitVectors(0, super.getX() + 40); // x1
            super.setHitVectors(1, super.getY() + 30); // y1
            super.setHitVectors(2, super.getX() - 10); // x2
            super.setHitVectors(3, super.getY() - 55); // y2

            super.setHitVectors(4, super.getX() - 40); // x11
            super.setHitVectors(5, super.getY() + 30); // y11
            super.setHitVectors(6, super.getX() - 10); // x22
            super.setHitVectors(7, super.getY() - 55); // y22
        } else {
            if (dragonFacingLeft) {
                super.setHitVectors(0, super.getX() + 70); // x1
                super.setHitVectors(1, super.getY() - 7); // y1
                super.setHitVectors(2, super.getX() - 65); // x2 -
                super.setHitVectors(3, super.getY() - 55); // y2

                super.setHitVectors(4, super.getX()); // x11
                super.setHitVectors(5, super.getY() + 30); // y11
                super.setHitVectors(6, super.getX() - 65); // x22
                super.setHitVectors(7, super.getY() - 55); // y22


            } else {
                super.setHitVectors(0, super.getX() - 70); // x1
                super.setHitVectors(1, super.getY() - 7); // y1
                super.setHitVectors(2, super.getX() + 60); // x2
                super.setHitVectors(3, super.getY() - 55); // y2

                super.setHitVectors(4, super.getX()); // x11
                super.setHitVectors(5, super.getY() + 30); // y11
                super.setHitVectors(6, super.getX() + 60); // x22
                super.setHitVectors(7, super.getY() - 55); // y22}
            }
        }
    }

    private void drawHPbar(Graphics g) {
        g.setColor(Color.RED);
        for (int i = 0; i < getHP() / 8; i++) {
            g.fillRect(super.getX() + i - 70, super.getY() + 38, 1, 2);
        }
    }

    private void positionUpdate() {
        if (super.isAlive()) {
            if (!turning) {
                fireballAttack++;
            }
            wantToGoLeft = super.getX() > getHandler().getGame().getPlayer().getX();
            checkForTurn();
        }

        if (rightIntersection() || leftIntersection()) {
            super.setY(super.getY() - 5);


        }
        if (bottomIntersection()) {
            super.setY(super.getY() + super.getGRAVITY());
        }


    }

    private void checkForHitToPlayer() {
        if (getEnemyHitBox().intersects(getHandler().getGame().getPlayer().getPlayerMovement().getHitBoxPlayer()) && !hitToPlayer && isAlive()) {
            getHandler().getGame().getPlayer().setHitpoints(getHandler().getGame().getPlayer().getHitpoints() - 1);
            getHandler().getGame().getPlayer().setTotalDMGrecieved(getHandler().getGame().getPlayer().getTotalDMGrecieved() + 1);
            hitToPlayer = true;
        }
    }

    private void checkForTurn() {
        if (downStuck || upStuck) {
            wantToGoLeft = true;
        }

        if (!turning) {
            if ((dragonFacingLeft && !wantToGoLeft) || (!dragonFacingLeft && wantToGoLeft)) {
                turning = true;
            } else {
                if (dragonFacingLeft && !leftIntersection()) {
                    super.setX(super.getX() - 4);
                }
                if (!dragonFacingLeft && !rightIntersection()) {
                    super.setX(super.getX() + 4);
                }
            }
        } else {

            if (clockForTurning == 5) {
                clockForTurning = 0;

                if (dragonFacingLeft) {

                    if (setTick) {
                        turnAnimationTick = Assets.dragonTurningLeft.length - 1;
                        setTick = false;
                    }
                    turnAnimationTick--;
                    if (turnAnimationTick < 0) {
                        turnAnimationTick = 0;
                        turning = false;
                        dragonFacingLeft = false;
                        setTick = true;
                    }
                } else {
                    turnAnimationTick++;
                    if (turnAnimationTick > Assets.dragonTurningLeft.length - 1) {
                        turning = false;
                        dragonFacingLeft = true;
                        turnAnimationTick = Assets.dragonTurningLeft.length - 1;
                    }
                }
            } else {
                clockForTurning++;
            }
        }
    }

    private void reward() {
        getHandler().getGame().getPlayer().setScore(getHandler().getGame().getPlayer().getScore() + 170);
        getHandler().getGame().getPlayer().setEnemiesKilled(getHandler().getGame().getPlayer().getEnemiesKilled() + 1);
        getHandler().getGame().getEvents().getRewards().add(new Reward(getHandler(), getX() - 30, getY(), RewardType.goldBar));
        getHandler().getGame().getEvents().getRewards().add(new Reward(getHandler(), getX(), getY(), RewardType.goldBar));
        getHandler().getGame().getEvents().getRewards().add(new Reward(getHandler(), getX() + 30, getY(), RewardType.goldBar));
        getHandler().getGame().getEvents().getDropCrates().add(new DropCrate(getHandler(), ItemType.apple));
        getHandler().getGame().getEvents().getDropCrates().add(new DropCrate(getHandler(), ItemType.apple));


    }


    public boolean bottomIntersection() {
        for (Rectangle rec : getHandler().getGame().getWorld().getListBarier()) {
            if (rec.intersects(super.getDownBounds())) {
                return false;
            }
        }
        return true;
    }

    public boolean rightIntersection() {
        for (Rectangle rec : getHandler().getGame().getWorld().getListBarier()) {
            if (rec.intersects(super.getRightBounds())) {
                return true;
            }
        }
        return false;
    }

    public boolean leftIntersection() {
        for (Rectangle rec : getHandler().getGame().getWorld().getListBarier()) {
            if (rec.intersects(super.getLeftBounds())) {
                return true;
            }
        }
        return false;
    }

}
