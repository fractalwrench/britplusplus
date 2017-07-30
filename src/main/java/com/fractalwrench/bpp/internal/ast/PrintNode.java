package com.fractalwrench.bpp.internal.ast;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class PrintNode extends AstNode implements Opcodes {

    private String input;

    public PrintNode(String input, AstNode left, AstNode right) {
        super(left, right);
        this.input = input;
        this.input = input.substring(1, input.length() - 1); // FIXME hack
    }

    public void generate(MethodVisitor mv) {
        mv.visitFieldInsn(GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;");
        mv.visitLdcInsn(input);
        mv.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V",
                false);
    }

    @Override
    public void generate(jdk.internal.org.objectweb.asm.MethodVisitor methodVisitor) {

    }
}
