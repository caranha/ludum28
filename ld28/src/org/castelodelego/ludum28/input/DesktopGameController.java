package org.castelodelego.ludum28.input;

import org.castelodelego.ludum28.Constants;
import org.castelodelego.ludum28.Globals;
import org.castelodelego.ludum28.Ludum28;
import org.castelodelego.ludum28.screens.GameScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class DesktopGameController implements InputProcessor {

	boolean android;
	
	long taptime;
	Vector2 origin;
	Vector2 dir;
	
	boolean keys[];	
	public DesktopGameController(boolean andro) {
		android = andro;
		keys = new boolean[4];
		dir = new Vector2(0,0);
	}


	Vector2 unprojectCoordinates(float x, float y)
	{
		Vector3 rawtouch = new Vector3(x, y,0);
		Globals.cam.unproject(rawtouch); 
		
		Vector2 ret = new Vector2(rawtouch.x, rawtouch.y);
		return ret;
	}	
		
	
	@Override
	public boolean keyDown(int keycode) {
		boolean caught = false;
		
		if (keycode == Input.Keys.F3)
		{
			if (Gdx.app.getLogLevel() == Application.LOG_DEBUG)
				Gdx.app.setLogLevel(Application.LOG_NONE);
			else
				Gdx.app.setLogLevel(Application.LOG_DEBUG);
			return true;
		}
		
		if (keycode == Input.Keys.S || keycode == Input.Keys.DPAD_DOWN)
		{
			keys[1] = true;
			caught = true;
		}
		
		if (keycode == Input.Keys.W || keycode == Input.Keys.DPAD_UP)
		{
			keys[0] = true;
			caught = true;
		}
		
		if (keycode == Input.Keys.A || keycode == Input.Keys.DPAD_LEFT)
		{
			keys[3] = true;
			caught = true;
		}
		
		if (keycode == Input.Keys.D || keycode == Input.Keys.DPAD_RIGHT)
		{
			keys[2] = true;
			caught = true;
		}
		

		if (caught)
		{
			Ludum28.gameScreen.getPlayer().setKeyArray(keys);
		}
		
		return caught;
	}

	@Override
	public boolean keyUp(int keycode) {
		boolean caught = false;
		if (keycode == Input.Keys.S || keycode == Input.Keys.DPAD_DOWN)
		{
			keys[1] = false;
			caught = true;
		}
		
		if (keycode == Input.Keys.W || keycode == Input.Keys.DPAD_UP)
		{
			keys[0] = false;
			caught = true;
		}
		
		if (keycode == Input.Keys.A || keycode == Input.Keys.DPAD_LEFT)
		{
			keys[3] = false;
			caught = true;
		}
		
		if (keycode == Input.Keys.D || keycode == Input.Keys.DPAD_RIGHT)
		{
			keys[2] = false;
			caught = true;
		}

		if (caught)
		{
			Ludum28.gameScreen.getPlayer().setKeyArray(keys);
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
		if (android)
			origin = unprojectCoordinates(screenX,screenY);
		else
			Ludum28.gameScreen.getPlayer().setTarget(unprojectCoordinates(screenX,screenY));
		return true;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		long time = Gdx.input.getCurrentEventTime();
		Gdx.app.log("Tap Time", (time-taptime)+"");
		if ((time - taptime) < 180000000)
			((GameScreen) Ludum28.gameScreen).doPlayerRoar();
		taptime = time;
		
		Ludum28.gameScreen.getPlayer().setTarget(null);
		return true;
	}
	
	

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (android)
		{
			Ludum28.gameScreen.getPlayer().setDirection(unprojectCoordinates(screenX,screenY).sub(origin).scl(10));
			origin = unprojectCoordinates(screenX,screenY);
		}			
		else	
			Ludum28.gameScreen.getPlayer().setTarget(unprojectCoordinates(screenX,screenY));
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
