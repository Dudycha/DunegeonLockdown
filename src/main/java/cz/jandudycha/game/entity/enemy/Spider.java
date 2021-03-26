package cz.jandudycha.game.entity.enemy;




import cz.jandudycha.game.event.dropItems.Reward;
import cz.jandudycha.game.event.dropItems.RewardType;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.Random;

public class Spider extends Enemy {

    private final Handler handler;
    private final int SPIDERDEFFAULTHP = 10;
    private boolean pavoukFacingLeft;
    private int rand = 0;
    private int jumpTick = 0;
    private int moveAnimation = 0, hitAnimationTick = 0;
    private int clock1 = 0, clock2 = 0;
    private boolean deadAnimationPlayed = false;
    private final int[] jump = {7, 12, 11, 9, 7, 7, 7, 6, 5, 5, 5, 3, 2, 2, 1};
    private boolean aboutToJump, jumpUp;
    private int deadAnimation = 0;
    private int clockSpeed = 20;
    private boolean hitToPlayer = false;
    private boolean escapingSafeSpot = false;
    private final Random rnd = new Random();


    public Spider(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        super.setVELOCITY(3);
        super.setHP(SPIDERDEFFAULTHP);
    }


    @Override
    public void update() {
        checkForPlayerSafeSpoting();
        checkForFellOffMap();
        boundsUpdate();
        checkForHitToPlayer();
        if (hitToPlayer) {
            tickHitAnimation();
        } else {
            positionUpdate();
        }

        super.despawn();

        if (clock1 == clockSpeed) {
            clock1 = 0;
            if (moveAnimation == 3) {
                moveAnimation = 0;
            } else {
                moveAnimation++;
            }

            if (!super.isAlive()) {
                clockSpeed = 5;
                if (deadAnimation < 3) {
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
        handler.getGame().getPlayer().setScore(handler.getGame().getPlayer().getScore() + 8);
        handler.getGame().getPlayer().setEnemiesKilled(handler.getGame().getPlayer().getEnemiesKilled() + 1);
        int roll = rnd.nextInt(100);
        if (roll < 33) {
            roll = rnd.nextInt(100);
            if (roll > -1 && roll < 80) {
                handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.coin));
            } else if (roll > 79 && roll < 97) {
                handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.goldNuget));
            } else if (roll > 96 && roll < 100) {
                handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.goldBar));
            }
        }
    }

    private void checkForPlayerSafeSpoting() {
        if (handler.getGame().getPlayer().getPlayerMovement().getHitBoxPlayer().intersects(handler.getGame().getWorld().getMiddleUpStuck()) &&
                getEnemyHitBox().intersects(handler.getGame().getWorld().getMiddleDownStuck()) && isStillAfterSpawning()) {
            escapingSafeSpot = true;
        }
    }


    @Override
    public void render(Graphics g) {
        if (super.isAlive()) {
            if (hitToPlayer) {
                if (pavoukFacingLeft) {
                    g.drawImage(Assets.pavoukAttackLeft[hitAnimationTick], super.getX(), super.getY(), 43, 43, null);
                } else {
                    g.drawImage(Assets.pavoukAttackRight[hitAnimationTick], super.getX(), super.getY(), 43, 43, null);
                }
            } else {
                if (pavoukFacingLeft) {
                    g.drawImage(Assets.pavoukLeft, super.getX(), super.getY(), 43, 43, null);
                } else {
                    g.drawImage(Assets.pavoukRight, super.getX(), super.getY(), 43, 43, null);
                }


            }
            drawHPbar(g);
        } else {
            if (!deadAnimationPlayed) {
                g.drawImage(Assets.spiderDeathAnimation[deadAnimation], super.getX(), super.getY(), 43, 43, null);
            } else {
                g.drawImage(Assets.spiderDeathAnimation[3], super.getX(), super.getY(), 43, 43, null);
            }

        }

/*
        g.setColor(Color.white);
        g.fillRect(super.getX() + 5, super.getY() + 7, 32, 26);

        g.setColor(Color.red);
        g.drawLine(getHitVectorsOnPosition(0), getHitVectorsOnPosition(1), getHitVectorsOnPosition(2), getHitVectorsOnPosition(3));
        g.drawLine(getHitVectorsOnPosition(4), getHitVectorsOnPosition(5), getHitVectorsOnPosition(6), getHitVectorsOnPosition(7));


        g.fillRect(super.getX() + 5, super.getY() +7, 30, 5);
        g.fillRect(super.getX() + 5, super.getY() + 29, 31, 5);
        g.fillRect(super.getX() + 33, super.getY() + 10, 5, 20);
        g.fillRect(super.getX() + 2, super.getY() + 10, 5, 20);
        */

    }

    private void drawHPbar(Graphics g) {
        g.setColor(Color.RED);
        for (int i = 0; i < getHP(); i++) {
            g.fillRect(super.getX() + (i * 3) + 8, super.getY() + 33, 3, 2);
        }
    }

    private void tickHitAnimation() {
        if (clock2 == 17) {
            clock2 = 0;
            hitAnimationTick++;
            if (hitAnimationTick > Assets.pavoukAttackLeft.length - 1) {
                hitToPlayer = false;
                hitAnimationTick = 0;
            }
        } else {
            clock2++;
        }
        if (!bottomIntersection()) {
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
        if (super.isAlive()) {
            if (!escapingSafeSpot) {
                if (isStillAfterSpawning()) {
                    if (super.getX() > handler.getGame().getPlayer().getX() && !leftIntersection()) {
                        super.setX(super.getX() - 2);
                        pavoukFacingLeft = true;
                    }
                    if (super.getX() < handler.getGame().getPlayer().getX() && !rightIntersection()) {
                        super.setX(super.getX() + 2);
                        pavoukFacingLeft = false;
                    }
                }
                if (rightIntersection() || leftIntersection()) {
                    super.setY(super.getY() - 5);

                }
            } else {
                if (!leftIntersection()) {
                    super.setX(super.getX() - 2);
                }
                if (rightIntersection() || leftIntersection()) {
                    super.setY(super.getY() - 5);

                }
                pavoukFacingLeft = true;

                if (getEnemyHitBox().intersects(handler.getGame().getWorld().getTurnPointForSpiders())) {
                    aboutToJump = true;
                    escapingSafeSpot = false;
                }
            }
            jump();
        }
        if (!bottomIntersection()) {
            super.setY(super.getY() + super.getGRAVITY());
        } else {
            setStillAfterSpawning(false);
        }


    }

    private void jump() {
        if (!aboutToJump) {
            if (rand == 1 && !escapingSafeSpot) {
                setY(getY() - jump[jumpTick]);
                jumpTick++;
                if (jumpTick == jump.length) {
                    jumpTick = 0;
                    rand = 0;
                }
            } else {
                if (bottomIntersection()) {
                    rand = (int) (Math.random() * 120);
                }
            }
        } else if (getEnemyHitBox().intersects(handler.getGame().getWorld().getJumpPoint())) {
            jumpUp = true;
            aboutToJump = false;
        }
        if (jumpUp) {
            setY(getY() - jump[jumpTick]);
            jumpTick++;
            if (jumpTick == jump.length) {
                jumpTick = 0;
                rand = 0;
                jumpUp = false;
            }
        }

    }

    private void boundsUpdate() {

        super.setEnemyHitBox(new Rectangle(super.getX() + 5, super.getY() + 7, 32, 26));
        super.setDownBounds(new Rectangle(super.getX() + 5, super.getY() + 29, 31, 5));
        super.setRightBounds(new Rectangle(super.getX() + 33, super.getY() + 10, 5, 20));
        super.setLeftBounds(new Rectangle(super.getX() + 2, super.getY() + 10, 5, 20));

        super.setHitVectors(0, super.getX() + 5); // x1
        super.setHitVectors(1, super.getY() + 7); // y1
        super.setHitVectors(2, super.getX() + 5 + 32); // x2
        super.setHitVectors(3, super.getY() + 5 + 26); // y2

        super.setHitVectors(4, super.getX() + 5 + 32); // x11
        super.setHitVectors(5, super.getY() + 7); // y11
        super.setHitVectors(6, super.getX() + 5); // x22
        super.setHitVectors(7, super.getY() + 5 + 26); // y22
    }

    public boolean bottomIntersection() {
        for (Rectangle rec : handler.getGame().getWorld().getListBarier()) {
            if (rec.intersects(super.getDownBounds())) {
                return true;
            }
        }
        return false;
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
