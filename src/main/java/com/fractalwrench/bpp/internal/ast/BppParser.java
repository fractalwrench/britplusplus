package com.fractalwrench.bpp.internal.ast;

import org.parboiled.*;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.errors.ErrorUtils;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;
import org.parboiled.support.ValueStack;
import org.parboiled.trees.ImmutableBinaryTreeNode;

@BuildParseTree
public class BppParser extends BaseParser<BppParser.MyNode> {

    Rule Print() {
        return Sequence(
                "Print",
                OneOrMore(WhiteSpace()),
                OneOrMore(StringLiteral()),
                push(new MyNode(null, null))
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

    Rule Root() {
        return OneOrMore(
                Print()
                );
    }

    public byte[] parse(String program, String name) throws Exception {
        Rule rule = Print();
        ReportingParseRunner<MyNode> parseRunner = new ReportingParseRunner<>(rule);
        ParsingResult<MyNode> result = parseRunner.run(program);

        if (result.hasErrors() || !result.matched) {
            ErrorUtils.printParseErrors(result);
            throw new BppParseException();
        }

        Node<MyNode> root = result.parseTreeRoot;
        ValueStack<MyNode> valueStack = result.valueStack;

        Node<MyNode> printValNode = root.getChildren().get(2);
        int start = printValNode.getStartIndex() + 1; // adjust for quote char
        int end = printValNode.getEndIndex() - 1; // adjust for quote char
        String printVal = program.substring(start, end);

        // TODO this should return the rootnode which represents an Abstract Syntax Tree.
        // The root node can in turn generate the bytecode using ASM and the visitor pattern.

        return RootNode.dump(name, printVal);
    }


    private static class BppParseException extends Exception {
    }

    public static class MyNode extends ImmutableBinaryTreeNode<MyNode> {

        public MyNode(MyNode left, MyNode right) {
            super(left, right);
        }
    }
}
