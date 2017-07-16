package com.fractalwrench.bpp.internal.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class PrintNode extends AstNode implements Opcodes {

    private String input;

    public PrintNode(String input, AstNode left, AstNode right) {
        super(left, right);
        this.input = input;
    }

    public void generate(ClassWriter cw) {



        MethodVisitor mv;
        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null);
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
        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
    }

    @Override
    public void generate(jdk.internal.org.objectweb.asm.MethodVisitor methodVisitor) {

    }
}
