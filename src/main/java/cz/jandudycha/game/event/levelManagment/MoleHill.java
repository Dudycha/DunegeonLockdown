package cz.jandudycha.game.event.levelManagment;




import cz.jandudycha.game.entity.enemy.SmallWorm;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.Random;

public class MoleHill {


    private final Handler handler;
    private final Events events;
    private int clock = 0;
    private int amoutToSpawn = 0;
    private int intervalOfSpawning = 0;
    private int x, y;
    private int wormSpawnClock = 0;
    private boolean initialSpawn = true;
    private int moleHillAnimation = 0;


    public MoleHill(Handler handler, Events events) {
        this.handler = handler;
        this.events = events;
    }

    public void spawn(int amoutToSpawn, int intervalOfSpawning) {
        Random rnd = new Random();
        this.amoutToSpawn = amoutToSpawn;
        this.intervalOfSpawning = intervalOfSpawning;

        int positionOfSpawn = rnd.nextInt(2);

        if (positionOfSpawn == 0) {
            y = 255;
            x = rnd.nextInt(250) + 50;
            this.intervalOfSpawning += rnd.nextInt(10);
        }  else if (positionOfSpawn == 1) {
            y = 198;
            x = rnd.nextInt(265) + 770;
            this.intervalOfSpawning += rnd.nextInt(10);
        }
    }

    private void spawnSmallWorm() {
        if (wormSpawnClock == intervalOfSpawning) {
            events.getListOfEnemies().add(new SmallWorm(handler, x, y));
            amoutToSpawn--;
            wormSpawnClock = 0;
        } else {
            wormSpawnClock++;
        }
    }

    public void update() {

        if (initialSpawn) {
            if (clock == 12) {
                clock = 0;
                moleHillAnimation++;
                if (moleHillAnimation == Assets.moleHill.length - 1) {
                    initialSpawn = false;
                }
            } else {
                clock++;
            }
        }
        if (!initialSpawn && amoutToSpawn > 0) {
            spawnSmallWorm();
        }
    }

    public int getAmoutToSpawn() {
        return amoutToSpawn;
    }

    public void render(Graphics g) {
        g.drawImage(Assets.moleHill[moleHillAnimation], x, y, 36, 36, null);
    }


}
