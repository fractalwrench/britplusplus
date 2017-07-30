package com.fractalwrench.bpp.internal.ast;

import org.objectweb.asm.*;


public class RootNode extends AstNode {

    public RootNode(AstNode left, AstNode right) {
        super(left, right);
    }

    public byte[] generateClass(String name) throws Exception {
        ClassWriter cw = new ClassWriter(0);

        cw.visit(49,
                ACC_PUBLIC + ACC_SUPER,
                name,
                null,
                "java/lang/Object",
                null);

        cw.visitSource(name + ".java", null);

        invokeMain(cw);
        visitMethods(cw);
        cw.visitEnd();
        return cw.toByteArray();
    }

    private void invokeMain(ClassWriter cw) {
        MethodVisitor mv;
        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL,
                "java/lang/Object",
                "<init>",
                "()V",
                false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    private void visitMethods(ClassWriter cw) {
        MethodVisitor mv;
        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null);


        BlockNode blockNode = (BlockNode) left();

        for (PrintNode printNode : blockNode.getPrintNodes()) {
            printNode.generate(mv);
        }

        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
    }

    @Override
    public void generate(jdk.internal.org.objectweb.asm.MethodVisitor methodVisitor) {

    }
}