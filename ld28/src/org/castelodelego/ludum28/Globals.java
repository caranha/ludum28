package org.castelodelego.ludum28;

import java.util.Random;

import org.castelodelego.ludum28.gamemodel.RandomTimeline;
import org.castelodelego.ludum28.gamemodel.StageTimeline;
import org.castelodelego.ludum28.input.KeyboardGameController;
import org.castelodelego.ludum28.input.MouseGameController;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Array;


/**
 * Contains all the statically accessible global variables.
 * 
 * @author caranha
 *
 */
public class Globals {
		
	
	
	public static Preferences scoreloader;
	public static int maxscore;
		
	public static AssetManager manager;
	public static AnimationManager animman;
	public static SpriteBatch batch;
	public static OrthographicCamera cam;
	public static Random dice;
		
	public static BitmapFont debugtext;

	public static Array<StageTimeline> levels;
	
	
	// Game-based Variables
	static InputProcessor gamecontroller;
	static int currentlevel = 0;
	
	public static boolean keyboard;
	public static boolean modes[] = {true, true, true, true};
	public static boolean gameover = false;	
	

	public static int currentdifficulty = 0;
	public static int score;
	// Game-based Variables\
		
	public static void init()
	{
		debugtext = new BitmapFont();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, Constants.SCREEN_W, Constants.SCREEN_H);
		
		batch = new SpriteBatch();
		animman = new AnimationManager();
		manager = new AssetManager();
		
		dice = new Random();
		
		levels = new Array<StageTimeline>();
		levels.add(new RandomTimeline());
	}
	
	/** 
	 * Called when a new game is started from scratch
	 * @param kb
	 */
	public static void startGame(boolean kb)
	{
		keyboard = kb;
		if (keyboard)
			Globals.gamecontroller = new KeyboardGameController();
		else	
			gamecontroller = new GestureDetector(new MouseGameController());
		for (int i = 0; i < 4; i++)
			modes[i] = true;
		
		score = 0;
		gameover = false;
		
		currentdifficulty = 0;
		currentlevel = 0;
		
	}
	
	public static void playerDies(int type)
	{
		modes[type] = false;
		gameover = true;
		for (int i = 0; i < 4; i++)
		{
			gameover = gameover && !modes[i];
		}
	}
	
	/**
	 * Call this function when the player has defeated 1 stage
	 */
	public static void playerWins()
	{
		currentdifficulty += 1;
		currentlevel = (currentlevel+1)%levels.size;
	}
	
	/**
	 * Called when the game over screen is invoked
	 */
	public static void endGame()
	{
		
	}

	/** Global Getters **/
	public static InputProcessor getGameController() {
		if (keyboard)
			((KeyboardGameController) gamecontroller).reset();
		return gamecontroller;
	}

	public static StageTimeline getCurrentLevel() {
		return levels.get(currentlevel);
	}
	
	
	
}
