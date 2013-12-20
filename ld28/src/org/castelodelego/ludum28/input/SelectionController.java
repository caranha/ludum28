package org.castelodelego.ludum28.input;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.screens.SelectionScreen;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Controls the input for the PowerUp/Difficulty selector
 * 
 * @author caranha
 *
 */
public class SelectionController implements InputProcessor {

	Vector2 unprojectCoordinates(float x, float y)
	{
		Vector3 rawtouch = new Vector3(x, y,0);
		Globals.cam.unproject(rawtouch); 
		
		Vector2 ret = new Vector2(rawtouch.x, rawtouch.y);
		return ret;
	}
	

	@Override
	public boolean keyTyped(char character) {
		
		if (character == Input.Keys.F3)
		{
			if (Gdx.app.getLogLevel() == Application.LOG_DEBUG)
				Gdx.app.setLogLevel(Application.LOG_NONE);
			else
				Gdx.app.setLogLevel(Application.LOG_DEBUG);
			return true;
		}
		
		switch(character)
		{
		// MODE SELECTION
		case '1':
			((SelectionScreen) Ludum28.selectionScreen).setPlayerMode(0);
			return true;
		case '2':
			((SelectionScreen) Ludum28.selectionScreen).setPlayerMode(1);
			return true;
		case '3':
			((SelectionScreen) Ludum28.selectionScreen).setPlayerMode(2);
			return true;
		case '4':
			((SelectionScreen) Ludum28.selectionScreen).setPlayerMode(3);
			return true;
		//DIFFICULTY SELECTION	
		case 'h':
		case 'H':
			((SelectionScreen) Ludum28.selectionScreen).setDifficultyLevel(0);
			return true;
		case 'j':
		case 'J':
			((SelectionScreen) Ludum28.selectionScreen).setDifficultyLevel(1);
			return true;
		case 'k':
		case 'K':
			((SelectionScreen) Ludum28.selectionScreen).setDifficultyLevel(2);
			return true;
		// START GAME	
		case ' ':
			((SelectionScreen) Ludum28.selectionScreen).startGame();
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		Vector2 touch = unprojectCoordinates(screenX,screenY);
		
		for (int i = 0; i < 4; i++)
			if (SelectionScreen.modeRect[i].contains(touch))
			{
				((SelectionScreen) Ludum28.selectionScreen).setPlayerMode(i);
				return true;
			}
		
		for (int i = 0; i < 3; i++)
			if (SelectionScreen.diffRect[i].contains(touch))
			{
				((SelectionScreen) Ludum28.selectionScreen).setDifficultyLevel(i);
				return true;
			}
		if (SelectionScreen.startRect.contains(touch))
		{
			((SelectionScreen) Ludum28.selectionScreen).startGame();
			return true;
		}
		return false;
	}

	
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

}
