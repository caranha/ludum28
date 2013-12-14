package org.castelodelego.ludum28.screens;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;

import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.input.KeyboardGameController;
import org.castelodelego.ludum28.input.MouseGameController;

public class MainScreen implements Screen, InputProcessor {

	boolean gotogame = false;
	
	public MainScreen()
	{
		
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		Globals.batch.begin();
		Globals.debugtext.setColor(Color.YELLOW);
		Globals.debugtext.draw(Globals.batch, "Click Mouse to Begin with Mouse Controls",100, Constants.SCREEN_H-80);
		Globals.debugtext.draw(Globals.batch,"Press Space to begin with Keyboard Controls", 100, Constants.SCREEN_H-40);		
		Globals.batch.end();
		
		if (gotogame)
		{
			((GameScreen) Ludum28.gameScreen).reset();
			((Game) Gdx.app.getApplicationListener()).setScreen(Ludum28.gameScreen);
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
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

	/** INPUT PROCESSOR METHODS **/
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Globals.gamecontroller = new GestureDetector(new MouseGameController());
		gotogame = true;
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		if (character == ' ')
		{
			Globals.gamecontroller = new KeyboardGameController();
			gotogame = true;
			return true;
		}
		return false;
	}

	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}