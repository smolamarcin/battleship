package com.epam.solid.nie.config.language;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LanguageConfigurationImplTest {
    private LanguageConfiguration languageConfiguration = new LanguageConfigurationImpl();
    @Test
    public void shouldRead_Default_LanguageValue_FromFile() throws Exception {
        Language currentLanguage = languageConfiguration.provide();
        assertTrue(currentLanguage.equals(Language.ENGLISH));
    }

    @Test
    public void shouldLoad_AnyLanguage_FromFile() throws Exception {
        assertNotNull(languageConfiguration.provide());
    }
}