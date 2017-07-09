package com.fractalwrench.bpp;

import com.fractalwrench.bpp.internal.ast.RootNode;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ByteArrayClassLoaderTest {

    private static final String CLASS_NAME = "Hello";
    private ByteArrayClassLoader byteArrayClassLoader;

    @Before
    public void setUp() throws Exception {
        byteArrayClassLoader = new ByteArrayClassLoader();
    }

    @Test
    public void loadClassFromByteCode() throws Exception {
        byte[] dump = RootNode.dump(CLASS_NAME);
        Class<?> hello = byteArrayClassLoader.loadClassFromByteCode(dump, CLASS_NAME);
        assertNotNull(hello);
        assertEquals(hello.getName(), CLASS_NAME);
    }

    @Test(expected = RuntimeException.class)
    public void loadClassFromFile() throws Exception {
        byteArrayClassLoader.loadClassFromFile(new File("fake.txt"), CLASS_NAME);
    }
}