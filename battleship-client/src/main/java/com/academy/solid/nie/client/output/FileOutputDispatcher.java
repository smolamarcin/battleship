package com.academy.solid.nie.client.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * Logs message to file
 */
public class FileOutputDispatcher implements Output {
    private final Logger logger = Logger.getLogger(FileOutputDispatcher.class.getName());
    private final String fileName;

    /**
     * @param fileName name of the file where messages will be saved
     */
    public FileOutputDispatcher(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public final void send(String msg) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            out.println(msg);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

}
