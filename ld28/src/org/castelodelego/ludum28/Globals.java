package org.castelodelego.ludum28;

import java.util.Random;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Contains all the statically accessible global variables.
 * 
 * @author caranha
 *
 */
public class Globals {
		
	public static Preferences scoreloader;
	
	public static AssetManager manager;
	public static AnimationManager animman;
	public static SpriteBatch batch;
	public static OrthographicCamera cam;
	public static Random dice;
		
	public static BitmapFont debugtext;
	
	public static InputProcessor gamecontroller;
	
	
	static void init()
	{
		debugtext = new BitmapFont();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, Constants.SCREEN_W, Constants.SCREEN_H);
		
		batch = new SpriteBatch();
		animman = new AnimationManager();
		manager = new AssetManager();
		
		dice = new Random();
	}		
}
