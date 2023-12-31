package com.mygdx.juego;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.juego.Principal;

// Please note that on macOS your application 2needs to be started with the -XstartOnFirstThread JVM argument

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
                config.setWindowedMode(700, 700);
                config.setResizable(false);
                config.setWindowIcon("iconobueno.png");
		config.setTitle("El invasor que vino del espacio espacial");
		new Lwjgl3Application(new Principal(), config);
	}
}
