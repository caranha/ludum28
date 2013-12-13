package org.castelodelego.ludum28;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ld28";
		cfg.useGL20 = false;
		cfg.width = Constants.SCREEN_W;
		cfg.height = Constants.SCREEN_H;
		
		new LwjglApplication(new Ludum28(), cfg);
	}
}
