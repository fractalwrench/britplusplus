package com.fractalwrench.bpp;

import java.io.File;

final class CmdLineOptions {

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

    File getInput() {
        return input;
    }

    File getOutput() {
        return output;
    }

    boolean isVerbose() {
        return verbose;
    }

    boolean isAutoRun() {
        return autoRun;
    }

    String getOutputClassName() {
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
