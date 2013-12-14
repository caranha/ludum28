package org.castelodelego.ludum28;

import org.castelodelego.ludum28.screens.GameOverScreen;
import org.castelodelego.ludum28.screens.GameScreen;
import org.castelodelego.ludum28.screens.MainScreen;
import org.castelodelego.ludum28.screens.SelectionScreen;
import org.castelodelego.ludum28.screens.SplashScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Ludum28 extends Game {

	public static Screen splashScreen;
	public static Screen mainScreen;
	public static Screen selectionScreen;
	public static Screen gameScreen;
	
	public static Screen gameOverScreen;
	public static Screen aboutScreen;

	
	@Override
	public void create() {	
		
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		Globals.init(); // TODO: Send an instance of myself to "globals"
		
		// Creating global resource managers
		queueAssets();
		
		splashScreen = new SplashScreen();
		mainScreen = new MainScreen();
		gameScreen = new GameScreen();
		selectionScreen = new SelectionScreen();
		gameOverScreen = new GameOverScreen();
		
		setScreen(splashScreen);
		
	}

	/**
	 * Add all assets for loading here.
	 * 
	 */
	private void queueAssets()
	{
		Globals.manager.load("sprites/pack.atlas", TextureAtlas.class); // packed images
		
		// adding sound
		Globals.manager.load("sfx/pew.ogg", Sound.class);
		Globals.manager.load("sfx/roar.ogg", Sound.class);
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void render() {		
		
		super.render();
		
		// Rendering here renders above everything else
		// Good for rendering debug info
		
		// Uncomment for FPS
		Globals.batch.begin();
		Globals.debugtext.setColor(Color.YELLOW);
		Globals.debugtext.draw(Globals.batch, "FPS: "+Gdx.graphics.getFramesPerSecond(), 0, Constants.SCREEN_H);		
		Globals.debugtext.draw(Globals.batch, "Score: "+Globals.score, Constants.SCREEN_W-100, Constants.SCREEN_H);
		Globals.debugtext.draw(Globals.batch, "MaxScore: "+Globals.maxscore, Constants.SCREEN_W-100, Constants.SCREEN_H-20);
		Globals.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
