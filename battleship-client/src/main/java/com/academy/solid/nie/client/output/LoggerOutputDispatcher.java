package com.academy.solid.nie.client.output;

import java.util.logging.Logger;

/**
 * output as logger.
 */
public class LoggerOutputDispatcher implements Output {
    private static final Logger LOGGER = Logger.getLogger(LoggerOutputDispatcher.class.getName());

    @Override
    public final void send(String msg) {
        LOGGER.info(msg);
    }
}
