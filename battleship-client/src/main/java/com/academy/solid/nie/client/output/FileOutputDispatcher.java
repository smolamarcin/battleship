package com.academy.solid.nie.client.output;

import java.io.*;
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
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),
                "UTF-8")))) {
            out.println(msg);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

}
