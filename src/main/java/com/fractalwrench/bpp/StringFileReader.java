package com.fractalwrench.bpp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads a file as a string.
 */
class StringFileReader {

    String readFileContents(File file) {
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

    File validateFile(String fileName) {
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
