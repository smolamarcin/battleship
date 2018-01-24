package com.academy.solid.nie.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Game {
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    private final Player first;
    private Player otherPlayer;
    private Player currentPlayer;
    private String move;

    Game(Player first, Player second) {
        this.first = first;
        this.currentPlayer = first;
        this.otherPlayer = second;
    }

    void play() throws IOException {
        log("It is now turn of " + getNameOfCurrentPlayer());
        move = currentPlayer.makeMove();
        log((getNameOfCurrentPlayer()) + " shoot at " + move);
        otherPlayer.inform(move);
        changeCurrentPlayer();
    }

    private String getNameOfCurrentPlayer() {
        return currentPlayer.equals(first) ? "First Player" : "Second Player";
    }

    private void changeCurrentPlayer() {
        if (otherPlayer.shallPlayersBeChanged(move)) {
            log((getNameOfCurrentPlayer()) + " missed");
            Player temp = currentPlayer;
            currentPlayer = otherPlayer;
            otherPlayer = temp;
        } else {
            log((getNameOfCurrentPlayer()) + " hit ship");
            if (otherPlayer.wasSunk()) {
                log("Ship has been sunk");
            }
            if (otherPlayer.isGameOver()) {
                log(getNameOfCurrentPlayer() + "has won the game");
            }
        }
    }

    boolean isGameOver() {
        return otherPlayer.isGameOver();
    }

    private void log(String msg) {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(msg);
        }
    }
}
