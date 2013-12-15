package org.castelodelego.ludum28.screens;

import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.entities.Player;
import org.castelodelego.ludum28.input.SelectionController;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class SelectionScreen implements Screen {

	// Mouse Hotspots
	public static Rectangle[] modeRect = {new Rectangle(420,240,170,115), new Rectangle(610,240,170,115), new Rectangle(420,120,170,115), new Rectangle(610,120,170,115)};
	public static Rectangle[] diffRect = {new Rectangle(410,365,120,100), new Rectangle(540,365,120,100), new Rectangle(670,365,120,100)};
	public static Rectangle startRect = new Rectangle(20,320,360,140);	
	
	// Internal variables
	SelectionController p = new SelectionController();
	int selectedPlayerMode = 0;
	int selectedDifficulty = 1;
	
	Sprite difficultyGUI[] = new Sprite[3];
	Sprite modeGUI[] = new Sprite[4];
	Sprite instructions;
	Sprite pressStart;
	
	private float animtimer;
	private Animation[] modeanim;
	
	public void setPlayerMode(int i) {
	
		if (Globals.modes[i])
		{
			(Globals.manager.get("sfx/goodchoice.ogg", Sound.class)).play();	
			selectedPlayerMode = i;
		}
		else
			(Globals.manager.get("sfx/badchoice.ogg", Sound.class)).play();		
	}
	
	public void setDifficultyLevel(int i) {
		selectedDifficulty = i;
		(Globals.manager.get("sfx/goodchoice.ogg", Sound.class)).play();
	}
	
	public void startGame() {
		if (Globals.modes[selectedPlayerMode])
		{
			Player ship = new Player(selectedPlayerMode);
			(Globals.manager.get("sfx/roar.ogg", Sound.class)).play();
			((GameScreen)Ludum28.gameScreen).reset(ship, Globals.getCurrentLevel(), Globals.getCurrentDifficulty(selectedDifficulty*4));
			((Game)Gdx.app.getApplicationListener()).setScreen(Ludum28.gameScreen);
		}
	}
	

	
	@Override
	public void render(float delta) {
		animtimer += delta;
		Gdx.gl.glClearColor(0.4f, 0.8f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		Globals.batch.begin();
		for (int i = 0; i < 3; i++)
		{
			if (i == selectedDifficulty)
				difficultyGUI[i].setColor(Color.RED);
			else
				difficultyGUI[i].setColor(1,1,1,0.7f);
			difficultyGUI[i].draw(Globals.batch);
		}
		
		for (int i = 0; i < 4; i++)
		{
			if (i == selectedPlayerMode)
				modeGUI[i].setColor(Color.RED);
			else if (Globals.modes[i])
				modeGUI[i].setColor(1,1,1,0.7f);
			else
				modeGUI[i].setColor(Color.GRAY);
			modeGUI[i].draw(Globals.batch);
		}
		
		instructions.setColor(Color.RED);
		instructions.draw(Globals.batch);
		
		pressStart.setColor(((float)Math.sin(animtimer)+1)/2f,((float)Math.sin(animtimer/7)+1)/2f,((float)Math.sin(animtimer/4)+1)/2f,1);
		pressStart.draw(Globals.batch);
		
		Globals.batch.draw(modeanim[selectedPlayerMode].getKeyFrame(animtimer), 30, 20,350,300);

		
		if (Gdx.app.getLogLevel() != Application.LOG_NONE)
		{
			Globals.debugtext.setColor(Color.YELLOW);
			Globals.debugtext.draw(Globals.batch, "Difficulty Selected: "+selectedDifficulty, 100, Constants.SCREEN_H-40);		
			Globals.debugtext.draw(Globals.batch, "Mode Selected: "+(selectedPlayerMode+1),100, Constants.SCREEN_H-80);	
			String modes = "Modes Available:";
			for (int i = 0; i < 4; i++)
				if (Globals.modes[i])
					modes += " "+(i+1);		
			Globals.debugtext.draw(Globals.batch, modes, 130, Constants.SCREEN_H-100);
		}
				
		Globals.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {

		animtimer = 0;
		
		if (!Globals.modes[selectedPlayerMode])
			for (int i = 0; i < 4; i++)
				if (Globals.modes[i])
				{
					selectedPlayerMode = i;
					break;
				}
		Gdx.input.setInputProcessor(p);
		
		TextureAtlas atlas = Globals.manager.get("sprites/pack.atlas", TextureAtlas.class);

		if (difficultyGUI[0] == null)
		{
			difficultyGUI[0] = atlas.createSprite("gui/difficultyEasy");
			difficultyGUI[0].setPosition(410, 365);
			difficultyGUI[1] = atlas.createSprite("gui/difficultyNormal");
			difficultyGUI[1].setPosition(540, 365);
			difficultyGUI[2] = atlas.createSprite("gui/difficultyHard");
			difficultyGUI[2].setPosition(670, 365);
			
			modeGUI[0] = atlas.createSprite("gui/buttonWeapon1");
			modeGUI[0].setPosition(420, 240);
			modeGUI[1] = atlas.createSprite("gui/buttonWeapon2");
			modeGUI[1].setPosition(610, 240);
			modeGUI[2] = atlas.createSprite("gui/buttonWeapon3");
			modeGUI[2].setPosition(420, 120);
			modeGUI[3] = atlas.createSprite("gui/buttonWeapon4");
			modeGUI[3].setPosition(610, 120);
			
			if (Globals.keyboard)
			{
				pressStart = atlas.createSprite("gui/startKBD");
				instructions = atlas.createSprite("gui/InstructionsKBD");
			}
			else
			{
				pressStart = atlas.createSprite("gui/startMouse");
				instructions = atlas.createSprite("gui/InstructionsMouse");
			}
			pressStart.setPosition(20, 320);
			instructions.setPosition(420, 5);
			
			modeanim = new Animation[4];
			for (int i = 0; i < 4; i++)
			{
				modeanim[i] = Globals.animman.get("DinoMode"+(i+1));
			}	

		}
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}







}
