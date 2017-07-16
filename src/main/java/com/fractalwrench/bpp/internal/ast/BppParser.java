package com.fractalwrench.bpp.internal.ast;

import org.parboiled.*;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.errors.ErrorUtils;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;
import org.parboiled.support.ValueStack;

@BuildParseTree
public class BppParser extends BaseParser<AstNode> {

    Rule Root() {
        return ZeroOrMore(
                Print(),
                push(new RootNode(peek(), null)) // add the print node by popping the left
        );
    }

    Rule Print() {
        return Sequence(
                "Print",
                OneOrMore(WhiteSpace()),
                OneOrMore(StringLiteral()),
                new Action<Object>() {
                    @Override
                    public boolean run(Context<Object> context) {
                        ValueStack<Object> valueStack = context.getValueStack();
                        return true;
                    }
                },
                push(new PrintNode(matchOrDefault(""    ), null, null)) // push the print node
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

        Node<AstNode> root = result.parseTreeRoot;
        ValueStack<AstNode> valueStack = result.valueStack;


        RootNode rootNode = (RootNode) valueStack.pop();
        AstNode pop = valueStack.pop();

        // TODO this should return the rootnode which represents an Abstract Syntax Tree.
        // The root node can in turn generate the bytecode using ASM and the visitor pattern.

        return rootNode.generateClass(name, "Hello Test BPP");
//        return new RootNode(null, null).generateClass(name, "");
    }


    private static class BppParseException extends Exception {
    }
    
}
