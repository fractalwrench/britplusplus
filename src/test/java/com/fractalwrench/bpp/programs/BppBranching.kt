package com.fractalwrench.bpp.programs

import org.junit.Test

class BppBranching : BppTestCase() {

    @Test
    @Throws(Exception::class)
    fun checkHelloWorld() {
        runBppSample("Branching", "One\nTwo\n")
    }

}
