package com.fractalwrench.bpp;

public class ByteArrayClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

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
