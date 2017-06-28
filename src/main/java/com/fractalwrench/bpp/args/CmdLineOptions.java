package com.fractalwrench.bpp.args;

import java.io.File;

public final class CmdLineOptions {

    private final File input;
    private final File output;
    private final String outputClassName;
    private final boolean verbose;
    private final boolean autoRun;

    CmdLineOptions(File input, File output, String outputClassName, boolean verbose, boolean autoRun) {
        this.input = input;
        this.output = output;
        this.outputClassName = outputClassName;
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

    public String getOutputClassName() {
        return outputClassName;
    }

    @Override
    public String toString() {
        return "CmdLineOptions{" +
                "input=" + input +
                ", output=" + output +
                ", outputClassName='" + outputClassName + '\'' +
                ", verbose=" + verbose +
                ", autoRun=" + autoRun +
                '}';
    }
}
