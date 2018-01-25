package com.academy.solid.nie.client.config;

import java.util.EnumMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Reads configuration from the file.
 */
public final class FileConfiguration implements Configuration {
    /**
     * Default configuration file name.
     */
    private static final String NAME = "configuration";
    private static EnumMap<ConfigProperty, String> map = new EnumMap<>(ConfigProperty.class);
    private static EnumMap<ConfigProperty, String> defaultPropertyMap = new EnumMap<>(ConfigProperty.class);

    /**
     * Return the config map which is used in the game.
     * The map is filled based on the configuration file.
     *
     * @return map with config parameters
     */
    public Map<ConfigProperty, String> provide() {
        if (map.isEmpty()) {
            try {
                loadConfigFromFile();
            } catch (MissingResourceException e) {
                fillDefaultConfigMap();
            }
        }
        return map;
    }

    private void loadConfigFromFile() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(NAME);
        for (ConfigProperty configProperty : ConfigProperty.values()) {
            try {
                map.put(configProperty, resourceBundle.getString(configProperty.name().toUpperCase()));
            } catch (MissingResourceException e) {
                fillDefaultConfigMap();
                provideDefaultProperty(configProperty, e);
            }
        }
    }

    private void provideDefaultProperty(ConfigProperty configProperty, MissingResourceException e) {
        ConfigProperty defaultProperty = ConfigProperty.valueOf(e.getKey());
        String defaultPropertyValue = defaultPropertyMap.get(defaultProperty);
        map.put(configProperty, defaultPropertyValue);
    }

    private void fillDefaultConfigMap() {
        defaultPropertyMap.put(ConfigProperty.LANGUAGE, "polish");
        defaultPropertyMap.put(ConfigProperty.SERVER_IP, "127.0.0.1");
        defaultPropertyMap.put(ConfigProperty.SERVER_PORT, "9970");
        defaultPropertyMap.put(ConfigProperty.OUTPUT, "logger");
        defaultPropertyMap.put(ConfigProperty.FILE, "file.txt");
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
