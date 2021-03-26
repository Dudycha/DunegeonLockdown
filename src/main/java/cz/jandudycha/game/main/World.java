package cz.jandudycha.game.main;

import java.awt.*;
import java.util.ArrayList;

public class World {
    private final ArrayList<Rectangle> listBarier = new ArrayList<>();
    private final ArrayList<Rectangle> listBarierExtraForPlayer = new ArrayList<>();
    private final Rectangle middleDownStuck = new Rectangle(340, 290, 280, 40);
    private final Rectangle middleUpStuck = new Rectangle(417, 30, 150, 190);
    private final Rectangle jumpPoint = new Rectangle(360, 260, 20, 20);
    private final Rectangle turnPoint = new Rectangle(170, 260, 20, 20);
    private final Rectangle turnPointGoblinLeft = new Rectangle(300, 260, 20, 20);
    private final Rectangle jumpPointRight = new Rectangle(600, 260, 20, 20);
    private final Rectangle turnPointForSpiders = new Rectangle(270, 260, 20, 20);
    private final Rectangle instaKillBox = new Rectangle(-500, 470, 2000, 20);
    private final Rectangle reliefForDragon = new Rectangle(340, 320, 280, 10);
    private final Rectangle turnPointForGoblins = new Rectangle(640, 257, 20, 20);
    private final Rectangle fallPitRight = new Rectangle(507, 330, 20, 160);
    private final Rectangle fallPitLeft = new Rectangle(430, 330, 20, 160);

    public World() {
        listBarier.add(new Rectangle(-200, 283, 529, 57));
        listBarier.add(new Rectangle(329, 329, 121, 168));
        listBarier.add(new Rectangle(507, 328, 137, 170));
        listBarier.add(new Rectangle(628, 276, 135, 68));
        listBarier.add(new Rectangle(751, 225, 498, 58));
        listBarier.add(new Rectangle(412, 219, 152, 34));

        listBarierExtraForPlayer.add(new Rectangle(0, 0, 3, 300));
        listBarierExtraForPlayer.add(new Rectangle(1047, 0, 3, 270));


    }

    public void update() {

    }

    public void render(Graphics g) {
/*
        g.setColor(Color.BLUE);
        g.fillRect((int)fallPitLeft.getX(),(int) fallPitLeft.getY(), (int)fallPitLeft.getWidth(), (int)fallPitLeft.getHeight());
        g.fillRect((int)fallPitRight.getX(),(int) fallPitRight.getY(), (int)fallPitRight.getWidth(), (int)fallPitRight.getHeight());

        g.setColor(Color.magenta);
        g.fillRect(360, 260, 20, 20); // jump point for enemies
        g.fillRect(600, 260, 20, 20);

        g.fillRect(170, 260, 20, 20); // turn point for dragon
        g.setColor(Color.CYAN);
        g.fillRect(270, 260, 20, 20); // turn point for Spiders
        g.fillRect(640, 257, 20, 20); // right one
        g.setColor(Color.BLUE);
        g.fillRect(300, 260, 20, 20); // for Goblin left


        g.setColor(Color.yellow);
        g.fillRect(340, 290, 280, 40); // middle Down stuck box
        g.fillRect(417, 30, 150, 190); // middle Up stuck box

        g.setColor(Color.pink);
        g.fillRect(340, 320, 280, 10); // relief For Dragon


        g.setColor(Color.blue);
        g.fillRect(0, 0, 3, 300);     // left and right borders for player
        g.fillRect(1047, 0, 3, 270);


        g.setColor(Color.green);
        g.fillRect(-200, 283, 529, 57);// world borders
        g.fillRect(329, 329, 121, 168);
        g.fillRect(507, 328, 137, 170);
        g.fillRect(628, 276, 135, 68);
        g.fillRect(751, 225, 498, 58);

        g.fillRect(412, 219, 152, 34);

        g.setColor(Color.red);
        g.fillRect(-500, 470, 2000, 20); // insta kill box

        g.setColor(Color.cyan);

        g.drawLine(415, 225, 565, 225);
        g.drawLine(565, 225, 565, 256);
        g.drawLine(565, 256, 415, 256);
        g.drawLine(415, 256, 415, 225);

//        for (int i = 0; i < worldBorers.length; i++) { // lines around screen for calculating shotgun firelines
//            g.drawLine(worldBorers[i][0],
//                    worldBorers[i][1],
//                    worldBorers[i][2],
//                    worldBorers[i][3]);
//        }


        for (Rectangle rec : listBarier) {
            g.setColor(Color.red);
            g.fillRect((int) rec.getX(), (int) rec.getY(), (int) rec.getWidth(), (int) rec.getHeight());
        }
*/

    }


    public ArrayList<Rectangle> getListBarierExtraForPlayer() {
        return listBarierExtraForPlayer;
    }

    public Rectangle getInstaKillBox() {
        return instaKillBox;
    }

    public Rectangle getTurnPointForSpiders() {
        return turnPointForSpiders;
    }

    public Rectangle getMiddleDownStuck() {
        return middleDownStuck;
    }

    public Rectangle getJumpPoint() {
        return jumpPoint;
    }

    public Rectangle getTurnPoint() {
        return turnPoint;
    }

    public Rectangle getMiddleUpStuck() {
        return middleUpStuck;
    }

    public Rectangle getReliefForDragon() {
        return reliefForDragon;
    }

    public Rectangle getFallPitRight() {
        return fallPitRight;
    }

    public Rectangle getFallPitLeft() {
        return fallPitLeft;
    }

    public Rectangle getTurnPointGoblinLeft() {
        return turnPointGoblinLeft;
    }

    public Rectangle getTurnPointForGoblins() {
        return turnPointForGoblins;
    }

    public Rectangle getJumpPointRight() {
        return jumpPointRight;
    }

    public ArrayList<Rectangle> getListBarier() {
        return listBarier;
    }


}
