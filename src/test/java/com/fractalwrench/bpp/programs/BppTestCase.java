package com.fractalwrench.bpp.programs;

import com.fractalwrench.bpp.BppRunner;
import com.fractalwrench.bpp.ast.ByteCodeGenerator;
import com.fractalwrench.bpp.common.StringFileReader;
import com.fractalwrench.bpp.executor.BppExecutor;
import com.fractalwrench.bpp.executor.ByteArrayClassLoader;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

class BppTestCase {

    private BppRunner runner;

    @Before
    public void setUp() throws Exception {
        runner = new BppRunner(new ByteArrayClassLoader(), new ByteCodeGenerator(), new StringFileReader(), new BppExecutor());
    }

    protected void runBppSample(String sampleName, String className, String expectedOutput) throws Exception {
        String observedOutput = runner.run("samples/" + sampleName, className);
        assertEquals(expectedOutput, observedOutput);
    }

}
