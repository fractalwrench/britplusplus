package com.fractalwrench.bpp.programs;

import org.junit.Test;

public class BppEchoArgs extends BppTestCase {

    @Test
    public void checkEchoArgs() throws Exception {
        runBppSample("EchoArgs", new String[]{"Echo!"}, "Echo!\n");
    }

}
