package com.epam.solid.nie.state;


import lombok.ToString;

@ToString
public class PlayState implements State {
    /**
     * Changes the current state.
     * @param gameState
     */
    @Override
    public void process(GameState gameState) {
        gameState.setState(this);
    }
}
