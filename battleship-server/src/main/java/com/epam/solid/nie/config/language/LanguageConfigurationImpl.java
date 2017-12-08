package com.epam.solid.nie.config.language;

import com.epam.solid.nie.config.Configuration;

import java.util.ResourceBundle;

public class LanguageConfigurationImpl implements LanguageConfiguration {

    private static final String LANGUAGE = "LANGUAGE";

    @Override
    public Language provide() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Configuration.CONFIGURATION);
        return Language.valueOf(resourceBundle.getString(LANGUAGE));
    }
}
