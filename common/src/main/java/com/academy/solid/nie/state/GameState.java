package com.academy.solid.nie.state;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * State during the game.
 */
@ToString
@Getter
@Setter
public class GameState implements State {

    private State state;

    /**
     * Changes the current state.
     *
     * @param gameState
     */
    @Override
    public final void process(final GameState gameState) {
        this.state.process(gameState);
    }
}
