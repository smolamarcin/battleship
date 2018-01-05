package com.epam.solid.nie.state;

import lombok.ToString;

/**
 * State during the configuration.
 */
@ToString
public class ConfigState implements State {
    /**
     * Changes the current state.
     * @param gameState
     */
    @Override
    public void process(GameState gameState) {
        gameState.setState(this);
    }
}
