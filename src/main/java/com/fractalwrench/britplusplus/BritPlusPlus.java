package com.fractalwrench.britplusplus;

import java.io.*;

/**
 * Compiles BritPlusPlus source code to Java ByteCode.
 */
public class BritPlusPlus {

    public static void main(String[] args) {
        String fileName = parseSourceFileName(args);
        String sourceCode = fetchSourceCode(fileName);
    }

    static String parseSourceFileName(String[] args) {
        if (args.length != 1) {
            String msg = String.format("Expected 1 argument, received %d. \n\nCorrect Usage: bpp <file>", args.length);
            throw new IllegalArgumentException(msg);
        }
        String filename = args[0];

        if (filename == null) {
            throw new IllegalArgumentException("Source file argument cannot be null!");
        }
        return filename;
    }

    static String fetchSourceCode(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("Cannot read filename 'null'!");
        }
        File file = new File(fileName);

        if (!file.exists()) {
            throw new RuntimeException(String.format("Unable to find file '%s'", fileName));
        }
        if (!file.canRead()) {
            throw new RuntimeException(String.format("Unable to read file '%s'", fileName));
        }
        return new StringFileReader().readFileContents(file);
    }

}
