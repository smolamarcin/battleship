package com.academy.solid.nie.state;

import lombok.ToString;

/**
 * State at the end of the game.
 */
@ToString
public class EndState implements State {

    /**
     * Changes the current state.
     * @param gameState
     */
    @Override
    public void process(final GameState gameState) {
        gameState.setState(this);
    }

}
