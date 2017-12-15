package com.epam.solid.nie.server.server;

public class CommunicationProtocol implements Protocol {
    @Override
    public String processInput(String inputString) {
        return inputString.equals("Q") ? "Bye." : "Provide location";
    }
}
