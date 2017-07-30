package com.fractalwrench.bpp.internal.ast;

import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends AstNode {

    private final List<PrintNode> printNodes = new ArrayList<>();

    public BlockNode(AstNode left, AstNode right) {
        super(left, right);
    }

    @Override
    public void generate(MethodVisitor methodVisitor) {

    }

    public void addPrintNode(PrintNode node) {
        printNodes.add(node);
    }
}
