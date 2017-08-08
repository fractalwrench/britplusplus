package com.fractalwrench.bpp

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

/**
 * Reads a file as a string.
 */
class StringFileReader {

    fun readFileContents(file: File): String {
        try {
            BufferedReader(FileReader(file)).use { reader ->
                val sb = StringBuilder()
                var line = reader.readLine()

                while (line != null) {
                    sb.append(line)
                    sb.append("\n")
                    line = reader.readLine()
                }
                return sb.toString()
            }
        } catch (e: IOException) {
            throw RuntimeException(String.format("Failed to read file '%s'", file), e)
        }

    }

}
