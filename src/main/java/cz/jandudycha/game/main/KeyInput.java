package cz.jandudycha.game.main;


import cz.jandudycha.game.ui.UIManager;

import java.awt.event.*;

public class KeyInput implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private UIManager uiManager;
    private boolean  left, right, space, leftMouse, reload, escape, bButton;

    private int mouseX, mouseY;
    private int numberPressed = 0;
    private boolean numberChanged = false;
    private double wheelMove = 0;
    private double wheelMovePom = 0;
    private boolean wasEscape,wasbButton;


    public KeyInput() {

    }

    public void setUIManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public void update() {
        if (uiManager != null) {
            uiManager.onMouseMove(mouseX,mouseY);
        }
        if (wheelMovePom != 0) {
            if (wheelMovePom < 0) {
                wheelMove = -1;
            } else {
                wheelMove = 1;
            }
            wheelMovePom = 0;
        } else {
            wheelMove = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            reload = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escape = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            numberPressed = 0;
            numberChanged = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            bButton = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            numberPressed = 1;
            numberChanged = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            numberPressed = 2;
            numberChanged = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) {
            numberPressed = 3;
            numberChanged = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_5) {
            numberPressed = 4;
            numberChanged = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_6) {
            numberPressed = 5;
            numberChanged = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            space = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            reload = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            bButton = false;
            wasbButton = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            escape = false;
            wasEscape = false;
        }


    }


    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isSpace() {
        return space;
    }

    public boolean isLeftMouse() {
        return leftMouse;
    }

    public boolean isReaload() {
        return reload;
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            leftMouse = true;
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            leftMouse = false;
        }
        if (uiManager != null && e.getButton() == 1) {
            uiManager.onMouseRelease(e);
        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

    }

    public int getnumberPressed() {
        return numberPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public double getWheelMove() {
        return wheelMove;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isWasbButton() {
        return !wasbButton;
    }

    public void setWasbButton(boolean wasbButton) {
        this.wasbButton = wasbButton;
    }

    public boolean isbButton() {
        return bButton;
    }


    public boolean isEscape() {
        return escape;
    }

    public boolean isWasEscape() {
        return !wasEscape;
    }

    public void setwasEscape(boolean wasEscape) {
        this.wasEscape = wasEscape;
    }


    public boolean isNumberChanged() {
        return numberChanged;
    }

    public void setNumberChanged(boolean numberChanged) {
        this.numberChanged = numberChanged;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        wheelMovePom = e.getPreciseWheelRotation();
    }


}
