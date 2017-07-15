package com.fractalwrench.bpp.internal.ast;

import org.junit.Test;
import org.parboiled.Rule;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BppParserTest {

    private final BppParser bppParser = new BppParser();

    @Test
    public void testWhitespace() throws Exception {
        String [] valid = {" ", "\n", "\r", "\t"}; // TODO parameterise?
        String [] invalid = {"", "test"}; // TODO parameterise?

        for (String s : valid) {
            assertTrue(checkRuleMatches(bppParser.WhiteSpace(), s));
        }
        for (String s : invalid) {
            assertFalse(checkRuleMatches(bppParser.WhiteSpace(), s));
        }
    }

    private boolean checkRuleMatches(Rule rule, String text) {
        ReportingParseRunner<Object> parseRunner = new ReportingParseRunner<>(rule);
        ParsingResult<Object> result = parseRunner.run(text);
        return result.matched;
    }

}