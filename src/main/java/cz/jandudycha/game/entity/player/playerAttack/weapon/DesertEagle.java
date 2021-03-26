package cz.jandudycha.game.entity.player.playerAttack.weapon;




import cz.jandudycha.game.entity.enemy.Enemy;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.ArrayList;

public class DesertEagle extends Weapon {
    private int playerX, playerY;
    private int mouseY, mouseX;
    private boolean readyToFire = false;
    private boolean drawFireLine = false;
    private boolean firinMechanism = false;
    private int clock1 = 0;
    private final int DAMAGE = 20;
    private int drawFireLineFromX, drawFireLineFromY, drawFireLineToX, drawFireLineToY;
    private final int[] array = new int[2];
    private final ArrayList<BulletHit> bulletHits = new ArrayList<BulletHit>();
    private final int MAGAZINECAPACITY = 8;
    private int ammoInMagazine = 8;
    private int reloadClock = 0;
    private boolean reloading = false;

    public DesertEagle(Handler handler) {
        super(handler);
        ammoInReserve = 8;
    }

    @Override
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
                extendLine();  //Bere souřadnice od hráče po myš a výslednou úsečku prodlouří až mimo obrazovku.
                checkForHit();
                drawFireLine = true;
                firinMechanism = false;
                readyToFire = false;
                ammoInMagazine--;
            } else {
                if (!readyToFire) {
                    if (clock1 == 5) {
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
            if (reloadClock == 30) {
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

        bulletHits.clear();
        for (int i = 0; i < getLengthOfArrayOfShotBounds(); i++) {
            if (lineLineIntersection(arrayOfShotBounds[i][0], arrayOfShotBounds[i][1], arrayOfShotBounds[i][2], arrayOfShotBounds[i][3], drawFireLineFromX, drawFireLineFromY, drawFireLineToX, drawFireLineToY)) {
                bulletHits.add(new BulletHit(array[0], array[1], false, 0));
            }

        }

        int position = -1;
        for (Enemy enemy : getHandler().getGame().getEvents().getListOfEnemies()) {
            position++;
            if (enemy.isAlive()) {
                if (lineLineIntersection(enemy.getHitVectorsOnPosition(0), enemy.getHitVectorsOnPosition(1), enemy.getHitVectorsOnPosition(2), enemy.getHitVectorsOnPosition(3), drawFireLineFromX, drawFireLineFromY, drawFireLineToX, drawFireLineToY) || (lineLineIntersection(enemy.getHitVectorsOnPosition(4), enemy.getHitVectorsOnPosition(5), enemy.getHitVectorsOnPosition(6), enemy.getHitVectorsOnPosition(7), drawFireLineFromX, drawFireLineFromY, drawFireLineToX, drawFireLineToY))) {
                    bulletHits.add(new BulletHit(array[0], array[1], true, position));
                }
            }
        }

        for (BulletHit hit : bulletHits) {
            if (hit.isHitOnEnemy()) {
                getHandler().getGame().getEvents().getListOfEnemies().get(hit.getPositionOfEnemyInList()).reciveHit(DAMAGE);
            }

        }

    }


    private void extendLine() {
        //  https://www.youtube.com/watch?v=mGcZGiUn39k&ab_channel=Mario%27sMathTutoring
        //Bere souřadnice od hráče po myš a výslednou úsečku prodlouří až mimo obrazovku.
        drawFireLineFromX = playerX + 15;
        drawFireLineFromY = playerY + 13;

        drawFireLineToX = (mouseX - 15) - playerX;
        drawFireLineToY = (mouseY - 13) - playerY;

        drawFireLineToX = drawFireLineToX * 300;
        drawFireLineToY = drawFireLineToY * 300;

        drawFireLineToX += playerX;
        drawFireLineToY += playerY;

    }

    @Override
    public void render(Graphics g) {
        renderFiring(g);
        renderWeaponPictureAndAmmo(g);
    }

    private void renderFiring(Graphics g) {

        // drawTestingInformations(g);


        if (drawFireLine) {
            g.setColor(Color.orange);
            g.drawLine(drawFireLineFromX, drawFireLineFromY, drawFireLineToX, drawFireLineToY);
            g.drawImage(Assets.bulletImpact, drawFireLineToX - 14, drawFireLineToY - 14, null);
        }

        if (getHandler().getGame().getKeyInput().isLeftMouse()) {
            renderMiniatureOfGun(g);
        }
    }


    @Override
    public void renderWeaponPictureAndAmmo(Graphics g) {
        int shiftForAmmo = 22;

        g.drawImage(Assets.desertEagleMainPicture[0], 280, 502, 43, 43, null);
        g.drawImage(Assets.desertEagleMainPicture[1], 280 + 43, 502, 43, 43, null);

        g.setColor(Color.yellow);
        g.setFont(Assets.coinFont);
        g.drawString("AMMO : " + ammoInReserve + "/" + ammoInMagazine, 17, 530);
        if (reloading) {
            g.drawString("Reloading !", 450, 530);
        } else {
            if (ammoInMagazine > 0) {
                for (int i = 0; i < ammoInMagazine; i++) {
                    g.drawImage(Assets.bullet50EA, 470 + i * shiftForAmmo, 502, 42, 42, null);
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
                    g.drawImage(Assets.desertEagleUp, playerX, playerY, null);
                } else if ((mouseX - playerX) / (playerY - mouseY) > 5) {
                    g.drawImage(Assets.desertEagleRight, playerX, playerY, null);
                } else {
                    g.drawImage(Assets.desertEagleUpRight, playerX, playerY, null);
                }
            }

            if (mouseX > playerX && mouseY > playerY) {           // PRAVA DOLNI SEKCE
                if ((mouseX - playerX) / (playerY - mouseY) > -1) {
                    g.drawImage(Assets.desertEagleDown, playerX, playerY, null);
                } else if ((mouseX - playerX) / (playerY - mouseY) < -5) {
                    g.drawImage(Assets.desertEagleRight, playerX, playerY, null);
                } else {
                    g.drawImage(Assets.desertEagleDownRight, playerX, playerY, null);
                }
            }

            if (mouseX < playerX && mouseY > playerY) {         // LEVA DOLNI SEKCE
                if ((mouseX - playerX) / (playerY - mouseY) < 1) {
                    g.drawImage(Assets.desertEagleDown, playerX, playerY, null);
                } else if ((mouseX - playerX) / (playerY - mouseY) > 5) {
                    g.drawImage(Assets.desertEagleLeft, playerX, playerY, null);
                } else {
                    g.drawImage(Assets.desertEagleDownLeft, playerX, playerY, null);
                }
            }

            if (mouseX < playerX && mouseY < playerY) {         // LEVA HORNI SEKCE
                if ((mouseX - playerX) / (playerY - mouseY) > -1) {
                    g.drawImage(Assets.desertEagleUp, playerX, playerY, null);
                } else if ((mouseX - playerX) / (playerY - mouseY) < -5) {
                    g.drawImage(Assets.desertEagleLeft, playerX, playerY, null);
                } else {
                    g.drawImage(Assets.desertEagleUpLeft, playerX, playerY, null);
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
