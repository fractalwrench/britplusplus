package com.fractalwrench.bpp.internal.ast

import jdk.internal.org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.parboiled.trees.ImmutableBinaryTreeNode

abstract class AstNode(left: AstNode?, right: AstNode?) : ImmutableBinaryTreeNode<AstNode>(left, right), Opcodes {

    abstract fun generate(methodVisitor: MethodVisitor)

}
