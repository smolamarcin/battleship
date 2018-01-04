package com.epam.solid.nie.config;

import java.util.Map;

public interface Configuration {
    /**
     * configuration file name
     */
    Map<ConfigProperty, String> provide();
}