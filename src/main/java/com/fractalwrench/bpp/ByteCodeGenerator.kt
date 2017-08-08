package com.fractalwrench.bpp

import com.fractalwrench.bpp.internal.ast.AstNode
import com.fractalwrench.bpp.internal.ast.BppParser
import org.parboiled.BaseParser
import org.parboiled.Parboiled

class ByteCodeGenerator : BaseParser<AstNode>() {

    @Throws(Exception::class)
    fun generate(sourceCode: String, name: String): ByteArray {
        val parser = Parboiled.createParser<BppParser, AstNode>(BppParser::class.java)
        return parser.parse(sourceCode, name)
    }

}
