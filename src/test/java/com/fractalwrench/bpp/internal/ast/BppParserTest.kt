package com.fractalwrench.bpp.internal.ast

import org.junit.Test
import org.parboiled.Rule
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.ParsingResult

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.fail

class BppParserTest {

    private val bppParser = BppParser()

    @Test
    @Throws(Exception::class)
    fun testWhitespace() {
        val valid = arrayOf(" ", "\n", "\r", "\t")
        val invalid = arrayOf("", "test")
        checkRuleMatches(valid, invalid, bppParser.WhiteSpace())
    }

    @Test
    @Throws(Exception::class)
    fun contextSwitch() {
        fail()
    }

    @Test
    @Throws(Exception::class)
    fun root() {
        fail()
    }

    @Test
    @Throws(Exception::class)
    fun block() {
        fail()
    }

    @Test
    @Throws(Exception::class)
    fun print() {
        fail()
    }

    @Test
    @Throws(Exception::class)
    fun stringLiteral() {
        fail()
    }

    @Test
    @Throws(Exception::class)
    fun getEscapeCharRules() {
        fail()
    }

    private fun checkRuleMatches(valid: Array<String>, invalid: Array<String>, rule: Rule) {
        for (s in valid) {
            assertTrue(matchesRule(rule, s))
        }
        for (s in invalid) {
            assertFalse(matchesRule(rule, s))
        }
    }

    private fun matchesRule(rule: Rule, text: String): Boolean {
        val parseRunner = ReportingParseRunner<Any>(rule)
        val result = parseRunner.run(text)
        return result.matched
    }

}