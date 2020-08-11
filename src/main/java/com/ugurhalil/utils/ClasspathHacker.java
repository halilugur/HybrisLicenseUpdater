package com.ugurhalil.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author halilugur
 */
public final class ClasspathHacker {
    /**
     * Hacks the system classloader to add a classpath entry at runtime.<br /><br />
     *
     * <b>Example</b><br /><br />
     * {@code ClasspathHacker.addToClasspath(new File('example.jar'));}<br />
     * {@code ClassInExampleJar.doStuff();}
     *
     * @param path The jar files to add to the classpath
     */
    public static void addToClasspath(String path) {
        List<File> jars = Arrays.stream(Objects.requireNonNull(new File(path).listFiles()))
                .filter(file -> file.getName().endsWith(".jar"))
                .collect(Collectors.toList());
        try {
            for (File jar : jars) {
                URL url = jar.toURI().toURL();

                URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
                Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                method.setAccessible(true);
                method.invoke(classLoader, url);
            }

        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception", e);
        }
    }
}