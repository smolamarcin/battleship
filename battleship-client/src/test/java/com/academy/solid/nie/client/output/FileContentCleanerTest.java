package com.academy.solid.nie.client.output;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.*;

@Test(groups = "unit")
public class FileContentCleanerTest extends FileCommon{

    public void clearFile() throws IOException {
        //given
        Output output = new FileOutputDispatcher(TEST1_TXT);
        FileContentCleaner cleaner = new FileContentCleaner();

        //when
        output.send("dfdfdf");
        cleaner.clear(TEST1_TXT);

        //then
        String result = String.valueOf(Files.readAllLines(Paths.get(TEST1_TXT)));
        assertFalse(result.contains("dfdfdf"));
    }
}
