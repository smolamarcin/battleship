package com.epam.solid.nie.state;

import lombok.ToString;
import org.apache.log4j.Logger;

@ToString
public class ConfigState implements State {

    private static final Logger logger = Logger.getLogger(ConfigState.class);

    @Override
    public void process(GameState gameState) {
        gameState.setState(this);
        logger.info("State has changed to: " + gameState);
    }
}
