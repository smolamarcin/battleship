package com.academy.solid.nie.client.language;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;

public class CommunicateProviderImplTest {
    private CommunicateProviderImpl communicateProvider = new CommunicateProviderImpl();

    @DataProvider
    public static Object[][] languages() {
        return new Object[][]{{Language.ENGLISH},
                {Language.POLISH}
        };
    }

    @Test(dataProvider = "languages", groups = {"unit"})
    public void populateShould_FillCommunicatesMap(Language language) {
        communicateProvider.populate(language);
        assertFalse(communicateProvider.isMapEmpty());
    }


}