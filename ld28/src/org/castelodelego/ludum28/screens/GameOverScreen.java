package org.castelodelego.ludum28.screens;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameOverScreen implements Screen,InputProcessor {

	boolean getout = false;
	float timeout;
	Sprite backdrop;
	
	@Override
	public void render(float delta) {
		
		timeout -= delta;
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		Globals.batch.begin();
		backdrop.draw(Globals.batch);
		Globals.getScoreFont().setColor(0,0.6f,0,1);
		Globals.getScoreFont().draw(Globals.batch, "YOUR ScORE: "+Globals.score, 65, 270);
		Globals.getScoreFont().draw(Globals.batch, " MAX ScORE: "+Globals.maxscore, 60, 230);
		Globals.batch.end();
		
		if (getout && timeout <= 0)
		{
			((Game) Gdx.app.getApplicationListener()).setScreen(Ludum28.mainScreen);
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Globals.musicbox.stop();
		Gdx.input.setInputProcessor(this);
		getout = false;
		timeout = 1;
		if (backdrop == null)
			backdrop = ((TextureAtlas) Globals.manager.get("sprites/pack.atlas", TextureAtlas.class)).createSprite("gui/GameOver");

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
		getout = true;
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		getout = true;
		return true;
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
