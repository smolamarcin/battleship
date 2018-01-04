package com.epam.solid.nie.config;

import java.util.Map;

/**
 * Provides action to configure the game.
 */
public interface Configuration {
    /**
     * configuration file name
     */
    Map<ConfigProperty, String> provide();
}