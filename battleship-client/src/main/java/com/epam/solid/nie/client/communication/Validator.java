package com.epam.solid.nie.client.communication;

/**
 *  Allows you to specify whether the given value is appropriate.
 */
public interface Validator {
    boolean validate(String arg);
}