package com.fractalwrench.bpp.common;

public class Logger {

    private static boolean enabled = true;

    public static void setEnabled(boolean enabled) {
        Logger.enabled = enabled;
    }

    public static void log(String msg) {
        if (enabled) {
            System.out.println(msg);
        }
    }

    public static void log(String msg, Throwable throwable) {
        if (enabled) {
            System.out.println(msg);
            throwable.printStackTrace();
        }
    }
}
