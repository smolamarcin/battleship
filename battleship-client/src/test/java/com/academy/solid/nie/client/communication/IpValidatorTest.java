package com.academy.solid.nie.client.communication;

import com.academy.solid.nie.client.communication.IpValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


/**
 *
 */
public class IpValidatorTest {
    @DataProvider
    public static Object[] properIPs() {
        return new Object[]{
                "47.1.54.16",
                "43.101.14.31",
                "255.101.14.13"
        };
    }

    @Test(dataProvider = "properIPs", groups = {"unit"})
    public void should_beValid_when_properIp(String ip) {
        IpValidator val = new IpValidator();
        assertTrue(val.validate(ip));
    }

    @DataProvider
    public static Object[] improperIPs() {
        return new Object[]{
                "",
                "1",
                "21.13",
                "13.111.1",
                "43.101.14.1.13",
                "w.101.14.1.13",
                "256.101.14.1.13",
                "101.256.1.13",
                "101.14.256.13",
                "101.14.1.256",
                "0.101.14.1.13"
        };
    }

    @Test(dataProvider = "improperIPs", groups = {"unit"})
    public void should_beInvalid_when_improperIp(String ip) {
        IpValidator val = new IpValidator();
        assertFalse(val.validate(ip));
    }
}
