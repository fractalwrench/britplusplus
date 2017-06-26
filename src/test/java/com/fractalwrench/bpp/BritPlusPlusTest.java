package com.fractalwrench.bpp;

import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class BritPlusPlusTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkNullArgs() throws Exception {
        BritPlusPlus.parseSourceFileName(new String[]{null});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyArgs() throws Exception {
        BritPlusPlus.parseSourceFileName(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkTooManyArgs() throws Exception {
        BritPlusPlus.parseSourceFileName(new String[]{"test", "test2"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNullFileRead() throws Exception {
        BritPlusPlus.fetchSourceCode(null);
    }

    @Test(expected = RuntimeException.class)
    public void checkNonExistentFile() throws Exception {
        BritPlusPlus.fetchSourceCode("fake.txt");
    }

    @Test
    public void checkSourceFileRead() throws Exception {
        URL resource = getClass().getClassLoader().getResource("input.txt");
        String file = resource.getFile();
        String sourceCode = BritPlusPlus.fetchSourceCode(file);
        assertEquals("Test!", sourceCode);
    }

}