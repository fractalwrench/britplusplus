package com.fractalwrench.bpp;

import org.apache.commons.cli.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CmdLineParserTest {

    private CmdLineParser cmdLineParser;

    @Before
    public void setUp() throws Exception {
        cmdLineParser = new CmdLineParser();
    }

    @Test(expected = ParseException.class)
    public void emptyArgs() throws Exception {
        cmdLineParser.parseBppOption(new String[]{});
    }

    @Test
    public void validArgs() throws Exception {
        String file = getClass().getClassLoader().getResource("input.txt").getFile();
        CmdLineOptions cmdLineOptions = cmdLineParser.parseBppOption(new String[]{"-i", file, "-v", "-d"});
        assertNotNull(cmdLineOptions);
        assertEquals(new File(file), cmdLineOptions.getInput());
        assertEquals(true, cmdLineOptions.isVerbose());
        assertEquals(false, cmdLineOptions.isAutoRun());
    }

}