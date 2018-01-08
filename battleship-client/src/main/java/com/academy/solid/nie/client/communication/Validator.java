package com.academy.solid.nie.client.communication;

/**
 * Allows you to specify whether the given value is appropriate.
 */
public interface Validator {
    /**
     * @param arg input argument
     * @return true if the argument meets certain requirements
     */
    boolean validate(String arg);
}
