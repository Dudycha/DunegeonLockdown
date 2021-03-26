package cz.jandudycha.game.event.testingStuff;




import cz.jandudycha.game.main.Handler;

import java.awt.*;

public class LineLineIntersection {

    private Handler handler;
    int pomx1, pomy1, pomx2, pomy2, pomx3, pomy3, pomx4, pomy4;
    //https://www.youtube.com/watch?v=TOEi6T2mtHo&ab_channel=TheCodingTrain

    public LineLineIntersection(Handler handler) {
        this.handler = handler;
    }

    public void update() {


    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);

        int px = handler.getGame().getPlayer().getPlayerMovement().getX()+20;
        int py = handler.getGame().getPlayer().getPlayerMovement().getY()+30;

        //0, 283, 329, 283




        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

     //   int x3 = 411;
      //  int y3 = 191;
      //  int x4 = -189;
      //  int y4 = 77891;

        int x3 = px;
        int y3 = py;
        int x4 = handler.getGame().getKeyInput().getMouseX() ;
        int y4 = handler.getGame().getKeyInput().getMouseY() ;

        pomx1 = x1;
        pomy1 = y1;
        pomx2 = x2;
        pomy2 = y2;
        pomx3 = x3;
        pomy3 = y3;
        pomx4 = x4;
        pomy4 = y4;


        int den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (den == 0) {
            g.drawString("rovnoběžné!", 20, 100);
        } else {
            double t = (double) ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
            double u = (double) -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;

           // g.drawString("den:" + den + " t:" + t + " u:" + u, 300, 20);
            g.setFont(new Font("tth", 1, 12));
            g.drawString("den:" + den + " t:" + t + " u:" + u + "  Prvni= " + pomx1 + "|" + pomy1 + "  /  " + pomx2 + "|" + pomy2 + "  Druha= " + pomx3 + "|" + pomy3 + "    /    " + pomx4 + "|" + pomy4, 300, 20);


            if (t > 0 && t < 1 && u > 0) {
                g.drawString("intersekce!", 20, 100);
                g.drawOval((int) (x1 + t * (x2 - x1)), (int) (y1 + t * (y2 - y1)), 3, 3);
            } else {
                g.drawString("NE intersekce!", 20, 100);
            }
        }

       // System.out.println(lineLineIntersection(x1, y1, x2, y2, x3, y3, x4, y4));

        g.setColor(Color.red);
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x3, y3, x4, y4);
    }

    private boolean lineLineIntersectionBoolean(int x1, int y1, int x2, int y2, int x11, int y11, int x22, int y22) {

        int den = (x1 - x2) * (y11 - y22) - (y1 - y2) * (x11 - x22);

        if (den == 0) {
            return false;
        } else {
            double t = (double) ((x1 - x11) * (y11 - y22) - (y1 - y11) * (x11 - x22)) / den;
            double u = (double) -((x1 - x2) * (y1 - y22) - (y1 - y2) * (x1 - x11)) / den;


            if (t > 0 && t < 1 && u > 0) {
                return true;
            } else {
                return false;
            }
        }

    }
}
