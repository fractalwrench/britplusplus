package com.fractalwrench.bpp.loader;


import com.fractalwrench.bpp.common.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Loads a generated class file and invokes its main method.
 */
public class FileClassLoader extends ClassLoader {

    public Class<?> loadClassFromFile(File file, String className) throws IOException, ClassNotFoundException {
        FileUtils.validateFile(file);
        URL url = file.toURI().toURL();
        URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
        return classLoader.loadClass(className);
    }

}
