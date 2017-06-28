package com.fractalwrench.bpp.args;

import com.fractalwrench.bpp.common.FileUtils;
import org.apache.commons.cli.*;

import java.io.File;

public class CmdLineParser {

    private static final String INPUT = "input";
    private static final String VERBOSE = "verbose";
    private static final String DISABLE_AUTORUN = "disable-autorun";

    public CmdLineOptions parseBppOption(String[] args) {
        Options options = getCmdLineOptions();
        CommandLineParser cmdLineParser = new DefaultParser();
        HelpFormatter helpFormatter = new HelpFormatter();

        try {
            CommandLine commandLine = cmdLineParser.parse(options, args);
            String input = commandLine.getOptionValue(INPUT);
            String verbose = commandLine.getOptionValue(VERBOSE);
            String disableAutoRun = commandLine.getOptionValue(DISABLE_AUTORUN);

            // use the input filename as the output class

            FileUtils.validateFile(input);
            File outputFile = new File(input.replaceAll("\\.[^.]*$", ".class"));
            String className = outputFile.getName().replaceAll("\\.class", "");

            return new CmdLineOptions(new File(input), outputFile, className, verbose == null, disableAutoRun == null);
        } catch (ParseException e) {
            helpFormatter.printHelp("BritPlusPlus", options);
            System.out.println(e.getMessage());
            System.exit(-1);
            return null;
        }
    }

    private Options getCmdLineOptions() {
        Option verbose = new Option("v", VERBOSE, false, "Enables verbose logging");
        Option disableAutoRun = new Option("d", DISABLE_AUTORUN, false, "Disables automatic running of compiled program");
        Option sourceInput = new Option("i", INPUT, true, "file ");
        sourceInput.setRequired(true);

        Options options = new Options();
        options.addOption(sourceInput);
        options.addOption(verbose);
        options.addOption(disableAutoRun);
        return options;
    }

}
