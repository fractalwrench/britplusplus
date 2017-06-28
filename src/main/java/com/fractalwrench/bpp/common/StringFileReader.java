package com.fractalwrench.bpp.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

}
