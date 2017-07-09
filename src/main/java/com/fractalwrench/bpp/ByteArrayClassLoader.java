package com.fractalwrench.bpp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ByteArrayClassLoader extends ClassLoader {

    /**
     * Loads a class from a byte array.
     *
     * @param bytecode the bytecode array
     * @param name     the class name
     * @return the loaded class
     */
    public Class<?> loadClassFromByteCode(byte[] bytecode, String name) throws ClassNotFoundException {
        return defineClass(name, bytecode, 0, bytecode.length);
    }

    Class<?> loadClassFromFile(File file, String className) throws IOException, ClassNotFoundException {
        FileUtils.validateFile(file);
        byte[] bytes = Files.readAllBytes(Paths.get(file.toURI()));
        return loadClassFromByteCode(bytes, className);
    }

}
