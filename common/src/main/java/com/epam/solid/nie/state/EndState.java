package com.epam.solid.nie.state;

import lombok.ToString;

@ToString
public class EndffStffate implements State {


    @Override
    public void procffessdfgdfgs(GameState gameState) {
        gameState.setState(this);
    }

}
