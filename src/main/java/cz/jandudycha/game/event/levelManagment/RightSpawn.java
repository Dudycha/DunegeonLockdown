package cz.jandudycha.game.event.levelManagment;


import cz.jandudycha.game.entity.enemy.*;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.main.Handler;

public class RightSpawn {
    private final Handler handler;
    private final Events events;
    private int amoutToSpawn = 0;
    private int intervalOfSpawning = 0, waitBeforSpawning = 0;
    private int counter60 = 0;
    private EnemyType enemyType;

    public RightSpawn(Handler handler, Events events) {
        this.handler = handler;
        this.events = events;

    }

    public void spawn(int amoutToSpawn, int intervalOfSpawning, int waitBeforSpawning, EnemyType enemyType) {
        this.amoutToSpawn = amoutToSpawn;
        this.intervalOfSpawning = intervalOfSpawning;
        this.waitBeforSpawning = waitBeforSpawning;
        this.enemyType = enemyType;
    }

    public void update() {
        if (amoutToSpawn > 0) {
            spawnEnemy();
        }
    }

    private void spawnEnemy() {
        if (waitBeforSpawning == 0) {
            if (counter60 == intervalOfSpawning) {

                int x = 1100;
                int y = 150;
                switch (enemyType) {
                    case Skeleton: {
                        events.getListOfEnemies().add(new Skeleton(handler, x, y));
                        break;
                    }
                    case Spider: {
                        events.getListOfEnemies().add(new Spider(handler, x, y));
                        break;
                    }
                    case SmallWorm: {
                        events.getListOfEnemies().add(new SmallWorm(handler, x, y));
                        break;
                    }
                    case Bull: {
                        events.getListOfEnemies().add(new Bull(handler, x, y));
                        break;
                    }
                    case Dragon: {
                        events.getListOfEnemies().add(new Dragon(handler, x, y));
                        break;
                    }
                    case Goblin: {
                        events.getListOfEnemies().add(new Goblin(handler, x, y));
                        break;
                    }
                }
                amoutToSpawn--;
                counter60 = 0;
            } else {
                counter60++;
            }
        } else {
            waitBeforSpawning--;
        }
    }

    public int getAmoutToSpawn() {
        return amoutToSpawn;
    }
}
