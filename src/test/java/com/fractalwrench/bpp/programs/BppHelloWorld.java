package com.fractalwrench.bpp.programs;

import org.junit.Test;

public class BppHelloWorld extends BppTestCase {

    @Test
    public void checkHelloWorld() throws Exception {
        runBppSample("HelloWorld", "Hello, World!\n");
    }

}
