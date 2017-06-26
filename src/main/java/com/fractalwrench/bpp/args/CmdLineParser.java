package com.fractalwrench.bpp.args;

import com.fractalwrench.bpp.common.StringFileReader;
import org.apache.commons.cli.*;

import java.io.File;

public class CmdLineParser {

    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String VERBOSE = "verbose";
    private static final String DISABLE_AUTORUN = "disable-autorun";

    public BppOptions parseBppOption(String[] args, StringFileReader fileReader) {
        Options options = getCmdLineOptions();
        CommandLineParser cmdLineParser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();

        try {
            CommandLine commandLine = cmdLineParser.parse(options, args);
            String input = commandLine.getOptionValue(INPUT);
            String output = commandLine.getOptionValue(OUTPUT);
            String verbose = commandLine.getOptionValue(VERBOSE);
            String disableAutoRun = commandLine.getOptionValue(DISABLE_AUTORUN);

            if (output == null) {
                output = input.replaceAll("\\.[^.]*$", ".class");
            }

            fileReader.validateFile(input);
            fileReader.validateFile(output);
            return new BppOptions(new File(input), new File(output), verbose == null, disableAutoRun == null);
        } catch (ParseException e) {
            helpFormatter.printHelp("BritPlusPlus", options);
            System.out.println(e.getMessage());
            System.exit(-1);
            return null;
        }
    }

    private Options getCmdLineOptions() {
        Option bytecodeOutput = new Option("o", OUTPUT, true, "Output file (do not include extension)");
        Option verbose = new Option("v", VERBOSE, false, "Enables verbose logging");
        Option disableAutoRun = new Option("d", DISABLE_AUTORUN, false, "Disables automatic running of compiled program");

        Option sourceInput = new Option("i", INPUT, true, "file ");
        sourceInput.setRequired(true);

        Options options = new Options();
        options.addOption(sourceInput);
        options.addOption(bytecodeOutput);
        options.addOption(verbose);
        options.addOption(disableAutoRun);
        return options;
    }

}
