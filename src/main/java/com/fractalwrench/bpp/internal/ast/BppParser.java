package com.fractalwrench.bpp.internal.ast;

import org.parboiled.*;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.errors.ErrorUtils;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;
import org.parboiled.support.ValueStack;
import org.parboiled.transform.BaseAction;

@BuildParseTree
public class BppParser extends BaseParser<AstNode> {

    Rule Root() {
        return ZeroOrMore(
                push(null),
                Block(),
                push(new RootNode(peek(), null)) // add the print node by popping the left
        );
    }

    Rule Block() {
        BlockNode blockNode = new BlockNode(null, null);

        return OneOrMore(
                push(blockNode),
                Print(),
//                push(new BlockNode(pop(), pop())),
                new BaseAction("") {

                    @Override
                    public boolean run(Context context) {
                        ValueStack valueStack = context.getValueStack();
                        PrintNode printNode = (PrintNode) valueStack.pop();
                        blockNode.addPrintNode(printNode);
                        return true;
                    }
                }
        );
    }

    Rule Print() {
        return Sequence(
                "Print",
                OneOrMore(WhiteSpace()),
                Sequence(
                        StringLiteral(),
                        push(new PrintNode(match(), null, null))
                ), OneOrMore(WhiteSpace())
        );
    }

    Rule StringLiteral() {
        return Sequence(
                '"',
                ZeroOrMore(
                        FirstOf(
                                getEscapeCharRules(),
                                Sequence(TestNot(AnyOf("\r\n\"\\")), ANY)
                        )
                ).suppressSubnodes(),
                '"'
        );
    }

    Rule getEscapeCharRules() {
        return Sequence('\\', AnyOf("\"\'\\"));
    }

    Rule WhiteSpace() {
        return AnyOf(" \n\t\r");
    }

    public byte[] parse(String program, String name) throws Exception {
        Rule rule = Root();
        ReportingParseRunner<AstNode> parseRunner = new ReportingParseRunner<>(rule);
        ParsingResult<AstNode> result = parseRunner.run(program);

        if (result.hasErrors() || !result.matched) {
            ErrorUtils.printParseErrors(result);
            throw new BppParseException();
        }

//        Node<AstNode> root = result.parseTreeRoot;
        ValueStack<AstNode> valueStack = result.valueStack;
        RootNode rootNode = (RootNode) valueStack.pop();
//        AstNode pop = valueStack.pop();

        // TODO this should return the rootnode which represents an Abstract Syntax Tree.
        // The root node can in turn generate the bytecode using ASM and the visitor pattern.

        return rootNode.generateClass(name);
//        return new RootNode(null, null).generateClass(name, "");
    }


    private static class BppParseException extends Exception {
    }

}
