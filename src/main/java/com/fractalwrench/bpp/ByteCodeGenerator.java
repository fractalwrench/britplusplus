package com.fractalwrench.bpp;

import com.fractalwrench.bpp.internal.ast.BppParser;
import org.parboiled.BaseParser;

public class ByteCodeGenerator extends BaseParser {

    private final BppParser bppParser = new BppParser();

    public byte[] generate(String sourceCode, String name) throws Exception {
        return bppParser.parse(sourceCode, name);
    }

}
