package com.academy.solid.nie.client.communication;


/**
 * Implementation of Validator interface.
 * Checks whether the given IP is in the correct format.
 *
 * @since 1.0.1
 */

public class IpValidator implements Validator {

    private final String digitsOnly = "[0-9]\\d*";

    private static final int NUMBER_OF_OCTETS_IN_IP = 4;
    private static final int MAXIMAL_POSSIBLE_NUMBER = 256;

    /**
     * Determines whether the given IP is in the correct format
     * and does not contain invalid characters.
     * Works for IPv4.
     *
     * @param ip
     * @return true if IP is in correct format
     */

    @Override
    public boolean validate(String ip) {
        String[] splitted = ip.split("\\.");
        if (splitted.length != NUMBER_OF_OCTETS_IN_IP) {
            return false;
        }
        for (String s : splitted) {
            if (!(s.matches(digitsOnly) && Integer.parseInt(s) < MAXIMAL_POSSIBLE_NUMBER)) {
                return false;
            }
        }
        return true;
    }
}
