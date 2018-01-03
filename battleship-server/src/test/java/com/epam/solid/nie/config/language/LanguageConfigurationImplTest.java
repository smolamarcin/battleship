package com.epam.solid.nie.config.language;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LanguageConfigurationImplTest {
    private LanguageConfiguration languageConfiguration = new LanguageConfigurationImpl();

    @Test(groups = {"unit test"})
    public void shouldRead_Default_LanguageValue_FromFile() {
        Language currentLanguage = languageConfiguration.provide();
        assertTrue(currentLanguage.equals(Language.ENGLISH));
    }

    @Test(groups = {"unit test"})
    public void shouldLoad_AnyLanguage_FromFile() {
        assertNotNull(languageConfiguration.provide());
    }
}