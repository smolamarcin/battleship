package com.academy.solid.nie.client.config;

import java.util.Map;

/**
 * Provides action to configure the game.
 */
public interface Configuration {
    /**
     * configuration file name
     *
     * @return a map with contains config properties
     */
    Map<ConfigProperty, String> provide();
}
