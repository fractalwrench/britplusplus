package com.fractalwrench.bpp

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

class BppExecutor {

    fun execute(clz: Class<*>, args: Array<String>) {
        try {
            val main = clz.getMethod("main", Array<String>::class.java)
            main.invoke(Any(), args as Any)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Failed to load generated Java bytecode class", e)
        } catch (e: NoSuchMethodException) {
            throw RuntimeException("Failed to find main(String[]args) in generated Java bytecode class", e)
        } catch (e: InvocationTargetException) {
            throw RuntimeException("Failed to invoke main(String[]args) in generated Java bytecode class", e)
        }

    }

}
