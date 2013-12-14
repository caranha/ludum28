package org.castelodelego.ludum28.input;

import org.castelodelego.ludum28.Ludum28;
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
		case 'a':
		case 'A':
			((SelectionScreen) Ludum28.selectionScreen).setDifficultyLevel(0);
			return true;
		case 's':
		case 'S':
			((SelectionScreen) Ludum28.selectionScreen).setDifficultyLevel(1);
			return true;
		case 'd':
		case 'D':
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
		// TODO Auto-generated method stub
		return false;
	}

	
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
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
