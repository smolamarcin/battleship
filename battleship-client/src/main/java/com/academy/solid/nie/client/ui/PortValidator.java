package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.client.communication.Validator;
import com.academy.solid.nie.client.output.Output;

import java.util.logging.Logger;

/**
 * Implementation of Validator interface.
 * Checks whether the given port number is in the correct format.
 */
public class PortValidator implements Validator {
    private static final int MINIMAL_POSSIBLE_NUMBER = 1024;
    private static final int MAXIMAL_POSSIBLE_NUMBER = 65535;
    private Output output;

    public PortValidator(final Output output) {
        this.output = output;
    }

    /**
     * Determines whether the given Port is in the correct range
     * and does not contain invalid characters.
     *
     * @param port
     * @return
     */
    @Override
    public boolean validate(String port) {
        return isNumeric(port) && isInRange(port);
    }

    private boolean isNumeric(String port) {
        boolean result = true;
        try {
            Integer.parseInt(port);
        } catch (NumberFormatException e) {
            output.send(e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean isInRange(String port) {
        int portValue = Integer.parseInt(port);
        return portValue >= MINIMAL_POSSIBLE_NUMBER && portValue <= MAXIMAL_POSSIBLE_NUMBER;
    }
}
