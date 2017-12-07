package com.epam.solid.nie.config.language;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommunicateProviderImplTest {
    private CommunicateProviderImpl communicateProvider = new CommunicateProviderImpl();

    @DataProvider
    public static Object[][] languages() {
        return new Object[][]{{Language.ENGLISH},
                {Language.POLISH}
        };
    }

    @Test(dataProvider = "languages")
    public void populateShould_FillMapWithCommunicates(Language language) throws Exception {
        communicateProvider.populate(language);
        assertFalse(communicateProvider.isMapEmpty());
    }

    @DataProvider
    public static Object[][] communicatesInEnglish() {
        return new Object[][]{{Language.ENGLISH,"Welcome in my game.", Communicate.WELCOME},
        };
    }

    @Test (dataProvider = "communicatesInEnglish")
    public void shouldLoadCorrectCommunicates_English(Language language,String expectedCommunicate,Communicate communicate){
        communicateProvider.populate(language);
        assertEquals(expectedCommunicate,communicateProvider.getCommunicate(communicate));
    }
    @DataProvider
    public static Object[][] communicatesInPolish() {
        return new Object[][]{{Language.POLISH,"Witaj w  mojej grze.", Communicate.WELCOME},
        };
    }

    @Test (dataProvider = "communicatesInPolish")
    public void shouldLoadCorrectCommunicates_Polish(Language language,String expectedCommunicate,Communicate communicate){
        communicateProvider.populate(language);
        assertEquals(expectedCommunicate,communicateProvider.getCommunicate(communicate));
    }


}