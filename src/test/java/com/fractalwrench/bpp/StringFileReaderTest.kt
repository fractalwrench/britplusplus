package com.fractalwrench.bpp

import org.junit.Test

import java.io.File
import java.net.URL

import org.junit.Assert.assertEquals

class StringFileReaderTest {

    @Test
    @Throws(Exception::class)
    fun readFileContents() {
        val resource = javaClass.classLoader.getResource("input.txt")
        val file = resource!!.file
        val fileContents = StringFileReader().readFileContents(File(file))
        assertEquals("Test!\n", fileContents)
    }

    @Test(expected = RuntimeException::class)
    @Throws(Exception::class)
    fun checkNoFile() {
        StringFileReader().readFileContents(File("fake.txt"))
    }
}