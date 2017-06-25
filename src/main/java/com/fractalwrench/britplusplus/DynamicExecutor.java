package com.fractalwrench.britplusplus;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Loads a generated class file and invokes its main method.
 */
class DynamicExecutor {

    private final File file;

    DynamicExecutor(File file) {
        this.file = file;
    }

    void execute() {
        try {
            URL url = file.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            Class<?> hello = classLoader.loadClass("Hello");

            Method[] methods = hello.getMethods();
            Method main = hello.getMethod("main", String[].class);
            main.invoke(new Object(), (Object) new String[]{});
            main.toString();

        } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
