package com.fractalwrench.bpp.internal.ast

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


class PrintNode(input: String, left: AstNode?, right: AstNode?) : AstNode(left, right), Opcodes {

    private var input: String? = null

    init {
        this.input = input
        this.input = input.substring(1, input.length - 1) // FIXME hack
    }

    fun generate(mv: MethodVisitor) {
        mv.visitFieldInsn(Opcodes.GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;")
        mv.visitLdcInsn(input)
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V",
                false)
    }

    override fun generate(methodVisitor: jdk.internal.org.objectweb.asm.MethodVisitor) {

    }
}
