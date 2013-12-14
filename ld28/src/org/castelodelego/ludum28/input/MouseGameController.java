package org.castelodelego.ludum28.input;

import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.screens.GameScreen;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class MouseGameController implements GestureListener {

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {

		((GameScreen) Ludum28.gameScreen).setPlayerTarget(new Vector2(x,Constants.SCREEN_H-y));
		return true;
		
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (count > 1)
		{
			((GameScreen) Ludum28.gameScreen).doPlayerRoar();
			return true;
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

}
