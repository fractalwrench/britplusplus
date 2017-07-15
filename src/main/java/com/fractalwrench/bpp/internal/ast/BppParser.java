package com.fractalwrench.bpp.internal.ast;

import org.parboiled.BaseParser;
import org.parboiled.Node;
import org.parboiled.Rule;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;

public class BppParser extends BaseParser {

    public Rule Print() {
        return Sequence("Print", OneOrMore(WhiteSpace()),  OneOrMore(StringLiteral()));
    }

    public Rule StringLiteral() {
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

    public Rule getEscapeCharRules() {
        return Sequence('\\', AnyOf("\"\'\\"));
    }

    public Rule WhiteSpace() {
        return AnyOf(" \n\t\r");
    }

    public byte[] parse(String program, String name) throws Exception {
        Rule rule = Print();
        ReportingParseRunner<Object> parseRunner = new ReportingParseRunner<>(rule);
        ParsingResult<Object> result = parseRunner.run(program);

        if (!result.matched) { // TODO test!
            throw new BppParseException();
        }

        Node<Object> root = result.parseTreeRoot;

        Node<Object> printValNode = root.getChildren().get(2);
        int start = printValNode.getStartIndex() + 1; // adjust for quote char
        int end = printValNode.getEndIndex() - 1; // adjust for quote char
        String printVal = program.substring(start, end);

        // TODO this should return the rootnode which represents an Abstract Syntax Tree.
        // The root node can in turn generate the bytecode using ASM and the visitor pattern.

        return RootNode.dump(name, printVal);
    }

    private static class BppParseException extends Exception {

    }



}
