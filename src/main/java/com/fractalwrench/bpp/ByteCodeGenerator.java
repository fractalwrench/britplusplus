package com.fractalwrench.bpp;

import com.fractalwrench.bpp.internal.ast.BppParser;
import org.parboiled.BaseParser;
import org.parboiled.Parboiled;

public class ByteCodeGenerator extends BaseParser {

    public byte[] generate(String sourceCode, String name) throws Exception {
        BppParser parser = Parboiled.createParser(BppParser.class);
        return parser.parse(sourceCode, name);
    }

}
