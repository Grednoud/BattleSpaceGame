package ru.codesteps.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.codesteps.BattleSpaceGame;

/**
 * DesktopLauncher is the entry point for the desktop version of BattleSpace game.
 * Uses LWJGL3 backend for modern OpenGL support.
 */
public class DesktopLauncher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) {
            return;
        }
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new BattleSpaceGame(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("BattleSpace");
        config.useVsync(true);
        config.setForegroundFPS(60);
        config.setWindowedMode(480, 640);
        config.setResizable(false);
        config.setWindowIcon("ship_logo.png");
        return config;
    }
}
