package org.castelodelego.ludum28.screens;

import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.entities.Player;
import org.castelodelego.ludum28.input.SelectionController;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class SelectionScreen implements Screen {

	SelectionController p = new SelectionController();
	int selectedPlayerMode = 0;
	int selectedDifficulty = 0;
	

	public void setPlayerMode(int i) {
	
		if (Globals.modes[i])
			selectedPlayerMode = i;
		else
			;//TODO: Add error sound when an invalid option is selected;
	}
	
	public void setDifficultyLevel(int i) {
		selectedDifficulty = i;
	}
	
	public void startGame() {
		if (Globals.modes[selectedPlayerMode])
		{
			Player ship = new Player(selectedPlayerMode);
			Globals.currentdifficulty += selectedDifficulty*2;
			((GameScreen)Ludum28.gameScreen).reset(ship, Globals.getCurrentLevel(), Globals.currentdifficulty);
			((Game)Gdx.app.getApplicationListener()).setScreen(Ludum28.gameScreen);
		}
		else
		{
			//TODO: Add error message: invalid player mode selected
		}
	}
	

	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		Globals.batch.begin();
		Globals.debugtext.setColor(Color.YELLOW);
		Globals.debugtext.draw(Globals.batch, "Mode Selected: "+selectedPlayerMode,100, Constants.SCREEN_H-80);
		Globals.debugtext.draw(Globals.batch, "Difficulty Selected: "+selectedDifficulty, 100, Constants.SCREEN_H-40);		
		Globals.batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		selectedPlayerMode = 0;
		selectedDifficulty = 0;
		Gdx.input.setInputProcessor(p);
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
