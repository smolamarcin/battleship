package com.academy.solid.nie.client.config;

import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Reads configuration from the file.
 */
public final class FileConfiguration implements Configuration {
    /**
     * Default configuration file name.
     */
    private static final String NAME = "configuration";
    private static EnumMap<ConfigProperty, String> map = new EnumMap<>(ConfigProperty.class);

    /**
     * Return the config map which is used in the game.
     * The map is filled based on the configuration file.
     *
     * @return map with config parameters
     */
    public Map<ConfigProperty, String> provide() {
        if (map.isEmpty()) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(NAME);
            Stream.of(ConfigProperty.values()).forEach(consume(resourceBundle));
        }
        return map;
    }

    private Consumer<ConfigProperty> consume(final ResourceBundle resourceBundle) {
        return element -> map.put(element, resourceBundle.getString(element.name()));
    }

    /**
     * Return specified message in the game.
     *
     * @param property sadasd
     * @return string representation of the property
     */
    public String getCommunicate(ConfigProperty property) {
        return map.get(property);
    }

}
