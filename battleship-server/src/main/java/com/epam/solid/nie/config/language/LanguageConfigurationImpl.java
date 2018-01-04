package com.epam.solid.nie.config.language;

import com.epam.solid.nie.config.FileConfiguration;

import java.util.ResourceBundle;

class LanguageConfigurationImpl implements LanguageConfiguration {

    private static final String LANGUAGE = "LANGUAGE";

    /**
     * Returns the appropriate language that is loaded from the configuration file.
     * @return
     */
    @Override
    public Language provide() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(FileConfiguration.CONFIGURATION);
        return Language.valueOf(resourceBundle.getString(LANGUAGE));
    }
}