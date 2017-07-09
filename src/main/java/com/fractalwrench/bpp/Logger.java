package com.fractalwrench.bpp;

public final class Logger {

    private static boolean enabled = true;

    private Logger() {
    }

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
