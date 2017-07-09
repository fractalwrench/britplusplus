package com.fractalwrench.bpp.programs;

import com.fractalwrench.bpp.BppRunner;
import com.fractalwrench.bpp.ByteCodeGenerator;
import com.fractalwrench.bpp.StringFileReader;
import com.fractalwrench.bpp.BppExecutor;
import com.fractalwrench.bpp.ByteArrayClassLoader;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

class BppTestCase {

    private BppRunner runner;

    @Before
    public void setUp() throws Exception {
        runner = new BppRunner(new ByteArrayClassLoader(), new ByteCodeGenerator(), new StringFileReader(), new BppExecutor());
    }

    protected void runBppSample(String sampleName, String expectedOutput) throws Exception {
        runBppSample(sampleName, new String[]{}, expectedOutput);
    }

    protected void runBppSample(String sampleName, String[] args, String expectedOutput) throws Exception {
        String filename = "samples/" + sampleName + ".bpp";
        String observedOutput = runner.run(filename, sampleName, args);
        assertEquals(expectedOutput, observedOutput);
    }

}
