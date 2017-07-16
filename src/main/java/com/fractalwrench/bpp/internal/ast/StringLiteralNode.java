package com.fractalwrench.bpp.internal.ast;

import org.objectweb.asm.Opcodes;


public class StringLiteralNode extends AstNode implements Opcodes {

    private String input;

    public StringLiteralNode(String input, AstNode left, AstNode right) {
        super(left, right);
        this.input = input;
    }

    @Override
    public void generate(jdk.internal.org.objectweb.asm.MethodVisitor methodVisitor) {

    }
}
