package com.fractalwrench.bpp.programs;

import org.junit.Test;

public class BppPrint extends BppTestCase {

    @Test
    public void checkHelloWorld() throws Exception {
        runBppSample("Print", "God bless Gutenburg!\n");
    }

}
