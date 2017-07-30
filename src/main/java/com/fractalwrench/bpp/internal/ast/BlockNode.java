package com.fractalwrench.bpp.internal.ast;

import jdk.internal.org.objectweb.asm.MethodVisitor;

public class BlockNode extends AstNode {

    public BlockNode(AstNode left, AstNode right) {
        super(left, right);
    }

    @Override
    public void generate(MethodVisitor methodVisitor) {

    }
}
