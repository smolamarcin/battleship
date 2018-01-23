package com.academy.solid.nie.client.output;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test(groups = "unit")
public class FileOutputDispatcherTest extends FileCommon {
    private static Logger log = Logger.getLogger(FileOutputDispatcher.class.getName());
    private static final String ONE = "1";
    private static OutputStream logCapturingStream;
    private static StreamHandler customLogHandler;

    @AfterTest
    public void tearDown(){
        FileContentCleaner fileContentCleaner = new FileContentCleaner();
        fileContentCleaner.clear(TEST1_TXT);
    }

    public void saveMessageToFile() throws IOException {
        //given
        Output output = new FileOutputDispatcher(TEST1_TXT);

        //when
        output.send("Dominik");
        output.send("Marcin");
        output.send("Marek");

        //then
        String result = String.valueOf(Files.readAllLines(Paths.get(TEST1_TXT)));
        assertTrue(result.contains("Marcin"));
        assertTrue(result.contains("Dominik"));
        assertTrue(result.contains("Marek"));
    }

    public void saveMessageToFileIOException() throws IOException {
        //given
        new File(ONE).mkdir();
        attachLogCapturer();
        Output output = new FileOutputDispatcher(ONE);

        //when
        output.send("Dominik");
        String testCapturedLog = getTestCapturedLog();

        //then
        assertTrue(testCapturedLog.contains("WARNING"));
        }

    private void attachLogCapturer() {
        logCapturingStream = new ByteArrayOutputStream();
        Handler[] handlers = log.getParent().getHandlers();
        customLogHandler = new StreamHandler(logCapturingStream, handlers[0].getFormatter());
        log.addHandler(customLogHandler);
    }

    private String getTestCapturedLog() throws IOException {
        customLogHandler.flush();
        return logCapturingStream.toString();
    }
}
