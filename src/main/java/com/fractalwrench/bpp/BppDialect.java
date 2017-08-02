package com.fractalwrench.bpp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class BppDialect {

    private final Map<String, String> map;
    private final String dialectName;

    BppDialect(Map<String, String> map, String dialectName) {
        this.map = new HashMap<>(map);
        this.dialectName = dialectName;
    }

    Map<String, String> getMap() {
        return Collections.unmodifiableMap(map);
    }

    String getDialectName() {
        return dialectName;
    }

    @Override
    public String toString() {
        return "BppDialect{" +
                "dialectName='" + dialectName + '\'' +
                ", map=" + map +
                '}';
    }
}
