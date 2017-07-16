package com.fractalwrench.bpp.internal.ast;

import org.objectweb.asm.*;


/**
 * The ASMified implementation of Hello World. {@link #dump(String, String)} generates the java bytecodes into a byte array.
 */
public class RootNode extends AstNode {

    public RootNode(AstNode left, AstNode right) {
        super(left, right);
    }

    public byte[] generateClass(String name, String printVal) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(49,
                ACC_PUBLIC + ACC_SUPER,
                name,
                null,
                "java/lang/Object",
                null);

        cw.visitSource(name + ".java", null);

        invokeMain(cw);
        visitMethods(printVal, cw);
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

    private void visitMethods(String printVal, ClassWriter cw) {

        PrintNode printNode = (PrintNode) left();
        printNode.generate(cw);

//        MethodVisitor mv;
//        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC,
//                "main",
//                "([Ljava/lang/String;)V",
//                null,
//                null);
//        mv.visitFieldInsn(GETSTATIC,
//                "java/lang/System",
//                "out",
//                "Ljava/io/PrintStream;");
//        mv.visitLdcInsn(printVal);
//        mv.visitMethodInsn(INVOKEVIRTUAL,
//                "java/io/PrintStream",
//                "println",
//                "(Ljava/lang/String;)V",
//                false);
//        mv.visitInsn(RETURN);
//        mv.visitMaxs(2, 1);
//        mv.visitEnd();
    }

    @Override
    public void generate(jdk.internal.org.objectweb.asm.MethodVisitor methodVisitor) {

    }
}