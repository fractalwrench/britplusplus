package com.fractalwrench.bpp.common;

import java.io.File;

public final class FileUtils {

    private FileUtils() {
    }

    public static File validateFile(String fileName) throws RuntimeException {
        return validateFile(new File(fileName));
    }

    public static File validateFile(File file) throws RuntimeException {
        if (file == null) {
            throw new IllegalArgumentException("Cannot read filename 'null'!");
        }
        if (!file.exists()) {
            throw new RuntimeException(String.format("Unable to find file '%s'", file));
        }
        if (!file.canRead()) {
            throw new RuntimeException(String.format("Unable to read file '%s'", file));
        }
        return file;
    }

}
