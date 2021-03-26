package cz.jandudycha.game.entity.enemy;



import cz.jandudycha.game.event.dropItems.Reward;
import cz.jandudycha.game.event.dropItems.RewardType;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.Random;

public class SmallWorm extends Enemy {

    private final Handler handler;
    private final int SMALLWORMDEFFAULTHP = 5;
    private int moveAnimation = 0;
    private int clock1 = 0;
    private boolean deadAnimationPlayed = false;
    private int deadAnimation = 0;
    private final Random rnd = new Random();

    public SmallWorm(Handler handler, int x, int y) {
        super(handler, x, y);
        this.handler = handler;
        super.setVELOCITY(3);
        super.setHP(SMALLWORMDEFFAULTHP);
    }

    @Override
    public void update() {
        checkForFellOffMap();
        boundsUpdate();
        positionUpdate();
        super.despawn();

        if (clock1 == 10) {
            clock1 = 0;
            if (moveAnimation == 2) {
                moveAnimation = 0;
            } else {
                moveAnimation++;
            }

            if (!super.isAlive()) {
                if (deadAnimation < 2) {
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
        handler.getGame().getPlayer().setScore(handler.getGame().getPlayer().getScore() + 3);
        handler.getGame().getPlayer().setEnemiesKilled(handler.getGame().getPlayer().getEnemiesKilled() + 1);
        int roll = rnd.nextInt(100);
        if (roll < 10) {
            roll = rnd.nextInt(100);
            if (roll > -1 && roll < 80) {
                handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.coin));
            } else if (roll > 79 && roll < 95) {
                handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.goldNuget));
            } else if (roll > 94 && roll < 100) {
                handler.getGame().getEvents().getRewards().add(new Reward(handler, getX(), getY(), RewardType.goldBar));
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (super.isAlive()) {
            g.drawImage(Assets.smallWorm[moveAnimation], super.getX(), super.getY(), 43, 43, null);


            drawHPbar(g);

        } else {
            if (!deadAnimationPlayed) {
                g.drawImage(Assets.smallWormDeadAnimation[deadAnimation], super.getX(), super.getY(), 43, 43, null);
            } else {
                g.drawImage(Assets.smallWormDeadAnimation[2], super.getX(), super.getY(), 43, 43, null);
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
                g.fillRect(super.getX() + (i * 3) + 13, super.getY() + 38, 3, 2);
            }
    }


    private void positionUpdate() {
        if (super.isAlive()) {
            if (isStillAfterSpawning()) {
                if (super.getX() > handler.getGame().getPlayer().getX() && !leftIntersection()) {
                    super.setX(super.getX() - 1);
                }
                if (super.getX() < handler.getGame().getPlayer().getX() && !rightIntersection()) {
                    super.setX(super.getX() + 1);
                }
            }
            if (rightIntersection() || leftIntersection()) {
                super.setY(super.getY() - 5);

            }

        }
        if (!bottomIntersection()) {
            super.setY(super.getY() + super.getGRAVITY());
        }else{
            setStillAfterSpawning(false);
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
