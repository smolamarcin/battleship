package com.epam.solid.nie.config;

import java.util.Map;

public interface Configuration {
    /**
     * configuration fddile name
     */
    String CONFIGURATION = "configuration";

    Map<ConfigProperty, String> provide();
}