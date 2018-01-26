package com.academy.solid.nie.client.output;

import java.io.*;
import java.util.logging.Logger;

/**
 * This class is used for clearing content of file.
 */
public class FileContentCleaner {
    private static final String EMPTY = "";
    private final Logger logger = Logger.getLogger(FileOutputDispatcher.class.getName());

    /**
     * @param fileName name of file to being cleared
     */
    public final void clear(String fileName) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),
                "UTF-8")))) {
            out.println(EMPTY);
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

}
