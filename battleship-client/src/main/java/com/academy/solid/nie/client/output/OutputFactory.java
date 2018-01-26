package com.academy.solid.nie.client.output;

import com.academy.solid.nie.client.config.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.academy.solid.nie.client.config.ConfigProperty.FILE;
import static com.academy.solid.nie.client.config.ConfigProperty.OUTPUT;

/**
 * provides correct output impl based on input
 */
public class OutputFactory {
    private Configuration configuration;
    private static Map<String, Output> outputs = new HashMap<>();

    /**
     * constructor for output factory
     *
     * @param configuration used to get correct output type from file
     */
    public OutputFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * @return provides implementation of output based on configuration file
     * If something goes wrong, default outputy type is loaded.
     */
    public Output create() {
        outputs.put(OutputType.FILE.name().toUpperCase(), new FileOutputDispatcher(configuration.getCommunicate(FILE)));
        outputs.put(OutputType.LOGGER.name().toUpperCase(), new LoggerOutputDispatcher());
        outputs.put(OutputType.WINDOW.name().toUpperCase(), new WindowOutputDispatcher());
        return outputs.getOrDefault(configuration.getCommunicate(OUTPUT), new LoggerOutputDispatcher());
    }

    /**
     * getter for output
     *
     * @return output
     */
    public Output getOutput() {
        return outputs.get(configuration.getCommunicate(OUTPUT));
    }
}
