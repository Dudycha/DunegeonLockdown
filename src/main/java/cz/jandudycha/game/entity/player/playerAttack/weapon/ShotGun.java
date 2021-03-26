package cz.jandudycha.game.entity.player.playerAttack.weapon;


import cz.jandudycha.game.entity.enemy.Enemy;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ShotGun extends Weapon {

    private int playerX,playerY;
    private int mouseY,mouseX;
    private boolean readyToFire = false;
    private boolean drawFireLine = false;
    private boolean firinMechanism = false;
    private int clock1 = 0;
    private final int DAMAGE = 5;
    private final int shotgunSpread = 190;
    private final int shotgunPellets = 7;
    private int drawFireLineFromX, drawFireLineFromY;
    private final int[][] fireLines = new int[shotgunPellets][2];
    private final int[] array = new int[2];
    private final ArrayList<BulletHit>[] bullethits = new ArrayList[shotgunPellets];
    private final int MAGAZINECAPACITY = 8;
    private int ammoInMagazine = 8;
    private int reloadClock = 0;
    private boolean reloading = false;


    public ShotGun(Handler handler) {
        super(handler);
        ammoInReserve = 8;
        for (int x = 0; x < bullethits.length; x++) {
            bullethits[x] = new ArrayList<>();
        }
    }

    public void update() {
        playerX = getHandler().getGame().getPlayer().getX() + getXShift();
        playerY = getHandler().getGame().getPlayer().getY() + 30;
        mouseX = getHandler().getGame().getKeyInput().getMouseX();
        mouseY = getHandler().getGame().getKeyInput().getMouseY();

        if (getHandler().getGame().getKeyInput().isLeftMouse()) {
            firinMechanism = true;
        }
        fire();
    }

    private void fire() {
        if (ammoInMagazine > 0) {
            if (firinMechanism && readyToFire && !reloading) {
                getHandler().getGame().getPlayer().setBulletsFired(getHandler().getGame().getPlayer().getBulletsFired() + 1);
                extendLines();  //Bere souřadnice od hráče po myš a výslednou úsečku prodlouří až mimo obrazovku.
                checkForHit();
                drawFireLine = true;
                firinMechanism = false;
                readyToFire = false;
                ammoInMagazine--;
            } else {
                if (!readyToFire) {
                    if (clock1 == 15) {
                        clock1 = 0;
                        if (!getHandler().getGame().getKeyInput().isLeftMouse()) {
                            readyToFire = true;
                        }
                    } else {
                        clock1++;
                        drawFireLine = false;
                        firinMechanism = false;
                    }
                }
            }
        } else {
            drawFireLine = false;
            firinMechanism = false;
        }

        if (getHandler().getGame().getKeyInput().isReaload() && !reloading && ammoInReserve > 0) {
            reloading = true;
        }

        if (reloading) {
            if (reloadClock == 70) {
                reloadClock = 0;
                reloading = false;
                ammoInReserve += ammoInMagazine;
                ammoInMagazine = 0;
                if (ammoInReserve > MAGAZINECAPACITY) {
                    ammoInMagazine = MAGAZINECAPACITY;
                    ammoInReserve -= MAGAZINECAPACITY;
                } else {
                    ammoInMagazine = ammoInReserve;
                    ammoInReserve = 0;
                }

            } else {
                reloadClock++;
            }

        }
    }

    private void checkForHit() {
        //Pomocí metody lineLineIntersection hledá průsečík mezi (drawFireLineFrom / drawFirelineTo) a hranicemi textur na mapě reprezentovanými sadou úseček
        //uložených v dourozměném poli v podobě čtyř čísel reprezentující úsečku. Druhá část (cykl) hledá stejným principem průsečík na nepřátelích, pokud k průsečíku dojde (zázahu)
        //tak se vytvoří nový bulletHit s hitOnEnemy = true a pozicí enemy v array listu na kterém došlo k zásahu

        for (ArrayList<BulletHit> bullethit : bullethits) {
            bullethit.clear();
        }



        for (int p = 0; p < shotgunPellets; p++) {

            for (int i = 0; i < getLengthOfArrayOfShotBounds(); i++) {
                if (lineLineIntersection(arrayOfShotBounds[i][0], arrayOfShotBounds[i][1], arrayOfShotBounds[i][2], arrayOfShotBounds[i][3], drawFireLineFromX, drawFireLineFromY, fireLines[p][0], fireLines[p][1])) {
                    bullethits[p].add(new BulletHit(array[0], array[1], false, 0));
                }
            }

            int position = -1;
            for (Enemy enemy : getHandler().getGame().getEvents().getListOfEnemies()) {
                position++;
                if (enemy.isAlive()) {
                    if (lineLineIntersection(enemy.getHitVectorsOnPosition(0), enemy.getHitVectorsOnPosition(1), enemy.getHitVectorsOnPosition(2), enemy.getHitVectorsOnPosition(3), drawFireLineFromX, drawFireLineFromY, fireLines[p][0], fireLines[p][1]) || (lineLineIntersection(enemy.getHitVectorsOnPosition(4), enemy.getHitVectorsOnPosition(5), enemy.getHitVectorsOnPosition(6), enemy.getHitVectorsOnPosition(7), drawFireLineFromX, drawFireLineFromY, fireLines[p][0], fireLines[p][1]))) {
                        bullethits[p].add(new BulletHit(array[0], array[1], true, position));
                    }
                }
            }
        }

        findClosestHit(); // Ze všech existujících zásahů vybere ten nejbližší a zkrátí výslednou drawFireline k tomuto zásahu + pokud došlo k zásahu na enemy
        // tak ho usmrtí

    }

    private void findClosestHit() {
        //https://www.youtube.com/watch?v=mGcZGiUn39k&ab_channel=Mario%27sMathTutoring
        // Ze všech existujících zásahů vybere ten nejbližší a zkrátí výslednou drawFireline k tomuto zásahu

        for (int p = 0; p < shotgunPellets; p++) {
        if (bullethits[p].size() > 0) {
            double length;
            double minLength = 99999999;
            int positionOfMin = 0;

            for (int i = 0; i < bullethits[p].size(); i++) {
                int fromX = playerX;
                int fromY = playerY;
                int toX = bullethits[p].get(i).getX();
                int toY = bullethits[p].get(i).getY();

                length = (Math.sqrt((toX - fromX) * (toX - fromX) + (toY - fromY) * (toY - fromY)));
                if (length < minLength) {
                    minLength = length;
                    positionOfMin = i;
                }
            }

            if (bullethits[p].get(positionOfMin).isHitOnEnemy()) {
                getHandler().getGame().getEvents().getListOfEnemies().get(bullethits[p].get(positionOfMin).getPositionOfEnemyInList()).reciveHit(DAMAGE);
            }

            array[0] = bullethits[p].get(positionOfMin).getX();
            array[1] = bullethits[p].get(positionOfMin).getY();
            fireLines[p][0] = bullethits[p].get(positionOfMin).getX();
            fireLines[p][1] = bullethits[p].get(positionOfMin).getY();
        }
        }
    }

    private void extendLines() {
        //  https://www.youtube.com/watch?v=mGcZGiUn39k&ab_channel=Mario%27sMathTutoring
        //Bere souřadnice od hráče po myš a výslednou úsečku prodlouří až mimo obrazovku.


        Random rnd = new Random();

        drawFireLineFromX = playerX + 15;
        drawFireLineFromY = playerY + 13;

        for (int i = 0; i < shotgunPellets; i++) {

            fireLines[i][0] = (mouseX -15) - playerX;
            fireLines[i][1] = (mouseY -13) - playerY;

            while (Math.abs(fireLines[i][0]) < 1500 && Math.abs(fireLines[i][1]) < 1500) {
                fireLines[i][0] = (int) (fireLines[i][0] * 1.2);
                fireLines[i][1] = (int) (fireLines[i][1] * 1.2);

                if (fireLines[i][0] == 0 || fireLines[i][1] == 0) {
                    break;
                }
            }
            fireLines[i][0] = fireLines[i][0] + rnd.nextInt(shotgunSpread) - shotgunSpread / 2;
            fireLines[i][1] = fireLines[i][1] + rnd.nextInt(shotgunSpread) - shotgunSpread / 2;
            fireLines[i][0] += playerX;
            fireLines[i][1] += playerY;
        }

    }

    public void render(Graphics g) {
        renderFiring(g);
        renderWeaponPictureAndAmmo(g);
    }

    private void renderFiring(Graphics g) {

        // drawTestingInformations(g);


        if (drawFireLine) {
            g.setColor(Color.orange);
            for (int i = 0; i < shotgunPellets; i++) {
                g.drawLine(drawFireLineFromX, drawFireLineFromY, fireLines[i][0], fireLines[i][1]);
                g.drawImage(Assets.bulletImpact, fireLines[i][0] - 14, fireLines[i][1] - 14, null);
            }
        }

        if (getHandler().getGame().getKeyInput().isLeftMouse()) {
            renderMiniatureOfGun(g);
        }
    }

    @Override
    public void renderWeaponPictureAndAmmo(Graphics g) {
        int shiftForAmmo = 23;
        int shiftForWeapon = 32;
        for (int i = 0; i < 6; i++) {
            g.drawImage(Assets.shoutgunMainPicture[i], 230 + (i * shiftForWeapon), 507, null);
        }
        g.setColor(Color.yellow);
        g.setFont(Assets.coinFont);
        g.drawString("AMMO : " + ammoInReserve + "/" + ammoInMagazine, 17, 530);
        if (reloading) {
            g.drawString("Reloading !", 450, 530);
        } else {
            if (ammoInMagazine > 0) {
                for (int i = 0; i < ammoInMagazine; i++) {
                    g.drawImage(Assets.gaugeBuckshot, 460 + i * shiftForAmmo, 503, 41, 41, null);
                }
            } else if (ammoInMagazine == 0 && ammoInReserve > 0) {
                g.drawString("Press R for reload !", 420, 530);
            } else if (ammoInMagazine == 0 && ammoInReserve == 0) {
                g.drawString("No ammo left !", 420, 530);
            }
        }

    }

    private boolean lineLineIntersection(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        // Určí míslo průseku dvou úseček a vrací false pokud průsečík neexistuje nebo pokuj jsou úsečky rovnoběžné

        int den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (den == 0) {
            return false;
        } else {
            double t = (double) ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
            double u = (double) -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;


            if (t > 0 && t < 1 && u > 0) {
                array[0] = (int) (x1 + t * (x2 - x1));
                array[1] = (int) (y1 + t * (y2 - y1));
                return true;

            } else {
                return false;
            }
        }

    }

    private void renderMiniatureOfGun(Graphics g) {


        // Určuje pozici pro vykreslení miniatury zbraně na základě rozdělení obrazovky na čtyři sekce (prava horni, leva horni...)
        // a v ní dále rozliší 3 úrovně pomocí určení poměru mezi (mouseX - playerX) / (playerY - mouseY).
        // Deleni nulou "yet needs to be fixed ;)"

        if (mouseX == 0 || mouseY == 0 || playerX == 0 || playerY == 0 || (mouseY - playerY == 0)) {
            System.out.println("deleni nulou");
        } else {
            if (mouseX > playerX && mouseY < playerY) {            // PRAVA HORNI SEKCE
                if ((mouseX - playerX) / (playerY - mouseY) < 1) {
                    g.drawImage(Assets.ShotgunUp, playerX, playerY, null);
                } else if ((mouseX - playerX) / (playerY - mouseY) > 5) {
                    g.drawImage(Assets.ShotgunRight, playerX, playerY, null);
                } else {
                    g.drawImage(Assets.ShotgunUpRight, playerX, playerY, null);
                }
            }

            if (mouseX > playerX && mouseY > playerY) {           // PRAVA DOLNI SEKCE
                if ((mouseX - playerX) / (playerY - mouseY) > -1) {
                    g.drawImage(Assets.ShotgunDown, playerX, playerY, null);
                } else if ((mouseX - playerX) / (playerY - mouseY) < -5) {
                    g.drawImage(Assets.ShotgunRight, playerX, playerY, null);
                } else {
                    g.drawImage(Assets.ShotgunDownRight, playerX, playerY, null);
                }
            }

            if (mouseX < playerX && mouseY > playerY) {         // LEVA DOLNI SEKCE
                if ((mouseX - playerX) / (playerY - mouseY) < 1) {
                    g.drawImage(Assets.ShotgunDown, playerX, playerY, null);
                } else if ((mouseX - playerX) / (playerY - mouseY) > 5) {
                    g.drawImage(Assets.ShotgunLeft, playerX, playerY, null);
                } else {
                    g.drawImage(Assets.ShotgunDownLeft, playerX, playerY, null);
                }
            }

            if (mouseX < playerX && mouseY < playerY) {         // LEVA HORNI SEKCE
                if ((mouseX - playerX) / (playerY - mouseY) > -1) {
                    g.drawImage(Assets.ShotgunUp, playerX, playerY, null);
                } else if ((mouseX - playerX) / (playerY - mouseY) < -5) {
                    g.drawImage(Assets.ShotgunLeft, playerX, playerY, null);
                } else {
                    g.drawImage(Assets.ShotgunUpLeft, playerX, playerY, null);
                }
            }
        }

    }

    private int getXShift() {
        // Posouva obrazek hrace podle toho kam se kouka aby byl vycentrovan
        if (getHandler().getGame().getPlayer().getPlayerMovement().isPlayerFacingRight()) {
            return 27;
        } else {
            return 10;
        }
    }
}
