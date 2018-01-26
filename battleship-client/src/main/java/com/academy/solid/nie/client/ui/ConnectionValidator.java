package com.academy.solid.nie.client.ui;

import com.academy.solid.nie.client.communication.Validator;

import java.io.IOException;
import java.net.InetAddress;

public class ConnectionValidator implements Validator {
    private static final int CONNECTION_TIMEOUT = 3000;

    @Override
    public boolean validate(String ip) {
        boolean reachable = false;
        try {
            reachable = InetAddress.getByName(ip).isReachable(CONNECTION_TIMEOUT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return reachable;
    }

}
