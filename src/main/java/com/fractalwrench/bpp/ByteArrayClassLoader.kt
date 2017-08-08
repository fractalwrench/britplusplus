package com.fractalwrench.bpp

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class ByteArrayClassLoader : ClassLoader() {

    /**
     * Loads a class from a byte array.

     * @param bytecode the bytecode array
     * *
     * @param name     the class name
     * *
     * @return the loaded class
     */
    @Throws(ClassNotFoundException::class)
    fun loadClassFromByteCode(bytecode: ByteArray, name: String): Class<*> {
        return defineClass(name, bytecode, 0, bytecode.size)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    internal fun loadClassFromFile(file: File, className: String): Class<*> {
        FileUtils.validateFile(file)
        val bytes = Files.readAllBytes(Paths.get(file.toURI()))
        return loadClassFromByteCode(bytes, className)
    }

}
