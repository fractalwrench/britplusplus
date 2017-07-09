package com.fractalwrench.bpp;

import com.fractalwrench.bpp.internal.ast.RootNode;
import org.parboiled.BaseParser;

public class ByteCodeGenerator extends BaseParser {

    public byte[] generate(String sourceCode, String name) throws Exception {
        // TODO use BppParser!
        return RootNode.dump(name);
    }

}
