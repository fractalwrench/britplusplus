package com.fractalwrench.bpp;

import com.fractalwrench.bpp.args.CmdLineOptions;
import com.fractalwrench.bpp.args.CmdLineParser;
import com.fractalwrench.bpp.ast.ByteCodeGenerator;
import com.fractalwrench.bpp.common.Logger;
import com.fractalwrench.bpp.common.StringFileReader;
import com.fractalwrench.bpp.executor.BppExecutor;
import com.fractalwrench.bpp.executor.ByteArrayClassLoader;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileOutputStream;

class BppApp {

    private final StringFileReader stringFileReader = new StringFileReader();
    private final ByteCodeGenerator generator = new ByteCodeGenerator();
    private final BppExecutor executor = new BppExecutor();
    private final CmdLineParser cmdLineParser = new CmdLineParser();
    private final ByteArrayClassLoader byteArrayClassLoader = new ByteArrayClassLoader();

    void run(String[] args) throws Exception {
        CmdLineOptions cmdLineOptions = null;

        try {
            cmdLineOptions = cmdLineParser.parseBppOption(args);
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        Logger.setEnabled(cmdLineOptions.isVerbose());

        String sourceCode = stringFileReader.readFileContents(cmdLineOptions.getInput());
        String className = cmdLineOptions.getOutputClassName();
        byte[] byteCode = generator.generate(sourceCode, className);

        File outputFile = cmdLineOptions.getOutput();
        writeByteCodeToFile(byteCode, outputFile);
        Class<?> clz = byteArrayClassLoader.loadClassFromFile(outputFile, className);
        executor.execute(clz, args);
    }

    private void writeByteCodeToFile(byte[] byteCode, File file) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(byteCode);
            fos.flush();
        } catch (Exception e) {
            throw new RuntimeException("Failed to write generated bytecode", e);
        }
    }
}
