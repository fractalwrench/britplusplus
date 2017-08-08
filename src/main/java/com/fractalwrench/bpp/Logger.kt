package com.fractalwrench.bpp

object Logger {

    private var enabled = true

    fun setEnabled(enabled: Boolean) {
        Logger.enabled = enabled
    }

    fun log(msg: String) {
        if (enabled) {
            println(msg)
        }
    }

    fun log(msg: String, throwable: Throwable) {
        if (enabled) {
            println(msg)
            throwable.printStackTrace()
        }
    }
}
