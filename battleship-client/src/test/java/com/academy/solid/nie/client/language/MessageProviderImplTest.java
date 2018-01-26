package com.academy.solid.nie.client.language;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class MessageProviderImplTest {
    private MessageProviderImpl messageProvider = new MessageProviderImpl();

    @Test(groups = "unit")
    public void shouldProvideMap_WithMessages() {
        //given
        List<Language> availableLanguages = Arrays.asList(Language.ENGLISH, Language.POLISH);
        //when - then
        for (Language language : availableLanguages) {
            messageProvider.populate(language);
            assertFalse(messageProvider.isMapEmpty());
        }
    }

    @Test(groups = "unit")
    public void shouldReturnMessages_WhenLanguageIsProvided() {
        //given
        Language language = Language.POLISH;
        //when
        messageProvider.populate(language);
        //then
        for (Message message : Message.values()) {
            assertNotNull(MessageProviderImpl.getCommunicate(message));
        }
    }
}
