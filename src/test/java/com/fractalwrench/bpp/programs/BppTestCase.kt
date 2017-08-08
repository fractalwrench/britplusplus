package com.fractalwrench.bpp.programs

import com.fractalwrench.bpp.BppRunner
import com.fractalwrench.bpp.ByteCodeGenerator
import com.fractalwrench.bpp.StringFileReader
import com.fractalwrench.bpp.BppExecutor
import com.fractalwrench.bpp.ByteArrayClassLoader
import org.junit.Before

import org.junit.Assert.assertEquals

open class BppTestCase {

    private var runner: BppRunner? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        runner = BppRunner(ByteArrayClassLoader(), ByteCodeGenerator(), StringFileReader(), BppExecutor())
    }

    @Throws(Exception::class)
    protected fun runBppSample(sampleName: String, expectedOutput: String) {
        runBppSample(sampleName, arrayOf<String>(), expectedOutput)
    }

    @Throws(Exception::class)
    protected fun runBppSample(sampleName: String, args: Array<String>, expectedOutput: String) {
        val filename = "samples/$sampleName.bpp"
        val observedOutput = runner!!.run(filename, sampleName, args)
        assertEquals(expectedOutput, observedOutput)
    }

}
