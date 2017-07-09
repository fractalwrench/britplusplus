package com.fractalwrench.bpp.programs;

import org.junit.Test;

public class BppBranching extends BppTestCase {

    @Test
    public void checkHelloWorld() throws Exception {
        runBppSample("Branching", "One\nTwo\n");
    }

}
