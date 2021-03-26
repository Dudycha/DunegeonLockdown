package cz.jandudycha.game.entity.enemy;



import cz.jandudycha.game.event.dropItems.Reward;
import cz.jandudycha.game.event.dropItems.RewardType;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.Random;

public class Skeleton extends Enemy {

    private final Handler handler;
    private final int SKELETONDEFAULTHP = 60;
    private int moveAnimation = 0, hitAnimationTick = 0;
    private boolean skeletonFacingLeft;
    private int clock1 = 0, clock2 = 0;
    private boolean deadAnimationPlayed = false;
    private int deadAnimation = 0;
    private int clockSpeed = 20;
    private boolean hitToPlayer = false;
    private final Random rnd = new Random();

    public Skeleton(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        super.setVELOCITY(3);
        super.setHP(SKELETONDEFAULTHP);
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

        if (clock1 == clockSpeed) {
            clock1 = 0;
            if (moveAnimation == 3) {
                moveAnimation = 0;
            } else {
                moveAnimation++;
            }

            if (!super.isAlive()) {
                clockSpeed = 5;
                if (deadAnimation < 5) {
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
        handler.getGame().getPlayer().setScore(handler.getGame().getPlayer().getScore() + 15);
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
                if (skeletonFacingLeft) {
                    g.drawImage(Assets.skeletonLeftAttackAnimation[hitAnimationTick][0], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.skeletonLeftAttackAnimation[hitAnimationTick][1], super.getX(), super.getY() - 13, 45, 45, null);
                } else {
                    g.drawImage(Assets.skeletonRightAttackAnimation[hitAnimationTick][0], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.skeletonRightAttackAnimation[hitAnimationTick][1], super.getX(), super.getY() - 13, 45, 45, null);
                }
            } else {
                if (skeletonFacingLeft) {
                    g.drawImage(Assets.skeletonLeftUpperPart[moveAnimation], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.skeletonLeftLowerPart[moveAnimation], super.getX(), super.getY() - 13, 45, 45, null);
                } else {
                    g.drawImage(Assets.skeletonRightUpperPart[moveAnimation], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.skeletonRightLowerPart[moveAnimation], super.getX(), super.getY() - 13, 45, 45, null);
                }


            }
            drawHPbar(g);
        } else {
            if (!deadAnimationPlayed) {
                if (skeletonFacingLeft) {
                    g.drawImage(Assets.skeletonLeftDeathAnimation[0][deadAnimation], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.skeletonLeftDeathAnimation[1][deadAnimation], super.getX(), super.getY() - 13, 45, 45, null);
                } else {
                    g.drawImage(Assets.skeletonRightDeathAnimation[0][deadAnimation], super.getX(), super.getY() - 58, 45, 45, null);
                    g.drawImage(Assets.skeletonRightDeathAnimation[1][deadAnimation], super.getX(), super.getY() - 13, 45, 45, null);
                }
            } else {
                if (skeletonFacingLeft) {
                    g.drawImage(Assets.skeletonLeftDeathAnimation[1][5], super.getX(), super.getY() - 13, 45, 45, null);
                } else {
                    g.drawImage(Assets.skeletonRightDeathAnimation[1][5], super.getX(), super.getY() - 13, 45, 45, null);
                }

            }

        }


/*
        g.setColor(Color.white);
        g.fillRect(super.getX() + 5, super.getY() -23, 30, 55);

        g.setColor(Color.red);
        g.drawLine(getHitVectorsOnPosition(0), getHitVectorsOnPosition(1), getHitVectorsOnPosition(2), getHitVectorsOnPosition(3));
        g.drawLine(getHitVectorsOnPosition(4), getHitVectorsOnPosition(5), getHitVectorsOnPosition(6), getHitVectorsOnPosition(7));


        g.fillRect(super.getX() + 5, super.getY() -26, 30, 5);
        g.fillRect(super.getX() + 5, super.getY() + 29, 31, 5);
        g.fillRect(super.getX() + 33, super.getY() -23, 5, 55);
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
        if (clock2 == 4) {
            clock2 = 0;
            hitAnimationTick++;
            if (hitAnimationTick > Assets.skeletonRightAttackAnimation.length - 1) {
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

        if (super.isAlive()) {
            if (isStillAfterSpawning()) {
                if (super.getX() > handler.getGame().getPlayer().getX() && !leftIntersection()) {
                    super.setX(super.getX() - 1);
                    skeletonFacingLeft = true;
                }
                if (super.getX() < handler.getGame().getPlayer().getX() && !rightIntersection()) {
                    super.setX(super.getX() + 1);
                    skeletonFacingLeft = false;
                }
            }
            if (rightIntersection() || leftIntersection()) {
                super.setY(super.getY() - 5);

            }

        }
        if (bottomIntersection()) {
            super.setY(super.getY() + super.getGRAVITY());
        } else {
            setStillAfterSpawning(false);
        }


    }


    private void boundsUpdate() {

        super.setEnemyHitBox(new Rectangle(super.getX() + 5, super.getY() - 23, 32, 55));
        super.setDownBounds(new Rectangle(super.getX() + 5, super.getY() + 29, 31, 5));
        super.setRightBounds(new Rectangle(super.getX() + 33, super.getY() - 23, 5, 55));
        super.setLeftBounds(new Rectangle(super.getX() + 2, super.getY() - 23, 5, 55));

        super.setHitVectors(0, super.getX() + 5); // x1
        super.setHitVectors(1, super.getY() - 22); // y1
        super.setHitVectors(2, super.getX() + 32); // x2
        super.setHitVectors(3, super.getY() + 31); // y2

        super.setHitVectors(4, super.getX() + 30); // x11
        super.setHitVectors(5, super.getY() - 22); // y11
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
