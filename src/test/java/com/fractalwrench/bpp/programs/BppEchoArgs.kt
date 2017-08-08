package com.fractalwrench.bpp.programs

import org.junit.Test

class BppEchoArgs : BppTestCase() {

    @Test
    @Throws(Exception::class)
    fun checkEchoArgs() {
        runBppSample("EchoArgs", arrayOf("Echo!"), "Echo!\n")
    }

}
