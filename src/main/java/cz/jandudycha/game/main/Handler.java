package cz.jandudycha.game.main;


import cz.jandudycha.game.main.states.Game;

public class Handler {

    private final Game game;

    public Handler(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
