package com.epam.solid.nie.client.communication;


/**
 * Implementation of Validator interface.
 * Checks whether the given IP is in the correct format.
 *
 * @since 1.0.1
 */

public class IpValidator implements Validator {
    //todo: better naming, ASK DOMINIK!
    private static final int IP_V4_SIZE = 4;
    private static final int MAX_SIZE = 256;

    /**
     * Determines whether the given IP is in the correct format
     * and does not contain invalid characters.
     * Works for IPv4.
     *
     * @param ip
     * @return true if IP is in correct format
     */

    @Override
    public boolean validate(final String ip) {
        String[] splitted = ip.split("\\.");
        if (splitted.length != IP_V4_SIZE) {
            return false;
        }
        for (String s : splitted) {
            if (!(s.matches("[1-9]\\d*") && Integer.parseInt(s) < MAX_SIZE)) {
                return false;
            }
        }
        return true;
    }
}
