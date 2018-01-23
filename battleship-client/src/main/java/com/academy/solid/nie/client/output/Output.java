package com.academy.solid.nie.client.output;

/**
 * represents used type of output
 */
public interface Output {

    /**
     * send message to output
     * @param msg is message being send to output
     */
    void send(String msg);

}
