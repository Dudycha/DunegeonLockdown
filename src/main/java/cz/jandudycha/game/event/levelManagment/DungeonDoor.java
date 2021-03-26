package cz.jandudycha.game.event.levelManagment;



import cz.jandudycha.game.entity.enemy.Goblin;
import cz.jandudycha.game.entity.enemy.Skeleton;
import cz.jandudycha.game.event.Events;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.Random;

public class DungeonDoor {

    private final Handler handler;
    private final Events events;
    private int clock = 0;
    private int amoutToSpawnSkeleton = 0, amoutToSpawnGoblin = 0;
    private int intervalOfSpawning = 0;
    private int doorAnimationClock = 0;
    private final int x, y;
    private boolean openDoor = false;
    private boolean doorOpen = false;
    private boolean closeDoor = false;
    private boolean doorClosed = true;
    private int counter60 = 0;
    private final Random rnd = new Random();

    public DungeonDoor(Handler handler, Events events, int x, int y) {
        this.handler = handler;
        this.events = events;
        this.x = x;
        this.y = y;
    }

    public void spawn(int amoutToSpawnSkeleton, int amoutToSpawnGoblin, int intervalOfSpawning) {
        this.amoutToSpawnGoblin = amoutToSpawnGoblin;
        this.amoutToSpawnSkeleton = amoutToSpawnSkeleton;
        this.intervalOfSpawning = intervalOfSpawning;
    }

    public void update() {
        doorController();
        if (amoutToSpawnSkeleton > 0 || amoutToSpawnGoblin > 0) {
            if (doorClosed) {
                openDungeonDoor();
            }
            if (doorOpen) {
                spawnEnemy();


            }

        } else if (amoutToSpawnSkeleton == 0) {
            closeDungeonDoor();
        }


    }


    private void spawnEnemy() {
        if (counter60 == intervalOfSpawning) {
            if (amoutToSpawnGoblin == 0 || amoutToSpawnSkeleton == 0) {
                if (amoutToSpawnGoblin > 0) {
                    events.getListOfEnemies().add(new Goblin(handler, x + 10, y + 38));
                    amoutToSpawnGoblin--;
                } else {
                    events.getListOfEnemies().add(new Skeleton(handler, x + 10, y + 38));
                    amoutToSpawnSkeleton--;
                }
            } else {


                if (rnd.nextInt(2) == 0) {
                    events.getListOfEnemies().add(new Skeleton(handler, x + 10, y + 38));
                    amoutToSpawnSkeleton--;
                } else {
                    events.getListOfEnemies().add(new Goblin(handler, x + 10, y + 38));
                    amoutToSpawnGoblin--;
                }
            }

            counter60 = 0;
        } else {
            counter60++;
        }
    }

    private void doorController() {
        if (clock == 17) {
            clock = 0;
            if (openDoor) {
                if (doorAnimationClock < Assets.dungeonDoor.length - 1) {
                    doorAnimationClock++;
                } else {
                    openDoor = false;
                    doorOpen = true;
                    doorClosed = false;
                }
            }
            if (closeDoor) {
                if (doorAnimationClock > 0) {
                    doorAnimationClock--;

                } else {
                    doorClosed = true;
                    doorOpen = false;
                    closeDoor = false;
                }
            }
        } else {
            clock++;
        }
    }

    public void render(Graphics g) {
        g.drawImage(Assets.dungeonDoor[doorAnimationClock][0], x, y - 5, 32, 37, null);
        g.drawImage(Assets.dungeonDoor[doorAnimationClock][1], x + 32, y - 5, 32, 37, null);
        g.drawImage(Assets.dungeonDoor[doorAnimationClock][2], x, y + 32, 32, 40, null);
        g.drawImage(Assets.dungeonDoor[doorAnimationClock][3], x + 32, y + 32, 32, 40, null);
    }

    public void openDungeonDoor() {
        openDoor = true;
    }

    public void closeDungeonDoor() {
        closeDoor = true;
    }

    public int getAmoutToSpawnSkeleton() {
        return amoutToSpawnSkeleton;
    }


    public int getAmoutToSpawnGoblin() {
        return amoutToSpawnGoblin;
    }

}
