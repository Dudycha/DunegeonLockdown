package cz.jandudycha.game.event.testingStuff;




import cz.jandudycha.game.main.Handler;

import java.awt.*;


public class LineCircleIntersection {
    private Handler handler;

    public LineCircleIntersection(Handler handler) {
        this.handler = handler;
    }

    public void update() {


    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);

        int px = handler.getGame().getPlayer().getPlayerMovement().getX();
        int py = handler.getGame().getPlayer().getPlayerMovement().getY();

        int x3 = px;
        int y3 = py;
        int x4 = handler.getGame().getKeyInput().getMouseX();
        int y4 = handler.getGame().getKeyInput().getMouseY();

        int circleX = 200;
        int circleY = 100;
        int circleRadius = 70;

        g.setColor(Color.RED);                                          //PLAYER - MOUSE
        g.drawOval(circleX, circleY, circleRadius, circleRadius);
        g.drawLine(x3, y3, x4, y4);

        g.setColor(Color.blue);                                         //PLAYER - MIDDLE-CIRCLE
        g.drawLine(x3,y3,circleX + circleRadius/2,circleY + circleRadius/2);

        g.setColor(Color.orange);                                       //MIDDLE-CIRCLE - PLAYER
        g.drawLine(x4,y4,circleX + circleRadius/2,circleY + circleRadius/2);

        g.setColor(Color.GREEN);                                        //PLAYER - mousex/
        g.drawLine(x3,y3,x4,y3);
    }
}
