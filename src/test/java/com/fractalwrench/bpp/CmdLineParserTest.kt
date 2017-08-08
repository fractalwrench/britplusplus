package com.fractalwrench.bpp

import org.apache.commons.cli.ParseException
import org.junit.Before
import org.junit.Test

import java.io.File

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class CmdLineParserTest {

    private var cmdLineParser: CmdLineParser? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        cmdLineParser = CmdLineParser()
    }

    @Test(expected = ParseException::class)
    @Throws(Exception::class)
    fun emptyArgs() {
        cmdLineParser!!.parseBppOption(arrayOf<String>())
    }

    @Test
    @Throws(Exception::class)
    fun validArgs() {
        val file = javaClass.classLoader.getResource("input.txt")!!.file
        val cmdLineOptions = cmdLineParser!!.parseBppOption(arrayOf("-i", file, "-v", "-d"))
        assertNotNull(cmdLineOptions)
        assertEquals(File(file), cmdLineOptions.input)
        assertEquals(true, cmdLineOptions.isVerbose)
        assertEquals(false, cmdLineOptions.isAutoRun)
    }

}