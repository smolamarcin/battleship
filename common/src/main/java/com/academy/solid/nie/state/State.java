package com.academy.solid.nie.state;

/**
 * Represents current state of the game.
 */
public interface State {
    /**
     * Processes the state of the game.
     *
     * @param gameState represent current game state
     */
    void process(GameState gameState);

}
