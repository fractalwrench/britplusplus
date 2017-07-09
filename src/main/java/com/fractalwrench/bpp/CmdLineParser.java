package com.fractalwrench.bpp;

import org.apache.commons.cli.*;

import java.io.File;

class CmdLineParser {

    private static final String INPUT = "input";
    private static final String VERBOSE = "verbose";
    private static final String DISABLE_AUTORUN = "disable-autorun";

    CmdLineOptions parseBppOption(String[] args) throws ParseException {
        Options options = getCmdLineOptions();
        CommandLineParser cmdLineParser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();

        try {
            CommandLine commandLine = cmdLineParser.parse(options, args);
            String input = commandLine.getOptionValue(INPUT);
            boolean verbose = commandLine.hasOption(VERBOSE);
            boolean disableAutoRun = !commandLine.hasOption(DISABLE_AUTORUN);

            // use the input filename as the output class

            FileUtils.validateFile(input);
            File outputFile = new File(input.replaceAll("\\.[^.]*$", ".class"));
            String className = outputFile.getName().replaceAll("\\.class", "");

            return new CmdLineOptions(new File(input), outputFile, className, verbose, disableAutoRun);
        } catch (ParseException e) {
            helpFormatter.printHelp("BritPlusPlus", options);
            throw e;
        }
    }

    private Options getCmdLineOptions() {
        Option verbose = new Option("v", VERBOSE, false, "Enables verbose logging");
        Option disableAutoRun = new Option("d", DISABLE_AUTORUN, false, "Disables automatic running of compiled program");
        Option sourceInput = new Option("i", INPUT, true, "Source File used for compiling the program");
        sourceInput.setRequired(true);

        Options options = new Options();
        options.addOption(sourceInput);
        options.addOption(verbose);
        options.addOption(disableAutoRun);
        return options;
    }

}
