package cz.jandudycha.game.event.levelManagment;



import cz.jandudycha.game.entity.enemy.Spider;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;

public class Pipe {
    private final Handler handler;
    private final Events events;
    private int spidersWaiting = 0;
    private int spidersWaitTicks = 0;
    private int amoutToSpawn = 0;
    private int intervalOfSpawning = 0;
    private final int x,y;
    private boolean closed = true, opened, waiting;

    private int spiderSpawnClock = 0;

    public Pipe(Handler handler, Events events, int x, int y) {
        this.handler = handler;
        this.events = events;
        this.x = x;
        this.y = y;
    }

    public void spawn(int amoutToSpawn, int intervalOfSpawning, int spidersWaitTicks) {
        this.amoutToSpawn = amoutToSpawn;
        this.intervalOfSpawning = intervalOfSpawning;
        this.spidersWaitTicks = spidersWaitTicks;
    }

    private void spawnSpider() {
        if (spiderSpawnClock == intervalOfSpawning) {
            events.getListOfEnemies().add(new Spider(handler, x, y));
            amoutToSpawn--;
            spiderSpawnClock = 0;
        } else {
            spiderSpawnClock++;
        }
    }

    public void update() {
        if (amoutToSpawn > 0 && closed) {
            waiting();
        }
        if (amoutToSpawn > 0 && waiting) {
            spidersWaiting++;
            if (spidersWaiting > spidersWaitTicks) {
                open();
                spidersWaiting = 0;
            }
        }
        if (opened && amoutToSpawn > 0) {
            spawnSpider();
        }
        if (amoutToSpawn < 1) {
            close();
        }


    }

    public void render(Graphics g) {
        if (closed) {
            g.drawImage(Assets.trubkaZavrena[0], x, y, null);
            g.drawImage(Assets.trubkaZavrena[1], x + 32, y, null);
            g.drawImage(Assets.trubkaZavrena[2], x, y + 32, null);
            g.drawImage(Assets.trubkaZavrena[3], x + 32, y + 32, null);
        } else if (waiting) {
            g.drawImage(Assets.trubkaSOcima[0], x, y, null);
            g.drawImage(Assets.trubkaSOcima[1], x + 32, y, null);
            g.drawImage(Assets.trubkaSOcima[2], x, y + 32, null);
            g.drawImage(Assets.trubkaSOcima[3], x + 32, y + 32, null);
        } else if (opened) {
            g.drawImage(Assets.trubkaOtevrena[0], x, y, null);
            g.drawImage(Assets.trubkaOtevrena[1], x + 32, y, null);
            g.drawImage(Assets.trubkaOtevrena[2], x, y + 32, null);
            g.drawImage(Assets.trubkaOtevrena[3], x + 32, y + 32, null);
        }
    }

    public void open() {
        opened = true;
        closed = false;
        waiting = false;
    }

    public void waiting() {
        opened = false;
        closed = false;
        waiting = true;
    }

    public void close() {
        opened = false;
        closed = true;
        waiting = false;
    }

    public int getAmoutToSpawn() {
        return amoutToSpawn;
    }
}
