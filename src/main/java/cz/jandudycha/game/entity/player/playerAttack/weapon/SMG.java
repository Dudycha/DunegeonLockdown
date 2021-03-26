package cz.jandudycha.game.entity.player.playerAttack.weapon;




import cz.jandudycha.game.entity.enemy.Enemy;
import cz.jandudycha.game.entity.player.playerAttack.PositionOfGun;
import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;
import java.util.ArrayList;

public class SMG extends Weapon {

    private int playerX,playerY;
    private int mouseY,mouseX;
    private boolean readyToFire = false;
    private boolean drawFireLine = false;
    private boolean firinMechanism = false;
    private int clock1 = 0;
    private final int DAMAGE = 4;
    private int drawFireLineFromX, drawFireLineFromY, drawFireLineToX, drawFireLineToY;
    int[] array = new int[2];
    private final ArrayList<BulletHit> bulletHits = new ArrayList<BulletHit>();
    private final int MAGAZINECAPACITY = 35;
    private int ammoInMagazine = 35;
    private int reloadClock = 0;
    private boolean reloading = false;

    public SMG(Handler handler) {
        super(handler);
        ammoInReserve = 60;
    }


    public void update() {
        playerX = getHandler().getGame().getPlayer().getX() + getXShift();
        playerY = getHandler().getGame().getPlayer().getY() + 30;
        mouseX = getHandler().getGame().getKeyInput().getMouseX();
        mouseY = getHandler().getGame().getKeyInput().getMouseY();

        if (getHandler().getGame().getKeyInput().isLeftMouse()) {
            firinMechanism = true;
        }
        fire();         //Mechanizmus zbraně, časování výstřelu a nabití další kulky z magazínu.

    }

    public void render(Graphics g) {
        renderWeaponPictureAndAmmo(g); // Vykreslí full picture obrázek zbraně + stav nábojů
        renderFiring(g);
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
                    if (clock1 == 4) {
                        clock1 = 0;
                        readyToFire = true;

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
            if (reloadClock == 45) {
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

        findClosestHit(); // Ze všech existujících zásahů vybere ten nejbližší a zkrátí výslednou drawFireline k tomuto zásahu + pokud došlo k zásahu na enemy
        // tak ho usmrtí

    }

    private void findClosestHit() {
        //https://www.youtube.com/watch?v=mGcZGiUn39k&ab_channel=Mario%27sMathTutoring
        // Ze všech existujících zásahů vybere ten nejbližší a zkrátí výslednou drawFireline k tomuto zásahu

        if (bulletHits.size() > 0) {
            double length;
            double minLength = 99999999;
            int positionOfMin = 0;

            for (int i = 0; i < bulletHits.size(); i++) {
                int fromX = playerX;
                int fromY = playerY;
                int toX = bulletHits.get(i).getX();
                int toY = bulletHits.get(i).getY();

                length = (Math.sqrt((toX - fromX) * (toX - fromX) + (toY - fromY) * (toY - fromY)));
                if (length < minLength) {
                    minLength = length;
                    positionOfMin = i;
                }
            }

            if (bulletHits.get(positionOfMin).isHitOnEnemy()) {
                getHandler().getGame().getEvents().getListOfEnemies().get(bulletHits.get(positionOfMin).getPositionOfEnemyInList()).reciveHit(DAMAGE);
            }

            array[0] = bulletHits.get(positionOfMin).getX();
            array[1] = bulletHits.get(positionOfMin).getY();
            drawFireLineToX = bulletHits.get(positionOfMin).getX();
            drawFireLineToY = bulletHits.get(positionOfMin).getY();
        }
    }

    private void extendLine() {
        //  https://www.youtube.com/watch?v=mGcZGiUn39k&ab_channel=Mario%27sMathTutoring
        //Bere souřadnice od hráče po myš a výslednou úsečku prodlouří až mimo obrazovku.
        drawFireLineFromX = playerX + 15;
        drawFireLineFromY = playerY + 13;

        drawFireLineToX = (mouseX -15) - playerX;
        drawFireLineToY = (mouseY -13) - playerY;

        drawFireLineToX = drawFireLineToX * 300;
        drawFireLineToY = drawFireLineToY * 300;

        drawFireLineToX += playerX;
        drawFireLineToY += playerY;

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

    private void drawTestingInformations(Graphics g) {

        g.setColor(Color.GREEN);
        for (BulletHit hits : bulletHits) {
            g.drawOval(hits.getX(), hits.getY(), 3, 3);  // KOLECKO NA MISTE ZASAHU
        }

        g.setColor(Color.magenta);
        g.drawOval(array[0], array[1], 3, 3);

        g.setColor(Color.GREEN);
        g.setFont(new Font("tth", 1, 9));
        g.drawString(array[0] + "|" + array[1], array[0] + 7, array[1] - 3);//SOURADNICE MISTA ZASAHU
        g.drawString(playerX + "|" + playerY, playerX, playerY - 20); //SOURADNICE HRACE NAD HRACEM

        g.setFont(new Font("tth", 1, 14));  //INNER CLOCK NABITI ZBRANE (CHAMBERING)
        g.setColor(Color.GREEN);

        g.drawString("mouse X: " + mouseX, 180, 20);    //SOURADNCE PRO HRACE A MYS
        g.drawString("mouse Y: " + mouseY, 180, 35);    //     -//-
        g.drawString("player X: " + playerX, 180, 50);  //     -//-
        g.drawString("player Y: " + playerY, 180, 65);  //     -//-


        g.drawString("clock1:" + clock1, 20, 20);

        if (drawFireLine) {
            g.drawString("drawFireLine:" + drawFireLine, 20, 35);
        } else {
            g.setColor(Color.RED);
            g.drawString("drawFireLine:" + drawFireLine, 20, 35);
        }
        g.setColor(Color.GREEN);

        if (firinMechanism) {
            g.drawString("firingMechanism:" + firinMechanism, 20, 50);
        } else {
            g.setColor(Color.RED);
            g.drawString("firingMechanism:" + firinMechanism, 20, 50);
        }
        g.setColor(Color.GREEN);

        if (readyToFire) {
            g.drawString("readyToFire:" + readyToFire, 20, 65);
        } else {
            g.setColor(Color.RED);
            g.drawString("readyToFire:" + readyToFire, 20, 65);
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
                    g.drawImage(Assets.SMGUp, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.Up);
                } else if ((mouseX - playerX) / (playerY - mouseY) > 5) {
                    g.drawImage(Assets.SMGRight, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.Right);
                } else {
                    g.drawImage(Assets.SMGUpRight, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.UpRight);
                }
            }

            if (mouseX > playerX && mouseY > playerY) {           // PRAVA DOLNI SEKCE
                if ((mouseX - playerX) / (playerY - mouseY) > -1) {
                    g.drawImage(Assets.SMGDown, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.Down);
                } else if ((mouseX - playerX) / (playerY - mouseY) < -5) {
                    g.drawImage(Assets.SMGRight, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.Right);
                } else {
                    g.drawImage(Assets.SMGDownRight, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.DownRight);
                }
            }

            if (mouseX < playerX && mouseY > playerY) {         // LEVA DOLNI SEKCE
                if ((mouseX - playerX) / (playerY - mouseY) < 1) {
                    g.drawImage(Assets.SMGDown, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.Down);
                } else if ((mouseX - playerX) / (playerY - mouseY) > 5) {
                    g.drawImage(Assets.SMGLeft, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.Left);
                } else {
                    g.drawImage(Assets.SMGDownLeft, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.LeftDown);
                }
            }

            if (mouseX < playerX && mouseY < playerY) {         // LEVA HORNI SEKCE
                if ((mouseX - playerX) / (playerY - mouseY) > -1) {
                    g.drawImage(Assets.SMGUp, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.Up);
                } else if ((mouseX - playerX) / (playerY - mouseY) < -5) {
                    g.drawImage(Assets.SMGLeft, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.Left);
                } else {
                    g.drawImage(Assets.SMGUpLeft, playerX, playerY, null);
                    setPositionOfGun(PositionOfGun.LeftUp);
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

    @Override
    public void renderWeaponPictureAndAmmo(Graphics g) {
        // Vykreslí full picture obrázek zbraně + stav nábojů
        int shift = 10;
        int shiftForWeapon = 32;

        for (int i = 0; i < 4; i++) {
            g.drawImage(Assets.SMGMainPicture[i], 250 + (i * shiftForWeapon), 507, null);
        }
        g.setColor(Color.yellow);
        g.setFont(Assets.coinFont);
        g.drawString("AMMO : " + ammoInReserve + "/" + ammoInMagazine, 17, 530);
        if (reloading) {
            g.drawString("Reloading !", 450, 530);
        } else {
            if (ammoInMagazine > 0) {
                for (int i = 0; i < ammoInMagazine; i++) {
                    g.drawImage(Assets.bullet9mm, 375 + i * shift, 507, null);
                }
            } else if (ammoInMagazine == 0 && ammoInReserve > 0) {
                g.drawString("Press R for reload !", 420, 530);
            } else if (ammoInMagazine == 0 && ammoInReserve == 0) {
                g.drawString("No ammo left !", 420, 530);
            }
        }
    }
}
