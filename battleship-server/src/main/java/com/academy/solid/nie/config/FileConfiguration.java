package com.academy.solid.nie.config;

import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Reads configuration from the file.
 */
public class FileConfiguration implements Configuration {
    public static final String CONFIGURATION = "configuration";
    private EnumMap<ConfigProperty, String> map = new EnumMap<>(ConfigProperty.class);

    /**
     * Return the config map which is used in the game.
     * The map is filled based on the configuration file.
     * @return map with config parameters
     */
    public Map<ConfigProperty, String> provide() {
        if (map.isEmpty()) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIGURATION);
            Stream.of(ConfigProperty.values()).forEach(consume(resourceBundle));
        }
        return map;
    }

    private Consumer<ConfigProperty> consume(final ResourceBundle resourceBundle) {
        return element -> map.put(element, resourceBundle.getString(element.name()));
    }
}
