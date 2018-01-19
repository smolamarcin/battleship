package com.academy.solid.nie.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Game {
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    private final Player first;
    private final Player second;
    private Player otherPlayer;
    private Player currentPlayer;
    private String move;

    Game(Player first, Player second) {
        this.first = first;
        this.second = second;
        this.currentPlayer = first;
        this.otherPlayer = second;
    }

    void play() throws IOException {
        move = currentPlayer.makeMove();
        changeCurrentPlayer();
        currentPlayer.inform(move);

        log((currentPlayer.equals(first) ? "First Player" : "Second Player") + ":" + move);
    }

    private void changeCurrentPlayer() {
        if (!otherPlayer.getShips().contains(move)) {
            Player temp = currentPlayer;
            currentPlayer = otherPlayer;
            otherPlayer = temp;
        }
    }

    boolean isGameOver() {
        return "Q".equals(move);
    }

    private void log(String msg) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(msg);
        }
    }
}
