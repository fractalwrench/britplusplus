package com.fractalwrench.bpp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Loads a generated class file and invokes its main method.
 */
class DynamicClassExecutor {

    private final File file;
    private final String className;
    private final BppExecutor bppExecutor;

    DynamicClassExecutor(File file, String className, BppExecutor bppExecutor) {
        this.file = file;
        this.bppExecutor = bppExecutor;
        this.className = "Hello";// className; // FIXME
    }

    void execute() throws Exception {
        try {
            URLClassLoader classLoader = getFileClassLoader();
            Class<?> aClass = classLoader.loadClass(className);
            bppExecutor.execute(aClass);
        } catch (MalformedURLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load generated Java bytecode class", e);
        }
    }

    private URLClassLoader getFileClassLoader() throws IOException {
        if (!file.exists() || !file.canRead()) {
            throw new IOException(String.format("Cannot find generated Java bytecode file '%s'", file));
        }
        URL url = file.toURI().toURL();
        return new URLClassLoader(new URL[]{url});
    }

}
