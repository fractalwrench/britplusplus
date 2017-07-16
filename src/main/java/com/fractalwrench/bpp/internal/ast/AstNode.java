package com.fractalwrench.bpp.internal.ast;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.parboiled.trees.ImmutableBinaryTreeNode;

public abstract class AstNode extends ImmutableBinaryTreeNode<AstNode> implements Opcodes {

    public AstNode(AstNode left, AstNode right) {
        super(left, right);
    }

    public abstract void generate(MethodVisitor methodVisitor);

}
