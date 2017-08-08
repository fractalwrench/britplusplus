package com.fractalwrench.bpp.internal.ast

import org.parboiled.*
import org.parboiled.annotations.BuildParseTree
import org.parboiled.errors.ErrorUtils
import org.parboiled.matchers.ZeroOrMoreMatcher
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.ParsingResult
import org.parboiled.support.ValueStack
import org.parboiled.transform.BaseAction

import java.util.ArrayList
import java.util.Arrays

@BuildParseTree
open class BppParser : BaseParser<AstNode?>() {

    internal open fun ContextSwitch(): Rule {
        return OneOrMore(
                String("Begin Dialect"),
                ContextSwitchAction()
        )
    }

    internal fun ContextSwitchAction(): BaseAction {
        return object : BaseAction("") {
            override fun run(context: Context<*>): Boolean {
                val match = match()
                // TODO change keywords by looking up map!!!
                return true
            }
        }
    }

    internal open fun Root(): Rule {
        return ZeroOrMore(
                push(null),
                ContextSwitch(),
                Block(),
                push(RootNode(peek(), null)) // add the print node by popping the left
        )
    }

    internal open fun Block(): Rule {
        val blockNode = BlockNode(null, null)

        return OneOrMore(
                push(blockNode),
                Print(),
                //                push(new BlockNode(pop(), pop())),
                object : BaseAction("") {

                    override fun run(context: Context<*>): Boolean {
                        val valueStack = context.valueStack
                        val printNode = valueStack.pop() as PrintNode
                        blockNode.addPrintNode(printNode)
                        return true
                    }
                }
        )
    }

    internal open fun Print(): Rule { // TODO whitespace!
        return Sequence(
                KEYWORD_PRINT,
                OneOrMore(WhiteSpace()),
                Sequence(
                        StringLiteral(),
                        push(PrintNode(match(), null, null))
                ),
                OneOrMore(WhiteSpace())
        )
    }

    internal open fun StringLiteral(): Rule {
        return Sequence(
                '"',
                ZeroOrMore(
                        FirstOf(
                                escapeCharRules,
                                Sequence(TestNot(AnyOf("\r\n\"\\")), BaseParser.ANY)
                        )
                ).suppressSubnodes(),
                '"'
        )
    }

    internal open val escapeCharRules: Rule
        get() = Sequence('\\', AnyOf("\"\'\\"))

    internal open fun WhiteSpace(): Rule {
        return AnyOf(" \n\t\r")
    }

    @Throws(Exception::class)
    fun parse(program: String, name: String): ByteArray {
        val rule = Root()
        val parseRunner = ReportingParseRunner<AstNode>(rule)
        val result = parseRunner.run(program)

        if (result.hasErrors() || !result.matched) {
            ErrorUtils.printParseErrors(result)
            throw BppParseException()
        }

        //        Node<AstNode> root = result.parseTreeRoot;
        val valueStack = result.valueStack
        val rootNode = valueStack.pop() as RootNode
        //        AstNode pop = valueStack.pop();

        // TODO this should return the rootnode which represents an Abstract Syntax Tree.
        // The root node can in turn generate the bytecode using ASM and the visitor pattern.

        return rootNode.generateClass(name)
        //        return new RootNode(null, null).generateClass(name, "");
    }


    private class BppParseException : Exception()

    companion object {

        private val KEYWORD_PRINT = "Print"
    }

}
