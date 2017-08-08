package com.fractalwrench.bpp

import java.io.File

internal object FileUtils {

    @Throws(RuntimeException::class)
    fun validateFile(fileName: String): File {
        return validateFile(File(fileName))
    }

    @Throws(RuntimeException::class)
    fun validateFile(file: File?): File {
        if (file == null) {
            throw IllegalArgumentException("Cannot read filename 'null'!")
        }
        if (!file.exists()) {
            throw RuntimeException(String.format("Unable to find file '%s'", file))
        }
        if (!file.canRead()) {
            throw RuntimeException(String.format("Unable to read file '%s'", file))
        }
        return file
    }

}
