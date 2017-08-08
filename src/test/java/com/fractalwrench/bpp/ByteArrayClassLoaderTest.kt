package com.fractalwrench.bpp

import com.fractalwrench.bpp.internal.ast.RootNode
import org.junit.Before
import org.junit.Test

import java.io.File

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class ByteArrayClassLoaderTest {
    private var byteArrayClassLoader: ByteArrayClassLoader? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        byteArrayClassLoader = ByteArrayClassLoader()
    }

    @Test
    @Throws(Exception::class)
    fun loadClassFromByteCode() {
        val dump = RootNode(null, null).generateClass(CLASS_NAME)
        val hello = byteArrayClassLoader!!.loadClassFromByteCode(dump, CLASS_NAME)
        assertNotNull(hello)
        assertEquals(hello.name, CLASS_NAME)
    }

    @Test(expected = RuntimeException::class)
    @Throws(Exception::class)
    fun loadClassFromFile() {
        byteArrayClassLoader!!.loadClassFromFile(File("fake.txt"), CLASS_NAME)
    }

    companion object {

        private val CLASS_NAME = "Hello"
    }
}