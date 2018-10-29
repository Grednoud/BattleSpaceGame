package ru.codesteps.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.codesteps.BattleSpaceGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.resizable = false;
                config.height = 640;
                config.width = 480;
		new LwjglApplication(new BattleSpaceGame(), config);
	}
}
