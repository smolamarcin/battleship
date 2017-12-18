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
    public void populateShould_FillCommunicatesMap(Language language) {
        communicateProvider.populate(language);
        assertFalse(communicateProvider.isMapEmpty());
    }



}