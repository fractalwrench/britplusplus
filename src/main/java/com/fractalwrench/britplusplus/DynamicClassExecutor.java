package com.fractalwrench.britplusplus;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Loads a generated class file and invokes its main method.
 */
class DynamicClassExecutor {

    private final File file;
    private final String className;

    DynamicClassExecutor(File file, String className) {
        this.file = file;
        this.className = className;
    }

    void execute() throws Exception {
        try {
            if (!file.exists() || !file.canRead()) {
                throw new IOException(String.format("Cannot find generated Java bytecode file '%s'", file));
            }
            URL url = file.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            Class<?> hello = classLoader.loadClass(this.className);

            Method main = hello.getMethod("main", String[].class);
            main.invoke(new Object(), (Object) new String[]{});

        } catch (MalformedURLException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load generated Java bytecode class", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failed to find main(String[]args) in generated Java bytecode class", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke main(String[]args) in generated Java bytecode class", e);
        }
    }
}
