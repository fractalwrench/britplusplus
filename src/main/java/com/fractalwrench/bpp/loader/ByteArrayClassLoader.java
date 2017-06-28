package com.fractalwrench.bpp.loader;

public class ByteArrayClassLoader extends ClassLoader {


    /**
     * Loads a class from a byte array.
     *
     * @param bytecode the bytecode array
     * @param name     the class name
     * @return the loaded class
     */
    public Class<?> loadClassFromByteCode(byte[] bytecode, String name) {

        return defineClass(name, bytecode, 0, bytecode.length);
    }

}
