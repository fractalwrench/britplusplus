package com.fractalwrench.bpp.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Reads a file as a string.
 */
public class StringFileReader {

    public String readFileContents(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to read file '%s'", file), e);
        }
    }

    public File validateFile(String fileName) {
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
        return file;
    }

}
