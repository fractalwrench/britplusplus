package com.fractalwrench.bpp;

import com.fractalwrench.bpp.internal.ast.HelloDump;

public class ByteCodeGenerator {

    public byte[] generate(String sourceCode, String name) throws Exception {
        // TODO implement me!
        return HelloDump.dump(name);
    }

}
