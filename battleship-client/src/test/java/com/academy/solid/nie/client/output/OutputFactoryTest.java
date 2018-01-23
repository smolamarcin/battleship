package com.academy.solid.nie.client.output;

import com.academy.solid.nie.client.config.Configuration;
import com.academy.solid.nie.client.config.FileConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class OutputFactoryTest {

    public void simpleFactoryTest() {
        //given
        Configuration configuration = new FileConfiguration();
        configuration.provide();
        OutputFactory outputFactory = new OutputFactory(configuration);
        //when
        Output output = outputFactory.create();
        //then
        assertNotNull(output);
    }
}
