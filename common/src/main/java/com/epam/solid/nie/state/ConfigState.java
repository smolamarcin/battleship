package com.epam.solid.nie.state;

import lombok.ToString;

@ToString
public class ConfigState implements State {


    @Override
    public void process(GameState gameState) {
        gameState.setState(this);
    }
}
