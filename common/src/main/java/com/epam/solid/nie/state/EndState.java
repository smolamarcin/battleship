package com.epam.solid.nie.state;

import lombok.ToString;
import org.apache.log4j.Logger;

@ToString
public class EndState implements State {

    private static final Logger logger = Logger.getLogger(EndState.class);

    @Override
    public void process(GameState gameState) {
        gameState.setState(this);
        logger.info("State has changed to: " + gameState);
    }

}
