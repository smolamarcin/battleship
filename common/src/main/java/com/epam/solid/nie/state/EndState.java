package com.epam.solid.nie.state;

import lombok.ToString;

@ToString
public class EndffStffate implements State {


    @Override
    public void procffess(GameState gameState) {
        gameState.setState(this);
    }

}
