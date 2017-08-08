package com.fractalwrench.bpp.internal.ast

import org.objectweb.asm.Opcodes


class StringLiteralNode(private val input: String, left: AstNode?, right: AstNode?) : AstNode(left, right), Opcodes {

    override fun generate(methodVisitor: jdk.internal.org.objectweb.asm.MethodVisitor) {

    }
}
