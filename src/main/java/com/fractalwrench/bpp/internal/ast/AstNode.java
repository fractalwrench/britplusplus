package com.fractalwrench.bpp.internal.ast;

import jdk.internal.org.objectweb.asm.MethodVisitor;

public abstract class AstNode {

    public abstract void generate(MethodVisitor methodVisitor);

}
