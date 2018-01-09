package com.academy.solid.nie.state;


import lombok.ToString;

/**
 * Respresents a state during the gameplay.
 */
@ToString
public class PlayState implements State {
    /**
     * Changes the current state.
     * @param gameState
     */
    @Override
    public final void process(final GameState gameState) {
        gameState.setState(this);
    }
}
