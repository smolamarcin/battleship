package com.epam.solid.nie.client.communication;


/**
 *
 */
public class IpValidator implements Validator {
    @Override
    public boolean validate(String ip) {
        String[] splitted = ip.split("\\.");
        if (splitted.length != 4)
            return false;
        for (String s : splitted)
            if (!(s.matches("[1-9]\\d*") && Integer.valueOf(s) < 256))
                return false;
        return true;
    }
}
