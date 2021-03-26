package cz.jandudycha.game.event;




import cz.jandudycha.game.entity.enemy.Enemy;
import cz.jandudycha.game.event.displayStuff.CoinBilanceChanged;
import cz.jandudycha.game.event.displayStuff.ItemToDisplay;
import cz.jandudycha.game.event.dropItems.DropCrate;
import cz.jandudycha.game.event.dropItems.ItemType;
import cz.jandudycha.game.event.dropItems.Reward;
import cz.jandudycha.game.event.levelManagment.EnemyType;
import cz.jandudycha.game.event.levelManagment.Level;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.ArrayList;

public class Events {

    private final ArrayList<Enemy> listOfEnemies = new ArrayList<>();
    private final ArrayList<Level> lisfOflevels = new ArrayList<>();
    private final ArrayList<DropCrate> dropCrates = new ArrayList<>();
    private final ArrayList<Reward> rewards = new ArrayList<>();
    private final ArrayList<ItemToDisplay> itemToDisplay = new ArrayList<>();
    private final Handler handler;
    private final Level defaultLevel;
    private boolean levelListComplete = false;
    private int currentLVL = 0;

    public Events(Handler handler) {
        this.handler = handler;
        defaultLevel = new Level(handler, this);
        createLevels();

        //  listOfEnemies.add(new Goblin(handler, 150, 150));
        //  listOfEnemies.add(new Dragon(handler, 150, 150));
        //  listOfEnemies.add(new Dragon(handler, 550, 250));
        //  listOfEnemies.add(new Bull(handler,   150, 150));
        //  listOfEnemies.add(new Spider(handler, 350, 150));
        //  listOfEnemies.add(new Skeleton(handler,450,150));

    }


    public void update() {

        for (Enemy enemy : listOfEnemies) {
            enemy.update();
        }
        for (Enemy enemy : listOfEnemies) {
            if (enemy.getDespawnTimer() <= 0) {
                listOfEnemies.remove(enemy);
                break;
            }
        }

        if (!lisfOflevels.get(lisfOflevels.size() - 1).isLevelFinished()) {
            if (lisfOflevels.get(currentLVL).isLevelFinished()) {
                currentLVL++;
            }
        }

        if (!lisfOflevels.get(lisfOflevels.size() - 1).isLevelFinished()) {
            lisfOflevels.get(currentLVL).update();
        } else {
            levelListComplete = true;
            defaultLevel.update();
        }

        for (DropCrate crate : dropCrates) {
            crate.update();
            if (crate.isTooken()) {
                dropCrates.remove(crate);
                break;
            }
        }
        for (Reward reward : rewards) {
            reward.update();
            if (reward.isPickedUp()) {
                rewards.remove(reward);
                break;
            }
        }

        if (handler.getGame().getCoinAmtLastTime() != handler.getGame().getPlayer().getCoins()) {
            itemToDisplay.add(new CoinBilanceChanged(handler, 0, 0));
        }
        for (ItemToDisplay item : itemToDisplay) {
            item.update();
            if (item.isReadyToRemove()) {
                itemToDisplay.remove(item);
                break;
            }
        }


    }

    public void render(Graphics g) {
        int displayLvl = currentLVL;
        if (!lisfOflevels.get(lisfOflevels.size() - 1).isLevelFinished()) {
            lisfOflevels.get(currentLVL).render(g);
            g.setColor(Color.red);
            g.drawString("Level: " + displayLvl, 20, 25);
        } else {
            defaultLevel.render(g);
        }
        for (Enemy enemy : listOfEnemies) {
            enemy.render(g);
        }
        for (DropCrate crate : dropCrates) {
            crate.render(g);
        }
        for (Reward reward : rewards) {
            reward.render(g);
        }
        for (ItemToDisplay item : itemToDisplay) {
            item.render(g);
        }
        if ((currentLVL == 1 || currentLVL == 0) && !isLevelListComplete()) {
            g.setColor(Color.ORANGE);
            g.setFont(Assets.coinFont);
            g.drawString("Press B for Buy menu !", 125, 480);
        }

    }

    private void createLevels() {
        int levelNumber = 0;

        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(0, 0, 0);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(0, 0, 0);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(0, 0, 0);
        lisfOflevels.get(levelNumber).modifyLevelMoleHill(0, 0, 0);
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(0, 0, 0, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(0, 0, 0, EnemyType.Skeleton);

        levelNumber = 1;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelMoleHill(1, 10, 30);

        levelNumber = 2;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(3, 60, 160);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(1, ItemType.apple);

        levelNumber = 3;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(3, 60, 60);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(3, 60, 260);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldNuget);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(2, ItemType.apple);

        levelNumber = 4;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(4, 50, 380);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(1, 0, 170);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(2, 30, 420);
        lisfOflevels.get(levelNumber).modifyLevelMoleHill(3, 6, 40);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(2, ItemType.apple);

        levelNumber = 5;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(5, 20, 160);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(2, 0, 30);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(5, 50, 160);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.random);

        levelNumber = 6; // funny
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelMoleHill(3, 8, 65);
        lisfOflevels.get(levelNumber).modifyLevelEnemyRain(80, 250, EnemyType.SmallWorm);

        levelNumber = 7;
        lisfOflevels.add(new Level(handler, this));  // Goblin introduced
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(0, 1, 30);

        levelNumber = 8; // second Gun after ?
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(5, 20, 160);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(2, 0, 30);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(5, 50, 460);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(1, 1, 1, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(1, 1, 1, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldBar);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(1, ItemType.medKit);

        levelNumber = 9;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(7, 2, 190);
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(6, 270, 1, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.apple);

        levelNumber = 10;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(10, 40, 180);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(0, 1, 10);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(16, 60, 320);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldNuget);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(2, ItemType.apple);

        levelNumber = 11;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(4, 160, 1, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(10, 40, 180);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(10, 50, 80);
        lisfOflevels.get(levelNumber).modifyLevelMoleHill(2, 15, 20);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(2, ItemType.apple);

        levelNumber = 12;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(7, 40, 120);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(7, 50, 80);
        lisfOflevels.get(levelNumber).modifyLevelMoleHill(1, 15, 20);
        lisfOflevels.get(levelNumber).modifyLevelEnemyRain(300, 100, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldNuget);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.apple);


        levelNumber = 13;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(5, 5, 20);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.goldNuget);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(1, ItemType.medKit);

        levelNumber = 14;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(8, 2, 190);
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(12, 160, 1, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldNuget);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(2, ItemType.medKit);

        levelNumber = 15;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(12, 160, 1, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(10, 40, 180);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(0, 1, 10);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(16, 150, 320);
        lisfOflevels.get(levelNumber).modifyLevelEnemyRain(80, 450, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(5, 290, 1, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.apple);

        levelNumber = 16;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(4, 260, 1, EnemyType.Goblin);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(4, 4, 120);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(5, 290, 1, EnemyType.Goblin);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldBar);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.apple);

        levelNumber = 17;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(1, 10, 120, EnemyType.Bull);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(1, ItemType.apple);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.random);

        levelNumber = 18; // possibly Ak after
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(4, 260, 1, EnemyType.Goblin);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(4, 4, 120);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(5, 290, 1, EnemyType.Goblin);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldBar);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.apple);


        levelNumber = 19;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(4, 260, 1, EnemyType.Goblin);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(4, 4, 120);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(5, 290, 1, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(6, 120, 120);
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(8, 80, 50);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.apple);

        levelNumber = 20;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(4, 260, 1, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(4, 4, 220);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(1, 10, 600, EnemyType.Bull);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(6, 120, 120);
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(8, 80, 250);
        lisfOflevels.get(levelNumber).modifyLevelDrops(3, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldNuget);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(1, ItemType.medKit);

        levelNumber = 21;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(11, 260, 1, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(12, 170, 190, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(11, 220, 120);
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(13, 80, 250);
        lisfOflevels.get(levelNumber).modifyLevelEnemyRain(60, 800, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldNuget);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(4, ItemType.apple);

        levelNumber = 22;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(1, 10, 1, EnemyType.Bull);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(1, 10, 600, EnemyType.Bull);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(4, 120, 320);
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(5, 80, 350);
        lisfOflevels.get(levelNumber).modifyLevelDrops(3, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldNuget);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(1, ItemType.apple);

        levelNumber = 23;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelMoleHill(5, 7, 30);
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(35, 40, 1, EnemyType.SmallWorm);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(35, 50, 600, EnemyType.SmallWorm);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(12, 60, 120);
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(12, 80, 250);
        lisfOflevels.get(levelNumber).modifyLevelEnemyRain(20, 150, EnemyType.SmallWorm);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.goldNuget);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(4, ItemType.apple);

        levelNumber = 24; // close to deagle
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelMoleHill(1, 7, 120);
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(2, 700, 70, EnemyType.Bull);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(2, 900, 170, EnemyType.Bull);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(9, 120, 60);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(1, 1, 120);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldBar);

        levelNumber = 25;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(12, 60, 70, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(10, 90, 170, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(28, 30, 10);
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(25, 30, 350);
        lisfOflevels.get(levelNumber).modifyLevelEnemyRain(50, 650, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(1, ItemType.medKit);

        levelNumber = 26;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(7, 40, 120, EnemyType.Goblin);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(7, 40, 220, EnemyType.Goblin);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(0, 7, 40);
        lisfOflevels.get(levelNumber).modifyLevelDrops(5, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(2, ItemType.apple);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.goldBar);

        levelNumber = 27; // Dragon
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(1, 10, 220, EnemyType.Dragon);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.apple);
        lisfOflevels.get(levelNumber).modifyLevelDrops(4, ItemType.goldNuget);

        levelNumber = 28;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(10, 40, 10, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(12, 30, 10);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(2, ItemType.medKit);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldBar);

        levelNumber = 29;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(1, 10, 220, EnemyType.Dragon);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(10, 40, 10, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(12, 30, 10);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(1, ItemType.medKit);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.goldBar);

        levelNumber = 30;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelEnemyRain(150, 100, EnemyType.Bull);
        lisfOflevels.get(levelNumber).modifyLevelDrops(4, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(4, ItemType.apple);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.goldBar);

        levelNumber = 31;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(12, 60, 70, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(10, 90, 170, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelRightPipe(28, 50, 10);
        lisfOflevels.get(levelNumber).modifyLevelLeftPipe(25, 40, 150);
        lisfOflevels.get(levelNumber).modifyLevelDrops(4, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.apple);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldNuget);

        levelNumber = 32;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(18, 30, 70, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(20, 40, 170, EnemyType.Skeleton);
        lisfOflevels.get(levelNumber).modifyLevelDungeonDoor(17, 0, 50);
        lisfOflevels.get(levelNumber).modifyLevelDrops(4, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.medKit);
        lisfOflevels.get(levelNumber).modifyLevelDrops(1, ItemType.goldBar);

        levelNumber = 33;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(1, 30, 160, EnemyType.Dragon);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(1, 40, 160, EnemyType.Dragon);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.apple);
        lisfOflevels.get(levelNumber).modifyLevelDrops(3, ItemType.goldNuget);

        levelNumber = 34;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(37, 40, 160, EnemyType.Goblin);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(33, 50, 60, EnemyType.Goblin);
        lisfOflevels.get(levelNumber).modifyLevelEnemyRain(80, 500, EnemyType.Goblin);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.apple);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.goldBar);

        levelNumber = 35;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(48, 20, 160, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(43, 30, 60, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelLeftSpawn(34, 30, 160, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(32, 40, 60, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelEnemyRain(30, 800, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelDrops(2, ItemType.random);
        lisfOflevels.get(levelNumber).modifyLevelHealDrops(3, ItemType.medKit);
        lisfOflevels.get(levelNumber).modifyLevelDrops(3, ItemType.goldNuget);

        levelNumber = 36;
        lisfOflevels.add(new Level(handler, this));
        lisfOflevels.get(levelNumber).modifyLevelRightSpawn(1, 30, 360, EnemyType.Spider);
        lisfOflevels.get(levelNumber).modifyLevelEnemyRain(400, 300, EnemyType.Dragon);

    }

    public boolean isLevelListComplete() {
        return levelListComplete;
    }

    public ArrayList<Reward> getRewards() {
        return rewards;
    }

    public ArrayList<Enemy> getListOfEnemies() {
        return listOfEnemies;
    }

    public ArrayList<DropCrate> getDropCrates() {
        return dropCrates;
    }

}
