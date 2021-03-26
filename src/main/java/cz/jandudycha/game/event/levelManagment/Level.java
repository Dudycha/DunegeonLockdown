package cz.jandudycha.game.event.levelManagment;




import cz.jandudycha.game.entity.enemy.Enemy;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.event.dropItems.DropCrate;
import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.main.Handler;

import java.awt.*;
import java.util.ArrayList;

public class Level {

    private final Handler handler;
    private final Events events;
    private boolean levelFinished = false;
    private final DungeonDoor dungeonDoor;
    private final Pipe leftPipe;
    private final Pipe rightPipe;
    private final LeftSpawn leftSpawn;
    private final RightSpawn rightSpawn;
    private final EnemyRain enemyRain;
    private final ArrayList<MoleHill> moleHills = new ArrayList<>();
    private int amtOfDrops = 0, amtOfHealDrops = 0;
    private ItemType itemType, itemTypeHeals;
    private int endLvLTimer = 120;


    public Level(Handler handler, Events events) {
        this.handler = handler;
        this.events = events;
        dungeonDoor = new DungeonDoor(handler, events, 452, 140);
        leftPipe = new Pipe(handler, events, 150, 200);
        rightPipe = new Pipe(handler, events, 850, 145);
        leftSpawn = new LeftSpawn(handler, events);
        rightSpawn = new RightSpawn(handler, events);
        enemyRain = new EnemyRain(handler, events);
    }

    public void update() {
        dungeonDoor.update();
        leftPipe.update();
        rightPipe.update();
        leftSpawn.update();
        rightSpawn.update();
        enemyRain.update();

        for (MoleHill moleHill : moleHills) {
            moleHill.update();
        }

        if (leftPipe.getAmoutToSpawn() == 0 && rightPipe.getAmoutToSpawn() == 0 && dungeonDoor.getAmoutToSpawnSkeleton() == 0 &&
                dungeonDoor.getAmoutToSpawnGoblin() == 0 && leftSpawn.getAmoutToSpawn() == 0 && moleHillsSpawnAmt() && rightSpawn.getAmoutToSpawn() == 0) {
            boolean allEnemyDead = false;
            for (Enemy enemy : events.getListOfEnemies()) {
                if (enemy.isAlive()) {
                    allEnemyDead = false;
                    break;
                } else {
                    allEnemyDead = true;
                }
            }

            if (allEnemyDead) {
                for (int i = 0; i < amtOfDrops; i++) {
                    events.getDropCrates().add(new DropCrate(handler, itemType));
                }
                amtOfDrops = 0;

                for (int i = 0; i < amtOfHealDrops; i++) {
                    events.getDropCrates().add(new DropCrate(handler, itemTypeHeals));
                }
                amtOfHealDrops = 0;
            }

            if (events.getListOfEnemies().size() == 0) {
                if (endLvLTimer == 0) {
                    levelFinished = true;
                } else {
                    endLvLTimer--;
                }

            }
        }


    }

    private boolean moleHillsSpawnAmt() {
        for (MoleHill moleHill : moleHills) {
            if (moleHill.getAmoutToSpawn() != 0) {
                return false;
            }
        }
        return true;
    }

    public void render(Graphics g) {
        dungeonDoor.render(g);
        leftPipe.render(g);
        rightPipe.render(g);
        for (MoleHill moleHill : moleHills) {
            moleHill.render(g);
        }

    }

    public void modifyLevelLeftPipe(int amountToSpawn, int intervalOfSpawning, int waitBeforeSpawning) {
        this.leftPipe.spawn(amountToSpawn, intervalOfSpawning, waitBeforeSpawning);
    }

    public void modifyLevelDungeonDoor(int amoutToSpawnSkeleton, int amoutToSpawnGoblin, int intervalOfSpawning) {
        this.dungeonDoor.spawn(amoutToSpawnSkeleton, amoutToSpawnGoblin, intervalOfSpawning);
    }

    public void modifyLevelRightPipe(int amountToSpawn, int intervalOfSpawning, int waitBeforeSpawning) {
        this.rightPipe.spawn(amountToSpawn, intervalOfSpawning, waitBeforeSpawning);
    }

    public void modifyLevelLeftSpawn(int amountToSpawn, int intervalOfSpawning, int waitBeforeSpawning, EnemyType enemytype) {
        this.leftSpawn.spawn(amountToSpawn, intervalOfSpawning, waitBeforeSpawning, enemytype);
    }

    public void modifyLevelRightSpawn(int amountToSpawn, int intervalOfSpawning, int waitBeforeSpawning, EnemyType enemytype) {
        this.rightSpawn.spawn(amountToSpawn, intervalOfSpawning, waitBeforeSpawning, enemytype);
    }

    public void modifyLevelEnemyRain(int spaceBetweenEnemies, int waitBeforeSpawning, EnemyType enemytype) {
        this.enemyRain.spawn(spaceBetweenEnemies, waitBeforeSpawning, enemytype);
    }

    public void modifyLevelMoleHill(int amoutOfMoleHills, int amountToSpawnInEach, int intervalOfSpawning) {
        for (int i = 0; i < amoutOfMoleHills; i++) {
            moleHills.add(new MoleHill(handler, events));
        }

        for (MoleHill moleHill : moleHills) {
            moleHill.spawn(amountToSpawnInEach, intervalOfSpawning);
        }
    }

    public void modifyLevelDrops(int amtOfDrops, ItemType itemType) {
        this.amtOfDrops = amtOfDrops;
        this.itemType = itemType;
    }

    public void modifyLevelHealDrops(int amtOfHealDrops, ItemType itemType) {
        this.amtOfHealDrops = amtOfHealDrops;
        this.itemTypeHeals = itemType;
    }


    public boolean isLevelFinished() {
        return levelFinished;
    }

}
