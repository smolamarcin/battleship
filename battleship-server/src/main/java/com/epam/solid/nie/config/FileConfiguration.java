package com.epam.solid.nie.config;

import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileConfiguration implements Configuration {
    /**
     * configuration file name
     */
    String CONFIGURATION = "configuration";

    private EnumMap<ConfigProperty, String> map = new EnumMap(ConfigProperty.class);

    public Map<ConfigProperty, String> provide() {
        if (map.isEmpty()) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIGURATION);
            Stream.of(ConfigProperty.values()).forEach(consume(resourceBundle));
        }
        return map;
    }

    private Consumer<ConfigProperty> consume(ResourceBundle resourceBundle) {
        return element -> map.put(element, resourceBundle.getString(element.name()));
    }
}