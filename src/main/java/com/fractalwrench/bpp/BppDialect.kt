package com.fractalwrench.bpp

import java.util.Collections
import java.util.HashMap

internal class BppDialect(map: Map<String, String>, val dialectName: String) {

    private val map: Map<String, String>

    init {
        this.map = HashMap(map)
    }

    fun getMap(): Map<String, String> {
        return Collections.unmodifiableMap(map)
    }
}
