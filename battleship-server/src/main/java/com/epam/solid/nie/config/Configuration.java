package com.epam.solid.nie.config;

import java.util.Map;

public interface Configuration {
    Map<ConfigProperty, String> provide();
}