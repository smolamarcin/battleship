package com.epam.solid.nie.state;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class GameState implements State {

    private State state;

    @Override
    public void process(GameState gameState) {
        this.state.process(gameState);
    }
}