package com.fractalwrench.bpp;

import com.fractalwrench.bpp.ast.ByteCodeGenerator;
import com.fractalwrench.bpp.common.StringFileReader;
import com.fractalwrench.bpp.executor.BppExecutor;
import com.fractalwrench.bpp.executor.ByteArrayClassLoader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BppHelloWorld {

    @Test
    public void checkHelloWorld() throws Exception {
        BppRunner runner = new BppRunner(new ByteArrayClassLoader(), new ByteCodeGenerator(), new StringFileReader(), new BppExecutor());
        String helloWorld = runner.run("samples/HelloWorld.bpp", "HelloWorld");
        String expected = "Hello, World!\n";
        assertEquals(expected, helloWorld);
    }

}
