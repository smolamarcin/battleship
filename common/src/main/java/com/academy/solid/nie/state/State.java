package com.academy.solid.nie.state;

/**
 * Represents current state of the game.
 */
public interface State {

    void process(GameState gameState);

}
