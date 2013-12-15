package org.castelodelego.ludum28.input;

import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.screens.SelectionScreen;


import com.badlogic.gdx.InputProcessor;

/**
 * Controls the input for the PowerUp/Difficulty selector
 * 
 * @author caranha
 *
 */
public class SelectionController implements InputProcessor {


	@Override
	public boolean keyTyped(char character) {
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

		for (int i = 0; i < 4; i++)
			if (SelectionScreen.modeRect[i].contains(screenX, Constants.SCREEN_H-screenY))
			{
				((SelectionScreen) Ludum28.selectionScreen).setPlayerMode(i);
				return true;
			}
		
		for (int i = 0; i < 3; i++)
			if (SelectionScreen.diffRect[i].contains(screenX, Constants.SCREEN_H-screenY))
			{
				((SelectionScreen) Ludum28.selectionScreen).setDifficultyLevel(i);
				return true;
			}
		if (SelectionScreen.startRect.contains(screenX, Constants.SCREEN_H-screenY))
		{
			((SelectionScreen) Ludum28.selectionScreen).startGame();
			return true;
		}
		
		// TODO Auto-generated method stub
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
