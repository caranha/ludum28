package org.castelodelego.ludum28;

import java.util.Random;

import org.castelodelego.ludum28.gamemodel.RandomTimeline;
import org.castelodelego.ludum28.gamemodel.StageTimeline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


/**
 * Contains all the statically accessible global variables.
 * 
 * @author caranha
 *
 */
public class Globals {
		
	
		
	public static AssetManager manager;
	public static AnimationManager animman;
	public static SpriteBatch batch;
	public static OrthographicCamera cam;
	public static SoundServer musicbox;
	public static Random dice;
		
	public static BitmapFont debugtext;
	static BitmapFont scorefont	;

	public static Array<StageTimeline> levels;
	
	
	// Game-based Variables
	
	public static boolean android;
	public static boolean keyboard;

	static int currentlevel = 0;
	
	public static boolean modes[] = {true, true, true, true};
	public static boolean gameover = false;	

	public static int maxscore;	
	public static int score;

	public static int wave;
	public static int difficulty;
	
	
	
	public static void init(boolean andro)
	{
		android = andro;
		
		debugtext = new BitmapFont();

		cam = new OrthographicCamera();
		cam.setToOrtho(false, Constants.SCREEN_W, Constants.SCREEN_H);
		
		batch = new SpriteBatch();
		animman = new AnimationManager();
		manager = new AssetManager();
		
		dice = new Random();
		
		levels = new Array<StageTimeline>();
		//levels.add(new AsteroidTimeline());
		levels.add(new RandomTimeline());
		
		
		maxscore = Gdx.app.getPreferences("DinoRush").getInteger("Maxscore", 0);		
		musicbox = new SoundServer();
	}
	
	/** 
	 * Called when a new game is started from scratch
	 * @param kb
	 */
	public static void startGame(boolean kb)
	{
		keyboard = kb;
		for (int i = 0; i < 4; i++)
			modes[i] = true;
		
		score = 0;
		gameover = false;
		
		wave = 0;
		difficulty = 1;
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
		wave++;
		currentlevel = (currentlevel+1)%levels.size;
	}
	
	/**
	 * Called when the game over screen is invoked
	 */
	public static void endGame()
	{
		if (score > maxscore)
		{
			maxscore = score;
			Gdx.app.getPreferences("DinoRush").putInteger("Maxscore", maxscore);
			Gdx.app.getPreferences("DinoRush").flush();
		}
	}

	public static StageTimeline getCurrentLevel() {
		return levels.get(currentlevel);
	}
	
	static public BitmapFont getScoreFont()
	{
		if (scorefont == null)
			scorefont = manager.get("fonts/score.fnt", BitmapFont.class);
		return scorefont;
	}

	
	
}
