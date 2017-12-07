package com.epam.solid.nie.server;

public class CommunicationProtocol implements Protocol {
    @Override
    public String processInput(String inputString, int id) {
        return inputString.equals("Q") ? "Bye." : "Provide location";
    }
}
