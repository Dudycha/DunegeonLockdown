package cz.jandudycha.game.ui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {
    private final ArrayList<UIObject> objects;


    public UIManager() {
        objects = new ArrayList<>();
    }

    public void update() {
        for (UIObject o : objects) {
            o.update();
        }
    }

    public void render(Graphics g) {
        for (UIObject o : objects) {
            o.render(g);
        }
    }

    public void onMouseMove(int mouseX,int mouseY) {
        for (UIObject o : objects) {
            o.onMouseMove(mouseX,mouseY);
        }
    }


    public void onMouseRelease(MouseEvent e) {
        for (UIObject o : objects) {
            o.onMouseRelease(e);
        }
    }

    public void addObject(UIObject o) {
        objects.add(o);
    }


    public ArrayList<UIObject> getObjects() {
        return objects;
    }


}
