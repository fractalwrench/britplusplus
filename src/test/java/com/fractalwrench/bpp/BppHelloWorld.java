package com.fractalwrench.bpp;

import com.fractalwrench.bpp.common.StringFileReader;
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
