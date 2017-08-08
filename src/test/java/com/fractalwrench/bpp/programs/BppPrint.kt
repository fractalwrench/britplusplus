package com.fractalwrench.bpp.programs

import org.junit.Test

class BppPrint : BppTestCase() {

    @Test
    @Throws(Exception::class)
    fun checkHelloWorld() {
        runBppSample("Print", "God bless Gutenburg!\nAnother Line!\nOne more time!\n")
    }
}
