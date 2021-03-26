package cz.jandudycha.game.main;




import cz.jandudycha.game.main.gameOver.GameOver;
import cz.jandudycha.game.main.states.*;
import cz.jandudycha.game.main.states.Menu;

import java.awt.*;

import java.awt.image.BufferStrategy;

public class RenderLayer extends Canvas implements Runnable {
    private final int WINDOW_WIDTH = 1050;
    private final int WINDOW_HEIGHT = 550;
    private boolean isRunning = false;
    private final KeyInput keyInput;

    //States
    private State game;
    private final State menu;
    private final State scoreBoard;
    private final State setings;
    private final State menuInGame;
    private final State buyMenu;
    private final State gameOver;


    public RenderLayer() {
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        keyInput = new KeyInput();
        newGame();
        scoreBoard = new ScoreBoard(this, keyInput);
        menu = new Menu(this, keyInput);
        setings = new Setings(this, keyInput);
        menuInGame = new MenuInGame(this, keyInput);
        buyMenu = new BuyMenu(this, keyInput);
        gameOver = new GameOver(this, keyInput);

        State.setState(menu);
        this.addKeyListener(keyInput);
        this.addMouseListener(keyInput);
        this.addMouseMotionListener(keyInput);
        this.addMouseWheelListener(keyInput);


    }

    public void newGame() {
        game = new Game(this, keyInput);

    }

    @Override
    public void run() {
        long lastTimeCycle = System.nanoTime();
        long lastTimeOutput = System.currentTimeMillis();
        double unprocessedTicks = 0;
        double nsPerTick = Math.pow(10, 9) / 60;
        int tick = 0;
        int FPS = 0;


        while (isRunning) {
            long nowTimeCycle = System.nanoTime();
            unprocessedTicks += (nowTimeCycle - lastTimeCycle) / nsPerTick;
            lastTimeCycle = nowTimeCycle;
            while (unprocessedTicks >= 1) {
                tick++;
                unprocessedTicks--;
                update();
            }
            FPS++;
            render();
            if (System.currentTimeMillis() - lastTimeOutput > 1000) {
                lastTimeOutput += 1000;
                System.out.println("Ticks:" + tick + " FPS:" + FPS);
                FPS = 0;
                tick = 0;
            }
        }
    }


    private void update() {
        keyInput.update();

        if (State.getState() != null) {
            State.getState().update();
        }

    }

    private void render() {
        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = buffer.getDrawGraphics();

        if (State.getState() != null) {
            State.getState().render(g);

        }


        g.dispose();
        buffer.show();
    }


    public void start() {
        isRunning = true;
        Thread t = new Thread(this);
        t.start();
    }

    public State getSetings() {
        return setings;
    }

    public State getGame() {
        return game;
    }

    public State getMenu() {
        return menu;
    }

    public State getScoreBoard() {
        return scoreBoard;
    }

    public State getMenuInGame() {
        return menuInGame;
    }

    public State getBuyMenu() {
        return buyMenu;
    }

    public State getGameOver() {
        return gameOver;
    }


    public KeyInput getKeyInput() {
        return keyInput;
    }
}
