package com.fractalwrench.bpp

import org.apache.commons.cli.*

import java.io.File

internal class CmdLineParser {

    @Throws(ParseException::class)
    fun parseBppOption(args: Array<String>): CmdLineOptions {
        val options = cmdLineOptions
        val cmdLineParser = DefaultParser()
        val helpFormatter = HelpFormatter()

        try {
            val commandLine = cmdLineParser.parse(options, args)
            val input = commandLine.getOptionValue(INPUT)
            val verbose = commandLine.hasOption(VERBOSE)
            val disableAutoRun = !commandLine.hasOption(DISABLE_AUTORUN)

            // use the input filename as the output class

            FileUtils.validateFile(input)
            val outputFile = File(input.replace("\\.[^.]*$".toRegex(), ".class"))
            val className = outputFile.name.replace("\\.class".toRegex(), "")

            return CmdLineOptions(File(input), outputFile, className, verbose, disableAutoRun)
        } catch (e: ParseException) {
            helpFormatter.printHelp("BritPlusPlus", options)
            throw e
        }

    }

    private val cmdLineOptions: Options
        get() {
            val verbose = Option("v", VERBOSE, false, "Enables verbose logging")
            val disableAutoRun = Option("d", DISABLE_AUTORUN, false, "Disables automatic running of compiled program")
            val sourceInput = Option("i", INPUT, true, "Source File used for compiling the program")
            sourceInput.isRequired = true

            val options = Options()
            options.addOption(sourceInput)
            options.addOption(verbose)
            options.addOption(disableAutoRun)
            return options
        }

    companion object {

        private val INPUT = "input"
        private val VERBOSE = "verbose"
        private val DISABLE_AUTORUN = "disable-autorun"
    }

}
