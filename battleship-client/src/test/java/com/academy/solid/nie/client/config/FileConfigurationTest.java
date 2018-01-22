package com.academy.solid.nie.client.config;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.*;

public class FileConfigurationTest {
    private FileConfiguration fileConfiguration = new FileConfiguration();
    @Test(groups = {"unit"})
    public void name() {
        //given
        fileConfiguration.provide();
        //when-then
        Stream.of(ConfigProperty.values()).forEach(e -> assertNotNull(fileConfiguration.getCommunicate(e)));
    }
}