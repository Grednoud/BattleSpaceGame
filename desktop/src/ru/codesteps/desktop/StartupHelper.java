package ru.codesteps.desktop;

import com.badlogic.gdx.Gdx;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * StartupHelper handles macOS-specific JVM requirements for LWJGL3.
 * On macOS, LWJGL3 requires the JVM to be started with -XstartOnFirstThread flag.
 */
public class StartupHelper {
    private static final String JVM_RESTARTED_ARG = "jvmIsRestarted";

    private StartupHelper() {
    }

    /**
     * Starts a new JVM if the application was not started with the required flags on macOS.
     * @return true if a new JVM was started (caller should exit), false otherwise
     */
    public static boolean startNewJvmIfRequired() {
        String osName = System.getProperty("os.name", "").toLowerCase();
        if (!osName.contains("mac")) {
            return false;
        }
        
        if ("true".equals(System.getProperty(JVM_RESTARTED_ARG))) {
            return false;
        }

        String javaHome = System.getProperty("java.home");
        String classPath = System.getProperty("java.class.path");
        String javaCommand = javaHome + File.separator + "bin" + File.separator + "java";

        ArrayList<String> command = new ArrayList<>();
        command.add(javaCommand);
        command.add("-XstartOnFirstThread");
        command.add("-D" + JVM_RESTARTED_ARG + "=true");
        command.addAll(ManagementFactory.getRuntimeMXBean().getInputArguments());
        command.add("-cp");
        command.add(classPath);
        command.add(DesktopLauncher.class.getName());

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            
            process.waitFor();
        } catch (Exception e) {
            System.err.println("Failed to restart JVM with -XstartOnFirstThread: " + e.getMessage());
            return false;
        }

        return true;
    }
}
