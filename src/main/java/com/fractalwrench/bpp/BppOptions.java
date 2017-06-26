package com.fractalwrench.bpp;

import java.io.File;

class BppOptions {

    private static final String INPUT = "input";
    private static final String OUTPUT = "output";
    private static final String VERBOSE = "verbose";
    private static final String DISABLE_AUTORUN = "disable-autorun";

    private final File input;
    private final File output;
    private final boolean verbose;
    private final boolean autoRun;

    public BppOptions(File input, File output, boolean verbose, boolean autoRun) {
        this.input = input;
        this.output = output;
        this.verbose = verbose;
        this.autoRun = autoRun;
    }

    public File getInput() {
        return input;
    }

    public File getOutput() {
        return output;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public boolean isAutoRun() {
        return autoRun;
    }

}
