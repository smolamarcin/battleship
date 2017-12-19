package com.epam.solid.nie.state;


import lombok.ToString;
import org.apache.log4j.Logger;

@ToString
public class PlayState implements State {

    @Override
    public void process(GameState gameState) {
        gameState.setState(this);
    }
}
