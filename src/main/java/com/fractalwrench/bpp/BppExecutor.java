package com.fractalwrench.bpp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BppExecutor {

    public void execute(Class<?> clz) { // TODO test me!
        try {
            Method main = clz.getMethod("main", String[].class);
            main.invoke(new Object(), (Object) new String[]{});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to load generated Java bytecode class", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failed to find main(String[]args) in generated Java bytecode class", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke main(String[]args) in generated Java bytecode class", e);
        }
    }

}
