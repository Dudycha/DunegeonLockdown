package cz.jandudycha.game.entity.enemy;



import cz.jandudycha.game.main.Handler;
import cz.jandudycha.game.texture.Assets;

import java.awt.*;

public class FireBall {
    private final Handler handler;
    private final int FIREBALL_DMG = 4;
    private final double xFrom, yFrom;
    private final double xTo, yTo;
    private int[] arrayX;
    private int[] arrayY;
    private int fireBallIteration = 0;
    private Rectangle hitBox;
    private boolean redayToRemove = false;


    public FireBall(Handler handler, int xFrom, int yFrom, int xTo, int yTo) {
        this.handler = handler;
        this.xFrom = xFrom;
        this.yFrom = yFrom;
        this.xTo = xTo;
        this.yTo = yTo;
        hitBox = new Rectangle(xFrom - 5, yFrom - 5, 10, 10);

        calculateRoute();
    }

    private void calculateRoute() {
        int pixAmt = 10, extendLine = 50;
        double vectorX, vectorY;

        double pomA = (xTo - xFrom) * (xTo - xFrom);              //Zarovnání do rohu obrazovky pro zjištění skutčných
        double pomB = (yTo - yFrom) * (yTo - yFrom);              //délek stran.

        double length = (Math.sqrt(pomA + pomB));                 //Pomocí pytharogorovy věty zjistí délku třetí strany.

        vectorX = xTo - xFrom;
        vectorY = yTo - yFrom;

        int iterationCount = ((int) length / pixAmt);            // Zjistí počet potřebných iterací pro dosažení cílového bodu.
        // Velokost pole pro uložení souřadnic také odpovídá tomuto číslu.


        arrayX = new int[iterationCount + extendLine];           //Vytvoří 2 pole pro x a y souřadnice s příslušnou kapacitou.
        arrayY = new int[iterationCount + extendLine];           //extendLine slouží pro prodloužení výsledné trasy až za cílový bod.

        while (length > pixAmt) {                                //Postupně zkracuje vektory tak dlouho až jsou menší než požadovaná délka v pixelech.
            vectorX = vectorX / 1.1;
            vectorY = vectorY / 1.1;
            length = (Math.sqrt(vectorX * vectorX + vectorY * vectorY));
        }

        double pomX = xFrom;
        double pomY = yFrom;

        for (int i = 0; i < iterationCount + extendLine; i++) {

            pomX = pomX + vectorX;                               //Postupně přidává a ukládá body výsledné trasy
            pomY = pomY + vectorY;

            arrayX[i] = (int) pomX;
            arrayY[i] = (int) pomY;

        }
    }

    public void update() {


        if (fireBallIteration == arrayX.length - 1) {
            redayToRemove = true;
        } else {
            fireBallIteration++;
            hitBox = new Rectangle(arrayX[fireBallIteration] + 5, arrayY[fireBallIteration] + 5, 10, 10);
        }

        if (hitBox.intersects(handler.getGame().getPlayer().getPlayerMovement().getHitBoxPlayer())) {
            handler.getGame().getPlayer().setHitpoints(handler.getGame().getPlayer().getHitpoints() - FIREBALL_DMG);
            handler.getGame().getPlayer().setTotalDMGrecieved(handler.getGame().getPlayer().getTotalDMGrecieved() + FIREBALL_DMG);
            redayToRemove = true;
        }
    }


    public void render(Graphics g) {
        g.drawImage(Assets.fireBall, arrayX[fireBallIteration] - 16, arrayY[fireBallIteration] - 16,50,50, null);


    }

    public boolean isRedayToRemove() {
        return redayToRemove;
    }
}
