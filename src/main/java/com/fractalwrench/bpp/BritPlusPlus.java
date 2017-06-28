package com.fractalwrench.bpp;

import com.fractalwrench.bpp.args.BppOptions;
import com.fractalwrench.bpp.args.CmdLineParser;
import com.fractalwrench.bpp.common.Logger;
import com.fractalwrench.bpp.common.StringFileReader;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Compiles BritPlusPlus source code to Java ByteCode.
 */
public class BritPlusPlus {

    public static void main(String[] args) throws Exception {
        StringFileReader stringFileReader = new StringFileReader();
        BppOptions bppOptions = new CmdLineParser().parseBppOption(args, stringFileReader);
        Logger.setEnabled(bppOptions.isVerbose());

        String sourceCode = stringFileReader.readFileContents(bppOptions.getInput());
        ByteCodeGenerator generator = new ByteCodeGenerator();
        File output = bppOptions.getOutput();
        byte[] byteCode = generator.generate(sourceCode, output.getName());

        writeByteCodeToFile(byteCode, output);
        String className = output.getName();
        DynamicClassExecutor dynamicExecutor = new DynamicClassExecutor(new File("."), className, new BppExecutor());
        dynamicExecutor.execute();
    }

    private static void writeByteCodeToFile(byte[] byteCode, File file) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(byteCode);
            fos.flush();
        } catch (Exception e) {
            throw new RuntimeException("Failed to write generated bytecode", e);
        }
    }

}
