package com.fractalwrench.bpp

import java.io.File

data class CmdLineOptions(val input: File, val output: File, val outputClassName: String, val isVerbose: Boolean, val isAutoRun: Boolean)
