package com.academy.solid.nie.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Game {
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    private final Player first;
    private final Player second;
    private Player currentPlayer;
    private String move;

    Game(Player first, Player second) {
        this.first = first;
        this.second = second;
        this.currentPlayer = first;
    }

    void play() throws IOException {
        move = currentPlayer.makeMove();
        changeCurrentPlayer();
        currentPlayer.inform(move);

        log((currentPlayer.equals(first) ? "First Player" : "Second Player") + ":" + move);
    }

    private void changeCurrentPlayer() {
        currentPlayer = currentPlayer.equals(first) ? second : first;
    }

    boolean isGameOver() {
        return move.equals("Q");
    }

    private void log(String msg) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(msg);
        }
    }
}
