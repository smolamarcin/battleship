package com.epam.solid.nie.state;


import org.apache.log4j.Logger;

public class PlayState implements State {

    final static Logger logger = Logger.getLogger(PlayState.class);

    @Override
    public void process(GameState gameState) {
        gameState.setState(this);
        logger.info("State has changed to: " + gameState);
    }
}
