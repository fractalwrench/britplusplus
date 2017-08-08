package com.fractalwrench.bpp.programs

import org.junit.Test

class BppHelloWorld : BppTestCase() {

    @Test
    @Throws(Exception::class)
    fun checkHelloWorld() {
        runBppSample("HelloWorld", "Hello, World!\n")
    }

}
