package com.fractalwrench.bpp.internal.ast;

import org.junit.Test;
import org.parboiled.Rule;
import org.parboiled.parserunners.ReportingParseRunner;
import org.parboiled.support.ParsingResult;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class BppParserTest {

    private final BppParser bppParser = new BppParser();

    @Test
    public void testWhitespace() throws Exception {
        String [] valid = {" ", "\n", "\r", "\t"};
        String [] invalid = {"", "test"};
        checkRuleMatches(valid, invalid, bppParser.WhiteSpace());
    }

    @Test
    public void contextSwitch() throws Exception {
        fail();
    }

    @Test
    public void root() throws Exception {
        fail();
    }

    @Test
    public void block() throws Exception {
        fail();
    }

    @Test
    public void print() throws Exception {
        fail();
    }

    @Test
    public void stringLiteral() throws Exception {
        fail();
    }

    @Test
    public void getEscapeCharRules() throws Exception {
        fail();
    }

    private void checkRuleMatches(String[] valid, String invalid[], Rule rule) {
        for (String s : valid) {
            assertTrue(matchesRule(rule, s));
        }
        for (String s : invalid) {
            assertFalse(matchesRule(rule, s));
        }
    }

    private boolean matchesRule(Rule rule, String text) {
        ReportingParseRunner<Object> parseRunner = new ReportingParseRunner<>(rule);
        ParsingResult<Object> result = parseRunner.run(text);
        return result.matched;
    }

}