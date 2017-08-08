package com.fractalwrench.bpp.internal.ast

import jdk.internal.org.objectweb.asm.MethodVisitor

import java.util.ArrayList

class BlockNode(left: AstNode?, right: AstNode?) : AstNode(left, right) {

    private val printNodes = ArrayList<PrintNode>()

    override fun generate(methodVisitor: MethodVisitor) {

    }

    fun addPrintNode(node: PrintNode) {
        printNodes.add(node)
    }

    fun getPrintNodes(): List<PrintNode> {
        return printNodes
    }
}
