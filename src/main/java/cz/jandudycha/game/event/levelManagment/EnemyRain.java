package cz.jandudycha.game.event.levelManagment;


import cz.jandudycha.game.entity.enemy.*;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.main.Handler;

public class EnemyRain {
    private int x = 0;
    private final Handler handler;
    private final Events events;
    private int spaceBetweenEnemies = 0;
    private int waitBeforSpawning = 0;
    private EnemyType enemyType;

    public EnemyRain(Handler handler, Events events) {
        this.handler = handler;
        this.events = events;

    }

    public void spawn(int spaceBetweenEnemies, int waitBeforSpawning, EnemyType enemyType) {
        this.spaceBetweenEnemies = spaceBetweenEnemies;
        this.waitBeforSpawning = waitBeforSpawning;
        this.enemyType = enemyType;
        x = 0;
    }

    public void update() {
        if (waitBeforSpawning == 0) {
            spawnEnemy();
        } else {
            waitBeforSpawning--;
        }
    }

    private void spawnEnemy() {
        while (x < 1050 && enemyType != null) {
            int y = -50;
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
            x += spaceBetweenEnemies;
        }
    }


}
