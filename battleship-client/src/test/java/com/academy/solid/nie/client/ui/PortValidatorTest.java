package com.academy.solid.nie.client.ui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PortValidatorTest {
    private PortValidator portValidator = new PortValidator();
    @DataProvider
    public static Object[][] ports() {

        return new Object[][]{{"Siema", false},
                {"Siasd", false},
                {"65535", true},
                {"2125", true},
                {"8081", true},
                {"29", false},
                {"655351", false},
                {"1023", false},
                {"", false},
                {"\n", false},
                {"1024", true}
    };

    }

    @Test(dataProvider = "ports")
    public void shouldValidatePort(String port, boolean result) {
        //given
        boolean actual = portValidator.validate(port);
        //when - then
        assertEquals(actual, result);
    }
}