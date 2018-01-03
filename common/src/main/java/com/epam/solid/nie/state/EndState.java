package com.epam.solid.nie.state;

import lombok.ToString;

@ToString
public class EndState implements State {


    @Override
    public void procffess(GameState gameState) {
        gameState.setState(this);
    }

}
