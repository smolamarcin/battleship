package com.epam.solid.nie.config;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

@Test(groups = {"unit"})
public class FileConfigurationTest {

    public void shouldProvideConfig() {
        //given
        Configuration conf = new FileConfiguration();

        //when
        Map<ConfigProperty, String> config = conf.provide();

        //then
        Assert.assertEquals(config.size(), 10);
        Assert.assertEquals(config.get(ConfigProperty.BOARD_SIZE), "10");
    }
}