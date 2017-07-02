package com.fractalwrench.bpp.common;

import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class StringFileReaderTest {

    @Test
    public void readFileContents() throws Exception {
        URL resource = getClass().getClassLoader().getResource("input.txt");
        String file = resource.getFile();
        String fileContents = new StringFileReader().readFileContents(new File(file));
        assertEquals("Test!", fileContents);
    }

    @Test(expected = RuntimeException.class)
    public void checkNoFile() throws Exception {
        new StringFileReader().readFileContents(new File("fake.txt"));
    }
}