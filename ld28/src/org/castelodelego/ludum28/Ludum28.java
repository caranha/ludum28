package org.castelodelego.ludum28;

import org.castelodelego.ludum28.screens.GameOverScreen;
import org.castelodelego.ludum28.screens.GameScreen;
import org.castelodelego.ludum28.screens.MainScreen;
import org.castelodelego.ludum28.screens.SelectionScreen;
import org.castelodelego.ludum28.screens.SplashScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Ludum28 extends Game {

	boolean android;
	
	public static SplashScreen splashScreen;
	public static MainScreen mainScreen;
	public static SelectionScreen selectionScreen;
	public static GameScreen gameScreen;
	
	public static GameOverScreen gameOverScreen;

	
	public Ludum28(boolean andro)
	{
		super();
		android = andro;
	}
	
	
	@Override
	public void create() {	
		
		Gdx.app.setLogLevel(Application.LOG_NONE);
		
		Globals.init(android);

		
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
		Globals.manager.load("sfx/explosion1.ogg", Sound.class);
		Globals.manager.load("sfx/badchoice.ogg", Sound.class);
		Globals.manager.load("sfx/goodchoice.ogg", Sound.class);
		Globals.manager.load("sfx/cannon.ogg", Sound.class);
		Globals.manager.load("sfx/falling.ogg", Sound.class);
		
		// adding music
		Globals.manager.load("music/play1.ogg", Music.class);
		Globals.manager.load("music/play2.ogg", Music.class);
		Globals.manager.load("music/title.ogg", Music.class);
		Globals.manager.load("music/boss1.ogg", Music.class);
		Globals.manager.load("music/boss2.ogg", Music.class);
		
		// adding font
		Globals.manager.load("fonts/score.fnt", BitmapFont.class);
	}
	

	@Override
	public void render() {		
		
		super.render();
		
		// Rendering here renders above everything else
		// Good for rendering debug info
		
		// Uncomment for FPS
		if (Gdx.app.getLogLevel()!=Application.LOG_NONE)
		{
			Globals.batch.begin();
			Globals.debugtext.setColor(Color.YELLOW);
			Globals.debugtext.draw(Globals.batch, "FPS: "+Gdx.graphics.getFramesPerSecond(), 0, Constants.SCREEN_H);		
			Globals.debugtext.draw(Globals.batch, "Score: "+Globals.score, Constants.SCREEN_W-130, Constants.SCREEN_H);
			Globals.debugtext.draw(Globals.batch, "MaxScore: "+Globals.maxscore, Constants.SCREEN_W-130, Constants.SCREEN_H-20);
			Globals.debugtext.draw(Globals.batch, "Wave: "+Globals.wave, Constants.SCREEN_W-130, Constants.SCREEN_H-40);
			Globals.batch.end();
		}
		
		Globals.musicbox.update(Gdx.app.getGraphics().getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
	}

}
