package com.fractalwrench.bpp.internal.ast

import org.objectweb.asm.*


class RootNode(left: AstNode?, right: AstNode?) : AstNode(left, right) {

    @Throws(Exception::class)
    fun generateClass(name: String): ByteArray {
        val cw = ClassWriter(0)

        cw.visit(49,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
                name,
                null,
                "java/lang/Object", null)

        cw.visitSource(name + ".java", null)

        invokeMain(cw)
        visitMethods(cw)
        cw.visitEnd()
        return cw.toByteArray()
    }

    private fun invokeMain(cw: ClassWriter) {
        val mv: MethodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
                "java/lang/Object",
                "<init>",
                "()V",
                false)
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
    }

    private fun visitMethods(cw: ClassWriter) {
        val mv: MethodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V", null, null)


        val blockNode = left() as BlockNode

        for (printNode in blockNode.getPrintNodes()) {
            printNode.generate(mv)
        }

        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(2, 1)
        mv.visitEnd()
    }

    override fun generate(methodVisitor: jdk.internal.org.objectweb.asm.MethodVisitor) {

    }
}