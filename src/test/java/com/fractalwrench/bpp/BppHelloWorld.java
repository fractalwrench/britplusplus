package com.fractalwrench.bpp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BppHelloWorld {

    @Test
    public void checkHelloWorld() throws Exception {
        String[] args = {"HelloWorld.bpp"};
        String helloWorld = BppRunner.run(args);
        assertEquals("Hello, World!", helloWorld);
    }

}
