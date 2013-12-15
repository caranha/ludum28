package org.castelodelego.ludum28.input;

import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class KeyboardGameController implements InputProcessor {

	long taptime;
	
	Vector2 unprojectCoordinates(float x, float y)
	{
		Vector3 rawtouch = new Vector3(x, y,0);
		Globals.cam.unproject(rawtouch); 
		
		Vector2 ret = new Vector2(rawtouch.x, rawtouch.y);
		return ret;
	}
	
	
	Vector2 dir = new Vector2(0,0);
	
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	
	public void reset()
	{
		up = false;
		down = false;
		left = false;
		right = false;
		dir.set(0,0);
	}
	
	
	public Vector2 composeDir()
	{
		int ud = (up?1:0) + (down?-1:0);
		int lr = (left?-1:0) + (right?1:0);
		return (new Vector2(lr,ud));
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		boolean caught = false;
		if (keycode == Input.Keys.S || keycode == Input.Keys.DPAD_DOWN)
		{
			down = true;
			caught = true;
		}
		
		if (keycode == Input.Keys.W || keycode == Input.Keys.DPAD_UP)
		{
			up = true;
			caught = true;
		}
		
		if (keycode == Input.Keys.A || keycode == Input.Keys.DPAD_LEFT)
		{
			left = true;
			caught = true;
		}
		
		if (keycode == Input.Keys.D || keycode == Input.Keys.DPAD_RIGHT)
		{
			right = true;
			caught = true;
		}

		if (caught)
		{
			((GameScreen) Ludum28.gameScreen).setPlayerDirection(composeDir());
		}
		
		return caught;
	}

	@Override
	public boolean keyUp(int keycode) {
		boolean caught = false;
		if (keycode == Input.Keys.S || keycode == Input.Keys.DPAD_DOWN)
		{
			down = false;
			caught = true;
		}
		
		if (keycode == Input.Keys.W || keycode == Input.Keys.DPAD_UP)
		{
			up = false;
			caught = true;
		}
		
		if (keycode == Input.Keys.A || keycode == Input.Keys.DPAD_LEFT)
		{
			left = false;
			caught = true;
		}
		
		if (keycode == Input.Keys.D || keycode == Input.Keys.DPAD_RIGHT)
		{
			right = false;
			caught = true;
		}

		if (caught)
		{
			((GameScreen) Ludum28.gameScreen).setPlayerDirection(composeDir());
		}
		
		return caught;

	}

	@Override
	public boolean keyTyped(char character) {
		if (character == ' ')
		{
			((GameScreen) Ludum28.gameScreen).doPlayerRoar();
			return true;
		}		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		((GameScreen) Ludum28.gameScreen).setPlayerTarget(unprojectCoordinates(screenX,screenY));
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		long time = Gdx.input.getCurrentEventTime();
		Gdx.app.log("Tap Time", (time-taptime)+"");
		if ((time - taptime) < 180000000)
			((GameScreen) Ludum28.gameScreen).doPlayerRoar();
		taptime = time;
		
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		((GameScreen) Ludum28.gameScreen).setPlayerTarget(unprojectCoordinates(screenX,screenY));
		return true;
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
